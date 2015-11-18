package com.jossv.framework.engine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by yangjiankang on 15/11/17.
 */
public interface AppContainer {

    boolean contain(String appid);

    void invokePage(HttpServletRequest request, HttpServletResponse response, String appid, String pageId);

    void invokePageService(HttpServletRequest request, HttpServletResponse response, String appid, String pageId, String serviceId);

    void addApp(String appid);

    void removeApp(String appid);

    void shutdown(String appid);

    void start(String appid);

    void restart(String appid);

    void refresh(String appid);

    int getStatus(String appid); // normal, starting, stopped, updating
}
