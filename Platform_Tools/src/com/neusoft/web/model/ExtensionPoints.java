package com.neusoft.web.model;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.neusoft.tools.ParamTools;

/**
 * @author jaddy0302 Rivulet ExtensionPoints.java 2010-3-13
 * 
 */
@Entity
@Table(name = "rivu_extensionpoints")
@org.hibernate.annotations.Proxy(lazy = false)
public class ExtensionPoints implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1514924365597110814L;
	private String id ;
	private String name ;
	private String clazz ;
	private Object plugin ;
	private String dscription ;
	private String userid ;
	private String groupid ;
	private String interfaceclazz ;
	private String extensiontype ;
	private String parameters ;
	private String iconimagepath ;
	
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
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}
	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	/**
	 * @return the dscription
	 */
	public String getDscription() {
		return dscription;
	}
	/**
	 * @param dscription the dscription to set
	 */
	public void setDscription(String dscription) {
		this.dscription = dscription;
	}
	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the groupid
	 */
	public String getGroupid() {
		return groupid;
	}
	/**
	 * @param groupid the groupid to set
	 */
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	/**
	 * @return the interfaceclazz
	 */
	public String getInterfaceclazz() {
		return interfaceclazz;
	}
	/**
	 * @param interfaceclazz the interfaceclazz to set
	 */
	public void setInterfaceclazz(String interfaceclazz) {
		this.interfaceclazz = interfaceclazz;
	}
	/**
	 * @return the extensiontype
	 */
	public String getExtensiontype() {
		return extensiontype;
	}
	/**
	 * @param extensiontype the extensiontype to set
	 */
	public void setExtensiontype(String extensiontype) {
		this.extensiontype = extensiontype;
	}
	/**
	 * @return the parameters
	 */
	public String getParameters() {
		return parameters;
	}
	/**
	 * @param parameters the parameters to set
	 */
	public void setParameters(String parameters) {
		this.parameters = parameters;
	}
	public String getIconimagepath() {
		return iconimagepath;
	}
	public void setIconimagepath(String iconimagepath) {
		this.iconimagepath = iconimagepath;
	}
	
	@Transient
	public String getProperty(String name){
		String value = "" ;
		if(this.getParameters()!=null){
			Properties properties = ParamTools.parseParamO(this.getParameters()) ;
			value = properties.getProperty(name) ;
		}
		return value ;
	}
	@Transient
	public String getProperty(){
		String value = "" ;
		if(this.getParameters()!=null){
			Properties properties = ParamTools.parseParamO(this.getParameters()) ;
			value = properties.getProperty("url") ;
		}
		return value ;
	}
	@Transient
	public String getEncodeName() throws UnsupportedEncodingException{
		return this.name!=null?java.net.URLEncoder.encode(this.name,"UTF-8"):"" ;
	}
	
	@Transient
	public Object getPlugin() {
		return plugin;
	}
	public void setPlugin(Object plugin) {
		this.plugin = plugin;
	}
	
	
}
