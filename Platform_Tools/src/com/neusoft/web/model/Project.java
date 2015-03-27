package com.neusoft.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_project")
@org.hibernate.annotations.Proxy(lazy = false)
public class Project implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
    
	private String id;                   //主键
	private String name;                 //项目名称
	private String code;                 //代码
	private String contexturl;           //上下文路径
	private boolean multilang;           //是否支持多语言
	private String demo;                 //备注
	private String orgi;                 //租户
	private String packagename;
	private String dbid ;
	
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
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getContexturl() {
		return contexturl;
	}
	public void setContexturl(String contexturl) {
		this.contexturl = contexturl;
	}
	public boolean isMultilang() {
		return multilang;
	}
	public void setMultilang(boolean multilang) {
		this.multilang = multilang;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getPackagename() {
		return packagename;
	}
	public void setPackagename(String packagename) {
		this.packagename = packagename;
	}
	public String getDbid() {
		return dbid;
	}
	public void setDbid(String dbid) {
		this.dbid = dbid;
	}
}
