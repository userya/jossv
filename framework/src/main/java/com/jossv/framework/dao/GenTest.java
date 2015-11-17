package com.jossv.framework.dao;

import java.io.FileInputStream;
import java.util.Properties;

import org.springframework.util.ResourceUtils;

import com.jossv.framework.dao.annotation.factory.impl.ClassTableFactory;
import com.jossv.framework.dao.generator.ddl.SchemaGenerator;

public class GenTest {

	
	public static void main(String[] args) throws Exception{
		ClassTableFactory f = new ClassTableFactory();
		f.setScanPackage("sample");
		f.init();
		Properties props = new Properties();
		props.load(new FileInputStream(ResourceUtils.getFile("classpath:jdbc.properties")));
		SchemaGenerator gen = new SchemaGenerator();
		gen.setUrl(props.getProperty("jdbc_url"));
		gen.setUsername(props.getProperty("jdbc_user"));
		gen.setPassword(props.getProperty("jdbc_password"));
		gen.setDriver(props.getProperty("jdbc_driverClassName"));
		gen.setDialect(props.getProperty("hibernate.dialect"));
		gen.create(f);
	}
}
