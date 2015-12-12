package com.jossv.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jossv.framework.engine.AppContainer;
import com.jossv.framework.engine.AppEngine;

@Controller
public class PageController {

	private AppContainer appContainer;

	@RequestMapping("/{appId}/{stageId}/{pageId}")
	public void page(@PathVariable String appId, @PathVariable String stageId, @PathVariable String pageId,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		AppEngine engine = appContainer.getAppEngine(appId);
		if (engine == null) {
			throw new RuntimeException("404");
		}
		String html = engine.getPage(stageId, pageId);
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(html);
	}

	@RequestMapping("/{appId}/{stageId}/{pageId}/{serviceId}")
	public void page(@PathVariable String appId, @PathVariable String stageId, @PathVariable String pageId,
			@PathVariable String serviceId, HttpServletRequest request, HttpServletResponse response) throws Exception {

	}

	@Autowired
	public void setAppContainer(AppContainer appContainer) {
		this.appContainer = appContainer;
	}

}
