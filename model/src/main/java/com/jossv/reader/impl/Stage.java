package com.jossv.reader.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jossv.model.data.Data;
import com.jossv.model.entity.Entity;
import com.jossv.model.page.Page;
import com.jossv.model.service.Service;
import com.jossv.model.table.Table;

public class Stage {

	private List<Data> datas = new ArrayList<>();

	private Map<String, Table> tables = new HashMap<>();

	private Map<String, Entity> entities = new HashMap<>();

	private Map<String, Service> services = new HashMap<>();

	private Map<String, Page> pages = new HashMap<>();

	private com.jossv.model.stage.Stage stage;

	public List<Data> getDatas() {
		return datas;
	}

	public void setDatas(List<Data> datas) {
		this.datas = datas;
	}

	public Map<String, Table> getTables() {
		return tables;
	}

	public void setTables(Map<String, Table> tables) {
		this.tables = tables;
	}

	public Map<String, Entity> getEntities() {
		return entities;
	}

	public void setEntities(Map<String, Entity> entities) {
		this.entities = entities;
	}

	public Map<String, Service> getServices() {
		return services;
	}

	public void setServices(Map<String, Service> services) {
		this.services = services;
	}

	public Map<String, Page> getPages() {
		return pages;
	}

	public void setPages(Map<String, Page> pages) {
		this.pages = pages;
	}

	public com.jossv.model.stage.Stage getStage() {
		return stage;
	}

	public void setStage(com.jossv.model.stage.Stage stage) {
		this.stage = stage;
	}

}
