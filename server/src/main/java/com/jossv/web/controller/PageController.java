package com.jossv.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/page/{pageId}")
    public void page(@PathVariable String pageId,
                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().append("hello world!!!!....");
    }

    @RequestMapping("/page/{pageId}/{serviceId}")
    public void page(@PathVariable String pageId, @PathVariable String serviceId,
                     HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.getWriter().append("hello world service....");
    }

}
