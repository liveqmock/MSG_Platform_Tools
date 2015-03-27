/*
 * $Id: IDaoManager.java 421119 2007-03-07 00:49:11Z jaddy $
 *
 *
 */

package com.neusoft.core.datasource.handler;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;



/**
 *
 * <p>Title: jaddy0302 Data Access Class</p>
 *
 * <p>Description: jaddy0302</p>
 *
 * <p>Copyright: jaddy Rivu , Copyright (c) 2010</p>
 *
 * <p>Company: jaddy0302</p>
 *
 * @author jaddy0302 date 2010-03-01
 * @version 1.0
 */
@SuppressWarnings({ "rawtypes", "unchecked" })
public class IDaoManager<T, P, PK extends Serializable> extends
        HibernateDaoSupport implements GeneraDAO<T, P, PK> {

    /**
     * <p>持久化业务逻辑数据对象 ，无返回值</p>
     */
    public void saveIObject(final T object) {
        getHibernateTemplate().save(object);
        getHibernateTemplate().flush();
        
    }

    /**
     * <p>持久化业务逻辑数据对象，新增或者更新 ，无返回值</p>
     * @param object T 业务逻辑数据对象
     */
    public void saveOrUpdateIObject(final T object) {
        getHibernateTemplate().saveOrUpdate(getHibernateTemplate().merge(object));
    }

    /**
     * <p>持久化操作， 删除数据库中的数据记录</p>
     * @param object T
     */
    public void deleteIObject(final T object) {
        getHibernateTemplate().delete(getHibernateTemplate().merge(object));
    }

    /**
     * <p>获得PK</p>
     * @param iClass Class
     * @param id PK
     * @return T
     */
    public T getIObjectByPK(final Class iClass, final PK iPK) {
        return getHibernateTemplate().merge((T) getHibernateTemplate().load(iClass, iPK));
    }

    /**
     * 更新 数据
     * @param object T
     */
    public void updateIObject(T object) {
        getHibernateTemplate().update(getHibernateTemplate().merge(object));
    }

    /**
     * 传入的数据对象类型查找数据
     * @param iClass Class
     * @return List
     */
    public List<T> findAllByIObjectCType(final Class iClass) {
        return getHibernateTemplate().find("from " + iClass.getName());
    }

    public List<T> findByIObjectCType(final Class iClass,final int page,final int pageSize) {
        return findPageByCriteria(DetachedCriteria.forClass(iClass),pageSize,page) ;
    }

    /**
     * 构造DetachedCriteria ， 带分页信息
     * @param detachedCriteria DetachedCriteria
     * @return DCriteriaPageSupport
     */
    public List<T> findPageByCriteria(final DetachedCriteria
            detachedCriteria) {
        return findPageByCriteria(detachedCriteria,
                                  DCriteriaPageSupport.I_PAGE_SIZE, 0);
    }

    /**
     * DetachedCriteria 带分页信息 ， 指定其实位置
     * @param detachedCriteria DetachedCriteria
     * @param startIndex int
     * @return DCriteriaPageSupport
     */
    public List<T> findPageByCriteria(final DetachedCriteria
            detachedCriteria, final int page) {
        return findPageByCriteria(detachedCriteria,
                                  DCriteriaPageSupport.I_PAGE_SIZE, page);
    }

    /**
     * DetachedCriteria 带分页信息 ，指定开始位置
     * @param detachedCriteria DetachedCriteria
     * @param pageSize int
     * @param startIndex int
     * @return DCriteriaPageSupport
     */
    public List<T> findPageByCriteria(final DetachedCriteria
            detachedCriteria, final int pageSize,
            final int page) {
        return findPageByCriteria(detachedCriteria , pageSize , (page>0)?(page-1)*pageSize:page*pageSize , true) ;
    }
    /**
     * sql语句删除数据
     */
    public int deleteBySql(String tablename,String where){
		Session session = getCurSession();
		Transaction tx = session.beginTransaction();
		int count = 0;
		try{
			String sqlDelete = "DELETE FROM "+ tablename +" WHERE "+ (where!=null?where:" 1=1 ");
			count = session.createSQLQuery(sqlDelete).executeUpdate();
			tx.commit();
		}catch(Exception e){
			tx.rollback();
		}
		return count;
    }
    /**
     * DetachedCriteria 带分页信息 ，指定开始位置
     * @param detachedCriteria DetachedCriteria
     * @param pageSize int
     * @param startIndex int
     * @return DCriteriaPageSupport
     */
    private List<T> findPageByCriteria(final DetachedCriteria
    		detachedCriteria, final int pageSize,
    		final int startIndex , boolean isPage) {
    	return (DCriteriaPageSupport) getHibernateTemplate().execute(new
    			HibernateCallback() {
    		public Object doInHibernate(Session session) throws
    		HibernateException {
    			Criteria criteria = detachedCriteria.getExecutableCriteria(
    					session);
    			int totalCount = (Integer) criteria.setProjection(Projections.
    					rowCount()).uniqueResult();
    			criteria.setProjection(null);
    			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY); 
    			DCriteriaPageSupport ps = new DCriteriaPageSupport(criteria.
    					setFirstResult(startIndex).setMaxResults(pageSize).list(),
    					totalCount, pageSize, startIndex);
    			return ps;
    		}
    	});
    }
    /**
     *
     *  detachedCriteria 查询
     * @param detachedCriteria DetachedCriteria
     * @return List
     */
    
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria) {
        return findAllByCriteria(detachedCriteria , null) ;
    }
	
	/**
    *
    *  detachedCriteria 查询
    * @param detachedCriteria DetachedCriteria
    * @return List
    */
   
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria , final Order order) {
       return findAllByCriteria(detachedCriteria , order , null );
   }
	
	/**
    *
    *  detachedCriteria 查询
    * @param detachedCriteria DetachedCriteria
    * @return List
    */
   
	public List<T> findAllByCriteria(final DetachedCriteria detachedCriteria , final Order order , final Projection projection) {
       return (DCriteriaPageSupport) getHibernateTemplate().executeFind(new HibernateCallback() {
           public Object doInHibernate(Session session) throws
                   HibernateException {
           	Criteria criteria = detachedCriteria.getExecutableCriteria(
   					session);
   			int totalCount = (Integer) criteria.setProjection(Projections.rowCount()).uniqueResult();
   			criteria.setProjection(projection);
   			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
   			if(order !=null ){
   				criteria.addOrder(order) ;
   			}
               return new DCriteriaPageSupport(criteria.list(),
   					totalCount, DCriteriaPageSupport.I_PAGE_SIZE, 0);
           }
       });
   }

    /**
     * detachedCriteria 查询所有记录数
     * @param detachedCriteria DetachedCriteria
     * @return int
     */
    public int getCountByCriteria(final DetachedCriteria detachedCriteria) {
        Integer count = (Integer) getHibernateTemplate().execute(new
                HibernateCallback() {
            public Object doInHibernate(Session session) throws
                    HibernateException {
                Criteria criteria = detachedCriteria.getExecutableCriteria(
                        session);
                return criteria.setProjection(Projections.rowCount()).
                        uniqueResult();
            }
        });
        return count;
    }
    /**
     * 
     * @param sql
     */
    public void updateByHQL(String hql){
    	getHibernateTemplate().bulkUpdate(hql) ;
    }
    /**
     * DetachedCriteria 带分页信息 ，指定开始位置
     * @param detachedCriteria DetachedCriteria
     * @param pageSize int
     * @param startIndex int
     * @return DCriteriaPageSupport
     */
    public List<T> hqlList(final String sql,final Class clazz, final int pageSize , final int page) {
      return (List) getHibernateTemplate().executeFind(new
          HibernateCallback() {
        public Object doInHibernate(Session session) throws
            HibernateException {
          Query query = session.createQuery(sql) ;
          query.setMaxResults(pageSize);
          query.setFirstResult((page>0)?(page-1)*pageSize:page*pageSize) ;
          Iterator iterator= query.list().iterator() ;
          List clazzList = new ArrayList() ;
          while(iterator.hasNext())
            clazzList.add(iterator.next()) ;
          return clazzList;
        }
      });
    }
    
    /**
     * 执行hql语句
     * @param hSQL
     * @return
     * @throws Exception
     */
    public int execByHQL(final String hSQL) throws Exception {
    	int n=0;
    	n=getHibernateTemplate().bulkUpdate(hSQL);
    	return n;
    }
    
    /**
     * 
     * @return
     */
    public Session getCurSession(){
    	
    	return getHibernateTemplate().getSessionFactory().getCurrentSession() ;
    }
    
    /**
     * 批量插入一批数据
     * @param list
     */
    public void saveBat(List<T> list,boolean isClose){
		Session session=getCurSession();
		int count=list.size();
		for(int i=0;i<count;i++){
			session.save(list.get(i));
			if(i>0&&i%100==0){
				session.flush();	
				session.clear();
			}
		}
		session.flush();
		if(isClose){
			session.close();
		}
	}
    
    /**
     * 不使用spring事务管理的批量插入
     * @param list
     */
    public void saveBat(List<T> list){
		Session session=getCurSession();
		Transaction ts=session.beginTransaction();
		int count=list.size();
		for(int i=0;i<count;i++){
			session.save(list.get(i));
			if(i>0&&i%100==0){
				session.flush();	
				session.clear();
			}
		}
		session.flush();
		ts.commit();
		session.close();
	}
}
