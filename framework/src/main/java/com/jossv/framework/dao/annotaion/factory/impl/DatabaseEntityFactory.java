package com.jossv.framework.dao.annotaion.factory.impl;

import com.alibaba.druid.pool.DruidDataSource;

public class DatabaseEntityFactory extends ClassEntityFactory {

	private DruidDataSource dataSource;
	
	@Override
	public void init() throws ClassNotFoundException {
		
		super.init();
	}

	public DruidDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DruidDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
