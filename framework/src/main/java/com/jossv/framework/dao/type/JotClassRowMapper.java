package com.jossv.framework.dao.type;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.RowMapper;

import com.jossv.framework.dao.sql.VirtualColumn;
import com.jossv.framework.dao.utils.DynamicValue;

public class JotClassRowMapper<T> implements RowMapper<T> {

	private DynamicValue dynamicValue;

	private List<VirtualColumn> columns;

	public JotClassRowMapper(DynamicValue dynamicValue, List<VirtualColumn> columns) {
		super();
		this.columns = columns;
		this.dynamicValue = dynamicValue;
	}

	public T mapRow(ResultSet rs, int rowNum) throws SQLException {
		@SuppressWarnings("unchecked")
		T mappedObject = (T) BeanUtils.instantiate(dynamicValue.getMapperClass());
		try {
			for (int i = 0; i < columns.size(); i++) {
				VirtualColumn vc = columns.get(i);
				String columnName = vc.getAliasName();
				String type = vc.getPhysicalColumn().getType();
				TypeHandler handler = TypeHandlerUtils.getTypeHandler(ColumnType.valueOf(type));
				String el = vc.getExp();
				Object value = handler.getValue(rs, columnName);
				dynamicValue.setValue(mappedObject, el, value);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return mappedObject;
	}

}
