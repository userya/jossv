package com.jossv.framework.service;

import java.util.Map;

public interface ServiceInvoker {

	void invoke(String id, Map<String, Object> parameters);
	
}
