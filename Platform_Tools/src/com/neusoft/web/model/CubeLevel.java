package com.neusoft.web.model;

import java.util.Date;

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
@Table(name = "rivu_cubelevel")
@org.hibernate.annotations.Proxy(lazy = false)
public class CubeLevel implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	private String name ;	//维度名称	
	private String code ;
	private String formatstr ;
	private String columname;
	private boolean uniquemembers ;
	private String type ;	//类型：Numeric
	private String leveltype ;	// 类型 ， TimeMonths ： TimeWeeks ： TimeYears
	private String tablename ; 
	private String cubeid;
	private String orgi ;
	private String dimid;
	private int sortindex ;
	private boolean permissions = false;
	private String parameters;
	private String attribue;
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
	public String getColumname() {
		return columname;
	}
	public void setColumname(String columname) {
		this.columname = columname;
	}
	public boolean isUniquemembers() {
		return uniquemembers;
	}
	public void setUniquemembers(boolean uniquemembers) {
		this.uniquemembers = uniquemembers;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLeveltype() {
		return leveltype;
	}
	public void setLeveltype(String leveltype) {
		this.leveltype = leveltype;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getCubeid() {
		return cubeid;
	}
	public void setCubeid(String cubeid) {
		this.cubeid = cubeid;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public int getSortindex() {
		return sortindex;
	}
	public void setSortindex(int sortindex) {
		this.sortindex = sortindex;
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
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getDimid() {
		return dimid;
	}
	public void setDimid(String dimid) {
		this.dimid = dimid;
	}
	public boolean isPermissions() {
		return permissions;
	}
	public void setPermissions(boolean permissions) {
		this.permissions = permissions;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getFormatstr() {
		return formatstr;
	}
	public void setFormatstr(String formatstr) {
		this.formatstr = formatstr;
	}
}
