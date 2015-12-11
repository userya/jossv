package com.jossv.framework.dao.sql;

import com.jossv.model.table.Column;

public class VirtualColumn {

	private DbAtom source;

	private com.jossv.model.table.Column physicalColumn;

	private String alias;

	private String exp;

	private String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DbAtom getSource() {
		return source;
	}

	public void setSource(DbAtom source) {
		this.source = source;
	}

	public Column getPhysicalColumn() {
		return physicalColumn;
	}

	public void setPhysicalColumn(Column physicalColumn) {
		this.physicalColumn = physicalColumn;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	// 后置用
	public String getColumnName() {
		if(alias == null || alias.trim().length() == 0) {
			return name;
		}
		return alias + "." + name;
	}

	// 前置用
	public String getAliasName() {
		if(alias == null || alias.trim().length() == 0) {
			return name;
		}
		return alias + "_" + name;
	}

	public String getQueryName() {
		if(alias == null || alias.trim().length() == 0) {
			return getColumnName();
		}
		return getColumnName() + " as " + getAliasName();
	}

}
