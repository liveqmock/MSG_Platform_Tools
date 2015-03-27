package com.neusoft.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

import com.neusoft.core.EapDataContext;

public class Knowledge implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 129051551440183921L;
	@Field
	private String id ;
	@Field
	private String title ;
	@Field
	private List<String> text = new ArrayList<String>();
	@Field
	private String datatype = EapDataContext.TypeCategoryEnum.STATUS.toString();
	@Field
	private Date datetime = new Date();
	@Field
	private String orgi ;
	@Field
	private String url ;
	@Field
	private String keyword ;
	@Field
	private String source ;
	@Field
	private Date validstart;
	@Field
	private Date validend ;
	@Field
	private String kmtypecate;
	@Field
	private String attachment ;
	@Field
	private String creater ;		//创建人
	@Field
	private String createrdept ;//	创建部门
	@Field
	private String content ;
	@Field
	private String dataid ;
	@Field
	private String status ; //	知识状态： 1:有效 ， 0:已删除 , 2:已过期 /停用
	@Field
	private String kb ;		//所属知识库
	
	@Field
	private String flowstats ;//流程状态 ， 0：已提交未发布， 1:已发布
	
	
	@Field
	private String version ;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getText() {
		return text;
	}
	public void setText(List<String> text) {
		this.text = text;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
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
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Date getValidstart() {
		return validstart;
	}
	public void setValidstart(Date validstart) {
		this.validstart = validstart;
	}
	public Date getValidend() {
		return validend;
	}
	public void setValidend(Date validend) {
		this.validend = validend;
	}
	public String getKmtypecate() {
		return kmtypecate;
	}
	public void setKmtypecate(String kmtypecate) {
		this.kmtypecate = kmtypecate;
	}
	public String getAttachment() {
		return attachment;
	}
	public void setAttachment(String attachment) {
		this.attachment = attachment;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDataid() {
		return dataid;
	}
	public void setDataid(String dataid) {
		this.dataid = dataid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFlowstats() {
		return flowstats;
	}
	public void setFlowstats(String flowstats) {
		this.flowstats = flowstats;
	}
	public String getKb() {
		return kb;
	}
	public void setKb(String kb) {
		this.kb = kb;
	}
}
