package com.jossv.framework.dao.annotaion.factory.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ClassUtils;
import org.springframework.util.ConcurrentReferenceHashMap;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.jossv.framework.dao.EntityFactory;
import com.jossv.framework.dao.TableFactory;
import com.jossv.framework.dao.annotaion.Entity;
import com.jossv.framework.dao.annotaion.Main;
import com.jossv.framework.dao.annotaion.RefUtils;
import com.jossv.framework.dao.annotaion.Relationship;
import com.jossv.framework.dao.annotaion.RelationshipType;
import com.jossv.framework.dao.model.DefineAble;
import com.jossv.framework.dao.sql.Condition;

public class ClassEntityFactory implements EntityFactory {

	protected TableFactory tableFactory;

	private String scanPackage;

	protected Map<String, com.jossv.framework.dao.model.Entity> entityMap = new ConcurrentReferenceHashMap<String, com.jossv.framework.dao.model.Entity>();

	protected Map<String, Map<String, String>> propertyMap = new ConcurrentReferenceHashMap<String, Map<String, String>>();

	public com.jossv.framework.dao.model.Entity getEntity(String clazz) {
		return entityMap.get(clazz);
	}

	public boolean contain(String clazz) {
		return entityMap.containsKey(clazz);
	}

	protected Object lock = new Object();

	public void init() throws ClassNotFoundException {
		synchronized (lock) {
			try {
				Class<?>[] tables = RefUtils.getClasses(scanPackage, Entity.class);
				if (tables != null) {
					for (int i = 0; i < tables.length; i++) {
						com.jossv.framework.dao.model.Entity entity = createEntity(tables[i]);
						entity.setEntityFactory(this);
						entityMap.put(entity.getId(), entity);
						propertyMap.put(entity.getId(), new HashMap<String, String>());
					}
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		// System.out.println(tableMap);
		for (final String className : entityMap.keySet()) {
			final Class<?> clazz = Class.forName(className);
			final com.jossv.framework.dao.model.Entity e = entityMap.get(className);
			ReflectionUtils.doWithLocalFields(clazz, new FieldCallback() {
				public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
					if (field.getAnnotation(Main.class) != null) {
						Main main = field.getAnnotation(Main.class);
						propertyMap.get(clazz.getName()).put(main.alias(), field.getName());
						Class<?> fieldClass = field.getType();
						DefineAble d = null;
						if (tableFactory.contain(fieldClass.getName())) {
							com.jossv.framework.dao.model.Table table = tableFactory.getTable(fieldClass.getName());
							d = table;
						} else if (ClassEntityFactory.this.contain(fieldClass.getName())) {
							d = ClassEntityFactory.this.entityMap.get(fieldClass.getName());
						} else {
							throw new RuntimeException("can not found " + main.alias());
						}
						String alias = main.alias();
						String condition = main.condition();
						e.setMainAlias(alias);
						e.setMain(d);
						e.setMainCondition(
								(condition == null || condition.trim().equals("")) ? null : new Condition(condition));
					} else if (field.getAnnotation(Relationship.class) != null) {
						Relationship ship = field.getAnnotation(Relationship.class);
						propertyMap.get(clazz.getName()).put(ship.alias(), field.getName());
						Class<?> fieldClass = field.getType();
						DefineAble d = null;
						if (ClassUtils.isAssignable(Collection.class, fieldClass)) {
							fieldClass.getGenericSuperclass();
							ParameterizedType type = (ParameterizedType) field.getGenericType();
							Type[] ts = type.getActualTypeArguments();
							fieldClass = (Class<?>) ts[0];
						}
						if (tableFactory.contain(fieldClass.getName())) {
							com.jossv.framework.dao.model.Table table = tableFactory.getTable(fieldClass.getName());
							d = table;
						} else if (ClassEntityFactory.this.contain(fieldClass.getName())) {
							d = ClassEntityFactory.this.entityMap.get(fieldClass.getName());
						} else {
							throw new RuntimeException("can not found " + ship.alias());
						}
						String alias = ship.alias();
						String condition = ship.condition();
						RelationshipType type = ship.type();
						com.jossv.framework.dao.model.Relationship sp = new com.jossv.framework.dao.model.Relationship();
						sp.setAlias(alias);
						sp.setRelObject(d);
						sp.setCondition(
								(condition == null || condition.trim().equals("")) ? null : new Condition(condition));
						sp.setType(type);
						if (type.equals(RelationshipType.ref)) {
							e.getRels().put(alias, sp);
						} else if (type.equals(RelationshipType.child)) {
							e.getChilren().put(alias, sp);
						} else {
							// do nothing
						}
					}
				}
			});
		}

	}

	private com.jossv.framework.dao.model.Entity createEntity(Class<?> clazz) {
		com.jossv.framework.dao.model.Entity entity = new com.jossv.framework.dao.model.Entity();
//		Entity ea = clazz.getAnnotation(Entity.class);
		String id = clazz.getName();
		entity.setId(id);
		return entity;
	}

	public TableFactory getTableFactory() {
		return tableFactory;
	}

	public void setTableFactory(TableFactory tableFactory) {
		this.tableFactory = tableFactory;
	}

	public String getScanPackage() {
		return scanPackage;
	}

	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}

	public Map<String, com.jossv.framework.dao.model.Entity> getEntityMap() {
		return entityMap;
	}



	public Map<String, Map<String, String>> getPropertyMap() {
		return propertyMap;
	}

}
