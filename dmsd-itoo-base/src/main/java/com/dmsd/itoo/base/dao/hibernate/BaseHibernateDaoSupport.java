package com.dmsd.itoo.base.dao.hibernate;

import com.dmsd.itoo.base.entity.TimeEntity;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Repository
public abstract class BaseHibernateDaoSupport<T> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entityClass = (Class<T>) getSuperClassGenricType(this.getClass(), 0);

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    protected static Class<?> getSuperClassGenricType(Class<?> clazz, int index)
            throws IndexOutOfBoundsException {
        Type genType = clazz.getGenericSuperclass();

        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }

        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

        if (index >= params.length || index < 0) {
            return Object.class;
        }
        if (!(params[index] instanceof Class<?>)) {
            return Object.class;
        }
        return (Class<?>) params[index];
    }


    public void delete(T object) {
        getSession().delete(object);
    }


    public void delete(String... ids) {
        getSession().createQuery(
                new StringBuilder().append("delete ")
                        .append(entityClass.getSimpleName())
                        .append(" where id in (:ids)").toString())
                .setParameterList("ids", ids).executeUpdate();
    }

    public void save(T object) {
        getSession().save(object);
    }

    public void save(List<T> objects) {
        for (T obj : objects) {
            getSession().save(obj);
        }
    }

    public void saveOrUpdate(T object) {
        if (object instanceof TimeEntity) {
            TimeEntity timeModel = ((TimeEntity) object);
            if (timeModel.getId() == null || timeModel.getId().equals("")) { // 非创建对象
                timeModel.setUpdateTime(new Date());
            }
        }
        getSession().saveOrUpdate(object);
    }

    public void update(T object) {
        if (object instanceof TimeEntity) {
            ((TimeEntity) object).setUpdateTime(new Date());
        }
        getSession().update(object);
    }

    public T find(String id) {
        return (T) getSession().createCriteria(entityClass).add(
                Restrictions.idEq(id)).setCacheable(true).uniqueResult();
    }

    public List<T> queryAll() {
        return getSession().createCriteria(entityClass).setCacheable(true).list();
    }

    public T find(String hql, Map<String, Object> params) {
        Query query = getSession().createQuery(hql);
        setParams(query, params);
        return (T) query.setCacheable(true).uniqueResult();
    }

    public T find(String hql, Object... params) {
        Query query = getSession().createQuery(hql);
        setParams(query, params);
        return (T) query.setCacheable(true).uniqueResult();
    }

    public List<T> query(String hql, Map<String, Object> params) {
        Query query = getSession().createQuery(hql);
        setParams(query, params);
        return query.setCacheable(true).list();
    }

    public List<T> query(String hql, Object... params) {
        Query query = getSession().createQuery(hql);
        setParams(query, params);
        return query.setCacheable(true).list();
    }

    public void deleteAll() {
        getSession().createQuery("delete " + entityClass.getSimpleName())
                .executeUpdate();
    }

    public int execute(String hql, Object... params) {
        Query query = getSession().createQuery(hql);
        this.setParams(query, params);
        return query.executeUpdate();
    }

    public int execute(String hql, Map<String, Object> params) {
        Query query = getSession().createQuery(hql);
        this.setParams(query, params);
        return query.executeUpdate();
    }

    protected void setParams(Query query, Map<String, Object> params) {
        if (null != params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }

    protected void setParams(Query query, Object... params) {
        if (null != params) {
            for (int i = 0; i < params.length; i++) {
                //query.setParameter(i, params[i]); //From Foo where name = ? and id =?
                query.setParameter(String.valueOf(i), params[i]);//From Foo where name = ?0 and id =?1
            }
        }
    }

    public int getCountByCriteria(DetachedCriteria detachedCriteria) {
        Criteria criteria = detachedCriteria
                .getExecutableCriteria(getSession());
        criteria.setMaxResults(Integer.MAX_VALUE);
        int totalCount =( (Long)criteria.setProjection(
                Projections.rowCount()).uniqueResult()).intValue();
        return totalCount;
    }

    public List<T> getListByCriteria(DetachedCriteria detachedCriteria,
                                     int start, int size) {
        Criteria criteria = detachedCriteria
                .getExecutableCriteria(getSession()).setFirstResult(start)
                .setMaxResults(size);
        return (List<T>) criteria.setCacheable(true).list();
    }

}