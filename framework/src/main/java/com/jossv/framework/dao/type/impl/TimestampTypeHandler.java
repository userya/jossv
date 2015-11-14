package com.jossv.framework.dao.type.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.util.StringUtils;

import com.jossv.framework.dao.model.Column;
import com.jossv.framework.dao.type.BaseTypeHandler;

public class TimestampTypeHandler extends BaseTypeHandler {

	
	protected Object getNotNullResult(ResultSet rs, String columnName) throws SQLException {
		return rs.getTimestamp(columnName);
	}

	
	protected Object convertNotNullValueFromString(String value, Column column) {
		Timestamp stamp = null;
		String f = column.getDateFormat();
		if (!StringUtils.isEmpty(f)) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(f);
				long t = sdf.parse(value).getTime();
				stamp = new Timestamp(t);
			} catch (ParseException e) {
				stamp = new Timestamp(Long.valueOf(value));
			}
		}
		return stamp;
	}

	
	protected Object getNotNullJdbcParameter(Object value) {
		return value;
	}

	
	protected String getNotNullDisplay(Object value, Column column) {
		if(!StringUtils.isEmpty(column.getDateFormat())){
			SimpleDateFormat sdf = new SimpleDateFormat(column.getDateFormat());
			return sdf.format((Timestamp)value);
		}
		return value.toString();
	}

}
