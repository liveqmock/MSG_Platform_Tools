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
@Table(name = "rivu_pageview")
@org.hibernate.annotations.Proxy(lazy = false)
public class PageView implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
    private String id;            //主键
    private String name;          //名称
    private String pagecode;      //页面代码
    private String datamodel ;	//数据模型ID
	private String incview ; 	//包含的页面模型类型
    private String orgi;          //租户
    private String typeid;        //分类ID
    private String demo;          //备注
    @Transient
    private List<String> incviews;
    
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
	
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getPagecode() {
		return pagecode;
	}
	public void setPagecode(String pagecode) {
		this.pagecode = pagecode;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
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
	@Transient
	public List<String> getIncviews() {
		return incviews;
	}
	@Transient
	public void setIncviews(List<String> incviews) {
		this.incviews = incviews;
	}
	
}
