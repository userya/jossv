package com.jossv.system.model.table;

import com.jossv.framework.dao.annotation.Column;
import com.jossv.framework.dao.annotation.Table;

/**
 * Created by yangjiankang on 15/11/17.
 */
@Table(name = "t_app")
public class AppVO {

    private Long id;

    private String name;

    private String label;

    private String description;

    @Column(length = 2000)
    private String config; // jdbc:etc

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }
}
