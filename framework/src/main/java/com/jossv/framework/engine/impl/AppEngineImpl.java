package com.jossv.framework.engine.impl;

import java.util.Map;

import com.jossv.framework.engine.AppEngine;
import com.jossv.framework.page.PageRender;
import com.jossv.model.service.Service;

/**
 * Created by yangjiankang on 15/11/17.
 */
public class AppEngineImpl extends AppResource implements AppEngine {

	@Override
	public void processService(String stageId, String serviceId, Map<String, Object> parameters) {
		Service service = this.getApp().getStageMap().get(stageId).getServices().get(serviceId);
		String id = service.getId();

		this.serviceFactory.findById(id).invoke(id, parameters);
	}

	@Override
	public String getPage(String stageId, String pageId) {
		PageRender render = this.pageFactory.getPageRender(pageId);
		return render.getPageHtml();
	}

}
