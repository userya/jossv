package com.jossv.framework.dao.utils;

import java.util.List;
import java.util.Map;

import com.jossv.framework.dao.model.Column;
import com.jossv.framework.dao.model.DefineAble;
import com.jossv.framework.dao.model.Entity;
import com.jossv.framework.dao.model.Relationship;
import com.jossv.framework.dao.model.Table;
import com.jossv.framework.dao.sql.Condition;
import com.jossv.framework.dao.sql.DbAtom;
import com.jossv.framework.dao.sql.Delete;
import com.jossv.framework.dao.sql.Insert;
import com.jossv.framework.dao.sql.JoinPart;
import com.jossv.framework.dao.sql.JoinType;
import com.jossv.framework.dao.sql.Select;
import com.jossv.framework.dao.sql.SelectPart;
import com.jossv.framework.dao.sql.Update;

public class EntitySqlBuilder {

	
	
	
	
	public static String getPkEl(DefineAble entityOrTable) {
		if (entityOrTable instanceof Table) {
			Table t = ((Table) entityOrTable);
			return t.getPkColumn();
		} else {
			DefineAble t = ((Entity) entityOrTable).getMain();
			if (t instanceof Table) {
				return ((Entity) entityOrTable).getMainAlias() + "." + ((Table) entityOrTable).getPkColumn();
			} else {
				// 只支持主实体为Table
				throw new RuntimeException("只支持主实体为Table");
			}
		}
	}
	
	public static Condition buildPropertyCondition(String el, Object pkValue) {
		Condition c = new Condition("${" + el + "} = :" + el);
		c.setParameter(el, pkValue);
		return c;
	}
	
	public static void getCndProperty(String alias , DefineAble entityOrTable, List<String> list){
		if (entityOrTable instanceof Table) {
			Table t = ((Table) entityOrTable);
			for(Column c : t.getColumns()) {
				String n = c.getName();
				if(alias != null && alias.trim().length() >0) {
					n = alias + "." + n;
				}
				list.add(n);
			}
		} else {
			Entity entity = (Entity) entityOrTable;
			DefineAble t = entity.getMain();
			String mainAlias = entity.getMainAlias();
			getCndProperty(mainAlias, t, list);
			Map<String, Relationship>  rs = entity.getRels();
			for (Relationship r: rs.values()) {
				String a = r.getAlias();
				DefineAble ra = r.getRelObject();
				getCndProperty(a, ra, list);
			}
		}
	}
	

	/**
	 * insert
	 * 
	 * @param entityOrTable
	 * @return
	 */
	public static Insert buildInsert(DefineAble entityOrTable) {
		Insert insert = new Insert();
		if (entityOrTable instanceof Table) {
			insert.setTable(((Table) entityOrTable));
		} else {
			DefineAble t = ((Entity) entityOrTable).getMain();
			if (t instanceof Table) {
				insert.setAlias(((Entity) entityOrTable).getMainAlias());
				insert.setTable(((Table) entityOrTable));
			} else {
				// 只支持主实体为Table
				throw new RuntimeException("只支持主实体为Table");
			}
		}
		return insert;
	}

	public static Update buildUpdate(DefineAble entityOrTable) {
		Update update = new Update();
		if (entityOrTable instanceof Table) {
			update.setTable(((Table) entityOrTable));
		} else {
			DefineAble t = ((Entity) entityOrTable).getMain();
			if (t instanceof Table) {
				update.setAlias(((Entity) entityOrTable).getMainAlias());
				update.setTable(((Table) entityOrTable));
			} else {
				// 只支持主实体为Table
				throw new RuntimeException("只支持主实体为Table");
			}
		}
		return update;
	}

	public static Delete buildDelete(DefineAble entityOrTable) {
		Delete delete = new Delete();
		if (entityOrTable instanceof Table) {
			delete.setTable(((Table) entityOrTable));
		} else {
			DefineAble t = ((Entity) entityOrTable).getMain();
			if (t instanceof Table) {
				delete.setAlias(((Entity) entityOrTable).getMainAlias());
				delete.setTable(((Table) entityOrTable));
			} else {
				// 只支持主实体为Table
				throw new RuntimeException("只支持主实体为Table");
			}
		}
		return delete;
	}

	public static Select buildSelect(DefineAble entityOrTable) {
		if (entityOrTable instanceof Table) {
			return build((Table) entityOrTable);
		} else {
			return build((Entity) entityOrTable);
		}
	}

	public static Select build(Entity entity) {
		Select select = new Select();
		DefineAble main = entity.getMain();
		DbAtom mainPart = (main instanceof Table) ? (Table) main : build((Entity) main);
		select.setMainPart(new SelectPart(entity.getMainAlias(), mainPart));
		Map<String, Relationship> rels = entity.getRels();
		for (String alias : rels.keySet()) {
			Relationship relation = rels.get(alias);
			DefineAble r = relation.getRelObject();
			DbAtom rPart = (r instanceof Table) ? (Table) r : build((Entity) r);
			JoinPart join = new JoinPart();
			join.setAlias(alias);
			join.setJoinType(JoinType.left);
			join.setPart(rPart);
			join.setCondition(relation.getCondition());
			select.getJoinParts().add(join);
		}
		return select;
	}

	public static Select build(Table table) {
		Select select = new Select();
		select.setMainPart(new SelectPart(table));
		return select;
	}

}
