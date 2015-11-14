package com.jossv.framework.dao.type.impl;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jossv.framework.dao.model.Column;
import com.jossv.framework.dao.type.BaseTypeHandler;


public class BigDecimalTypeHandler extends BaseTypeHandler {

	
	protected Object getNotNullResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getBigDecimal(columnName);
	}

	
	protected Object convertNotNullValueFromString(String value, Column column) {
		return new BigDecimal(value);
	}

	
	protected Object getNotNullJdbcParameter(Object value) {
		return value;
	}

	
	protected String getNotNullDisplay(Object value, Column column) {

		return value.toString();
	}

}
