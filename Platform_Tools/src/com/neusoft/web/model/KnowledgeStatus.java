package com.neusoft.web.model;

import org.apache.solr.client.solrj.beans.Field;

import com.neusoft.core.EapDataContext;

public class KnowledgeStatus {
	@Field
	private String id ;
	@Field
	private String datatype = EapDataContext.TypeCategoryEnum.STATUS.toString();
	@Field
	private String dataid ;
	@Field
	private String status ;
	@Field
	private String flowstatus ;
	@Field
	private String orgi ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getFlowstatus() {
		return flowstatus;
	}
	public void setFlowstatus(String flowstatus) {
		this.flowstatus = flowstatus;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getDatatype() {
		return datatype;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
}
