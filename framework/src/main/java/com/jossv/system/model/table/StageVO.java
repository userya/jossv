package com.jossv.system.model.table;

import com.jossv.framework.dao.annotation.Table;

@Table(name = "t_stage")
public class StageVO {
	
	private Long id;
	
	private Long appId;
	
	private String label;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getAppId() {
		return appId;
	}

	public void setAppId(Long appId) {
		this.appId = appId;
	}
	
	
}
