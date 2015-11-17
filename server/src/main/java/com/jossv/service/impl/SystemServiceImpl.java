package com.jossv.service.impl;

import com.jossv.framework.dao.DaoFactory;
import com.jossv.service.SystemService;

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
}
