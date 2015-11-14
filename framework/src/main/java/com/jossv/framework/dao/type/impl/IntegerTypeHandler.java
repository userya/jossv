package com.jossv.framework.dao.type.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jossv.framework.dao.model.Column;
import com.jossv.framework.dao.type.BaseTypeHandler;

public class IntegerTypeHandler extends BaseTypeHandler {

	
	protected Object getNotNullResult(ResultSet rs, String columnName) throws SQLException {
		return new Integer(rs.getInt(columnName));
	}

	
	protected Object convertNotNullValueFromString(String value, Column column) {
		return Integer.valueOf(value);
	}

	
	protected Object getNotNullJdbcParameter(Object value) {
		return value;
	}

	
	protected String getNotNullDisplay(Object value, Column column) {
		// TODO Auto-generated method stub
		return value.toString();
	}

}
