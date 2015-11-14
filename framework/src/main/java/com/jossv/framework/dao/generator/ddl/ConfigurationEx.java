package com.jossv.framework.dao.generator.ddl;

import java.util.Iterator;
import java.util.Set;

import org.hibernate.MappingException;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Mappings;
import org.hibernate.mapping.ForeignKey;
import org.hibernate.mapping.Table;

/**
 * 类名:ConfigurationEx
 * <p>功能说明：Hibernate Configuration 扩展</p>
 * @author  yangjk
 * @version 1.0
 */
@SuppressWarnings("serial")
public class ConfigurationEx extends Configuration {

	
	public Mappings createMappings() {

		return new MappingsImpl() {
			
			public Table addTable(String schema, String catalog, String name, String subselect, boolean isAbstract) {
				name = getObjectNameNormalizer().normalizeIdentifierQuoting(name);
				schema = getObjectNameNormalizer().normalizeIdentifierQuoting(schema);
				catalog = getObjectNameNormalizer().normalizeIdentifierQuoting(catalog);
				String key = subselect == null ? Table.qualify(catalog, schema, name) : subselect;
				Table table = tables.get(key);
				if (table == null) {
					table = new TableEx();
					table.setAbstract(isAbstract);
					table.setName(name);
					table.setSchema(schema);
					table.setCatalog(catalog);
					table.setSubselect(subselect);
					tables.put(key, table);
				} else {
					if (!isAbstract) {
						table.setAbstract(false);
					}
				}
				return table;
			}

		};
	}

	
	protected void secondPassCompileForeignKeys(Table table, Set<ForeignKey> done) throws MappingException {
		table.createForeignKeys();
		@SuppressWarnings("rawtypes")
		Iterator iter = table.getForeignKeyIterator();
		while (iter.hasNext()) {
			ForeignKey fk = (ForeignKey) iter.next();
			if (!done.contains(fk)) {
				done.add(fk);
//				fk.setReferencedTable(fk.getReferencedTable());
				fk.alignColumns();
			}
		}
	}
}
