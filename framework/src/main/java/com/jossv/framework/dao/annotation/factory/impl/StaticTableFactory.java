package com.jossv.framework.dao.annotation.factory.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.jossv.model.table.Table;

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
			com.jossv.framework.dao.model.Table t = new com.jossv.framework.dao.model.Table();
			try {
				BeanUtils.copyProperties(t, table);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			t.getColumn().addAll(table.getColumn());
			tableMap.put(t.getId(), t);
		}
	}

}
