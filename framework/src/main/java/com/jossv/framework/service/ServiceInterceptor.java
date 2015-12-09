package com.jossv.framework.service;

public interface ServiceInterceptor {

	boolean before(ServiceContext context);

	boolean after(ServiceContext context);

}
