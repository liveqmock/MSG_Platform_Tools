package com.neusoft.web.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_snsaccount")
@org.hibernate.annotations.Proxy(lazy = false)
public class SNSAccount{
	private String id ;
	private String name ;		//修改为  是否需要发布审核
	private String code ;		//修改为 发布审核人数
	private String username ;
	private String password ;
	private String snstype ;
	private Date createtime ;
	private String account ;
	private String allowremote ;
	private String email ;
	private String userno ;
	private String token ;
	private String apipoint ;
	private String appkey ;
	private String apptoken ;
	private String sessionkey ;
	private boolean defaultaccount ;
	private String moreparam ;				//改变用处，用于记录 爬虫的 爬取位置
	private String orgi ;
	private String lastatupdate ;
	private String lastprimsgupdate ;
	
	private String status = "0";
	private boolean fetcher = false ;
	
	private Object data ;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSnstype() {
		return snstype;
	}
	public void setSnstype(String snstype) {
		this.snstype = snstype;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getAllowremote() {
		return allowremote;
	}
	public void setAllowremote(String allowremote) {
		this.allowremote = allowremote;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserno() {
		return userno;
	}
	public void setUserno(String userno) {
		this.userno = userno;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getAppkey() {
		return appkey;
	}
	public void setAppkey(String appkey) {
		this.appkey = appkey;
	}
	public String getApptoken() {
		return apptoken;
	}
	public void setApptoken(String apptoken) {
		this.apptoken = apptoken;
	}
	public String getSessionkey() {
		return sessionkey;
	}
	public void setSessionkey(String sessionkey) {
		this.sessionkey = sessionkey;
	}
	public String getMoreparam() {
		return moreparam;
	}
	public void setMoreparam(String moreparam) {
		this.moreparam = moreparam;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getApipoint() {
		return apipoint;
	}
	public void setApipoint(String apipoint) {
		this.apipoint = apipoint;
	}
	public boolean isDefaultaccount() {
		return defaultaccount;
	}
	public void setDefaultaccount(boolean defaultaccount) {
		this.defaultaccount = defaultaccount;
	}
	public String getLastatupdate() {
		return lastatupdate;
	}
	public void setLastatupdate(String lastatupdate) {
		this.lastatupdate = lastatupdate;
	}
	public String getLastprimsgupdate() {
		return lastprimsgupdate;
	}
	public void setLastprimsgupdate(String lastprimsgupdate) {
		this.lastprimsgupdate = lastprimsgupdate;
	}
	@Transient
	public boolean isFetcher() {
		return fetcher;
	}
	public void setFetcher(boolean fetcher) {
		this.fetcher = fetcher;
	}
	@Transient
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Transient
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
