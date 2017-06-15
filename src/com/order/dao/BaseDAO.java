package com.order.dao;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.order.model.Page;
import com.order.model.UserInfo;


public abstract class BaseDAO {
	@Autowired
	private SessionFactory sessionFactory;

	protected BaseDAO() {
	}

	protected Session getCurrentSession() {
		if (sessionFactory != null) {
			return sessionFactory.getCurrentSession();
		}
		return null;
	}

	protected void saveOrUpdate(Object obj) {
		getCurrentSession().saveOrUpdate(obj);
	}

	protected void merge(Object obj) {
		getCurrentSession().merge(obj);
	}

	protected void delete(Object obj) {
		getCurrentSession().delete(obj);
	}

	protected Object findById(Integer id, Class clas) {
		return getCurrentSession().get(clas.getName(), id);
	}

	protected List queryAll(String queryStr, Map<String, Object> map) {
		Query query = getCurrentSession().createQuery(queryStr);
		if (map != null) {
			for (String key : map.keySet()) {
				query.setParameter(key, map.get(key));
			}
		}
		return query.list();
	}
	
	protected Object queryIdentity(String queryStr, Map<String, Object> map) {
		Query query = getCurrentSession().createQuery(queryStr);
		if(map!=null){
			for(String key:map.keySet())
				query.setParameter(key,map.get(key));
		}
		return query.uniqueResult();
	}
	
	protected List queryPage(String queryStr,Page page){
		Query query = getCurrentSession().createQuery(queryStr);
		if (page != null)
		{
			query.setFirstResult(page.getEveryPage() * (page.getCurrentPage() - 1));
			query.setMaxResults(page.getEveryPage());
		}
		return query.list();
	}
	
	protected Object count(String queryStr){
		Query query = getCurrentSession().createSQLQuery(queryStr);
		return query.uniqueResult() ;
	}
}
