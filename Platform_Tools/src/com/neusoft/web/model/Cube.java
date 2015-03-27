package com.neusoft.web.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_cube")
@org.hibernate.annotations.Proxy(lazy = false)
public class Cube implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	private String name ;	
	private String code ;
	private String modeltype ;	//模型类型， 虚拟 立方体：立方体
	private String dstype ;	//db , r3
	private Database db;
	private String mposleft;	//指标位置
	private String mpostop ;	//指标位置
	private String typeid ;	
	private String orgi ;
	private Set<CubeMetadata> metadata = new HashSet<CubeMetadata>();
	private Set<Dimension> dimension = new HashSet<Dimension>();
	private Set<CubeMeasure> measure = new HashSet<CubeMeasure>();
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="db")
	public Database getDb() {
		return db;
	}
	public void setDb(Database db) {
		this.db = db;
	}
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name = "cubeid")
//	@OrderBy("sortindex")
	@Transient
	public Set<Dimension> getDimension() {
		return dimension;
	}
	public void setDimension(Set<Dimension> dimension) {
		this.dimension = dimension;
	}
	public String getMposleft() {
		return mposleft;
	}
	public void setMposleft(String mposleft) {
		this.mposleft = mposleft;
	}
	public String getMpostop() {
		return mpostop;
	}
	public void setMpostop(String mpostop) {
		this.mpostop = mpostop;
	}
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name = "cubeid")
//	@OrderBy("sortindex")
	@Transient
	public Set<CubeMeasure> getMeasure() {
		return measure;
	}
	public void setMeasure(Set<CubeMeasure> measure) {
		this.measure = measure;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
//	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	@JoinColumn(name = "cube")
	@Transient
	public Set<CubeMetadata> getMetadata() {
		return metadata;
	}
	public void setMetadata(Set<CubeMetadata> metadata) {
		this.metadata = metadata;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getDstype() {
		return dstype;
	}
	public void setDstype(String dstype) {
		this.dstype = dstype;
	}
	public String getModeltype() {
		return modeltype;
	}
	public void setModeltype(String modeltype) {
		this.modeltype = modeltype;
	}
}
