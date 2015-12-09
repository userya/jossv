package com.jossv.framework.page;

import java.util.List;

import com.jossv.system.model.table.PageVO;

public class PageFactoryImpl implements PageFactory {
	
	private List<PageVO> pages;

	public PageFactoryImpl(List<PageVO> pages) {
		super();
		this.pages = pages;
	}
	
	public void init(){
		
	}

	public List<PageVO> getPages() {
		return pages;
	}

	public void setPages(List<PageVO> pages) {
		this.pages = pages;
	}

}
