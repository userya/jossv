package com.jossv.framework.dao.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jossv.framework.dao.model.Table;

/**
 * insert into table(id, name) values(:id, :name);
 * 
 * @author yangjiankang
 *
 */
public class Insert implements SqlPart {

	private Table table;

	private String alias;


	private Map<String, ELValueVO> parameters = new HashMap<String, ELValueVO>();

	public Insert setParameter(String parameterName, String typeEl, Object value) {
		parameters.put(parameterName, new ELValueVO(typeEl, value));
		return this;
	}

	public Insert setParameter(String parameterName, Object value) {
		return setParameter(parameterName, parameterName, value);
	}

	public void getSql(StringBuilder append, Map<String, Object> params) {
		List<VirtualColumn> list = getVirtualColums();
		append.append("insert into ").append(table.getTableName()).append("(");
		for (int i = 0; i < list.size(); i++) {
			VirtualColumn vc = list.get(i);
			params.put(vc.getExp(), vc.getColumnName());
			append.append(vc.getColumnName());
			if (i != list.size() - 1) {
				append.append(",");
			}
		}
		append.append(")values(");
		for (int i = 0; i < list.size(); i++) {
			VirtualColumn vc = list.get(i);
			append.append(":");
			append.append(vc.getExp());
			if (i != list.size() - 1) {
				append.append(" ,");
			}
		}
		append.append(" )");
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

	public Map<String, ELValueVO> getParameters() {
		return parameters;
	}

}
