package com.jossv.framework.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jossv.framework.dao.BaseObject;
import com.jossv.framework.dao.DaoFactory;
import com.jossv.model.entity.Entity;
import com.jossv.model.service.Service;
import com.jossv.model.table.Table;
import com.jossv.reader.impl.App;
import com.jossv.reader.impl.Stage;

public class ServiceFactoryImpl implements ServiceFactory {

	private List<Service> services;

	private DaoFactory daoFactory;

	private App app;

	private Map<String, ServiceInvoker> invokers = new HashMap<>();

	public ServiceFactoryImpl(App app, List<Service> services) {
		super();
		this.app = app;
		this.services = services;
	}

	public void init() {
		if (services != null) {
			for (Service serviceVO : services) {
				JossServiceVO vo = createJossServiceVO(serviceVO);
				ServiceInvokerImpl inv = createServiceInvoker(vo);
				// Map<String, Table>

				invokers.put(vo.getId(), inv);
			}
		}
	}

	protected JossServiceVO createJossServiceVO(Service serviceVO) {
		// TODO
		JossServiceVO vo = new JossServiceVO();
		vo.setId(serviceVO.getId());
		vo.setActions(serviceVO.getAction());
		vo.setAuth(serviceVO.isAuth());
		return vo;
	}

	protected ServiceInvokerImpl createServiceInvoker(JossServiceVO vo) {
		// TODO
		ServiceInvokerImpl invoker = new ServiceInvokerImpl();
		invoker.setFactory(this);
		invoker.setServiceVO(vo);

		Stage stage = this.app.getServiceMap().get(vo.getId());

		for (Table table : stage.getTables().values()) {
			invoker.getDaos().put(table.getId(), daoFactory.getDAO(BaseObject.class, table.getId()));
		}
		for (Entity entity : stage.getEntities().values()) {
			invoker.getDaos().put(entity.getId(), daoFactory.getDAO(BaseObject.class, entity.getId()));
		}

		return invoker;
	}

	@Override
	public ServiceInvoker findById(String id) {
		return invokers.get(id);
	}

	public DaoFactory getDaoFactory() {
		return daoFactory;
	}

	public void setDaoFactory(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

}
