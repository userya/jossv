package com.jossv.framework.conf;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by yangjiankang on 15/11/15.
 */
public class AppConfig {

    private String configLocation;

    Map<String, String> propMap = new HashMap<>();

    public AppConfig(String configLocation) {
        this.configLocation = configLocation;
        init();

    }

    protected void init() {
        String path = "";

    }

    public String getConfigLocation() {
        return configLocation;
    }

    public void setConfigLocation(String configLocation) {
        this.configLocation = configLocation;
    }


}
