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
@Table(name = "rivu_projecttype")
@org.hibernate.annotations.Proxy(lazy = false)
public class ProjectType implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
    private String id;               //主键
    private String name;             //名称
    private String code;             //代码
    private String demo;             //备注
    private String viewtype;         //类型（模板，页面，url）
    private String viewtypeid;       //类型值
    private String orgi;             //租户
    private String type;             //标识(1:思维导图     2:分类)
    private String jsontext;         //json代码
    private String parentid;         //上级ID
    private String showtype;         //显示方式
    @Transient
    private List<String> idlist;     
    
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

	public String getJsontext() {
		return jsontext;
	}

	public void setJsontext(String jsontext) {
		this.jsontext = jsontext;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getViewtype() {
		return viewtype;
	}
	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}
	public String getViewtypeid() {
		return viewtypeid;
	}
	public void setViewtypeid(String viewtypeid) {
		this.viewtypeid = viewtypeid;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	@Transient
	public List<String> getIdlist() {
		return idlist;
	}
	@Transient
	public void setIdlist(List<String> idlist) {
		this.idlist = idlist;
	}

	public String getShowtype() {
		return showtype;
	}

	public void setShowtype(String showtype) {
		this.showtype = showtype;
	}
    
    
    
}
