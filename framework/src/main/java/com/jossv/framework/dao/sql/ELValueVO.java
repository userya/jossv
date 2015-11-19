package com.jossv.framework.dao.sql;

import org.springframework.jdbc.core.SqlParameterValue;

public class ELValueVO {

	private String el;
	private Object value;
	
	private SqlParameterValue sqlValue;
	
	public ELValueVO(String el, Object value) {
		super();
		this.el = el;
		this.value = value;
	}
	public String getEl() {
		return el;
	}
	public void setEl(String el) {
		this.el = el;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public SqlParameterValue getSqlValue() {
		return sqlValue;
	}
	public void setSqlValue(SqlParameterValue sqlValue) {
		this.sqlValue = sqlValue;
	}
	
}
