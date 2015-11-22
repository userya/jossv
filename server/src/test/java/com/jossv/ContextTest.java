package com.jossv;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jossv.framework.engine.AppContainer;

public class ContextTest {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		AppContainer app = context.getBean(AppContainer.class);
		System.out.println(app.contain("aa"));
	}
	
	
}
