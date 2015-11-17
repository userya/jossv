package com.jossv.framework.dao.model;

import java.util.ArrayList;
import java.util.List;

import com.jossv.framework.dao.sql.DbAtom;

public class Table extends DbAtom implements DefineAble {

	private String id;

	private String tableName;

	private String label;

	private String pkColumn = "id";

	private Boolean view ;
	
	private Boolean virtual;
	
	private List<Column> columns = new ArrayList<Column>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPkColumn() {
		return pkColumn;
	}

	public void setPkColumn(String pkColumn) {
		this.pkColumn = pkColumn;
	}

	public Boolean getView() {
		return view;
	}

	public void setView(Boolean view) {
		this.view = view;
	}

	public Boolean getVirtual() {
		return virtual;
	}

	public void setVirtual(Boolean virtual) {
		this.virtual = virtual;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
}
