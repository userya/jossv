package com.jossv.framework.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jossv.framework.dao.BaseObject;
import com.jossv.framework.dao.CommonDAO;

public class ServiceInvokerImpl implements ServiceInvoker {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private JossServiceVO serviceVO;

	private ServiceFactory factory;

	private List<ServiceInterceptor> interceptors = new ArrayList<>();

	private Map<String, CommonDAO<BaseObject>> daos = new HashMap<>();

	private static ScriptEngineManager manager = new ScriptEngineManager();

	@SuppressWarnings("unchecked")
	@Override
	public void invoke(String id, Map<String, Object> parameters) {
		ServiceContext context = new ServiceContext();
		if (!serviceVO.getRefService().isEmpty()) {
			for (String sid : serviceVO.getRefService().keySet()) {
				context.getRef().put(sid, factory.findById(sid));
			}
		}
		context.put("daos", daos);
		context.put("params", parameters);
		for (ServiceInterceptor interceptor : interceptors) {
			interceptor.before(context);
		}
		ScriptEngine engine = manager.getEngineByName(serviceVO.getLanguage());
		for (String key : context.keySet()) {
			engine.put(key, context.get(key));
		}
		try {
			long start = System.currentTimeMillis();
			Map<String, Object> result = (Map<String, Object>) engine.eval(serviceVO.getActions());
			long end = System.currentTimeMillis();
			logger.debug("eval script speed : {}", end - start);
			if (result != null) {
				for (String key : result.keySet()) {
					context.put(key, result.get(key));
				}
			}
		} catch (ScriptException e) {
			e.printStackTrace();
			throw new com.jossv.framework.exception.BaseException(e);
		}

		for (ServiceInterceptor interceptor : interceptors) {
			interceptor.after(context);
		}
	}

	public JossServiceVO getServiceVO() {
		return serviceVO;
	}

	public void setServiceVO(JossServiceVO serviceVO) {
		this.serviceVO = serviceVO;
	}

	public ServiceFactory getFactory() {
		return factory;
	}

	public void setFactory(ServiceFactory factory) {
		this.factory = factory;
	}

	public List<ServiceInterceptor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<ServiceInterceptor> interceptors) {
		this.interceptors = interceptors;
	}

	public Map<String, CommonDAO<BaseObject>> getDaos() {
		return daos;
	}

	public void setDaos(Map<String, CommonDAO<BaseObject>> daos) {
		this.daos = daos;
	}

}
