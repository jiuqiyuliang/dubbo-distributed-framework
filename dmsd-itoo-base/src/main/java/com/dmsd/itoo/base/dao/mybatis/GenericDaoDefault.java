package com.dmsd.itoo.base.dao.mybatis;

import com.dmsd.itoo.base.dao.changedb.GenericUtils;
import com.dmsd.itoo.base.entity.IdEntity;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.spring.SqlSessionUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;


import java.io.Serializable;
import java.util.*;

/**
 * Created by LiuYing on 2016/7/3.
 */
public class GenericDaoDefault<E extends IdEntity> extends SqlSessionDaoSupport implements IGenericDao<E> {

    protected Class<?> entityClass;

    public GenericDaoDefault() {
        this.entityClass = GenericUtils.getGenericClass(getClass());
    }


    @Override
    public int deleteByPrimaryKey(String id) {
        return super.getSqlSession().insert(getStatementId("deleteByPrimaryKey"), id);
    }

    @Override
    public int insertSelective(E entity) {
        return super.getSqlSession().insert(getStatementId("insertSelective"), entity);
    }

    @Override
    public E selectByPrimaryKey(String id) {
        return super.getSqlSession().selectOne(getStatementId("selectByPrimaryKey"), id);
    }

    @Override
    public int updateByPrimaryKeySelective(E entity) {
        return super.getSqlSession().update(getStatementId("updateByPrimaryKeySelective"), entity);
    }

    @Override
    public int updateByPrimaryKey(E entity) {
        return super.getSqlSession().update(getStatementId("updateByPrimaryKey"), entity);
    }

    // TODO: 2016/7/7 已用
    @Override
    public int insert(E entity) {
        return super.getSqlSession().insert(getStatementId("insert"), entity);
    }


    // TODO: 2016/7/7
    @Override
    public void delete(String sql, Object... args) {

        if (args == null || args.length == 0) {
            super.getSqlSession().delete(getStatementId(sql));
        } else if (args.length == 1) {
            super.getSqlSession().delete(getStatementId(sql), args[0]);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < args.length; i++)
                map.put("" + i, args[i]);
            super.getSqlSession().delete(getStatementId(sql), map);
        }
    }

    // TODO: 2016/7/7  
    @Override
    public void delete(E entity) {
        deleteByPrimaryKey(entity.getId());
    }

    // TODO: 2016/7/7  
    @Override
    public List<E> getAll() {
        return super.getSqlSession().selectList(getStatementId("getAll"));
    }

    // TODO: 2016/7/7  已用
    @Override
    public E get(Serializable id) {
        return super.getSqlSession().selectOne(getStatementId("get"), id);
    }

    // TODO: 2016/7/7  
    @Override
    public List<Object> query(String sql, Object... args) {

        List<Object> result = null;
        if (args == null || args.length == 0) {
            result = super.getSqlSession().selectList(getStatementId(sql));
        } else if (args.length == 1) {
            result = super.getSqlSession().selectList(getStatementId(sql),
                    args[0]);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < args.length; i++)
                map.put("" + i, args[i]);
            result = super.getSqlSession().selectList(getStatementId(sql), map);
        }
        return result != null ? result : new ArrayList<Object>();
    }

    // TODO: 2016/7/7  
    @Override
    public Object queryOne(String sql, Object... args) {

        Object ob = null;
        if (args == null || args.length == 0) { // 鏃犲姩鎬佸弬鏁�
            ob = super.getSqlSession().selectOne(getStatementId(sql));
        } else if (args.length == 1) { // 1涓姩鎬佸弬鏁�
            ob = super.getSqlSession().selectOne(getStatementId(sql), args[0]);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < args.length; i++)
                map.put("" + i, args[i]);
            ob = super.getSqlSession().selectOne(getStatementId(sql), map);
        }
        return ob;
    }


    // TODO: 2016/7/7  
    @Override
    public void update(String sql, Object... args) {
        if (args == null || args.length == 0) {
            super.getSqlSession().update(getStatementId(sql));
        } else if (args.length == 1) {
            super.getSqlSession().update(getStatementId(sql), args[0]);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < args.length; i++) {
                map.put("" + i, args[i]);
            }
            super.getSqlSession().update(getStatementId(sql), map);
        }
    }


    // TODO: 2016/7/7  
    @Override
    public Map<String, Object> getMap(String sql, Object... args) {
        Map<String, Object> result = null;
        if (args == null || args.length == 0) {
            result = super.getSqlSession().selectOne(getStatementId(sql));
        } else if (args.length == 1) {
            result = super.getSqlSession().selectOne(getStatementId(sql), args[0]);
        } else {
            Map<String, Object> map = new HashMap<String, Object>();
            for (int i = 0; i < args.length; i++) {
                map.put("" + i, args[i]);
            }
            result = super.getSqlSession().selectOne(getStatementId(sql), map);
        }
        return result;
    }


    // TODO: 2016/7/7  已用
    @Override
    public void batchUpdate(List<E> entities) {
        SqlSession sqlSession = getBatchSession();
        for (IdEntity e : entities)
            sqlSession.update(getStatementId("update"), e);
        sqlSession.commit();
    }

    @Override
    public void batchUpdate(List<String> ids, E e) {
        SqlSession sqlSession = getBatchSession();
        for (String id : ids) {
            e.setId(id);
            sqlSession.delete(getStatementId("updateByPrimaryKeySelective"), e);
        }
        sqlSession.commit();
    }


    // TODO: 2016/7/7
    @Override
    public void batchInsert(String sql, List<E> entities) {

        SqlSession sqlSession = getBatchSession();
        for (IdEntity e : entities)
            sqlSession.insert(getStatementId(sql), e);
        sqlSession.commit();
    }

    // TODO: 2016/7/11 Test Successed
    @Override
    public void batchInsert(List<E> entities) {
        SqlSession sqlSession = getBatchSession();
        for (IdEntity e : entities)
            sqlSession.insert(getStatementId("insert"), e);
        sqlSession.commit();
    }

    // TODO: 2016/7/7  
    @Override
    public void batchDelete(List<E> entities) {
        SqlSession sqlSession = getBatchSession();
        for (IdEntity e : entities)
            sqlSession.delete(getStatementId("deleteByPrimaryKey"), e.getId());
        sqlSession.commit();
    }

    // TODO: 2016/7/7 Test Succeed
    @Override
    public void batchDeleteByIds(List<String> ids) {

        SqlSession sqlSession = getBatchSession();
        for (Serializable id : ids)
            sqlSession.delete(getStatementId("deleteByPrimaryKey"), id);
        sqlSession.commit();
    }


    // TODO: 2016/7/7  

    /**
     * @return
     */
    private SqlSession getBatchSession() {
        return SqlSessionUtils.getSqlSession(new SqlSessionFactoryBuilder().build(super.getSqlSession().getConfiguration()), ExecutorType.BATCH, null);
    }


    // TODO: 2016/7/7
    public String getStatementId(String postfix) {
        return new StringBuffer().append(this.entityClass.getSimpleName())
                .append("Mapper.").append(postfix).toString();
    }

}



