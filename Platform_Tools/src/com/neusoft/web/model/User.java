/**
 * Licensed to the Rivulet under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     webapps/LICENSE-Rivulet-1.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.neusoft.web.model;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.annotations.GenericGenerator;

import com.neusoft.util.queue.AgentStatus;

/**
 * @author jaddy0302 Rivulet User.java 2010-3-17
 * 
 */
@Entity
@Table(name = "rivu_user")
@org.hibernate.annotations.Proxy(lazy = false)
public class User implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4921506359981218058L;
	@Field
	private String id ;
	private String username ;
	private String password ;
	private String email ;
	private String firstname ;
	private String midname ;
	private String lastname ;
	private String language ;
	private String jobtitle ;
	private String department ;
	private String gender ;
	private String birthday ;
	private String nickname ;
	private String secureconf = "5";
	private String usertype ; // 0 Admin User  : !0  Other User 
	private String rulename ;
	private String searchprojectid ;
	private String type ;
	private Map property ;
	private String agentno ;
	private boolean login = false ;
	private String loginType = "ESP-R3" ;
	private String loginTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date()) ;
	private String orgi ;
	@Transient
	private AgentStatus agentstatus ;
	
	
	private String newpwd;
	// 新密码
	
	@Transient
	public String getNewpwd() {
		return newpwd;
	}

	public void setNewpwd(String newpwd) {
		this.newpwd = newpwd;
	}
	
	
	
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	/**
	 * @return the id
	 */
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")	
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password!=null?password:"";
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * @param firstname the firstname to set
	 */
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	/**
	 * @return the midname
	 */
	public String getMidname() {
		return midname;
	}
	/**
	 * @param midname the midname to set
	 */
	public void setMidname(String midname) {
		this.midname = midname;
	}
	/**
	 * @return the lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * @param lastname the lastname to set
	 */
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the jobtitle
	 */
	public String getJobtitle() {
		return jobtitle;
	}
	/**
	 * @param jobtitle the jobtitle to set
	 */
	public void setJobtitle(String jobtitle) {
		this.jobtitle = jobtitle;
	}
	/**
	 * @return the department
	 */
	public String getDepartment() {
		return department;
	}
	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}
	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}
	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}
	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	/**
	 * @return the secureconf
	 */
	public String getSecureconf() {
		return secureconf;
	}
	/**
	 * @param secureconf the secureconf to set
	 */
	public void setSecureconf(String secureconf) {
		this.secureconf = secureconf;
	}
	public String getUsertype() {
		return usertype;
	}
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	public String getRulename() {
		return rulename;
	}
	public void setRulename(String rulename) {
		this.rulename = rulename;
	}
	public String getSearchprojectid() {
		return searchprojectid;
	}
	public void setSearchprojectid(String searchprojectid) {
		this.searchprojectid = searchprojectid;
	}
	@Transient
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Transient
	public Map getProperty() {
		return property;
	}
	public void setProperty(Map property) {
		this.property = property;
	}
	@Transient
	public boolean isTemp(boolean temp){
		return temp ;
	}
	
	@Transient
	public boolean isLogin() {
		return login;
	}
	@Transient
	public void setLogin(boolean login) {
		this.login = login;
	}
	@Transient
	public String getLoginType() {
		return loginType;
	}
	@Transient
	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}
	@Transient
	public String getLoginTime() {
		return loginTime;
	}
	@Transient
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	@Transient
	public AgentStatus getAgentstatus() {
		return agentstatus;
	}

	public void setAgentstatus(AgentStatus agentstatus) {
		this.agentstatus = agentstatus;
	}
	@Transient
	public String getAgentno() {
		return agentno!=null ? agentno : username;
	}

	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}
}
