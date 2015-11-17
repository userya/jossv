package com.jossv.framework.web;

import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JossvDispatchServlet extends DispatcherServlet {

    private static final long serialVersionUID = -8940027065426811417L;


    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
        super.doService(request, response);
    }


}
