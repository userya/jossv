package com.jossv.framework.dao.sql;

import java.util.Map;

import com.jossv.framework.dao.model.Table;

public class SelectPart {

	private String alias;
	
	private DbAtom part; // table | select
	
	
	public SelectPart() {
		
	}
	
	public SelectPart(DbAtom part) {
		super();
		this.part = part;
	}

	public SelectPart(String alias, DbAtom part) {
		super();
		this.alias = alias;
		this.part = part;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public DbAtom getPart() {
		return part;
	}

	public void setPart(DbAtom part) {
		this.part = part;
	}

	public void getSqlPart(StringBuilder builder, Map<String, Object> params) {
		DbAtom part = this.getPart();
		String alias = this.getAlias();
		if (part instanceof Table) {
			Table t = (Table) part;
			builder.append(t.getTableName()).append(" ");
			if(alias != null && alias.trim().length() >0) {
				builder.append(alias);
			}
		} else if (part instanceof Select) {
			Select t = (Select) part;
			builder.append("(");
			t.getSql(builder, params);
			builder.append(") ");
			builder.append(alias);
		} 
	}
	
}
