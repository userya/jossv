package com.jossv.framework.dao.type;

import java.sql.ResultSet;

import com.jossv.framework.dao.model.Column;

public interface TypeHandler {

	Object getValue(ResultSet rs, String columnName);

	Object getJdbcParameter(Object value);
	
	Object getJdbcParameterByString(String value, Column column);

	Object convertValueFromString(String value, Column column);
	
	String getDisplay(Object value, Column column);
	
}
