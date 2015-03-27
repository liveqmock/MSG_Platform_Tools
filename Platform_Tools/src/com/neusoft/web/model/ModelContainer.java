package com.neusoft.web.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "rivu_modelcontainer")
@Proxy(lazy = false)
public class ModelContainer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id ;
	private String name;
	private String title;
	private String divid;
	private String code ;
	private String iconstr;
	private String stylestr;
	private String formstyle;
	private boolean useform = false;
	private String formaction ;
	private String formasubmitype;
	private String formtoggle;
	private String formtarget ;
	private String fromdataaction ;
	private String reportid;
	private String modelcols;
	private String orgi ;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIconstr() {
		return iconstr;
	}
	public void setIconstr(String iconstr) {
		this.iconstr = iconstr;
	}
	public String getStylestr() {
		return stylestr;
	}
	public void setStylestr(String stylestr) {
		this.stylestr = stylestr;
	}
	public String getFormstyle() {
		return formstyle;
	}
	public void setFormstyle(String formstyle) {
		this.formstyle = formstyle;
	}
	public boolean isUseform() {
		return useform;
	}
	public void setUseform(boolean useform) {
		this.useform = useform;
	}
	public String getFormaction() {
		return formaction;
	}
	public void setFormaction(String formaction) {
		this.formaction = formaction;
	}
	public String getFormasubmitype() {
		return formasubmitype;
	}
	public void setFormasubmitype(String formasubmitype) {
		this.formasubmitype = formasubmitype;
	}
	public String getFormtoggle() {
		return formtoggle;
	}
	public void setFormtoggle(String formtoggle) {
		this.formtoggle = formtoggle;
	}
	public String getFromdataaction() {
		return fromdataaction;
	}
	public void setFromdataaction(String fromdataaction) {
		this.fromdataaction = fromdataaction;
	}
	public String getReportid() {
		return reportid;
	}
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	public String getModelcols() {
		return modelcols;
	}
	public void setModelcols(String modelcols) {
		this.modelcols = modelcols;
	}
	public String getDivid() {
		return divid;
	}
	public void setDivid(String divid) {
		this.divid = divid;
	}
	public String getFormtarget() {
		return formtarget;
	}
	public void setFormtarget(String formtarget) {
		this.formtarget = formtarget;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
}
