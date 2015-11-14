package com.jossv.framework.dao;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameterValue;

import com.jossv.framework.dao.annotaion.factory.impl.ClassEntityFactory;
import com.jossv.framework.dao.model.DefineAble;
import com.jossv.framework.dao.model.Entity;
import com.jossv.framework.dao.model.Table;
import com.jossv.framework.dao.sql.Condition;
import com.jossv.framework.dao.sql.Delete;
import com.jossv.framework.dao.sql.ELValueVO;
import com.jossv.framework.dao.sql.Insert;
import com.jossv.framework.dao.sql.ParamUtils;
import com.jossv.framework.dao.sql.Select;
import com.jossv.framework.dao.sql.SqlPart;
import com.jossv.framework.dao.sql.TemplateUtils;
import com.jossv.framework.dao.sql.Update;
import com.jossv.framework.dao.sql.VirtualColumn;
import com.jossv.framework.dao.sql.Where;
import com.jossv.framework.dao.type.ColumnType;
import com.jossv.framework.dao.type.ColumnTypeUtils;
import com.jossv.framework.dao.type.JotClassRowMapper;
import com.jossv.framework.dao.type.TypeHandlerUtils;
import com.jossv.framework.dao.utils.EntitySqlBuilder;

public class CommonDAOImpl<T> implements CommonDAO<T> {

	private final static Logger logger = LoggerFactory.getLogger(CommonDAOImpl.class);
	
	private EntityFactory entityFactory;

	private DefineAble entityOrTable;

	private JdbcTemplate jdbcTemplate;

	private Class<?> mappedClass;

	public CommonDAOImpl(JdbcTemplate jdbcTemplate, EntityFactory entityFactory, DefineAble entityOrTable,
			Class<?> mappedClass) {
		super();
		this.entityOrTable = entityOrTable;
		this.jdbcTemplate = jdbcTemplate;
		this.mappedClass = mappedClass;
		this.entityFactory = entityFactory;
	}

	/**
	 * 处理 where
	 * 
	 * @param ps
	 * @param list
	 * @param where
	 */
	protected void processSqlParameterValue(Map<String, SqlParameterValue> ps, List<VirtualColumn> list,
			SqlPart sqlpart) {
		if (sqlpart != null) {
			Map<String, ELValueVO> pmap = sqlpart.getParameters();
			// 转换sql type value
			Map<String, VirtualColumn> columnMap = new HashMap<String, VirtualColumn>();
			for (int i = 0; i < list.size(); i++) {
				com.jossv.framework.dao.sql.VirtualColumn vc = list.get(i);
				columnMap.put(vc.getExp(), vc);
			}
			if (pmap != null) {
				for (String pname : pmap.keySet()) {
					ELValueVO v = pmap.get(pname);
					String exp = v.getEl();
					if (columnMap.containsKey(exp)) {
						VirtualColumn vc = columnMap.get(exp);
						Object val = v.getValue();
						ColumnType ct = ColumnType.valueOf(vc.getPhysicalColumn().getType());
						Integer sqlType = ColumnTypeUtils.getJdbcType(ct);
						SqlParameterValue spv = new SqlParameterValue(sqlType,
								TypeHandlerUtils.getTypeHandler(ct).getJdbcParameter(val));
						ps.put(pname, spv);
					}
				}
			}
		}
	}

	// ${id} = :id
	/**
	 * 
	 * @param sqlpart
	 * @param sqlParameters
	 *            SQL parameter 传入为空
	 * @return
	 */
	public String getSQL(SqlPart sqlpart, List<Object> sqlParameters) {
		StringBuilder builder = new StringBuilder();
		Map<String, Object> params = new HashMap<String, Object>();
		sqlpart.getSql(builder, params);
		String sql = TemplateUtils.complie(builder.toString(), params);
		Map<String, SqlParameterValue> ps = new HashMap<String, SqlParameterValue>();
		// 处理sql parameter
		processSqlParameterValue(ps, sqlpart.getVirtualColums(), sqlpart);
		sql = ParamUtils.complie(sql, ps, sqlParameters);
		logger.debug("sql:{}", sql);
		return sql;
	}

	protected String getPropertyName(Class<?> instance, String property) {
		Entity entity = entityFactory.getEntity(instance.getName());
		if (entity != null) {
			return ((ClassEntityFactory) entityFactory).getPropertyMap().get(instance.getName()).get(property);
		} else {
			Table table = entityFactory.getTableFactory().getTable(instance.getName());
			if (table != null) {
				return property;
			}
		}
		return null;
	}

