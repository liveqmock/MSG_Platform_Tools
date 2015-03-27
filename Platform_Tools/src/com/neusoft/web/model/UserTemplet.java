/**
 * 
 */
package com.neusoft.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author iceworld
 *
 */
@Entity
@Table(name = "rivu_usertemplet")
@org.hibernate.annotations.Proxy(lazy = false)
public class UserTemplet implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1946579239823440392L;
	private String id ;
	private String name ;
	private String code ;
	private String userid ;
	private String groupid ;				//变更用处，groupid 作为模板的标题，支持 参数替换
	private String description ;
	private String templettext ;
	private String templatetype ; //List OR Preview
	private Date createtime = new Date();
	private String orgi ;
	private String channel ;
	
	
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTemplettext() {
		return templettext;
	}
	public void setTemplettext(String templettext) {
		this.templettext = templettext;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	
	public String getTemplatetype() {
		return templatetype;
	}
	public void setTemplatetype(String templatetype) {
		this.templatetype = templatetype;
	}
	@Transient
	public String getTitle(){
		return this.groupid ;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
}
