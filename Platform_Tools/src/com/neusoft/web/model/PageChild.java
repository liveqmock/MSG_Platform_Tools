package com.neusoft.web.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_pagechild")
@org.hibernate.annotations.Proxy(lazy = false)
public class PageChild implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
    
	private String id;                   //主键
	private String name ;				 //页面名城
	private String incviewid;            //页面类型ID
	private String incviewname;          //页面类型名称
	private String pageviewid;           //页面视图ID
   	private String pageviewname;         //页面视图名称
	private String pageviewtext;         //页面内容
	private String defaulttemplate;      //默认模板ID
	private String orgi ;		
	
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
	public String getIncviewid() {
		return incviewid;
	}
	public void setIncviewid(String incviewid) {
		this.incviewid = incviewid;
	}
	public String getIncviewname() {
		return incviewname;
	}
	public void setIncviewname(String incviewname) {
		this.incviewname = incviewname;
	}
	public String getPageviewid() {
		return pageviewid;
	}
	public void setPageviewid(String pageviewid) {
		this.pageviewid = pageviewid;
	}
	public String getPageviewname() {
		return pageviewname;
	}
	public void setPageviewname(String pageviewname) {
		this.pageviewname = pageviewname;
	}
	public String getPageviewtext() {
		return pageviewtext;
	}
	public void setPageviewtext(String pageviewtext) {
		this.pageviewtext = pageviewtext;
	}
	public String getDefaulttemplate() {
		return defaulttemplate;
	}
	public void setDefaulttemplate(String defaulttemplate) {
		this.defaulttemplate = defaulttemplate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
}
