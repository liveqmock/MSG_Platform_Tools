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
@Table(name = "rivu_cubemeasure")
@org.hibernate.annotations.Proxy(lazy = false)
public class CubeMeasure implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	private String name ;	//维度名称	
	private String code ;	//代码
	private String modeltype ;	//虚拟维度
	private CubeMeasure measure ;
	private String columname;
	private boolean uniquemembers ;
	private String type ;	//类型：Numeric
	private String aggregator = "sum";	// 聚合方式：sum ， distinctcount , count
	private boolean calculatedmember = false ;
	private String formatstring ;
	private String tablename ; 
	private String cubeid;
	private String orgi ;
	private String mid;
	private int sortindex ;
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
	public String getAggregator() {
		return aggregator;
	}
	public void setAggregator(String aggregator) {
		this.aggregator = aggregator;
	}
	public String getFormatstring() {
		return formatstring;
	}
	public void setFormatstring(String formatstring) {
		this.formatstring = formatstring;
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
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public boolean isCalculatedmember() {
		return calculatedmember;
	}
	public void setCalculatedmember(boolean calculatedmember) {
		this.calculatedmember = calculatedmember;
	}
	public String getModeltype() {
		return modeltype;
	}
	public void setModeltype(String modeltype) {
		this.modeltype = modeltype;
	}
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="measure")
	public CubeMeasure getMeasure() {
		return measure;
	}
	public void setMeasure(CubeMeasure measure) {
		this.measure = measure;
	}
}