	protected Object getValueByEl(T vo, String el) {
		if (vo instanceof BaseObject) {
			return ((BaseObject) vo).getValue(el);
		}
		Object r = null;
		Object toPut = vo;
		int fromIndex = 0;
		int index = el.indexOf('.');
		String name = el;
		try {
			while (index != -1) {
				String p = el.substring(fromIndex, index);
				String propertyName = getPropertyName(toPut.getClass(), p);
				if (propertyName == null) {
					throw new RuntimeException("can not found property named :" + p);
				}
				PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(toPut.getClass(), propertyName);
				Object o = pd.getReadMethod().invoke(toPut);
				fromIndex = index + 1;
				toPut = o;
				name = el.substring(fromIndex);
				index = el.indexOf('.', index + 1);
			}
			PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(toPut.getClass(), name);
			r = pd.getReadMethod().invoke(toPut);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return r;
	}

	public Condition buildCondition(T vo) {
		List<String> els = new ArrayList<String>();
		EntitySqlBuilder.getCndProperty(null, entityOrTable, els);
		Where where = new Where();
		for (String el : els) {
			Object v = getValueByEl(vo, el);
			if(v != null && !v.toString().trim().equals("")) {
				where.and(EntitySqlBuilder.buildPropertyCondition(el, v));
			}
		}
		return where.getCondition();
	}

	public int insert(T vo) {
		Insert insert = EntitySqlBuilder.buildInsert(entityOrTable);
		List<VirtualColumn> list = insert.getVirtualColums();
		for (VirtualColumn vc : list) {
			insert.setParameter(vc.getExp(), getValueByEl(vo, vc.getExp()));
		}
		List<Object> params = new ArrayList<Object>();
		String sql = getSQL(insert, params);
		return this.jdbcTemplate.update(sql, params.toArray(new Object[0]));
	}

	public int batchInsert(List<T> vos) {

		return 0;
	}
	
	public int update(T vo, T condition) {
		return update(vo, this.buildCondition(condition));
	}

	public int updateIgnoreNull(T vo, T condition) {
		return updateIgnoreNull(vo, this.buildCondition(condition));
	}

	public int updateIgnoreEmpty(T vo, T condition) {
		return updateIgnoreEmpty(vo, this.buildCondition(condition));
	}

	public int update(T vo) {
		return update(vo, UpdateGetPropertyType.full);
	}

	public int updateIgnoreNull(T vo) {
		return update(vo, UpdateGetPropertyType.ignoreNull);
	}

	public int updateIgnoreEmpty(T vo) {
		return update(vo, UpdateGetPropertyType.ignoreEmpty);
	}

	protected int update(T vo, UpdateGetPropertyType type) {
		String pk = EntitySqlBuilder.getPkEl(entityOrTable);
		Object value = this.getValueByEl(vo, pk);
		Condition pkCnd = EntitySqlBuilder.buildPropertyCondition(pk, value);
		return update(vo, pkCnd, type);
	}

	enum UpdateGetPropertyType {
		full, ignoreEmpty, ignoreNull
	}

	protected int update(T vo, Condition cnd, UpdateGetPropertyType type) {
		Update update = EntitySqlBuilder.buildUpdate(entityOrTable);
		update.getWhere().and(cnd);
		List<VirtualColumn> list = update.getVirtualColums();
		for (VirtualColumn vc : list) {
			Object v = getValueByEl(vo, vc.getExp());
			boolean ignore = false;
			if (type.equals(UpdateGetPropertyType.ignoreEmpty)) {
				if (v == null || v.toString().equals("")) {
					ignore = true;
				}
			} else if (type.equals(UpdateGetPropertyType.ignoreEmpty)) {
				if (v == null) {
					ignore = true;
				}
			}
			if (!ignore) {
				update.setParameter(vc.getExp(), v);
			} else {
				update.getIgnoreColumnEl().add(vc.getExp());
			}
		}
		List<Object> params = new ArrayList<Object>();
		String sql = getSQL(update, params);
		return this.jdbcTemplate.update(sql, params.toArray(new Object[0]));
	}

	public int update(T vo, Condition cnd) {
		return update(vo, cnd, UpdateGetPropertyType.full);
	}

	public int updateIgnoreNull(T vo, Condition cnd) {
		return update(vo, cnd, UpdateGetPropertyType.ignoreNull);
	}

	public int updateIgnoreEmpty(T vo, Condition cnd) {
		return update(vo, cnd, UpdateGetPropertyType.ignoreEmpty);
	}

	protected String getAlias() {
		String alias = null;
		if (entityOrTable instanceof Entity) {
			alias = ((Entity) entityOrTable).getMainAlias();
		}
		return alias;
	}

	

	public List<T> query(Condition cnd) {
		final Select select = EntitySqlBuilder.buildSelect(entityOrTable);
		if (cnd != null) {
			select.getWhere().and(cnd);
		}
		List<Object> params = new ArrayList<Object>();
		String sql = getSQL(select, params);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<T> list = this.jdbcTemplate.query(sql, params.toArray(new Object[0]),
				new JotClassRowMapper(entityFactory, this.mappedClass, select.getVirtualColums()));
		return list;
	}

	public int delete(T cnd) {
		return delete(this.buildCondition(cnd));
	}

	public int delete(Condition cnd) {
		Delete delete = EntitySqlBuilder.buildDelete(entityOrTable);
		if (cnd != null) {
			delete.getWhere().and(cnd);
		}
		List<Object> params = new ArrayList<Object>();
		String sql = getSQL(delete, params);
		return this.jdbcTemplate.update(sql, params.toArray(new Object[0]));
	}

	public int delete(Long id) {
		if(id == null) {
			throw new RuntimeException("pk can not be null");
		}
		String el = EntitySqlBuilder.getPkEl(entityOrTable);
		Condition c = EntitySqlBuilder.buildPropertyCondition(el, id);
		return delete(c);
	}

	public T findById(Long id) {
		if(id == null) {
			throw new RuntimeException("pk can not be null");
		}
		String el = EntitySqlBuilder.getPkEl(entityOrTable);
		Condition c = EntitySqlBuilder.buildPropertyCondition(el, id);
		List<T> list = this.query(c);
		return list.size() > 0 ? list.get(0) : null;
	}
	

}
