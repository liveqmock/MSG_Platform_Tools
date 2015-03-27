package com.neusoft.core.datasource.handler;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;

public interface GeneraDAO<T, P, PK extends Serializable> extends IBaseDAO<T , P , PK>
{

    public List<T> findPageByCriteria(
            final DetachedCriteria detachedCriteria);

    /**
     * DetachedCriteria 带分页信息 ， 指定其实位置
     * @param detachedCriteria DetachedCriteria
     * @param startIndex int
     * @return DCriteriaPageSupport
     */
    public List<T> findPageByCriteria(
            final DetachedCriteria detachedCriteria, final int startIndex);
    
    /**
     * 执行hql语句
     * @param hSQL
     * @return
     * @throws Exception
     */
    public int execByHQL(final String hSQL) throws Exception ;
    /**
     * 直接执行sql语句批量删除数据
     * @param tablename
     * @param where
     * @return
     */
    public int deleteBySql(String tablename,String where);

    /**
     * DetachedCriteria 带分页信息 ，指定开始位置
     * @param detachedCriteria DetachedCriteria
     * @param pageSize int
     * @param startIndex int
     * @return DCriteriaPageSupport
     */
    public List<T> findPageByCriteria(
            final DetachedCriteria detachedCriteria, final int pageSize,
            final int startIndex);

    /**
     *
     *  detachedCriteria 查询
     * @param detachedCriteria DetachedCriteria
     * @return List
     */
    public List<T> findAllByCriteria(
            final DetachedCriteria detachedCriteria);
    
    /**
    *
    *  detachedCriteria 查询
    * @param detachedCriteria DetachedCriteria
    * @return List
    */
   public List<T> findAllByCriteria(
           final DetachedCriteria detachedCriteria , Order order);
   
   /**
   *
   *  detachedCriteria 查询
   * @param detachedCriteria DetachedCriteria
   * @return List
   */
  public List<T> findAllByCriteria(
          final DetachedCriteria detachedCriteria , Order order  ,  Projection projection);

    /**
     * detachedCriteria 查询所有记录数
     * @param detachedCriteria DetachedCriteria
     * @return int
     */
    public int getCountByCriteria(
            final DetachedCriteria detachedCriteria);
    /**
     * 批量插入一批数据
     * @param list
     */
    public void saveBat(List<T> list,boolean isClose);
    
    /**
     * 批量插入一批数据,方法内控制事务session使用完毕后关闭
     * @param list
     */
    public void saveBat(List<T> list);
    
    public List<T> hqlList(final String sql,final Class clazz, final int pageSize , final int page) ;
    
}
