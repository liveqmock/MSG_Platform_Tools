package com.neusoft.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_pagetype")
@org.hibernate.annotations.Proxy(lazy = false)
public class PageType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;        //主键ID
	private String name;      //类型名称
	private String parentid;  //父节点Id
	private String datamodel ;	//数据模型ID
	private String incview ; 	//包含的页面模型类型
	private String orgi;      //租户
	private String demo;      //备注
	
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getDatamodel() {
		return datamodel;
	}
	public void setDatamodel(String datamodel) {
		this.datamodel = datamodel;
	}
	public String getIncview() {
		return incview;
	}
	public void setIncview(String incview) {
		this.incview = incview;
	}
}
