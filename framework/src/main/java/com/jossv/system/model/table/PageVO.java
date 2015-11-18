package com.jossv.system.model.table;

/**
 * Created by yangjiankang on 15/11/17.
 */
@com.jossv.framework.dao.annotation.Table(name = "t_page")
public class PageVO {

    private Long id;

    private Long stageId;

    public Long getStageId() {
		return stageId;
	}

	public void setStageId(Long stageId) {
		this.stageId = stageId;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
