package com.jossv.framework.dao.generator.ddl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.hibernate.cfg.Environment;
import org.hibernate.cfg.Mappings;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.PrimaryKey;
import org.hibernate.mapping.SimpleValue;
import org.hibernate.mapping.Table;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;

import com.jossv.framework.dao.TableFactory;
import com.jossv.framework.dao.type.ColumnType;
import com.jossv.framework.dao.type.ColumnTypeUtils;

/**
 * 
 * 类名:SchemaGenerator
 * <p>
 * 功能说明：创建表结构
 * </p>
 * 
 * @author yangjk
 * @version 1.0
 */
public class SchemaGenerator {

	private String dialect;
	private String url;
	private String driver;
	private String username;
	private String password;

	public SchemaGenerator() {
	}

	protected Table getTable(com.jossv.framework.dao.model.Table entity, Mappings mappings) {
		String schema = null;
		String catalog = null;
		String name = entity.getTableName();
		String subselect = null;
		boolean isAbstract = false;

		TableEx table = (TableEx) mappings.getTable(schema, catalog, name);
		if (table != null) {
			return table;
		}
		table = (TableEx) mappings.addTable(schema, catalog, name, subselect, isAbstract);
		PrimaryKey pk = new PrimaryKey();
		pk.setTable(table);
		Map<String, Column> propMap = new HashMap<String, Column>();
		for (com.jossv.model.table.Column c : entity.getColumn()) {
			Column column = new Column();
			column.setName(c.getColumnName() == null ? c.getName() : c.getColumnName());
			SimpleValue simpleValue = new SimpleValue(mappings, table);
			String htype = ColumnTypeUtils.getHibernateType(ColumnType.valueOf(c.getType().name()));
			simpleValue.setTypeName(htype);
			column.setValue(simpleValue);
			column.setNullable(c.isNullable());
			column.setLength(c.getLength());
			if (htype.toLowerCase().endsWith("decimal")) {
				column.setPrecision(c.getLength() <= 0 || c.getLength() == 255 ? 19 : c.getLength());
			} else {
				column.setPrecision(c.getLength());
			}
			column.setComment(c.getLabel());
			column.setScale(c.getScale());

			table.addColumn(column);
			/**
			 * 设置主键
			 */
			if (entity.getPkColumn() != null && entity.getPkColumn().equals(c.getName())) {
				pk.addColumn(column);
			}
			propMap.put(c.getColumnName(), column);
		}
		if (pk.getColumnSpan() > 0) {
			table.setPrimaryKey(pk);
		}
		return table;
	}

	protected String getUKName(String tableName, String refName) {
		String ukName = "uk_" + tableName + "_" + refName; // TODO
		// 长度限制
		if (ukName.length() >= 30) {
			ukName = "uk_" + new Random().nextInt(10000) + "_" + refName;
		}
		if (ukName.length() >= 30) {
			ukName = "uk_" + new Random().nextInt(10000);
		}
		return ukName;
	}

	/**
	 * 设定连接信息，表，comment, pk, uk, index, foreignKeys...
	 * 
	 * @param entity
	 */
	public void create(com.jossv.framework.dao.model.Table entity) {
		ConfigurationEx configuration = createConfigurationEx();
		Mappings mappings = configuration.createMappings();
		this.getTable(entity, mappings);
		SchemaUpdate update = new SchemaUpdate(configuration);
		update.execute(true, true);
	}

	protected ConfigurationEx createConfigurationEx() {

		ConfigurationEx configuration = new ConfigurationEx();
		configuration.setProperty(Environment.DIALECT, dialect);
		// configuration.setProperty(Environment.DEFAULT_SCHEMA, username);
		configuration.setProperty(Environment.URL, url);
		configuration.setProperty(Environment.DRIVER, driver);
		configuration.setProperty(Environment.USER, username);
		configuration.setProperty(Environment.PASS, password);

		return configuration;
	}

	public void create(TableFactory context) {

		Map<String, com.jossv.framework.dao.model.Table> maps = context.getTableMap();
		ConfigurationEx configuration = createConfigurationEx();

		Mappings mappings = configuration.createMappings();
		if (maps != null) {
			for (com.jossv.framework.dao.model.Table table : maps.values()) {
				if (table.isView() || table.isVirtual()) {
					continue;
				}
				this.getTable(table, mappings);
			}
		}
		SchemaUpdate update = new SchemaUpdate(configuration);
		update.execute(true, true);
	}

	public void dropAndcreate(TableFactory context) {
		Map<String, com.jossv.framework.dao.model.Table> maps = context.getTableMap();
		ConfigurationEx configuration = createConfigurationEx();
		Mappings mappings = configuration.createMappings();
		if (maps != null) {
			for (com.jossv.framework.dao.model.Table table : maps.values()) {
				if (table.isView() || table.isVirtual()) {
					continue;
				}
				this.getTable(table, mappings);
			}
		}
		SchemaUpdate update = new SchemaUpdate(configuration);
		SchemaExport se = new SchemaExport(configuration);
		se.drop(true, true);
		update.execute(true, true);
	}

	public String getDialect() {
		return dialect;
	}

	public String getUrl() {
		return url;
	}

	public String getDriver() {
		return driver;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public void setDialect(String dialect) {
		this.dialect = dialect;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
