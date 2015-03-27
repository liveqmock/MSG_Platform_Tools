package com.neusoft.web.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.neusoft.core.channel.SNSUser;

@Entity
@Table(name = "rivu_pagetemplate")
@org.hibernate.annotations.Proxy(lazy = false)
public class PageTemplate implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private String id;             //主键
	private String name;           //名称
	private String code;           //代码
	private String type;           //类型(1:分类    2:模板)
	private String parentid;       //上级id
	private String templatetext;   //模板代码
	private String datamodel;      //关联数据模板id
	private String demo;           //备注
	private String orgi;           //租户
	@Transient
	private byte[] html ;				//发布生产的HTML
	private String validtype;		//身份绑定策略
	private boolean userauth;		//实名认证
	private String userfaceid;
	private SearchSetting setting;
	private String ifaces;			//接口列表 以空格分隔
	private Map<String,Object> params;
	private boolean userbind;		//是否绑定身份 
	private SNSUser snsUser;
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
	@Transient
	public SNSUser getSnsUser() {
		return snsUser;
	}
	public void setSnsUser(SNSUser snsUser) {
		this.snsUser = snsUser;
	}
	@Transient
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	
	public boolean isUserbind() {
		return userbind;
	}
	public void setUserbind(boolean userbind) {
		this.userbind = userbind;
	}
	public String getIfaces() {
		return ifaces;
	}
	public void setIfaces(String ifaces) {
		this.ifaces = ifaces;
	}
	@Transient
	public SearchSetting getSetting() {
		return setting;
	}
	public void setSetting(SearchSetting setting) {
		this.setting = setting;
	}
	public String getValidtype() {
		return validtype;
	}
	public void setValidtype(String validtype) {
		this.validtype = validtype;
	}
	public boolean isUserauth() {
		return userauth;
	}
	public void setUserauth(boolean userauth) {
		this.userauth = userauth;
	}
	public String getUserfaceid() {
		return userfaceid;
	}
	public void setUserfaceid(String userfaceid) {
		this.userfaceid = userfaceid;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getTemplatetext() {
		return templatetext;
	}
	public void setTemplatetext(String templatetext) {
		this.templatetext = templatetext;
	}
	public String getDatamodel() {
		return datamodel;
	}
	public void setDatamodel(String datamodel) {
		this.datamodel = datamodel;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	@Transient
	public byte[] getHtml() {
		return html;
	}
	public void setHtml(byte[] html) {
		this.html = html;
	}
    
}
