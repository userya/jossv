package com.jossv.framework.page.impl;

import java.io.IOException;

import com.jossv.framework.exception.BaseException;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerUtils {

	private static Configuration configuration = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);

	static {
		configuration.setClassForTemplateLoading(FreemarkerUtils.class, "template");
	}

	public static Template getTemplate(String name) {
		try {
			return configuration.getTemplate(name);
		} catch (IOException e) {
			throw new BaseException(e);
		}
	}
}
