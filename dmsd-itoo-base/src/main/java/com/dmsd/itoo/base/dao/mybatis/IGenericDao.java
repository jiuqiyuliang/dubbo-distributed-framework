package com.dmsd.itoo.base.dao.mybatis;



import com.dmsd.itoo.base.entity.IdEntity;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface IGenericDao<E extends IdEntity> {

	// TODO: 2016/7/11 Test Succeed
	int deleteByPrimaryKey(String id);


	// TODO: 2016/7/11 Test Succeed
	int insertSelective(E entity);

	// TODO: 2016/7/11 Test Succeed
	E selectByPrimaryKey(String id);

	// TODO: 2016/7/11 Test Succeed
	int updateByPrimaryKeySelective(E entity);


	//// TODO: 2016/7/11 Test Succeed
	int updateByPrimaryKey(E entity);


	// TODO: 2016/7/11 Test Succeed
	 int insert(E entity);



	/**
	 * 
	 * @param sql
	 * @param paramArrayOfObj
	 */
	 void delete(String sql, Object... paramArrayOfObj);

	// TODO: 2016/7/11 Test Succeed
	 void delete(E entity);

	/**
	 * 
	 * @return
	 */
	public List<E> getAll();

	
	public E get(Serializable paramSerializable);

	
	public List<Object> query(String sql, Object... paramArrayOfObj);

	/**
	 * 
	 * @param sql
	 * @param paramArrayOfObj
	 * @return
	 */
	public Object queryOne(String sql, Object... paramArrayOfObj);

	public void update(String sql, Object... paramArrayOfObject);

	public Map<String,Object> getMap(String sql, Object... paramArrayOfObj);

	public void batchUpdate(List<E> entities);


	// TODO: 2016/7/11 Test Succeed

	/**
	 * 根据主键批量更新实体
	 * @param ids 需要更新实体的id的集合
	 * @param e   e用于存储需要变更的字段，只有需要变更的字段赋值。
     */
	public void batchUpdate(List<String> ids, E e);

	public void batchInsert(String sql, List<E> entities);


	// TODO: 2016/7/11 Test Succeed
	public void batchInsert(List<E> entities);

	// TODO: 2016/7/11 Test Succeed
	public void batchDelete(List<E> entities);

	// TODO: 2016/7/11 Test Succeed
	 void batchDeleteByIds(List<String> paramList);

	public String getStatementId(String postfix);
}
