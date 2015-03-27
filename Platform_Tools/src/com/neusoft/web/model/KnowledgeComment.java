package com.neusoft.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

import com.neusoft.core.EapDataContext;

public class KnowledgeComment implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2001032845693129464L;
	@Field
	private String id ;
	@Field
	private String datatype = EapDataContext.TypeCategoryEnum.COMMENT.toString();
	@Field
	private String dataid ;			//数据ID
	@Field	
	private String creater ;		//评论人	
	@Field
	private String createrdept ;	//评论部门
	@Field
	private String orgi ;			//租户ID
	@Field
	private String kb ;				//知识库ID
	@Field	
	private String kmtypecate;		//知识分类ID
	@Field
	private String content ;		//评论内容
	@Field
	private List<String> text = new ArrayList<String>();			//评论文本
	@Field
	private Date datetime ;			//评论时间
	@Field
	private int stars;				//评论得分（1星、2星、3星、4星、5星）
	
	
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
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<String> getText() {
		return text;
	}
	public void setText(List<String> text) {
		this.text = text;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	public int getStars() {
		return stars;
	}
	public void setStars(int stars) {
		this.stars = stars;
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
}
