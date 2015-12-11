package com.jossv.reader.impl;

import java.util.HashMap;
import java.util.Map;

import com.jossv.model.config.Config;
import com.jossv.model.config.Property;

public class App {

	private String name;
	
	private Config config;
	
	private Map<String, Stage> stageMap = new HashMap<>();
	
	private Map<String, Stage> serviceMap = new HashMap<>(); //serviceid -> 

	public String getValue(String configName) {
		for (Property prop : config.getProperty()) {
			if(prop.getName().equals(configName)){
				return prop.getValue().trim();
			}
		}
		return null;
	}
	
	public Map<String, Stage> getStageMap() {
		return stageMap;
	}

	public void setStageMap(Map<String, Stage> stageMap) {
		this.stageMap = stageMap;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Config getConfig() {
		return config;
	}

	public void setConfig(Config config) {
		this.config = config;
	}

	public Map<String, Stage> getServiceMap() {
		return serviceMap;
	}

	public void setServiceMap(Map<String, Stage> serviceMap) {
		this.serviceMap = serviceMap;
	}

}
