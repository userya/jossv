package com.jossv.framework.dao;

import java.util.List;

import com.jossv.framework.dao.sql.Condition;

public interface CommonDAO<T> {

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

	List<T> query(Condition cnd);

}
