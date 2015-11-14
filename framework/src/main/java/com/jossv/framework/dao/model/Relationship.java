package com.jossv.framework.dao.model;

import com.jossv.framework.dao.annotaion.RelationshipType;
import com.jossv.framework.dao.sql.Condition;

public class Relationship {

	private DefineAble relObject;
	
	private String alias;
	
	private Condition condition;
	
	private RelationshipType type ;
	
	public DefineAble getRelObject() {
		return relObject;
	}

	public void setRelObject(DefineAble relObject) {
		this.relObject = relObject;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}

	public RelationshipType getType() {
		return type;
	}

	public void setType(RelationshipType type) {
		this.type = type;
	}
	
	
	
}
