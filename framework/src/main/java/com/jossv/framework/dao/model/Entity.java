package com.jossv.framework.dao.model;

import java.util.LinkedHashMap;
import java.util.Map;

import com.jossv.framework.dao.EntityFactory;
import com.jossv.framework.dao.sql.Condition;

public class Entity implements DefineAble {

	private String id ;
	
	private DefineAble main;
	
	private String mainAlias = "tbl";
	
	private Condition mainCondition ;
	
	private Map<String, Relationship> rels = new LinkedHashMap<String, Relationship>();

	/**
	 * select 子 的时候 ,套一层 加入查询条件 (select 1 from xxx tbl where ${tbl.id} = table?子实体别名:实体别名 ${c.pid} : )
	 * ${tbl.id} = 
	 */
	private Map<String, Relationship> children = new LinkedHashMap<String, Relationship>();
	
	private EntityFactory entityFactory;

	public Relationship getRelationship(String alias){
		if(rels.containsKey(alias)) {
			return rels.get(alias);
		}
		if(children.containsKey(alias)) {
			return children.get(alias);
		}

		return null;
	}
	
	
	public DefineAble getMain() {
		return main;
	}

	public void setMain(DefineAble main) {
		this.main = main;
	}

	public String getMainAlias() {
		return mainAlias;
	}

	public void setMainAlias(String mainAlias) {
		this.mainAlias = mainAlias;
	}

	public Map<String, Relationship> getRels() {
		return rels;
	}

	public void setRels(Map<String, Relationship> rels) {
		this.rels = rels;
	}

	public Map<String, Relationship> getChildren() {
		return children;
	}

	public void setChildren(Map<String, Relationship> children) {
		this.children = children;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Condition getMainCondition() {
		return mainCondition;
	}

	public void setMainCondition(Condition mainCondition) {
		this.mainCondition = mainCondition;
	}


	public EntityFactory getEntityFactory() {
		return entityFactory;
	}


	public void setEntityFactory(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}
	
}
