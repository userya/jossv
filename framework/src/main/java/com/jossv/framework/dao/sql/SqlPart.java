package com.jossv.framework.dao.sql;

import java.util.List;
import java.util.Map;

public interface SqlPart {

	/**
	 * 返回  insert into table(${id},name) values(:id, :name)
	 * @param builder
	 * @param params   el替换变量map
	 */
	void getSql(StringBuilder builder, Map<String, Object> params);
	
	List<VirtualColumn> getVirtualColums();

	Map<String, ELValueVO> getParameters();
	
//	Where getWhere();
	
	
}
