package com.neusoft.core.channel;

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
@Table(name = "rivu_webimuser")
@org.hibernate.annotations.Proxy(lazy = false)
public class WebIMUser implements SNSUser{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4912511673984694880L;
	@Field
	private String id ;
	@Field("str_userid")
	private String userid ;
	@Field("orgi")
	private String orgi ;
	@Field("channel")
	private String channel = EapDataContext.ChannelTypeEnum.WEBIM.toString();
	@Field("ip")
	private String clientip ;	
	private String apiusername ;
	private String username ;
	private boolean vip ;
	private boolean userbind ;
	private boolean userau;
	private String status ;
	private String account ;
	private String memo ;
	
	public WebIMUser(){}
	public WebIMUser(String id , String orgi , String userid , String username , String clientip){
		this.id = id ;
		this.userid = userid ;
		this.orgi = orgi ;
		this.username = username ;
		this.clientip = clientip ;
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
	
	public boolean isUserau() {
		return userau;
	}
	public void setUserau(boolean userau) {
		this.userau = userau;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getClientip() {
		return clientip;
	}
	public void setClientip(String clientip) {
		this.clientip = clientip;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	@Transient
	public String getApiusername() {
		return apiusername;
	}
	public void setApiusername(String apiusername) {
		this.apiusername = apiusername;
	}
	public boolean isUserbind() {
		return userbind;
	}
	public void setUserbind(boolean userbind) {
		this.userbind = userbind;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
}
