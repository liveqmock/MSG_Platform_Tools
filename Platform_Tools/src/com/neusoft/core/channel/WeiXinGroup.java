package com.neusoft.core.channel;

import java.util.List;


public class WeiXinGroup implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7530432292285212159L;
	private String id;
	private String name;
	private int cnt;
	private List<WeiXinUser> users;
	
	public List<WeiXinUser> getUsers() {
		return users;
	}
	public void setUsers(List<WeiXinUser> users) {
		this.users = users;
	}
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
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}
