package com.jossv.framework.dao.sql;

public class ELValueVO {

	private String el;
	private Object value;
	
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
	
}
