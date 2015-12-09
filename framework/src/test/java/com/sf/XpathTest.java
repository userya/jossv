package com.sf;

import java.util.List;

import org.apache.commons.io.IOUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;

public class XpathTest {
	public static void main(String[] args) throws Exception {
		SAXBuilder builder = new SAXBuilder();
		Document document = builder.build(IOUtils.toInputStream("<html><head></head><body><div id='aa'></div><div id=''></div><div id=''></div></body></html>", "utf-8"));// 获得文档对象
		XPathExpression<Element> xpath = XPathFactory.instance().compile("/html/body/div", Filters.element());
		Element emt = xpath.evaluateFirst(document);
		if (emt != null) {
			System.out.println("XPath has result: " + emt.getAttributeValue("id"));
		}
		emt.setAttribute("attr1", "attrValue");
		emt.addContent(new Element("test").setAttribute("inside", "1"));
		List<Element> list = emt.getChildren();
		int index = 0;
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).equals(emt)) {
				index = i;
				break;
			}
		}
//		emt.getParentElement().removeContent(index);
		Document ddd = builder.build(IOUtils.toInputStream("<div id='etsss'>sssss</div>", "utf-8"));
		
		emt.getParentElement().addContent(index+1, ddd.getRootElement().detach());
		emt.getParentElement().addContent(index-1>0?index-1:0, new Element("test").setAttribute("before", "vv"));
		
		
		XMLOutputter outputter = new XMLOutputter();
		Format newFormat = Format.getPrettyFormat();
		newFormat.setEncoding("utf-8");
		newFormat.setIndent("  "); // 缩进
		newFormat.setTextMode(org.jdom2.output.Format.TextMode.NORMALIZE);
		outputter.setFormat(newFormat);
		String layoutStr = outputter.outputString(document.getRootElement());
		
		System.out.println(layoutStr);
	}
}
