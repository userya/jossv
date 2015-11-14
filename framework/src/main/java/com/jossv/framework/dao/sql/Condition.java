package com.jossv.framework.dao.sql;

import java.util.HashMap;
import java.util.Map;

public class Condition {

	private String expression;

//	private Map<String, SqlParameterValue> sqlParameters = new HashMap<String, SqlParameterValue>();

	private Map<String, ELValueVO> parameters = new HashMap<String, ELValueVO>();
	
	/**
	 * @param parameterName pp
	 * @param el t.id
	 * @param value 1
	 * @return
	 */
	public Condition setParameter(String parameterName, String typeEl, Object value) {
		parameters.put(parameterName, new ELValueVO(typeEl, value));
		return this;
	}
	
	public Condition setParameter(String parameterName, Object value) {
		return setParameter(parameterName, parameterName, value);
	}

	public Condition() {
		super();
	}

	public Condition(String expression) {
		super();
		this.expression = expression;
	}

	public String getExpression() {
		return expression;
	}

	public void setExpression(String expression) {
		this.expression = expression;
	}

	public void getSqlPart(StringBuilder builder) {
		builder.append(expression);
	}

//	public Map<String, SqlParameterValue> getSqlParameters() {
//		return sqlParameters;
//	}

//	public void setSqlParameters(Map<String, SqlParameterValue> sqlParameters) {
//		this.sqlParameters = sqlParameters;
//	}

	public Map<String, ELValueVO> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, ELValueVO> parameters) {
		this.parameters = parameters;
	}



}
