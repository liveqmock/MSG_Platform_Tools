package com.neusoft.web.model.weibo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.neusoft.core.TaskType;
import com.neusoft.web.model.DataModel;

@Entity
@Table(name = "wbcims_wbtopic")
@org.hibernate.annotations.Proxy(lazy = false)
public class WeiBoTopic implements DataModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 796489332405876783L;
	private String id;
	private String username;
	private String groupname ;
	private String clazz ;
	private String topic ;
	private String sourcesite;
	private WeiBoUser wbuser ;
	private boolean userdata ;	//是否是监测目标
	private int replay ;	//频率
	private int forward ;
	private String freq ;
	private String account ; 	//预警账号
	private String level ;		//预警级别
	private Date createtime;
	private Date lastcrawltime ;
	private String wburl ;		//微博网址
	private String wbtext ;		//微博主题
	private String memo ;//备用
	private String title ;//备用
	private String orgi ;//备用
	private String templetid ;//备用				,  微博 ID
	private String groupid ;//备用
	private String userid ;//备用
	private String creater ;//备用
	private String status;	//备用
	private Date nextcrawl ;
	private String topicid ;			//微博原ID，如果是 repost ， 则是 被转发的 微博 ID，如果本事是原创，则 是原创ID
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
	public String getWbtext() {
		return wbtext;
	}
	public void setWbtext(String wbtext) {
		this.wbtext = wbtext;
	}
	public String getGroupname() {
		return groupname;
	}
	public void setGroupname(String groupname) {
		this.groupname = groupname;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="wbuser")
	public WeiBoUser getWbuser() {
		return wbuser;
	}
	public void setWbuser(WeiBoUser wbuser) {
		this.wbuser = wbuser;
	}
	public boolean isUserdata() {
		return userdata;
	}
	public void setUserdata(boolean userdata) {
		this.userdata = userdata;
	}
	public int getReplay() {
		return replay;
	}
	public void setReplay(int replay) {
		this.replay = replay;
	}
	public int getForward() {
		return forward;
	}
	public void setForward(int forward) {
		this.forward = forward;
	}
	public String getClazz() {
		return clazz;
	}
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	public String getFreq() {
		return freq;
	}
	public void setFreq(String freq) {
		this.freq = freq;
	}
	public String getSourcesite() {
		return sourcesite;
	}
	public void setSourcesite(String sourcesite) {
		this.sourcesite = sourcesite;
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
	public Date getNextcrawl() {
		return nextcrawl;
	}
	public void setNextcrawl(Date nextcrawl) {
		this.nextcrawl = nextcrawl;
	}
	@Transient
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.id;
	}
	@Transient
	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return TaskType.TOPIC.toString();
	}
	public String getTopicid() {
		return topicid;
	}
	public void setTopicid(String topicid) {
		this.topicid = topicid;
	}
	public long getSince() {
		return since;
	}
	public void setSince(long since) {
		this.since = since;
	}
}

