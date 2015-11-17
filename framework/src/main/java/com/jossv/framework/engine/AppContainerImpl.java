package com.jossv.framework.engine;

import com.jossv.system.service.SystemService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by yangjiankang on 15/11/17.
 */
public class AppContainerImpl implements AppContainer {

    private SystemService systemService;

    private Map<String, AppEngine> appMap = new ConcurrentHashMap<>();

    public boolean contain(String appid) {
        return appMap.containsKey(appid);
    }

    public void invokePage(HttpServletRequest request, HttpServletResponse response, String appid, String pageId) {
        appMap.get(appid).processInvoke(request, response, pageId);
    }

    public void invokePageService(HttpServletRequest request, HttpServletResponse response, String appid, String pageId, String serviceId) {
        appMap.get(appid).processInvoke(request, response, pageId, serviceId);
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
