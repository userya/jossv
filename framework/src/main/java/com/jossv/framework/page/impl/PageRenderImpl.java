package com.jossv.framework.page.impl;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.commons.io.IOUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.jossv.framework.page.PageRender;
import com.jossv.model.page.Page;

import freemarker.ext.dom.NodeModel;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PageRenderImpl implements PageRender {

	private Page page;

	private String layout;

	private String template = "default";

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void getDefault(Writer out) throws Exception {
		Template tpl = FreemarkerUtils.getTemplate("default.ftl");
		Map<String, Object> data = new HashMap<String, Object>();
		InputSource is = new InputSource(IOUtils.toInputStream(layout, "utf-8"));
		data.put("pageModel", page);
		data.put("doc", NodeModel.parse(is));
		data.put("dataModel", page.getId());
		tpl.process(data, out);
	}

}
