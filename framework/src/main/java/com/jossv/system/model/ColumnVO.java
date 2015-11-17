package com.jossv.system.model;

import com.jossv.framework.dao.annotation.Table;

/**
 * Created by yangjiankang on 15/11/17.
 */
@Table(name = "t_table_column")
public class ColumnVO extends com.jossv.framework.dao.model.Column {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
