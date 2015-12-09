package com.sf;

import java.io.File;
import java.io.StringWriter;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import freemarker.ext.dom.NodeModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import junit.framework.TestCase;

public class FreemarerTest extends TestCase {

	public void testA() throws Exception {
		Configuration c = new Configuration();
		URL path = FreemarerTest.class.getResource("conf");
		c.setDirectoryForTemplateLoading(new File(path.getFile()));
		Template tpl = c.getTemplate("page.ftl");
		StringWriter sw = new StringWriter();
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("header", new HeaderDirective());
		data.put("h", new InV());
		NodeModel nm = NodeModel.parse(new File(path.getFile().toString() + "/page.xml"));
		
		data.put("doc", nm);
		tpl.process(data, sw);
		System.out.println(sw.toString());
	}

}
