package com.jossv.framework.dao.type.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jossv.framework.dao.model.Column;
import com.jossv.framework.dao.type.BaseTypeHandler;

public class BooleanTypeHandler extends BaseTypeHandler {

	
	protected Object getNotNullResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getBoolean(columnName);
	}

	
	protected Object convertNotNullValueFromString(String value, Column column) {
		if (value.equals("1") || value.equalsIgnoreCase("true") || value.equalsIgnoreCase("yes")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	
	protected Object getNotNullJdbcParameter(Object value) {
		Boolean v = (Boolean) value;
		return v ? "1" : "0";
	}

	
	protected String getNotNullDisplay(Object value, Column column) {
		return (Boolean)value?"是":"否";
	}

}
