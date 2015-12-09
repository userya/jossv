package com.jossv.framework.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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
import com.jossv.system.model.entity.App;
import com.jossv.system.model.entity.Stage;
import com.jossv.system.model.entity.Table;
import com.jossv.system.model.table.EntityVO;
import com.jossv.system.model.table.PageVO;
import com.jossv.system.model.table.ServiceVO;

/**
 * Created by yangjiankang on 15/11/17.
 */
public class AppEngineImpl implements AppEngine {

	private App app;

	private ClassLoader classLoader;

	private DruidDataSource dataSource;

	private TableFactory tableFactory;

	private EntityFactory entityFactory;

	private DaoFactory daoFactory;

	private ServiceFactory serviceFactory;

	private PageFactory pageFactory;

	private Map<Long, StageEngine> stageEngineMap = new ConcurrentHashMap<>();

	public StageEngine getStageEngine(String stageId){
		return stageEngineMap.get(stageId);
	}
	
//	public void processInvoke(String pageId, String stageId) {
//		
//	}
//
//	public void processInvoke(String pageId, String stageId,
//			String serviceId) {
//
//	}

	protected void initDataSource() {
		String config = app.getApp().getConfig();
		JSONObject json = JSON.parseObject(config);
		DruidDataSource ds = new DruidDataSource();
		ds.setUrl(json.getString("jdbc_url"));
		ds.setUsername(json.getString("jdbc_user"));
		ds.setPassword(json.getString("json_password"));
		ds.setMaxActive(20);
		ds.setInitialSize(1);
		ds.setMaxWait(60000);
		ds.setMinIdle(1);
		this.dataSource = ds;
	}

	protected void initTableFactory(List<Table> tables) {
		StaticTableFactory factory = new StaticTableFactory(tables);
		factory.init();
		this.tableFactory = factory;
	}

	protected void initEntityFactory(List<EntityVO> entities) {
		StaticEntityFactory factory = new StaticEntityFactory(entities);
		factory.setTableFactory(this.tableFactory);
		factory.init();
		this.entityFactory = factory;
	}

	protected void initDaoFactory() {
		DaoFactoryImpl dao = new DaoFactoryImpl(entityFactory, dataSource);
		this.daoFactory = dao;
	}

	protected void initServiceFactory(List<ServiceVO> services) {
		ServiceFactoryImpl serviceF = new ServiceFactoryImpl(services);
		serviceF.setDaoFactory(daoFactory);
		serviceF.init();
		this.serviceFactory = serviceF;
	}

	protected void initPageFactory(List<PageVO> pages) {
		PageFactoryImpl pageF = new PageFactoryImpl(pages);
		pageF.init();
		this.pageFactory = pageF;
	}

	protected void initStageEngine(Map<String, Stage> stateMap) {
		for (Stage stage : stateMap.values()) {
			StageEngineImpl s = new StageEngineImpl();
			s.setAppEngine(this);
			s.setStage(stage);
		}
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
		List<Table> tables = new ArrayList<>();
		List<EntityVO> entities = new ArrayList<>();
		List<ServiceVO> services = new ArrayList<>();
		List<PageVO> pages = new ArrayList<>();
		Map<String, Stage> stateMap = new HashMap<>();
		List<Stage> list = app.getStage();
		if (list != null) {
			for (Stage stage : list) {
				tables.addAll(stage.getTables());
				entities.addAll(stage.getEntitis());
				services.addAll(stage.getServices());
				pages.addAll(stage.getPages());
				stateMap.put(stage.getStage().getId() + "", stage);
			}
		}

		initTableFactory(tables);
		initEntityFactory(entities);
		initDaoFactory();
		initServiceFactory(services);
		initPageFactory(pages);
		initStageEngine(stateMap);
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
