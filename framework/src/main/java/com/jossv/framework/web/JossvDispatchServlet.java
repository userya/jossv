package com.jossv.framework.web;

import java.io.File;
import java.net.MalformedURLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.DispatcherServlet;

import com.jossv.framework.clazz.JossvClassLoader;

public class JossvDispatchServlet extends DispatcherServlet {

	private static final long serialVersionUID = -8940027065426811417L;

	private static final String DEBUG_CLASS_LOCATION = "debug-class-location";

	protected String getFullName() {
		String classPath = this.getInitParameter(DEBUG_CLASS_LOCATION);
		String fname = JossvDispatchServlet.class.getClassLoader().getResource(".").getFile();
		String fullName = FilenameUtils.normalize(fname + classPath);
		return fullName;
	}

	protected ClassLoader createClassLoader() {
		String classPath = this.getInitParameter(DEBUG_CLASS_LOCATION);
		if (StringUtils.hasText(classPath)) {
			String fname = JossvDispatchServlet.class.getClassLoader().getResource(".").getFile();
			String fullName = FilenameUtils.normalize(fname + classPath);
			JossvClassLoader loader = null;
			try {
				loader = new JossvClassLoader(new File(fullName).toURI().toURL());
			} catch (MalformedURLException e) {
				e.printStackTrace();
				logger.error("can not found file :" + fullName + ", use default product class loader....");
				return ClassUtils.getDefaultClassLoader();
			}
			return loader;
		} else {
			return ClassUtils.getDefaultClassLoader();
		}
	}

	protected void refreshContext() {
		JossvXmlWebApplicationContext c = (JossvXmlWebApplicationContext) this.getWebApplicationContext();
		ClassPathXmlApplicationContext p = (ClassPathXmlApplicationContext) c.getParent();

		ClassLoader loader = createClassLoader();
		p.setClassLoader(loader);
		c.setClassLoader(loader);
		p.refresh();
		c.refresh();
		needRefresh = false;
	}

	private boolean needRefresh = false;

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (needRefresh) {
			refreshContext();
		}
		super.doService(request, response);
	}
	
	protected ApplicationContext createServerApplicationContext(ApplicationContext parent, ClassLoader classLoader) {

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
		context.setParent(parent);
		context.setClassLoader(classLoader);
		context.setConfigLocation("classpath:" + getServletName() + "-context.xml");
		context.refresh();

		return context;
	}

}
