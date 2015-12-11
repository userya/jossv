package com.jossv.framework.engine.impl;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jossv.framework.dao.DaoFactory;
import com.jossv.framework.dao.DaoFactoryImpl;
import com.jossv.framework.dao.EntityFactory;
import com.jossv.framework.dao.TableFactory;
import com.jossv.framework.dao.annotation.factory.impl.StaticEntityFactory;
import com.jossv.framework.dao.annotation.factory.impl.StaticTableFactory;
import com.jossv.framework.page.PageFactory;
import com.jossv.framework.page.PageFactoryImpl;
import com.jossv.framework.service.ServiceFactory;
import com.jossv.framework.service.ServiceFactoryImpl;
import com.jossv.model.entity.Entity;
import com.jossv.model.page.Page;
import com.jossv.model.service.Service;
import com.jossv.reader.impl.App;

public class AppResource {

	protected com.jossv.reader.impl.App app;

	protected ClassLoader classLoader;

	protected DruidDataSource dataSource;

	protected TableFactory tableFactory;

	protected EntityFactory entityFactory;

	protected DaoFactory daoFactory;

	protected ServiceFactory serviceFactory;

	protected PageFactory pageFactory;

	protected void initDataSource() {
		String config = app.getValue("dataSource");
		JSONObject json = JSON.parseObject(config);
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(json.getString("jdbc_url"));
		ds.setUsername(json.getString("jdbc_user"));
		ds.setPassword(json.getString("jdbc_password"));
		ds.setDriverClassName(json.getString("jdbc_driver"));
		ds.setMaxActive(20);
		ds.setInitialSize(1);
		ds.setMaxWait(60000);
		ds.setMinIdle(1);
		this.dataSource = ds;
	}

	protected void initTableFactory(List<com.jossv.model.table.Table> tables) {
		StaticTableFactory factory = new StaticTableFactory(tables);
		factory.init();
		this.tableFactory = factory;
	}

	protected void initEntityFactory(List<Entity> entities) {
		StaticEntityFactory factory = new StaticEntityFactory(entities);
		factory.setTableFactory(this.tableFactory);
		factory.init();
		this.entityFactory = factory;
	}

	protected void initDaoFactory() {
		DaoFactoryImpl dao = new DaoFactoryImpl(entityFactory, dataSource);
		this.daoFactory = dao;
	}

	protected void initServiceFactory(List<Service> services) {
		ServiceFactoryImpl serviceF = new ServiceFactoryImpl(this.app, services);
		serviceF.setDaoFactory(daoFactory);
		serviceF.init();
		this.serviceFactory = serviceF;
	}

	protected void initPageFactory(List<Page> pages) {
		PageFactoryImpl pageF = new PageFactoryImpl(pages);
		pageF.init();
		this.pageFactory = pageF;
	}

	public void start() {
		classLoader = null;
		dataSource = null;
		tableFactory = null;
		entityFactory = null;
		daoFactory = null;
		serviceFactory = null;
		pageFactory = null;
		initDataSource();
		List<com.jossv.model.table.Table> tables = new ArrayList<>();
		List<Entity> entities = new ArrayList<>();
		List<Service> services = new ArrayList<>();
		List<Page> pages = new ArrayList<>();

		for (com.jossv.reader.impl.Stage stage : app.getStageMap().values()) {
			if (stage.getStage() != null) {
				tables.addAll(stage.getTables().values());
				entities.addAll(stage.getEntities().values());
				services.addAll(stage.getServices().values());
				pages.addAll(stage.getPages().values());
			}
		}

		initTableFactory(tables);
		initEntityFactory(entities);
		initDaoFactory();
		initServiceFactory(services);
		initPageFactory(pages);
		this.daoFactory.initSchema();
	}

	public void shutdown() {

	}

	public void restart() {

	}

	public void refresh() {

	}

	public int getStatus() {
		return 0;
	}

	public TableFactory getTableFactory() {
		return tableFactory;
	}

	public void setTableFactory(TableFactory tableFactory) {
		this.tableFactory = tableFactory;
	}

	public EntityFactory getEntityFactory() {
		return entityFactory;
	}

	public void setEntityFactory(EntityFactory entityFactory) {
		this.entityFactory = entityFactory;
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public ServiceFactory getServiceFactory() {
		return serviceFactory;
	}

	public void setServiceFactory(ServiceFactory serviceFactory) {
		this.serviceFactory = serviceFactory;
	}

	public PageFactory getPageFactory() {
		return pageFactory;
	}

	public void setPageFactory(PageFactory pageFactory) {
		this.pageFactory = pageFactory;
	}

	public ClassLoader getClassLoader() {
		return classLoader;
	}

	public void setClassLoader(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}

	public App getApp() {
		return app;
	}

	public void setApp(App app) {
		this.app = app;
	}

}
