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

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author jaddy0302 Rivulet SearchSetting.java 2010-4-20
 * 
 */
@Entity
@Table(name = "rivu_searchsetting")
@org.hibernate.annotations.Proxy(lazy = false)
public class SearchSetting implements Serializable  {
	private static final long serialVersionUID = 1L;
	private String id ;
	
	private boolean contractip = true;		//联络提示，如：两分钟未收到任何消息提示 是否在线
	private int contractipimin = 2 ;		//联络提示，提示时长
	
	private boolean dislinktip	= true;		//断开链接提示，如：两分钟后还未收到消息，则断开人工坐席链接
	private int dislinkmin		= 2 ;		//断开时长
	
	private boolean skill		= false ;	//启用技能组
	
	private boolean userdislink = false ;	//禁用 用户主动断开提示
	
	private String userdislinkins ;			//用户主动断开处理 指令， 如：99  //功能变更，改为 是否启用人工坐席
	
	private boolean userbind = false ;		//启用用户身份绑定
	
	private String bindmsgtype = "0";		//身份绑定信息提示类型，默认文本提示
	
	private String bindmsg ;				//身份绑定提示信息文本内容
	
	private String bindmaterial	;			//身份绑定提示信息素材
	
	private String gwhost ; 				//服务器地址
	
	private String orgi ;
	private String authurl ;				//认证URL
	private String loginurl ;				//登陆URL
	private String regurl;					//注册URL
	private String nodeservurl;				//smc请求node的地址(cmp服务器)
	private Integer ifacecycle;				//静态接口更新周期
	private String contractipimsg ;			//无对话提示，提示消息
	private String dislinkmsg;				//断开时长提示消息
	private String notfoundmsg;			//未命中提示消息
	
	
	
	
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
	
	public String getNotfoundmsg() {
		return notfoundmsg;
	}
	public void setNotfoundmsg(String notfoundmsg) {
		this.notfoundmsg = notfoundmsg;
	}
	public String getContractipimsg() {
		return contractipimsg;
	}
	public void setContractipimsg(String contractipimsg) {
		this.contractipimsg = contractipimsg;
	}
	public String getDislinkmsg() {
		return dislinkmsg;
	}
	public void setDislinkmsg(String dislinkmsg) {
		this.dislinkmsg = dislinkmsg;
	}
	public Integer getIfacecycle() {
		return ifacecycle;
	}
	public void setIfacecycle(Integer ifacecycle) {
		this.ifacecycle = ifacecycle;
	}
	public String getAuthurl() {
		return authurl;
	}
	public void setAuthurl(String authurl) {
		this.authurl = authurl;
	}
	public String getLoginurl() {
		return loginurl;
	}
	public void setLoginurl(String loginurl) {
		this.loginurl = loginurl;
	}
	public String getRegurl() {
		return regurl;
	}
	public void setRegurl(String regurl) {
		this.regurl = regurl;
	}
	public boolean isContractip() {
		return contractip;
	}
	public void setContractip(boolean contractip) {
		this.contractip = contractip;
	}
	public int getContractipimin() {
		return contractipimin;
	}
	public void setContractipimin(int contractipimin) {
		this.contractipimin = contractipimin;
	}
	public boolean isDislinktip() {
		return dislinktip;
	}
	public void setDislinktip(boolean dislinktip) {
		this.dislinktip = dislinktip;
	}
	public int getDislinkmin() {
		return dislinkmin;
	}
	public void setDislinkmin(int dislinkmin) {
		this.dislinkmin = dislinkmin;
	}
	public boolean isSkill() {
		return skill;
	}
	public void setSkill(boolean skill) {
		this.skill = skill;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public boolean isUserdislink() {
		return userdislink;
	}
	public void setUserdislink(boolean userdislink) {
		this.userdislink = userdislink;
	}
	public String getUserdislinkins() {
		return userdislinkins;
	}
	public void setUserdislinkins(String userdislinkins) {
		this.userdislinkins = userdislinkins;
	}
	public boolean isUserbind() {
		return userbind;
	}
	public void setUserbind(boolean userbind) {
		this.userbind = userbind;
	}
	public String getBindmsgtype() {
		return bindmsgtype;
	}
	public void setBindmsgtype(String bindmsgtype) {
		this.bindmsgtype = bindmsgtype;
	}
	public String getBindmsg() {
		return bindmsg;
	}
	public void setBindmsg(String bindmsg) {
		this.bindmsg = bindmsg;
	}
	public String getBindmaterial() {
		return bindmaterial;
	}
	public void setBindmaterial(String bindmaterial) {
		this.bindmaterial = bindmaterial;
	}
	public String getGwhost() {
		return gwhost;
	}
	public void setGwhost(String gwhost) {
		this.gwhost = gwhost;
	}
	public String getNodeservurl() {
		return nodeservurl;
	}
	public void setNodeservurl(String nodeservurl) {
		this.nodeservurl = nodeservurl;
	}
	
	
	
	
}
