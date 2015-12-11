package com.jossv.framework.dao;

import java.util.Map;

public interface TableFactory {

	boolean contain(String id);
	
	com.jossv.framework.dao.model.Table getTable(String id);
	
	Map<String, com.jossv.framework.dao.model.Table> getTableMap();
}
