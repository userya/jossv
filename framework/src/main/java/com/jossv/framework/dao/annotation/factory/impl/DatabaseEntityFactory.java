package com.jossv.framework.dao.annotation.factory.impl;

import com.alibaba.druid.pool.DruidDataSource;

public class DatabaseEntityFactory extends ClassEntityFactory {

	private DruidDataSource dataSource;
	
	@Override
	public void init() {
		
		super.init();
	}

	public DruidDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DruidDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
