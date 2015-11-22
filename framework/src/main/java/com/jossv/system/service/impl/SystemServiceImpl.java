package com.jossv.system.service.impl;

import java.util.List;

import com.jossv.framework.dao.CommonDAO;
import com.jossv.framework.dao.DaoFactory;
import com.jossv.system.model.entity.App;
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
	public App getApp(Long id) {
		CommonDAO<App> dao = daoFactory.getDAO(App.class);
		App app = dao.findByIdWithChild(1L);
		return app;
	}

	@Override
	public List<App> getAllApp() {
		CommonDAO<App> dao = daoFactory.getDAO(App.class);
		return dao.queryWithChildren(null);
	}
}
