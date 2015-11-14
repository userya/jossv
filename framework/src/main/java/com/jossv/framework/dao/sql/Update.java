package com.jossv.framework.dao.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.jossv.framework.dao.model.Column;
import com.jossv.framework.dao.model.Table;

/*
 * update t_test a set a.id = :a.id, a.name = :a.name where a.id = :a.id
 */
public class Update implements SqlPart {

	private Table table;
	
	private String alias;
	
	private Map<String, ELValueVO> parameters = new HashMap<String, ELValueVO>();
	
	private Set<String> ignoreColumnEl = new HashSet<String>();

	public Update setParameter(String parameterName, String typeEl, Object value) {
		parameters.put(parameterName, new ELValueVO(typeEl, value));
		return this;
	}

	public Update setParameter(String parameterName, Object value) {
		return setParameter(parameterName, parameterName, value);
	}
	
	private Where where = new Where();
	
	
	public void getSql(StringBuilder builder, Map<String, Object> params) {
		boolean hasAlias = alias != null && alias.trim().length() > 0 ;
		builder.append("update ").append(table.getTableName()).append(" ");
		if(hasAlias) {
			builder.append(alias).append(" ");
		}
		builder.append("set ");
		List<VirtualColumn> list = getVirtualColums();
		for (int i = 0; i < list.size(); i++) {
			VirtualColumn vc = list.get(i);
			params.put(vc.getExp(), vc.getColumnName());
			if(!ignoreColumnEl.contains(vc.getExp())) {
				builder.append(vc.getColumnName());
				builder.append("=:");
				builder.append(vc.getExp());
				builder.append(",");
			}
		}
		builder.delete(builder.length()-1, builder.length());
		where.getSqlPart(builder);
	}

	public List<VirtualColumn> getVirtualColums() {
		List<VirtualColumn> list = new ArrayList<VirtualColumn>();
		if (table != null) {
			for (int i = 0; i < table.getColumns().size(); i++) {
				Column column = table.getColumns().get(i);
				VirtualColumn vc = new VirtualColumn();
				vc.setAlias(alias);
				vc.setExp(column.getName());
				vc.setName(column.getColumnName());
				vc.setPhysicalColumn(column);
				vc.setSource(table);
				list.add(vc);
			}
		}
		return list;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
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


	public Map<String, ELValueVO> getParameters() {
		Map<String, ELValueVO> ps = new HashMap<String, ELValueVO>();
		ps.putAll(this.parameters);
		if(where.getCondition() != null) {
			ps.putAll(where.getCondition().getParameters());
		}
		return ps;
	}

	public Set<String> getIgnoreColumnEl() {
		return ignoreColumnEl;
	}

	public void setIgnoreColumnEl(Set<String> ignoreColumnEl) {
		this.ignoreColumnEl = ignoreColumnEl;
	}

	


}
