package com.jossv.framework.engine;

import java.io.Writer;

import com.jossv.system.model.entity.Stage;

public class StageEngineImpl implements StageEngine {

	private Stage stage;
	
	private AppEngine AppEngine;

	@Override
	public void getTemplate(String pageId, Writer out) {
		
		
	}
	

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public AppEngine getAppEngine() {
		return AppEngine;
	}

	public void setAppEngine(AppEngine appEngine) {
		AppEngine = appEngine;
	}
	
	
	
	
}
