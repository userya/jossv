package com.jossv.framework.dao.annotaion.factory.impl;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import com.jossv.framework.dao.TableFactory;
import com.jossv.framework.dao.annotaion.Column;
import com.jossv.framework.dao.annotaion.RefUtils;
import com.jossv.framework.dao.annotaion.Table;
import com.jossv.framework.dao.annotaion.Transient;
import com.jossv.framework.dao.type.ColumnType;
import com.jossv.framework.dao.type.ColumnTypeUtils;

public class ClassTableFactory implements TableFactory {

	private String scanPackage;

	protected Map<String, com.jossv.framework.dao.model.Table> tableMap = new HashMap<String, com.jossv.framework.dao.model.Table>();

	protected Object lock = new Object();

	public boolean contain(String clazzName) {
		return tableMap.containsKey(clazzName);
	}

	public com.jossv.framework.dao.model.Table getTable(String id) {
		return tableMap.get(id);
	}
	
	public void init() {
		synchronized (lock) {
			try {
				Class<?>[] tables = RefUtils.getClasses(scanPackage, Table.class);
				if (tables != null) {
					for (int i = 0; i < tables.length; i++) {
						com.jossv.framework.dao.model.Table table = createTable(tables[i]);
						tableMap.put(table.getId(), table);
					}
				}
			} catch (ClassNotFoundException e) {
				throw new RuntimeException(e);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}

	private com.jossv.framework.dao.model.Table createTable(Class<?> clazz) {
		final com.jossv.framework.dao.model.Table tbl = new com.jossv.framework.dao.model.Table();
		Table table = clazz.getAnnotation(Table.class);
		String tableName = table.name();
		String label = table.label();
		String pkColumn = table.pkColumnName();
		tbl.setId(clazz.getName());
		tbl.setTableName(tableName);
		tbl.setLabel(label);
		tbl.setPkColumn(pkColumn);

		ReflectionUtils.doWithLocalFields(clazz, new FieldCallback() {
			public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
				com.jossv.framework.dao.model.Column column = new com.jossv.framework.dao.model.Column();
				Transient tr = field.getAnnotation(Transient.class);
				if (tr != null) {
					return;
				}
				String name = field.getName();
				String type = "STRING";
				boolean unique = false;
				boolean nullable = true;
				int length = 255;
				int precision = 0;
				int scale = 0;
				String label = "";
				String columnName = "";
				String codeNumber = "";
				String dateFormat = "";
				Column col = field.getAnnotation(Column.class);
				if (col != null) {
					columnName = col.name();
					type = col.type().name();
					unique = col.unique();
					nullable = col.nullable();
					length = col.length();
					precision = col.precision();
					scale = col.scale();
					label = col.label();
					codeNumber = col.codeNumber();
					dateFormat = col.dateFormat();
				}else {
					ColumnType t = ColumnTypeUtils.getJotType(field.getType());
					if(t == null) {
						throw new RuntimeException("can not found type:" + field.getType());
					}
					type = t.name();
				}
				if(label.equals("")) {
					label = name;
				}
				if(columnName.equals("")) {
					columnName = name ;
				}
				if(type.equals(ColumnType.BIG_DECIMAL.name())) {
					if(precision == 0 ) {
						precision = 10;
					}
					if(scale == 0) {
						scale = 3;
					}
				}
				
				column.setName(name);
				column.setType(type);
				column.setUnique(unique);
				column.setNullable(nullable);
				column.setLength(length);
				column.setPrecision(precision);
				column.setScale(scale);
				column.setLabel(label);
				column.setColumnName(columnName);
				column.setCodeNumber(codeNumber);
				column.setDateFormat(dateFormat);
				tbl.getColumns().add(column);
			}
		});
		return tbl;
	}

	
	public String getScanPackage() {
		return scanPackage;
	}

	public void setScanPackage(String scanPackage) {
		this.scanPackage = scanPackage;
	}

	public Map<String, com.jossv.framework.dao.model.Table> getTableMap() {
		return tableMap;
	}
	
}
