package com.neusoft.web.model.weibo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.neusoft.core.TaskType;
import com.neusoft.web.model.DataModel;

@Entity
@Table(name = "wbcims_wbkeyword")
@org.hibernate.annotations.Proxy(lazy = false)
public class WeiBoKeyword implements DataModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 796489332405876783L;
	private String id;
	private String username;
	private String groupname ;
	private String clazz ;
	private boolean userdata ;	//是否是监测目标
	private String keyword ;
	private String sourcesite;
	private String freq ;	//频率
	private String account ; 	//预警账号
	private String level ;		//预警级别
	private Date createtime = new Date();
	private Date lastcrawltime ;
	private int wbnum ; 		//已采集微博数据量
	private String wburl ;		//微博网址
	private String memo ;//备用
	private String title ;//备用
	private String orgi ;//备用
	private String templetid ;//备用
	private String groupid ;//备用
	private String userid ;//备用
	private String creater ;//备用
	private String status;	//备用
	private Date nextcrawl ;
	private long since = 1;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSourcesite() {
		return sourcesite;
	}
	public void setSourcesite(String sourcesite) {
		this.sourcesite = sourcesite;
	}
	
	public String getFreq() {
		return freq;
	}
	public void setFreq(String freq) {
		this.freq = freq;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getLastcrawltime() {
		return lastcrawltime;
	}
	public void setLastcrawltime(Date lastcrawltime) {
		this.lastcrawltime = lastcrawltime;
	}
	public int getWbnum() {
		return wbnum;
	}
	public void setWbnum(int wbnum) {
		this.wbnum = wbnum;
	}
	public String getWburl() {
		return wburl;
	}
	public void setWburl(String wburl) {
		this.wburl = wburl;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getTempletid() {
		return templetid;
	}
	public void setTempletid(String templetid) {
		this.templetid = templetid;
	}
	public String getGroupid() {
		return groupid;
	}
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public boolean isUserdata() {
		return userdata;
	}
	public void setUserdata(boolean userdata) {
		this.userdata = userdata;
	}
	@Transient
	public String getName(){
		return this.getKeyword() ;
	}
	@Transient
	public String getType(){
		return TaskType.KEYWORD.toString() ;
	}
	public Date getNextcrawl() {
		return nextcrawl;
	}
	public void setNextcrawl(Date nextcrawl) {
		this.nextcrawl = nextcrawl;
	}
	public long getSince() {
		return since;
	}
	public void setSince(long since) {
		this.since = since;
	}
}
