package com.jossv;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jossv.system.service.SystemService;

public class ContextTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		SystemService service = context.getBean(SystemService.class);
		System.out.println(service);
		service.getApp(1L);
		
	}
	
	
}
