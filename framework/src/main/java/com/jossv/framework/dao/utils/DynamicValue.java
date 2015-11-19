package com.jossv.framework.dao.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.BeanUtils;

import com.jossv.framework.dao.BaseObject;
import com.jossv.framework.dao.EntityFactory;
import com.jossv.framework.dao.annotation.factory.impl.ClassEntityFactory;
import com.jossv.framework.dao.model.Entity;
import com.jossv.framework.dao.model.Table;

public class DynamicValue {

	private Class<?> mapperClass;

	
	private EntityFactory entityFactory;
	
	
	public DynamicValue(Class<?> mapperClass, EntityFactory entityFactory) {
		super();
		this.mapperClass = mapperClass;
		this.entityFactory = entityFactory;
	}


	protected String getPropertyName(Class<?> instance, String property) {
		Entity entity = entityFactory.getEntity(instance.getName());
		if (entity != null) {
			return ((ClassEntityFactory) entityFactory).getPropertyMap().get(instance.getName()).get(property);
		} else {
			Table table = entityFactory.getTableFactory().getTable(instance.getName());
			if (table != null) {
				return property;
			}
		}
		return null;
	}
	
	public Object getValue(Object vo, String el) {
		if (vo instanceof BaseObject) {
			return ((BaseObject) vo).getValue(el);
		}
		Object r = null;
		Object toPut = vo;
		int fromIndex = 0;
		int index = el.indexOf('.');
		String name = el;
		try {
			while (index != -1) {
				String p = el.substring(fromIndex, index);
				String propertyName = getPropertyName(toPut.getClass(), p);
				if (propertyName == null) {
					throw new RuntimeException("can not found property named :" + p);
				}
				PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(toPut.getClass(), propertyName);
				Object o = pd.getReadMethod().invoke(toPut);
				fromIndex = index + 1;
				toPut = o;
				name = el.substring(fromIndex);
				index = el.indexOf('.', index + 1);
			}
			PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(toPut.getClass(), name);
			r = pd.getReadMethod().invoke(toPut);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return r;
	}

	public void setValue(Object instance, String el, Object value) {
		if (this.mapperClass.equals(BaseObject.class)) {
			((BaseObject) instance).setValue(el, value);
		} else {
			Object toPut = instance;
			int fromIndex = 0;
			int index = el.indexOf('.');
			String name = el;
			try {
				while (index != -1) {
					String p = el.substring(fromIndex, index);
					String propertyName = getPropertyName(toPut.getClass(), p);
					if (propertyName == null) {
						throw new RuntimeException("can not found property named :" + p);
					}
					PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(toPut.getClass(), propertyName);
					Object o = pd.getReadMethod().invoke(toPut);
					if (o == null) {
						Class<?> c = pd.getPropertyType();
						o = BeanUtils.instantiate(c);
						pd.getWriteMethod().invoke(toPut, o);
					}
					fromIndex = index + 1;
					toPut = o;
					name = el.substring(fromIndex);
					index = el.indexOf('.', index + 1);
				}
				PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(toPut.getClass(), getPropertyName(toPut.getClass(), name));
				pd.getWriteMethod().invoke(toPut, value);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}
	}


	public Class<?> getMapperClass() {
		return mapperClass;
	}


	public void setMapperClass(Class<?> mapperClass) {
		this.mapperClass = mapperClass;
	}


	public EntityFactory getEntityFactory() {
		return entityFactory;
	}


	public void setEntityFactory(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

}
