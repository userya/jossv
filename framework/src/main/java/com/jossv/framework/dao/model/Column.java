package com.jossv.framework.dao.model;

import com.jossv.framework.dao.type.ColumnType;

public class Column {
	
	private String name;
	@com.jossv.framework.dao.annotation.Column(name="_type")
	private String type = "STRING";
	@com.jossv.framework.dao.annotation.Column(name="_uniq", type=ColumnType.BOOLEAN)
	private Boolean unique = false;
	@com.jossv.framework.dao.annotation.Column(name="_nullable", type=ColumnType.BOOLEAN)
	private Boolean nullable = true;
	@com.jossv.framework.dao.annotation.Column(name="_len", type=ColumnType.INTEGER)
	private Integer length = 255;
	
	@com.jossv.framework.dao.annotation.Column(name="_precision", type=ColumnType.INTEGER)
	private Integer precision = 0;
	@com.jossv.framework.dao.annotation.Column(name="_scale", type=ColumnType.INTEGER)
	private Integer scale = 0;
	
	@com.jossv.framework.dao.annotation.Column(name="_label")
	private String label;
	
	private String columnName;
	
	private String codeNumber;
	
	private String dateFormat;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isUnique() {
		return unique;
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getPrecision() {
		return precision;
	}

	public void setPrecision(int precision) {
		this.precision = precision;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getCodeNumber() {
		return codeNumber;
	}

	public void setCodeNumber(String codeNumber) {
		this.codeNumber = codeNumber;
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}
	
	
	
	
	
}
