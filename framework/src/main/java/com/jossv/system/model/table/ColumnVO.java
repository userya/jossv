package com.jossv.system.model.table;

import com.jossv.framework.dao.annotation.Table;

/**
 * Created by yangjiankang on 15/11/17.
 */
@Table(name = "t_table_column")
public class ColumnVO extends com.jossv.framework.dao.model.Column {

    private Long id;

    private Long tableId;

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
