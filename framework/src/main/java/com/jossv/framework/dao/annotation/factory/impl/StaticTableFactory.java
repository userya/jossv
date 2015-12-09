package com.jossv.framework.dao.annotation.factory.impl;

import java.util.List;

import com.jossv.system.model.entity.Table;
import com.jossv.system.model.table.ColumnVO;
import com.jossv.system.model.table.TableVO;

public class StaticTableFactory extends ClassTableFactory {

	private List<Table> tables;

	public StaticTableFactory(List<Table> tables) {
		super();
		this.tables = tables;
	}

	@Override
	public void init() {
		if (tables == null) {
			return;
		}
		for (Table table : tables) {
			TableVO t = table.getTable();
			List<ColumnVO> list = table.getColumns();
			if (list != null) {
				for (ColumnVO columnVO : list) {
					t.getColumns().add(columnVO);
				}
			}
			tableMap.put(t.getId(), t);
		}
	}

}
