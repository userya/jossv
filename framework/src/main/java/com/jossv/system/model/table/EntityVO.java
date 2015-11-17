package com.jossv.system.model.table;

import com.jossv.framework.dao.annotation.Column;

/**
 * Created by yangjiankang on 15/11/17.
 */
@com.jossv.framework.dao.annotation.Table(name = "t_entity")
public class EntityVO {

    private Long id;

    @Column(length = 2000)
    private String content;

    private Long appId;

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
