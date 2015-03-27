package com.neusoft.util.tools;

import java.util.Date;

public class WeiXinToken implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7831823438124271706L;
	private Date createDate = new Date();
	private String access_token ;
	private int expires_in ;
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public int getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(int expires_in) {
		this.expires_in = expires_in;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public boolean isAva(){
		return (expires_in + this.createDate.getTime()/1000) > System.currentTimeMillis()/1000 ;
	}
}
