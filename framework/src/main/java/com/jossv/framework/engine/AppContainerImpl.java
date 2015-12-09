package com.jossv.framework.engine;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.jossv.system.model.entity.App;
import com.jossv.system.service.SystemService;

/**
 * Created by yangjiankang on 15/11/17.
 */
public class AppContainerImpl implements AppContainer {

	private SystemService systemService;

	private Map<String, AppEngine> appMap = new ConcurrentHashMap<>();

	public void init() {
		appMap.clear();
		List<App> list = systemService.getAllApp();
		if (list != null) {
			for (App app : list) {
				appMap.put(app.getApp().getName(), createAppEngine(app));
			}
		}
	}

	protected AppEngine createAppEngine(App app) {
		AppEngineImpl a = new AppEngineImpl();
		a.setApp(app);
		a.start();
		return a;
	}

	public boolean contain(String appid) {
		return appMap.containsKey(appid);
	}
	
	public StageEngine getStageEngine(String appid, String stageId){
		if(appMap.containsKey(appid) && appMap.get(appid).getStageEngine(stageId) != null) {
			return appMap.get(appid).getStageEngine(stageId);
		}
		return null;
	}

//	public void invokePage(String appid, String stage,
//			String pageId) {
//		appMap.get(appid).processInvoke(stage, pageId);
//	}
//
//	public void invokePageService(String appid, String stage,
//			String pageId, String serviceId) {
//		appMap.get(appid).processInvoke(pageId, stage, serviceId);
//	}

	@Override
	public void addApp(String appid) {
		if (appMap.containsKey(appid)) {
			throw new RuntimeException("app is exists, pleas check " + appid);
		}
	}

	@Override
	public void removeApp(String appid) {

	}

	@Override
	public void shutdown(String appid) {
		appMap.get(appid).shutdown();
	}

	@Override
	public void start(String appid) {
		appMap.get(appid).start();
	}

	@Override
	public void restart(String appid) {
		appMap.get(appid).restart();
	}

	@Override
	public void refresh(String appid) {
		appMap.get(appid).refresh();
	}

	@Override
	public int getStatus(String appid) {
		return appMap.get(appid).getStatus();
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
}
