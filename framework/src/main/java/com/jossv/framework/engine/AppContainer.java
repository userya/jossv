package com.jossv.framework.engine;

/**
 * Created by yangjiankang on 15/11/17.
 */
public interface AppContainer {

    boolean contain(String appid);

//    void invokePage(String appid, String stage, String pageId);
//
//    void invokePageService( String appid,String stage,  String pageId, String serviceId);
    
    AppEngine getAppEngine(String appid);

    void addApp(String appid);

    void removeApp(String appid);

    void shutdown(String appid);

    void start(String appid);

    void restart(String appid);

    void refresh(String appid);

    int getStatus(String appid); // normal, starting, stopped, updating
}
