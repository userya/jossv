package com.jossv.framework.dao;

import java.util.Map;

import com.jossv.framework.dao.model.Table;

public interface TableFactory {

	boolean contain(String id);
	
	Table getTable(String id);
	
	Map<String, com.jossv.framework.dao.model.Table> getTableMap();
}
