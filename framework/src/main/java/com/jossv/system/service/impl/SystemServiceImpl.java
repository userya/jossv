package com.jossv.system.service.impl;

import com.jossv.framework.dao.DaoFactory;
import com.jossv.system.model.table.AppVO;
import com.jossv.system.service.SystemService;

/**
 * Created by yangjiankang on 15/11/17.
 */
public class SystemServiceImpl implements SystemService {

    private DaoFactory daoFactory;

    public DaoFactory getDaoFactory() {
        return daoFactory;
    }

    public void setDaoFactory(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    @Override
    public AppVO getApp(Long id) {


        return null;
    }
}
