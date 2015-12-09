package com.jossv.framework.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jossv.framework.dao.DaoFactory;
import com.jossv.system.model.table.ServiceVO;

public class ServiceFactoryImpl implements ServiceFactory {

	private List<ServiceVO> services;

	private DaoFactory daoFactory;

	private Map<String, ServiceInvoker> invokers = new HashMap<>();

	public ServiceFactoryImpl(List<ServiceVO> services) {
		super();
		this.services = services;
	}

	public void init() {
		if (services != null) {
			for (ServiceVO serviceVO : services) {
				JossServiceVO vo = createJossServiceVO(serviceVO);
				ServiceInvoker inv = createServiceInvoker(vo);
				invokers.put(vo.getId(), inv);
			}
		}
	}

	protected JossServiceVO createJossServiceVO(ServiceVO serviceVO) {
		//TODO
		return null;
	}

	protected ServiceInvoker createServiceInvoker(JossServiceVO vo) {
		
		//TODO
		return null;
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
