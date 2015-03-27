package com.neusoft.web.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_datatabletype")
@org.hibernate.annotations.Proxy(lazy = false)
public class DataTableType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
    
	private String id;        //主键ID
	private String name;      //类型名称
	private String code;	  //代码
	private String parentid;  //父节点Id
	private String orgi;      //租户
	private String demo;      //备注
	@Transient
	private List<DataTableView> viewlist;
	@Transient
	private List<DataTableType> typelist;
	
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
	@Transient
	public List<DataTableView> getViewlist() {
		return viewlist;
	}
	@Transient
	public void setViewlist(List<DataTableView> viewlist) {
		this.viewlist = viewlist;
	}
	@Transient
	public List<DataTableType> getTypelist() {
		return typelist;
	}
	@Transient
	public void setTypelist(List<DataTableType> typelist) {
		this.typelist = typelist;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
