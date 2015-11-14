package com.jossv.framework.dao.type.impl;

import java.io.StringWriter;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.jossv.framework.dao.model.Column;
import com.jossv.framework.dao.type.BaseTypeHandler;

/**
 * 以大字段存储的字符串
 * @author Yangjk
 *
 */
public class LongTextTypeHandler extends BaseTypeHandler {

	@Override
	protected Object getNotNullResult(ResultSet rs, String columnName) throws SQLException {
		Blob blob = rs.getBlob(columnName);
		if(blob == null) {
			return null;
		}
		StringWriter out = new StringWriter();
		/*
		try {
			
			IOUtils.copy(blob.getBinaryStream(), out, "utf-8");
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		*/
		//TODO
		return out.toString();
	}

	@Override
	protected Object convertNotNullValueFromString(String value, Column column) {
		
		return value;
	}

	@Override
	protected Object getNotNullJdbcParameter(Object value) {
		
		return value;
	}

	@Override
	protected String getNotNullDisplay(Object value, Column column) {
		
		return (String)value;
	}

}
