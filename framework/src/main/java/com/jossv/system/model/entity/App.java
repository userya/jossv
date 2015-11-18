package com.jossv.system.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.jossv.framework.dao.annotation.Entity;
import com.jossv.framework.dao.annotation.Main;
import com.jossv.framework.dao.annotation.Relationship;
import com.jossv.framework.dao.annotation.RelationshipType;
import com.jossv.system.model.table.AppVO;

@Entity
public class App {

	@Main(alias = "a")
	private AppVO app;

	@Relationship(alias = "stage", condition = "${stage.s.appId} = ${a.id}", type = RelationshipType.child)
	private List<Stage> stage = new ArrayList<>();

	public AppVO getApp() {
		return app;
	}

	public void setApp(AppVO app) {
		this.app = app;
	}

	public List<Stage> getStage() {
		return stage;
	}

	public void setStage(List<Stage> stage) {
		this.stage = stage;
	}

}
