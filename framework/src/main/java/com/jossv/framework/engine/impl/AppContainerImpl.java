package com.jossv.framework.engine.impl;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ConcurrentHashMap;

import com.jossv.framework.engine.AppContainer;
import com.jossv.framework.engine.AppEngine;
import com.jossv.reader.AppsReader;
import com.jossv.reader.impl.App;

/**
 * Created by yangjiankang on 15/11/17.
 */
public class AppContainerImpl implements AppContainer, Observer {

	private AppsReader appsReader;

	private Map<String, AppEngine> appMap = new ConcurrentHashMap<>();

	public void init() {
		appMap.clear();
		Map<String, App> list = appsReader.getApps();
		if (list != null) {
			for (com.jossv.reader.impl.App app : list.values()) {
				appMap.put(app.getName(), createAppEngine(app));
			}
		}
	}

	protected AppEngine createAppEngine(com.jossv.reader.impl.App app) {
		AppEngineImpl a = new AppEngineImpl();
		a.setApp(app);
		a.start();
		return a;
	}

	public boolean contain(String appid) {
		return appMap.containsKey(appid);
	}

	@Override
	public AppEngine getAppEngine(String appid) {
		return appMap.get(appid);
	}

	// public void invokePage(String appid, String stage,
	// String pageId) {
	// appMap.get(appid).processInvoke(stage, pageId);
	// }
	//
	// public void invokePageService(String appid, String stage,
	// String pageId, String serviceId) {
	// appMap.get(appid).processInvoke(pageId, stage, serviceId);
	// }

	@Override
	public void addApp(String appid) {
		if (appMap.containsKey(appid)) {
			throw new RuntimeException("app is exists, pleas check " + appid);
		}
	}

	@Override
	public void removeApp(String appid) {
		appMap.remove(appid);
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

	@Override
	public void update(Observable o, Object arg) {
		//TODO 精细化管理，暂时已应用粒度重启.
		this.init();
	}

	public AppsReader getAppsReader() {
		return appsReader;
	}

	public void setAppsReader(AppsReader appsReader) {
		this.appsReader = appsReader;
		this.appsReader.addObserver(this);
	}
}
