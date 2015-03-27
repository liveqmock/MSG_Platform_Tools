package com.neusoft.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_projecttypechild")
@org.hibernate.annotations.Proxy(lazy = false)
public class ProjectTypeChild implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;             //主键
	private String typeid;         //分类id
	private String viewtypeid;     //类型值
	
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
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getViewtypeid() {
		return viewtypeid;
	}
	public void setViewtypeid(String viewtypeid) {
		this.viewtypeid = viewtypeid;
	}
	
	
    
}
