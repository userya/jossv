package com.jossv.framework.dao.sql;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jossv.framework.dao.model.Column;
import com.jossv.framework.dao.model.Table;

import freemarker.template.TemplateException;

public class Select extends DbAtom implements SqlPart {

	private SelectPart mainPart;

	private List<JoinPart> joinParts = new ArrayList<JoinPart>();

	private Where where = new Where();

	private String selectExpression;

	private List<VirtualColumn> getVirtualColums(SelectPart select) {
		DbAtom part = select.getPart();
		String alias = select.getAlias();
		List<VirtualColumn> list = new ArrayList<VirtualColumn>();
		if (part instanceof Table) {
			Table t = (Table) part;
			for (Column column : t.getColumns()) {
				VirtualColumn c = new VirtualColumn();
				c.setAlias(alias);
				c.setSource(part);
				c.setPhysicalColumn(column);
				if (alias == null || alias.trim().length() == 0) {
					c.setExp(column.getName());
				} else {
					c.setExp(alias + "." + column.getName());
				}
				c.setName(column.getColumnName());
				list.add(c);
			}

		} else if (part instanceof Select) {
			Select t = (Select) part;
			for (VirtualColumn vc : t.getVirtualColums()) {
				VirtualColumn c = new VirtualColumn();
				c.setAlias(alias);
				c.setSource(part);
				c.setPhysicalColumn(vc.getPhysicalColumn());
				c.setExp(alias + "." + vc.getExp());
				c.setName(vc.getAliasName());
				list.add(c);
			}
		}
		return list;
	}

	public List<VirtualColumn> getVirtualColums() {
		List<VirtualColumn> list = new ArrayList<VirtualColumn>();
		list.addAll(getVirtualColums(mainPart));
		for (JoinPart part : joinParts) {
			list.addAll(getVirtualColums(part));
		}
		return list;
	}

	public void getSql(StringBuilder builder, Map<String, Object> params) {
		// select * from mainPart join xxx
		builder.append("select ");
		List<VirtualColumn> columns = getVirtualColums();
		for (VirtualColumn column : columns) {
			params.put(column.getExp(), column.getColumnName());
		}
		if (this.selectExpression != null && this.selectExpression.trim().length() > 0) {
			builder.append(this.selectExpression);
		} else {
			int i = 0;
			for (VirtualColumn vc : columns) {
				builder.append(vc.getQueryName());
				if (columns.size() - 1 != i) {
					builder.append(", ");
				}
				i++;
			}
		}

		builder.append(" from ");
		mainPart.getSqlPart(builder, params);

		for (JoinPart join : this.joinParts) {
			join.getSqlPart(builder, params);
		}

		if (where != null) {
			where.getSqlPart(builder);
		}
	}

	public SelectPart getMainPart() {
		return mainPart;
	}

	public void setMainPart(SelectPart mainPart) {
		this.mainPart = mainPart;
	}

	public List<JoinPart> getJoinParts() {
		return joinParts;
	}

	public void setJoinParts(List<JoinPart> joinParts) {
		this.joinParts = joinParts;
	}

	public Where getWhere() {
		return where;
	}

	public void setWhere(Where where) {
		this.where = where;
	}

	public String getSelectExpression() {
		return selectExpression;
	}

	public void setSelectExpression(String selectExpression) {
		this.selectExpression = selectExpression;
	}

	public static void main(String[] args) throws IOException, TemplateException {
		Table table = new Table();
		table.setTableName("t_user");
		Column id = new Column();
		id.setName("id");
		id.setColumnName("id");
		Column username = new Column();
		username.setName("username");
		username.setColumnName("user_name");
		table.getColumns().add(id);
		table.getColumns().add(username);
		Select select = new Select();
		select.setMainPart(new SelectPart("t", table));
		JoinPart join = new JoinPart();
		join.setJoinType(JoinType.inner);
		join.setAlias("b");
		join.setJoinType(JoinType.left);
		join.setPart(table);
		join.setCondition(new Condition("${t.id} = ${b.username}"));
		select.joinParts.add(join);
		StringBuilder sb = new StringBuilder();
		Map<String, Object> p = new HashMap<String, Object>();
		select.getSql(sb, p);
		System.out.println(sb.toString());
		System.out.println(TemplateUtils.complie(sb.toString(), p));

		Select select1 = new Select();
		select1.setMainPart(new SelectPart("a", select));
		StringBuilder sb1 = new StringBuilder();

		Where where = new Where();
		where.and(new Condition("${a.t.username} =  :abc"));
		select1.setWhere(where); // a.t_id
		select1.setSelectExpression("distinct ${a.t.username}");

		JoinPart join1 = new JoinPart();
		join1.setJoinType(JoinType.inner);
		join1.setAlias("bb");
		join1.setPart(table);
		join1.setCondition(new Condition("${a.t.username } = ${ bb.username } and ${bb.username} != :username"));
		select1.joinParts.add(join1);

		Map<String, Object> p1 = new HashMap<String, Object>();
		select1.getSql(sb1, p1);
		System.out.println(p1);
		System.out.println(sb1.toString());
		System.out.println(TemplateUtils.complie(sb1.toString(), p1));
		// List<Object> pp = new ArrayList<Object>();
		// Map<String, ELValueVO> v = new HashMap<String, ELValueVO>();
		// v.put("abc", "1234");
		// v.put("username", "admin");
		// System.out.println(ParamUtils.complie(TemplateUtils.complie(sb1.toString(),
		// p1),v, pp));
		// System.out.println(pp);
	}

	public Map<String, ELValueVO> getParameters() {
		if(this.getWhere() != null && this.getWhere().getCondition() != null) {
			return this.getWhere().getCondition().getParameters();
		}
		return null;
	}

}
