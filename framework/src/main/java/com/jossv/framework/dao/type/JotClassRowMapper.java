package com.jossv.framework.dao.type;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.RowMapper;

import com.jossv.framework.dao.BaseObject;
import com.jossv.framework.dao.EntityFactory;
import com.jossv.framework.dao.annotation.factory.impl.ClassEntityFactory;
import com.jossv.framework.dao.model.Entity;
import com.jossv.framework.dao.model.Table;
import com.jossv.framework.dao.sql.VirtualColumn;

public class JotClassRowMapper<T> implements RowMapper<T> {

	private Class<T> mapperClass;

	private EntityFactory entityFactory;

	private List<VirtualColumn> columns;

	public JotClassRowMapper(EntityFactory entityFactory, Class<T> mapperClass, List<VirtualColumn> columns) {
		super();
		this.mapperClass = mapperClass;
		this.columns = columns;
		this.entityFactory = (ClassEntityFactory) entityFactory;
	}

	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		T mappedObject = BeanUtils.instantiate(this.mapperClass);
		try {
			for (int i = 0; i < columns.size(); i++) {
				VirtualColumn vc = columns.get(i);
				String columnName = vc.getAliasName();
				String type = vc.getPhysicalColumn().getType();
				TypeHandler handler = TypeHandlerUtils.getTypeHandler(ColumnType.valueOf(type));
				String el = vc.getExp();
				Object value = handler.getValue(rs, columnName);
				if (this.mapperClass.equals(BaseObject.class)) {
					((BaseObject) mappedObject).setValue(el, value);
				} else {
					setValue(mappedObject, el, value);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return mappedObject;
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

	protected void setValue(T instance, String el, Object value)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Object toPut = instance;
		int fromIndex = 0;
		int index = el.indexOf('.');
		String name = el;
		while (index != -1) {
			String p = el.substring(fromIndex, index);
			String propertyName = getPropertyName(toPut.getClass(), p);
			if(propertyName == null) {
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
		PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(toPut.getClass(), name);
		pd.getWriteMethod().invoke(toPut, value);
	}

}
