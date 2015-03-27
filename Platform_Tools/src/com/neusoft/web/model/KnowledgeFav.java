package com.neusoft.web.model;

import java.util.Date;

import org.apache.solr.client.solrj.beans.Field;

import com.neusoft.core.EapDataContext;

public class KnowledgeFav implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5082871699739572274L;
	@Field
	private String id ;
	@Field
	private String datatype = EapDataContext.TypeCategoryEnum.FAV.toString();
	@Field
	private String dataid ;
	@Field
	private String kb ;		//知识库ID
	@Field
	private String kmtypecate;	//知识分类ID
	@Field
	private String creater ;	//收藏用户
	@Field
	private String createrdept ;//	收藏部门
	@Field
	private Date datetime;//	收藏时间
	@Field
	private String orgi ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	public String getKb() {
		return kb;
	}
	public void setKb(String kb) {
		this.kb = kb;
	}
	public String getKmtypecate() {
		return kmtypecate;
	}
	public void setKmtypecate(String kmtypecate) {
		this.kmtypecate = kmtypecate;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getCreaterdept() {
		return createrdept;
	}
	public void setCreaterdept(String createrdept) {
		this.createrdept = createrdept;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
}
