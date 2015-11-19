package com.jossv.framework.dao;

import java.util.List;

import com.jossv.framework.dao.sql.Condition;
import com.jossv.framework.dao.utils.DynamicValue;

public interface CommonDAO<T> {

	
	DynamicValue getValueUtils() ;
	
	int insert(T vo);

	int batchInsert(List<T> vos);

	int update(T vo);

	int updateIgnoreNull(T vo);

	int updateIgnoreEmpty(T vo);

	int update(T vo, T condition);

	int updateIgnoreNull(T vo, T condition);

	int updateIgnoreEmpty(T vo, T condition);

	int update(T vo, Condition cnd);

	int updateIgnoreNull(T vo, Condition cnd);

	int updateIgnoreEmpty(T vo, Condition cnd);

	int delete(Long id);

	int delete(Condition cnd);

	int delete(T cnd);

	T findById(Long id);

	T findByIdWithChild(Long id);

	List<T> query(Condition cnd);

	List<T> queryWithChildren(Condition cnd);

	<I> List<I> queryChildren(Condition mainCnd, String childAlias, Condition childCnd, Class<I> clazz);

}
