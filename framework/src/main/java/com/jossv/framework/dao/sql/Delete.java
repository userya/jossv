package com.jossv.framework.dao.sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jossv.framework.dao.model.Table;

public class Delete implements SqlPart {

	private Table table;

	private String alias;

	private Where where = new Where();

	public void getSql(StringBuilder append, Map<String, Object> params) {
		boolean hasAlias = (alias != null && alias.trim().length() > 0);
		append.append("delete ");
		if (hasAlias) {
			append.append(alias).append(" ");
		}
		append.append("from ");
		append.append(table.getTableName());
		if (hasAlias) {
			append.append(alias).append(" ");
		}

		List<VirtualColumn> list = getVirtualColums();
		for (int i = 0; i < list.size(); i++) {
			VirtualColumn vc = list.get(i);
			params.put(vc.getExp(), vc.getColumnName());
		}

		where.getSqlPart(append);
	}

	public List<VirtualColumn> getVirtualColums() {
		List<VirtualColumn> list = new ArrayList<VirtualColumn>();
		if (table != null) {
			for (int i = 0; i < table.getColumn().size(); i++) {
				com.jossv.model.table.Column column = table.getColumn().get(i);
				VirtualColumn vc = new VirtualColumn();
				vc.setAlias(alias);
				vc.setExp(column.getName());
				vc.setName(column.getColumnName() == null ? column.getName() : column.getColumnName());
				vc.setPhysicalColumn(column);
				vc.setSource(table);
				list.add(vc);
			}
		}
		return list;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Where getWhere() {
		return where;
	}

	public void setWhere(Where where) {
		this.where = where;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public Map<String, ELValueVO> getParameters() {
		return where.getCondition().getParameters();
	}

}
