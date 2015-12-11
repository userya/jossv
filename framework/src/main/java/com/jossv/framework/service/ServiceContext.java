package com.jossv.framework.service;

import java.util.HashMap;
import java.util.Map;

import com.jossv.framework.dao.BaseObject;
import com.jossv.framework.dao.CommonDAO;

/**
 * ref
 * 
 * 
 * @author yangjiankang
 *
 */
public class ServiceContext extends HashMap<String, Object> {

	private static final long serialVersionUID = -4972726791933028979L;

	private Map<String, ServiceInvoker> ref = new HashMap<>();
	
	private Map<String, Object> params = new HashMap<>();
	
	private Map<String, CommonDAO<BaseObject>> daos = new HashMap<>();

	public Map<String, ServiceInvoker> getRef() {
		return ref;
	}

	public void setRef(Map<String, ServiceInvoker> ref) {
		this.ref = ref;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public Map<String, CommonDAO<BaseObject>> getDaos() {
		return daos;
	}

	public void setDaos(Map<String, CommonDAO<BaseObject>> daos) {
		this.daos = daos;
	}
	
}
