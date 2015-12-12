package com.jossv.framework.page.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.xml.sax.InputSource;

import com.jossv.framework.page.PageRender;
import com.jossv.model.page.Page;

import freemarker.ext.dom.NodeModel;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PageRenderImpl implements PageRender {

	private Page page;

	private String layout;

	private String layoutHtml;
	
	private String pageHtml;

	private String translateMacro = "translate.ftl";
	
	private String htmlTemplate = "default.ftl";

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
		StringWriter out = new StringWriter();
		complieLayout(out);
		this.layoutHtml = out.toString();
		StringWriter out1 = new StringWriter();
		complieHtml(out1);
		pageHtml = out1.toString();
	}

	protected void complieLayout(Writer out) {
		Template tpl = FreemarkerUtils.getTemplate(translateMacro);
		Map<String, Object> data = new HashMap<String, Object>();
		try {
			InputSource is = new InputSource(IOUtils.toInputStream(layout, "utf-8"));
			data.put("doc", NodeModel.parse(is));
			tpl.process(data, out);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	protected void complieHtml(Writer out){
		Template tpl = FreemarkerUtils.getTemplate(htmlTemplate);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("layout", this.layoutHtml);
		data.put("config", page.getConfig());
		try {
			tpl.process(data, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	

	public String getLayoutHtml() {
		return layoutHtml;
	}

	public void setLayoutHtml(String layoutHtml) {
		this.layoutHtml = layoutHtml;
	}

	public String getPageHtml() {
		return pageHtml;
	}

	public void setPageHtml(String pageHtml) {
		this.pageHtml = pageHtml;
	}

}
