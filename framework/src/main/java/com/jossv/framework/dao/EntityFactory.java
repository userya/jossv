package com.jossv.framework.dao;

import java.util.Map;

import com.jossv.framework.dao.model.Entity;

public interface EntityFactory {

	Entity getEntity(String id);

	boolean contain(String id);
	
	Map<String, com.jossv.framework.dao.model.Entity> getEntityMap() ;
	
	TableFactory getTableFactory();
}
