package com.jossv.framework.service;

import java.util.ArrayList;
import java.util.List;

public class ServiceInvokerImpl implements ServiceInvoker {

	private JossServiceVO serviceVO;

	private ServiceFactory factory;

	private List<ServiceInterceptor> interceptors = new ArrayList<>();

	@Override
	public void invoke(String id, ServiceContext context) {
		if (!serviceVO.getRefService().isEmpty()) {
			for (String sid : serviceVO.getRefService().keySet()) {
				context.getRef().put(sid, factory.findById(sid));
			}
		}
		for (ServiceInterceptor interceptor : interceptors) {
			interceptor.before(context);
		}

		
		
		for (ServiceInterceptor interceptor : interceptors) {
			interceptor.after(context);
		}
	}

}
