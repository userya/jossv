package com.jossv.framework.dao.model;

import java.util.ArrayList;
import java.util.List;

import com.jossv.framework.dao.sql.DbAtom;

public class Table extends DbAtom implements DefineAble {

	private String id;

	private String tableName;

	private String label;

	private String pkColumn = "id";

	private boolean view;
	
	private boolean virtual;
	
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

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public String getPkColumn() {
		return pkColumn;
	}

	public void setPkColumn(String pkColumn) {
		this.pkColumn = pkColumn;
	}

	public boolean isView() {
		return view;
	}

	public void setView(boolean view) {
		this.view = view;
	}

	public boolean isVirtual() {
		return virtual;
	}

	public void setVirtual(boolean virtual) {
		this.virtual = virtual;
	}

	

}
