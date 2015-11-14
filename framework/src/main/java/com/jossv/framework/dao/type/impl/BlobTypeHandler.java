package com.jossv.framework.dao.type.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jossv.framework.dao.model.Column;
import com.jossv.framework.dao.type.BaseTypeHandler;

public class BlobTypeHandler extends BaseTypeHandler {
	
	protected Object getNotNullResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getBlob(columnName);
	}

	
	protected Object convertNotNullValueFromString(String value, Column column) {
		throw new RuntimeException("can not covert blob from String");
	}

	
	protected Object getNotNullJdbcParameter(Object value) {
		return value;
	}

	
	protected String getNotNullDisplay(Object value, Column column) {
		return value.toString();
	}


}
