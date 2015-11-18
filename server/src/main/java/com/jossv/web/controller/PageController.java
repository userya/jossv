package com.jossv.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jossv.framework.engine.AppContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    private AppContainer appContainer;

    @RequestMapping("/p/{appId}/{pageId}")
    public void page(@PathVariable String appId, @PathVariable String pageId,
                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!appContainer.contain(appId)) {
            throw new RuntimeException("404");
        }
        appContainer.invokePage(request, response, appId, pageId);
    }

    @RequestMapping("/p/{appId}/{pageId}/{serviceId}")
    public void page(@PathVariable String appId, @PathVariable String pageId, @PathVariable String serviceId,
                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (!appContainer.contain(appId)) {
            throw new RuntimeException("404");
        }
        appContainer.invokePageService(request, response, appId, pageId, serviceId);
    }

    @Autowired
    public void setAppContainer(AppContainer appContainer) {
        this.appContainer = appContainer;
    }

}
