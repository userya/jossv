package com.jossv.framework.engine;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jossv.framework.dao.DaoFactory;
import com.jossv.framework.dao.EntityFactory;
import com.jossv.framework.dao.TableFactory;
import com.jossv.framework.page.PageFactory;
import com.jossv.framework.service.ServiceFactory;
import com.jossv.system.model.entity.App;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Created by yangjiankang on 15/11/17.
 */
public class AppEngineImpl implements AppEngine {

	private App app;

	private ClassLoader classLoader;

	private DataSource dataSource;

	private TableFactory tableFactory;

	private EntityFactory entityFactory;

	private DaoFactory daoFactory;

	private ServiceFactory serviceFactory;

	private PageFactory pageFactory;

	public void processInvoke(HttpServletRequest request, HttpServletResponse response, String pageId) {
		try {
			response.getWriter().println("hello world!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void processInvoke(HttpServletRequest request, HttpServletResponse response, String pageId,
			String serviceId) {

	}
	
	
	protected void initDataSource(){
		String config = app.getApp().getConfig();
		JSONObject json = JSON.parseObject(config);
		
		
		DruidDataSource ds = new DruidDataSource();
		
		
		this.dataSource  = ds ;
	}

	@Override
	public void start() {
		classLoader = null;
		dataSource = null;
		tableFactory = null;
		entityFactory = null;
		daoFactory = null;
		serviceFactory = null;
		pageFactory = null;
		initDataSource();
		
		
	}

	@Override
	public void shutdown() {

	}

	@Override
	public void restart() {

	}

	@Override
	public void refresh() {

	}

	@Override
	public int getStatus() {
		return 0;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
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
