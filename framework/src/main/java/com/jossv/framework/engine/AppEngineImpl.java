package com.jossv.framework.engine;

import com.jossv.framework.dao.DaoFactory;
import com.jossv.framework.dao.EntityFactory;
import com.jossv.framework.dao.TableFactory;
import com.jossv.framework.page.PageFactory;
import com.jossv.framework.service.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Created by yangjiankang on 15/11/17.
 */
public class AppEngineImpl implements AppEngine {

    private ClassLoader classLoader;

    private DataSource dataSource;

    private TableFactory tableFactory;

    private EntityFactory entityFactory;

    private DaoFactory daoFactory;

    private ServiceFactory serviceFactory;

    private PageFactory pageFactory;


    public void processInvoke(HttpServletRequest request, HttpServletResponse response, String pageId){

    }

    public void processInvoke(HttpServletRequest request, HttpServletResponse response, String pageId, String serviceId){

    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
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
}
