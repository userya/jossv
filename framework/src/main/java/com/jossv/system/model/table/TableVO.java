package com.jossv.system.model.table;

/**
 * Created by yangjiankang on 15/11/17.
 */
@com.jossv.framework.dao.annotation.Table(name = "t_table")
public class TableVO extends com.jossv.framework.dao.model.Table {

	private Long stageId;

    public Long getStageId() {
		return stageId;
	}

	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}

}
