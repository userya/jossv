package com.jossv.framework.dao;

public interface DaoFactory {
	
	<T> CommonDAO<T> getDAO(Class<T> clazz);

	<T> CommonDAO<T> getDAO(Class<T> clazz, String id);
	
}
