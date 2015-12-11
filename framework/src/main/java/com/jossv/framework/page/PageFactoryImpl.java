package com.jossv.framework.page;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

import com.jossv.framework.page.impl.PageRenderImpl;
import com.jossv.model.page.Page;

public class PageFactoryImpl implements PageFactory {

	private List<Page> pages;

	private Map<String, PageRender> renderMap = new HashMap<>();

	public PageFactoryImpl(List<Page> pages) {
		super();
		this.pages = pages;
	}

	public void init() {
		if (pages != null) {
			for (Page page : pages) {
				PageRenderImpl render = new PageRenderImpl();
				render.setPage(page);

				renderMap.put(page.getId(), render);

				Element node = (Element) page.getLayout();
				TransformerFactory transFactory = TransformerFactory.newInstance();
				Transformer transformer;
				try {
					transformer = transFactory.newTransformer();
					StringWriter buffer = new StringWriter();
					transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
					transformer.transform(new DOMSource(node.getFirstChild()), new StreamResult(buffer));
					String str = buffer.toString();
					render.setLayout(str);
				} catch (TransformerConfigurationException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				} catch (TransformerException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}

			}
		}

	}

	public List<Page> getPages() {
		return pages;
	}

	public void setPages(List<Page> pages) {
		this.pages = pages;
	}

	public Map<String, PageRender> getRenderMap() {
		return renderMap;
	}

	public void setRenderMap(Map<String, PageRender> renderMap) {
		this.renderMap = renderMap;
	}

	@Override
	public PageRender getPageRender(String id) {
		return renderMap.get(id);
	}

}
