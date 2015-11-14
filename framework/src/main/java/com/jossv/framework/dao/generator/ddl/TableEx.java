package com.jossv.framework.dao.generator.ddl;

import java.util.List;

import org.hibernate.mapping.ForeignKey;
import org.hibernate.mapping.Table;

/**
 * 类名:TableEx
 * <p>功能说明：Hibernate Table 扩展</p>
 * @author  yangjk
 * @version 1.0
 */
public class TableEx extends Table {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3150283652873324740L;

	@SuppressWarnings("rawtypes")
	public ForeignKey createForeignKey(String keyName, List keyColumns, Table refTable, List referencedColumns) {
		ForeignKey fk = super.createForeignKey(keyName, keyColumns, refTable.getName(), referencedColumns);
		fk.setReferencedTable(refTable);
		return fk;
	}

}
