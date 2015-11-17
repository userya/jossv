package com.jossv.framework.engine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangjiankang on 15/11/17.
 */
public class AppContainerImpl implements AppContainer {

    private Map<String, AppEngine> appMap = new HashMap<>();

    public boolean contain(String appid) {
        return appMap.containsKey(appid);
    }


    public void invokePage(HttpServletRequest request, HttpServletResponse response, String appid, String pageId) {
        appMap.get(appid).processInvoke(request, response, pageId);
    }

    public void invokePageService(HttpServletRequest request, HttpServletResponse response, String appid, String pageId, String serviceId) {
        appMap.get(appid).processInvoke(request, response, pageId, serviceId);
    }
}
