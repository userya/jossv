package com.jossv.framework.dao.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Where {

	private List<Condition> cnds = new ArrayList<Condition>();

	private List<AndOr> ops = new ArrayList<Where.AndOr>();

	enum AndOr {
		and, or
	}

	public Where and(Condition cnd) {
		if (cnd != null) {
			cnds.add(cnd);
			ops.add(AndOr.and);
			build();
		}
		return this;
	}
	
	public Where or(Condition cnd) {
		if (cnd != null) {
			cnds.add(cnd);
			ops.add(AndOr.or);
			build();
		}
		return this;
	}
	
	public Where(Condition cnd) {
		and(cnd);
	}
	
	public Where() {
		
	}
	
	private Condition condition;

	private void build(){
		StringBuilder sb = new StringBuilder();
		Map<String, ELValueVO> values = new HashMap<String, ELValueVO>();
		for (int i = 0; i < cnds.size(); i++) {
			AndOr andor = ops.get(i);
			Condition cnd = cnds.get(i);
			
			if(i != 0) {
				sb.append( andor.equals(AndOr.and) ? " and " : " or ");
			}
//			if(has) {
//				sb.append("(");
//			}
			sb.append(cnd.getExpression());
//			if(has) {
//				sb.append(")");
//			}
			values.putAll(cnd.getParameters()); // TODO 要做重名转换优化
		}
		
		if(sb.length() > 0){
			Condition c = new Condition(sb.toString());
			c.getParameters().putAll(values);
			condition = c;
		}
	}
	
	public Condition getCondition() {
		return condition;
	}

	

	public void getSqlPart(StringBuilder builder) {
		Condition condition = getCondition();
		if (condition != null) {
			builder.append(" where ");
			condition.getSqlPart(builder);
		}

	}

}
