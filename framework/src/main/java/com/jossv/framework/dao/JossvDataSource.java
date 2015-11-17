package com.jossv.framework.dao;

import com.alibaba.druid.pool.DruidDataSource;

import java.sql.SQLException;

/**
 * Created by yangjiankang on 15/11/15.
 */
public class JossvDataSource extends DruidDataSource {

    private String configLocation;


    @Override
    public void init() throws SQLException {


        super.init();
    }

    public String getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }
}
