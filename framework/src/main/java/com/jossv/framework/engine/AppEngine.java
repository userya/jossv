package com.jossv.framework.engine;

/**
 * <!-- 对于每个APP 来说, 都有一个独立的dataSource, table|entity容器, DaoFactory, Service容器, Page容器-->
 * Created by yangjiankang on 15/11/17.
 */
public interface AppEngine {

//    void processInvoke(String pageId, String stageId);
//
//    void processInvoke(String pageId, String stageId, String serviceId);
    
    StageEngine getStageEngine(String stageId);

    void shutdown();

    void start();

    void restart();

    void refresh();

    int getStatus(); // normal, starting, stopped, updating

}
