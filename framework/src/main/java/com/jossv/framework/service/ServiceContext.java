package com.jossv.framework.service;

import java.util.HashMap;
import java.util.Map;

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

	public Map<String, ServiceInvoker> getRef() {
		return ref;
	}

	public void setRef(Map<String, ServiceInvoker> ref) {
		this.ref = ref;
	}
	
}
