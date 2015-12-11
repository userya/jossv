package com.jossv.framework.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jossv.framework.service.params.AutoParameter;
import com.jossv.framework.service.params.InputParameter;
import com.jossv.framework.service.params.OutputParameter;

public class JossServiceVO {

	private String id;
	
	private Boolean auth = true; 

	private String actions; // script first groovy;
	
	private String language = "groovy";
	
	private Map<String, JossServiceVO> refService = new HashMap<>();

	private List<InputParameter> inputParameters = new ArrayList<>();

	private List<OutputParameter> outputParameters = new ArrayList<>();

	private List<AutoParameter> autoParameters = new ArrayList<>();

	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Map<String, JossServiceVO> getRefService() {
		return refService;
	}

	public void setRefService(Map<String, JossServiceVO> refService) {
		this.refService = refService;
	}

	public List<InputParameter> getInputParameters() {
		return inputParameters;
	}

	public void setInputParameters(List<InputParameter> inputParameters) {
		this.inputParameters = inputParameters;
	}

	public List<OutputParameter> getOutputParameters() {
		return outputParameters;
	}

	public void setOutputParameters(List<OutputParameter> outputParameters) {
		this.outputParameters = outputParameters;
	}

	public List<AutoParameter> getAutoParameters() {
		return autoParameters;
	}

	public void setAutoParameters(List<AutoParameter> autoParameters) {
		this.autoParameters = autoParameters;
	}

	public String getActions() {
		return actions;
	}

	public void setActions(String actions) {
		this.actions = actions;
	}

	public Boolean getAuth() {
		return auth;
	}

	public void setAuth(Boolean auth) {
		this.auth = auth;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

}
