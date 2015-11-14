package com.jossv.framework.dao;

import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ConcurrentReferenceHashMap;

import com.alibaba.druid.pool.DruidDataSource;
import com.jossv.framework.dao.generator.ddl.SchemaGenerator;
import com.jossv.framework.dao.model.DefineAble;

public class DaoFactoryImpl implements DaoFactory {

	private EntityFactory entityFactory;

	private JdbcTemplate jdbcTemplate;

	private SchemaGenerator schemaGenerator;

	@SuppressWarnings("rawtypes")
	private Map<String, CommonDAO> daoMap = new ConcurrentReferenceHashMap<String, CommonDAO>();

	public DaoFactoryImpl(EntityFactory entityFactory, DruidDataSource dataSource) {
		super();
		this.entityFactory = entityFactory;
		this.jdbcTemplate = new JdbcTemplate(dataSource);

		schemaGenerator = new SchemaGenerator();
		schemaGenerator.setUrl(dataSource.getUrl());
		schemaGenerator.setUsername(dataSource.getUsername());
		schemaGenerator.setPassword(dataSource.getPassword());
		schemaGenerator.setDriver(dataSource.getDriverClassName());
		schemaGenerator.setDialect("org.hibernate.dialect.MySQLDialect");// TODO
	}

	public <T> CommonDAO<T> getDAO(Class<T> clazz) {
		return getDAO(clazz, clazz.getName());
	}

	@SuppressWarnings("unchecked")
	public <T> CommonDAO<T> getDAO(Class<T> clazz, String id) {
		if (!daoMap.containsKey(id)) {
			DefineAble da = null;
			if (entityFactory.contain(id)) {
				da = entityFactory.getEntity(id);
			} else {
				da = entityFactory.getTableFactory().getTable(id);
			}
			if (da != null) {
				CommonDAOImpl<T> dao = new CommonDAOImpl<T>(jdbcTemplate, entityFactory, da, clazz);
				daoMap.put(id, dao);
			} else {
				throw new RuntimeException("can not found id with :" + id);
			}
		}
		return daoMap.get(id);
	}

	public void remove(String id) {
		if (daoMap.containsKey(id)) {
			daoMap.remove(id);
		}
	}

	public SchemaGenerator getSchemaGenerator() {
		return schemaGenerator;
	}

}
