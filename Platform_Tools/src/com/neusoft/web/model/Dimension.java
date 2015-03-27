package com.neusoft.web.model;

import java.util.Date;
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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_dimension")
@org.hibernate.annotations.Proxy(lazy = false)
public class Dimension implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	private String name ;	//维度名称	
	private String code ;
	private String type ;	//类型：TimeDimension
	private String modeltype ;
	private String cubeid;
	private String orgi ;
	private String allmembername ;
	private String postop ;
	private Dimension dim ;
	private String posleft ;
	private int sortindex ;
	private String parameters;
	private String attribue;
	private Set<CubeLevel> cubeLevel ;
	private Date createtime = new Date();
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCubeid() {
		return cubeid;
	}
	public void setCubeid(String cubeid) {
		this.cubeid = cubeid;
	}
	public int getSortindex() {
		return sortindex;
	}
	public void setSortindex(int sortindex) {
		this.sortindex = sortindex;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
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
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "dimid")
	@OrderBy("sortindex")
	public Set<CubeLevel> getCubeLevel() {
		return cubeLevel;
	}
	public void setCubeLevel(Set<CubeLevel> cubeLevel) {
		this.cubeLevel = cubeLevel;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getModeltype() {
		return modeltype;
	}
	public void setModeltype(String modeltype) {
		this.modeltype = modeltype;
	}
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dim")
	public Dimension getDim() {
		return dim;
	}
	public void setDim(Dimension dim) {
		this.dim = dim;
	}
	public String getAllmembername() {
		return allmembername;
	}
	public void setAllmembername(String allmembername) {
		this.allmembername = allmembername;
	}
}
