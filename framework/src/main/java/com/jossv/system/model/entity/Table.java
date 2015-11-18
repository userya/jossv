package com.jossv.system.model.entity;

import java.util.ArrayList;
import java.util.List;

import com.jossv.framework.dao.annotation.Entity;
import com.jossv.framework.dao.annotation.Main;
import com.jossv.framework.dao.annotation.Relationship;
import com.jossv.framework.dao.annotation.RelationshipType;
import com.jossv.system.model.table.ColumnVO;
import com.jossv.system.model.table.TableVO;

@Entity
public class Table {
	
	@Main(alias = "t")
	private TableVO table ;
	
	@Relationship(alias = "cols", condition = "${cols.tableId} = ${t.id}", type = RelationshipType.child)
	private List<ColumnVO> columns = new ArrayList<>();

	public TableVO getTable() {
		return table;
	}

	public void setTable(TableVO table) {
		this.table = table;
	}

	public List<ColumnVO> getColumns() {
		return columns;
	}

	public void setColumns(List<ColumnVO> columns) {
		this.columns = columns;
	}
	
	
}
