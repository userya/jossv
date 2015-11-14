package com.jossv.framework.dao.type.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.jossv.framework.dao.BaseObject;
import com.jossv.framework.dao.model.Column;
import com.jossv.framework.dao.type.BaseTypeHandler;

public class CodeTypeHandler extends BaseTypeHandler {

	
	protected Object getNotNullResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getString(columnName);
	}

	
	protected Object convertNotNullValueFromString(String value, Column column) {
		
		return value;
	}

	
	protected Object getNotNullJdbcParameter(Object value) {
		if(value instanceof BaseObject) {
//			BaseObject b = (BaseObject)value;
//			return b.get("dictinfoguid");
		}
		return value;
	}

	
	protected String getNotNullDisplay(Object value, Column column) {
		
		return value.toString();
	}

}
