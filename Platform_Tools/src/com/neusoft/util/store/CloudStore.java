package com.neusoft.util.store;

import java.io.IOException;
import java.util.List;

import org.apache.solr.client.solrj.SolrServerException;

public interface CloudStore<T , PK> extends java.io.Serializable {
	/**
     * ����һ���¶������ ����һ������
     * @param object T
     * @return T
     */
    public void saveOrUpdateIObject(final T object)  throws IOException, SolrServerException ;

    /**
     * ��� PK ֵ�� primaryKey�� ��ݼ�¼
     * @param primaryKey PK
     * @return T
     */
    public T getIObjectByPK(final Class<?> iClass, PK primaryKey)  throws SolrServerException ;

    /**
     *
     * @param example T
     * @return List
     */
    public List<T> findAllByIObjectCType(final Class<?> iClass) throws IOException, SolrServerException ;

    /**
     *
     * @param example T
     * @param first int
     * @param max int
     * @return List
     */
    public List<T> findByIObjectCType(final Class<?> iClass, final int page,final int pageSize)  throws IOException, SolrServerException ;


    /**
     *
     * @param object T
     */
    public void updateIObject(final T object)  throws IOException, SolrServerException ;

    /**
     *
     * @param object T
     */
    public void deleteIObject(final T object)  throws IOException, SolrServerException ;
}
