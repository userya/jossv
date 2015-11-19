package com.jossv.framework.dao.model;

import com.jossv.framework.dao.BaseObject;
import com.jossv.framework.dao.annotation.RelationshipType;
import com.jossv.framework.dao.sql.Condition;

public class Relationship {

	private DefineAble relObject;
	
	private String alias;
	
	private Condition condition;
	
	private RelationshipType type ;
	
	private String relObjectId;
	
	private Class<?> targetClass = BaseObject.class;
	
	private String sourceId;
	private String targetId;
	
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

	public Class<?> getTargetClass() {
		return targetClass;
	}

	public void setTargetClass(Class<?> targetClass) {
		this.targetClass = targetClass;
	}

	public String getRelObjectId() {
		return relObjectId;
	}

	public void setRelObjectId(String relObjectId) {
		this.relObjectId = relObjectId;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	
	
	
}
