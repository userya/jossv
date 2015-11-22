package com.jossv.framework.engine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <!-- 对于每个APP 来说, 都有一个独立的dataSource, table|entity容器, DaoFactory, Service容器, Page容器-->
 * Created by yangjiankang on 15/11/17.
 */
public interface AppEngine {

    void processInvoke(HttpServletRequest request, HttpServletResponse response, String pageId);

    void processInvoke(HttpServletRequest request, HttpServletResponse response, String pageId, String serviceId);

    void shutdown();

    void start();

    void restart();

    void refresh();

    int getStatus(); // normal, starting, stopped, updating

}
