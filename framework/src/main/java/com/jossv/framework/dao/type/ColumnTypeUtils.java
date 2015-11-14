package com.jossv.framework.dao.type;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

public abstract class ColumnTypeUtils {

	private static Map<ColumnType, String> hibernateMapping = new HashMap<ColumnType, String>();

	private static Map<ColumnType, Integer> jdbcTypeMapping = new HashMap<ColumnType, Integer>();

	private static Map<Integer, ColumnType> jotTypeMapping = new HashMap<Integer, ColumnType>();
	
	private static Map<Class<?>, ColumnType> jotClassTypeMapping = new HashMap<Class<?>, ColumnType>();
	
	static {
		jotClassTypeMapping.put(String.class, ColumnType.STRING);
		jotClassTypeMapping.put(BigDecimal.class, ColumnType.BIG_DECIMAL);
		jotClassTypeMapping.put(Integer.class, ColumnType.INTEGER);
		jotClassTypeMapping.put(Long.class, ColumnType.LONG);
		jotClassTypeMapping.put(Boolean.class, ColumnType.BOOLEAN);
		jotClassTypeMapping.put(Timestamp.class, ColumnType.TIMESTAMP);
		jotClassTypeMapping.put(Byte[].class, ColumnType.BLOB);
	}

	static {
		for (ColumnType type : ColumnType.values()) {
			if(type.name().equalsIgnoreCase("CODE")) {
				hibernateMapping.put(type, ColumnType.STRING.name().toLowerCase());
			}else if(type.name().equalsIgnoreCase("boolean")){
				hibernateMapping.put(type, "numeric_boolean");
			}else if(type.name().equalsIgnoreCase("long_text")){
				hibernateMapping.put(type, "blob");
			}else {
				hibernateMapping.put(type, type.name().toLowerCase());
			}
			
		}
		// INTEGER, LONG, BOOLEAN, STRING,
		// TIMESTAMP, BIG_DECIMAL,
		// CLOB, BLOB;
		
		jdbcTypeMapping.put(ColumnType.CODE, Types.VARCHAR);
		
		jdbcTypeMapping.put(ColumnType.INTEGER, Types.INTEGER);
		jdbcTypeMapping.put(ColumnType.LONG, Types.BIGINT);
		jdbcTypeMapping.put(ColumnType.BOOLEAN, Types.BIT);
		jdbcTypeMapping.put(ColumnType.STRING, Types.VARCHAR);
		jdbcTypeMapping.put(ColumnType.TIMESTAMP, Types.TIMESTAMP);
		jdbcTypeMapping.put(ColumnType.BIG_DECIMAL, Types.NUMERIC);
		jdbcTypeMapping.put(ColumnType.CLOB, Types.CLOB);
		jdbcTypeMapping.put(ColumnType.BLOB, Types.BLOB);
		
		jdbcTypeMapping.put(ColumnType.LONG_TEXT, Types.BLOB);
		for (ColumnType key : jdbcTypeMapping.keySet()) {
			if(key.equals(ColumnType.CODE)) {
				continue;
			}
			jotTypeMapping.put(jdbcTypeMapping.get(key), key);
		}
		jotTypeMapping.put(Types.LONGVARCHAR, ColumnType.STRING);
	}

	public static String getHibernateType(ColumnType type) {
		return hibernateMapping.get(type);
	}

	public static Integer getJdbcType(ColumnType type) {
		return jdbcTypeMapping.get(type);
	}

	public static ColumnType getJotType(int jdbcType) {
		return jotTypeMapping.get(jdbcType);
	}

	public static ColumnType getJotType(Class<?> clazz) {
		return jotClassTypeMapping.get(clazz);
	}
	
}
