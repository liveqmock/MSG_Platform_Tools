package com.neusoft.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_wsbusiness")
@org.hibernate.annotations.Proxy(lazy = false)
public class WsRule implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
    
	private String id;                  //主键
	private String name;                //名称
	private String code;                //代码
	private String demo;                //备注
	private String type;                //分类(1:分类  2:业务)
	private String parentid;            //上级ID
	private String wstype="rule";       //工程业务梳理-规则
	private String orgi;                //租户
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getWstype() {
		return wstype;
	}
	public void setWstype(String wstype) {
		this.wstype = wstype;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	
}
