package com.jossv.framework.web;

import org.springframework.web.context.support.XmlWebApplicationContext;

public class JossvXmlWebApplicationContext extends XmlWebApplicationContext {

	protected String[] getDefaultConfigLocations() {
		if (getNamespace() != null) {
			return new String[] { "classpath:" + getNamespace() + DEFAULT_CONFIG_LOCATION_SUFFIX };
		} else {
			return new String[] { "classpath:applicationContext.xml" };
		}
	}

}
