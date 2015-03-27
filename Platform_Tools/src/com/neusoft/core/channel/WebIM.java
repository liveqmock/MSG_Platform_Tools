package com.neusoft.core.channel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.annotations.GenericGenerator;

import com.neusoft.core.EapDataContext;

@Entity
@Table(name = "rivu_webimmessage")
@org.hibernate.annotations.Proxy(lazy = false)
public class WebIM implements Channel{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6993923893684668856L;
	@Field
	private String channel = EapDataContext.ChannelTypeEnum.WEBIM.toString();
	@Field
	private String id ;		//记录ID ID
	@Field
	private String sessionid ;		//会话 ID
	@Field
	private String text ;//对话消息
	@Field
	private String orgi ;	//租户
	
	private String username ;
	@Field
	private String userid ;	//用户ID
	@Field("datetime")
	private Date createtime = new Date();
	@Transient
	private SNSUser snsuser ;
	
	private boolean topmessage;
	
	private String messagetype ;
	
	private byte[] bytedata ;
	
	private String contextid ;
	private String touser ;
	
	private String replytype ;
	
	private String instruct ;
	private String pipeline ;
	private String source ;
	
	public WebIM(){	}
	public WebIM(String id, String text , String orgi , SNSUser snsuser , String sessionid , String messagetype){
		this.id = id ;
		this.text = text ;
		this.orgi = orgi ;
		this.snsuser = snsuser ;
		this.sessionid = sessionid ;
		this.messagetype = messagetype ;
	}
	@Override
	public String getChannel() {
		return channel;
	}
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}


	public void setChannel(String channel) {
		this.channel = channel;
	}
	public void setId(String id) {
		this.id = id;
	}


	@Override
	public String getOrgi() {
		// TODO Auto-generated method stub
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return text;
	}
	@Transient
	@Override
	public String getUser() {
		// TODO Auto-generated method stub
		return this.id;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public void setText(String text) {
		this.text = text;
	}
	@Transient
	public SNSUser getSnsuser() {
		return snsuser;
	}
	public void setSnsuser(SNSUser snsuser) {
		this.snsuser = snsuser;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String getContextid() {
		return contextid;
	}
	public void setContextid(String contextid) {
		this.contextid = contextid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessagetype() {
		return messagetype;
	}
	public void setMessagetype(String messagetype) {
		this.messagetype = messagetype;
	}
	public boolean isTopmessage() {
		return topmessage;
	}
	public void setTopmessage(boolean topmessage) {
		this.topmessage = topmessage;
	}
	public byte[] getBytedata() {
		return bytedata;
	}
	public void setBytedata(byte[] bytedata) {
		this.bytedata = bytedata;
	}
	public String getTouser() {
		return touser;
	}
	public void setTouser(String touser) {
		this.touser = touser;
	}
	
	public String getReplytype() {
		return replytype;
	}
	public void setReplytype(String replytype) {
		this.replytype = replytype;
	}
	public String getInstruct() {
		return instruct;
	}
	public void setInstruct(String instruct) {
		this.instruct = instruct;
	}
	@Transient
	public String getPipeline() {
		return pipeline;
	}
	public void setPipeline(String pipeline) {
		this.pipeline = pipeline;
	}
	@Transient
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
}
