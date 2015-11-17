package com.jossv.framework.engine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by yangjiankang on 15/11/17.
 */
public interface AppContainer {

    boolean contain(String appid);

    void invokePage(HttpServletRequest request, HttpServletResponse response, String appid, String pageId);

    void invokePageService(HttpServletRequest request, HttpServletResponse response, String appid, String pageId, String serviceId);

}
