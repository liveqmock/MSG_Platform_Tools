package com.neusoft.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_cubemetadata")
@org.hibernate.annotations.Proxy(lazy = false)
public class CubeMetadata implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public String id ;
	private TableTask tb ;
	private String orgi ;
	private String name ;
	private String title ;
	private String createtime ;
	private String cube;
	private String postop;
	private String posleft ;
	private String mtype  ;
	private String parameters;
	private String attribue;
	private String namealias ;
	
	
	/**
	 * @return the id
	 */
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
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="tb")
	public TableTask getTb() {
		return tb;
	}
	public void setTb(TableTask tb) {
		this.tb = tb;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCube() {
		return cube;
	}
	public void setCube(String cube) {
		this.cube = cube;
	}
	public String getPostop() {
		return postop;
	}
	public void setPostop(String postop) {
		this.postop = postop;
	}
	public String getPosleft() {
		return posleft;
	}
	public void setPosleft(String posleft) {
		this.posleft = posleft;
	}
	public String getMtype() {
		return mtype;
	}
	public void setMtype(String mtype) {
		this.mtype = mtype;
	}
	public String getParameters() {
		return parameters;
	}
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public String getAttribue() {
		return attribue;
	}
	public void setAttribue(String attribue) {
		this.attribue = attribue;
	}
	public String getNamealias() {
		return namealias;
	}
	public void setNamealias(String namealias) {
		this.namealias = namealias;
	}
}
