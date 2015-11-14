package com.jossv.framework.dao.sql;

import java.util.Map;

/**
 * left join, inner join, right join
 * @author yangjiankang
 *
 */
public class JoinPart extends SelectPart {

	private JoinType joinType;
	private Condition condition;

	public JoinType getJoinType() {
		return joinType;
	}

	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}

	@Override
	public void getSqlPart(StringBuilder builder, Map<String, Object> params) {
		if(joinType.equals(JoinType.inner)) {
			
		}else if(joinType.equals(JoinType.left)) {
			builder.append(" left");
		}else if(joinType.equals(JoinType.right)) {
			builder.append(" right");
		}
		builder.append(" join ");
		super.getSqlPart(builder, params);
		builder.append(" on ");
		condition.getSqlPart(builder);
	}

	public Condition getCondition() {
		return condition;
	}

	public void setCondition(Condition condition) {
		this.condition = condition;
	}
	
	
	
}
