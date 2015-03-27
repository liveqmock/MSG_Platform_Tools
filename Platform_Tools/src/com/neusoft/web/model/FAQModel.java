package com.neusoft.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name = "rivu_faq")
@org.hibernate.annotations.Proxy(lazy = false)
public class FAQModel {
	private static final long serialVersionUID = 1L;
	private String id;
	private String orgi; //租户标识
	private Date createtime = new Date();
	private String title;  //标题
	private String maincontext; //内容
	private String keywords;  //关键字
	private String wildcards;  //通配符
	private String createuser;//创建人
	private String createuserid;//创建人
	private String skillid;//技能组id
	private String faqtype; //分类
	
	
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
	public String getCreateuserid() {
		return createuserid;
	}
	public void setCreateuserid(String createuserid) {
		this.createuserid = createuserid;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMaincontext() {
		return maincontext;
	}
	public void setMaincontext(String maincontext) {
		this.maincontext = maincontext;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getWildcards() {
		return wildcards;
	}
	public void setWildcards(String wildcards) {
		this.wildcards = wildcards;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getSkillid() {
		return skillid;
	}
	public void setSkillid(String skillid) {
		this.skillid = skillid;
	}
	public String getFaqtype() {
		return faqtype;
	}
	public void setFaqtype(String faqtype) {
		this.faqtype = faqtype;
	}
	
}
