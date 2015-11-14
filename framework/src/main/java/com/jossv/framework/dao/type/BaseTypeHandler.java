package com.jossv.framework.dao.type;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jossv.framework.dao.model.Column;

public abstract class BaseTypeHandler implements TypeHandler {

	
	public Object getValue(ResultSet rs, String columnName) {
		try {
//			if (rs.wasNull()) {
//				return null;
//			} else {
				return getNotNullResult(rs, columnName);
//			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	public Object getJdbcParameterByString(String value, Column column) {
		return getJdbcParameter(convertValueFromString(value,column));
	}

	abstract protected Object getNotNullResult(ResultSet rs, String columnName) throws SQLException;

	
	public Object convertValueFromString(String value, Column column) {
		if (value == null || value.trim().length() == 0) {
			return null;
		}
		return convertNotNullValueFromString(value, column);
	}

	abstract protected Object convertNotNullValueFromString(String value, Column column);

	
	public Object getJdbcParameter(Object value) {
		if (value == null) {
			return null;
		}
		return getNotNullJdbcParameter(value);
	}

	abstract protected Object getNotNullJdbcParameter(Object value);

	
	
	public String getDisplay(Object value, Column column) {
		if(value == null) {
			return "";
		}
		return getNotNullDisplay(value, column);
	}
	
	abstract protected String getNotNullDisplay(Object value, Column column);
	
	
	
	
	
}
