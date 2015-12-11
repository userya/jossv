package com.jossv;

import java.util.HashMap;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jossv.framework.engine.AppContainer;

public class ContextTest {

	public static void main(String[] args) {
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
		AppContainer app = context.getBean(AppContainer.class);
		System.out.println(app.contain("admin"));
		app.getAppEngine("admin").processService("user", "test", new HashMap<>());
		app.getAppEngine("admin").processService("user", "test", new HashMap<>());
		app.getAppEngine("admin").processService("user", "test", new HashMap<>());
		String s = app.getAppEngine("admin").getPage("user", "test");
		System.out.println(s);
		context.close();
		
	}

}
