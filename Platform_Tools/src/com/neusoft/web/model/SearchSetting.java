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
  private String id;
  private boolean contractip = true;
  private int contractipimin = 2;

  private boolean dislinktip = true;
  private int dislinkmin = 2;

  private boolean skill = false;

  private boolean userdislink = false;
  private String userdislinkins;
  private boolean userbind = false;

  private String bindmsgtype = "0";
  private String bindmsg;
  private String bindmaterial;
  private String gwhost;
  private String orgi;
  private String authurl;
  private String loginurl;
  private String regurl;
  private Integer ifacecycle;
  private String contractipimsg;
  private String dislinkmsg;
  private String notfoundmsg;
  private Boolean work = Boolean.valueOf(false);
  private String worktime;
  private String nowork;

  @Id
  @Column(length=32)
  @GeneratedValue(generator="system-uuid")
  @GenericGenerator(name="system-uuid", strategy="uuid")
  public String getId()
  {
    return this.id;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public String getNotfoundmsg() {
    return this.notfoundmsg;
  }
  public void setNotfoundmsg(String notfoundmsg) {
    this.notfoundmsg = notfoundmsg;
  }
  public String getContractipimsg() {
    return this.contractipimsg;
  }
  public void setContractipimsg(String contractipimsg) {
    this.contractipimsg = contractipimsg;
  }
  public String getDislinkmsg() {
    return this.dislinkmsg;
  }
  public void setDislinkmsg(String dislinkmsg) {
    this.dislinkmsg = dislinkmsg;
  }
  public Integer getIfacecycle() {
    return this.ifacecycle;
  }
  public void setIfacecycle(Integer ifacecycle) {
    this.ifacecycle = ifacecycle;
  }
  public String getAuthurl() {
    return this.authurl;
  }
  public void setAuthurl(String authurl) {
    this.authurl = authurl;
  }
  public String getLoginurl() {
    return this.loginurl;
  }
  public void setLoginurl(String loginurl) {
    this.loginurl = loginurl;
  }
  public String getRegurl() {
    return this.regurl;
  }
  public void setRegurl(String regurl) {
    this.regurl = regurl;
  }
  public boolean isContractip() {
    return this.contractip;
  }
  public void setContractip(boolean contractip) {
    this.contractip = contractip;
  }
  public int getContractipimin() {
    return this.contractipimin;
  }
  public void setContractipimin(int contractipimin) {
    this.contractipimin = contractipimin;
  }
  public boolean isDislinktip() {
    return this.dislinktip;
  }
  public void setDislinktip(boolean dislinktip) {
    this.dislinktip = dislinktip;
  }
  public int getDislinkmin() {
    return this.dislinkmin;
  }
  public void setDislinkmin(int dislinkmin) {
    this.dislinkmin = dislinkmin;
  }
  public boolean isSkill() {
    return this.skill;
  }
  public void setSkill(boolean skill) {
    this.skill = skill;
  }
  public String getOrgi() {
    return this.orgi;
  }
  public void setOrgi(String orgi) {
    this.orgi = orgi;
  }
  public boolean isUserdislink() {
    return this.userdislink;
  }
  public void setUserdislink(boolean userdislink) {
    this.userdislink = userdislink;
  }
  public String getUserdislinkins() {
    return this.userdislinkins;
  }
  public void setUserdislinkins(String userdislinkins) {
    this.userdislinkins = userdislinkins;
  }
  public boolean isUserbind() {
    return this.userbind;
  }
  public void setUserbind(boolean userbind) {
    this.userbind = userbind;
  }
  public String getBindmsgtype() {
    return this.bindmsgtype;
  }
  public void setBindmsgtype(String bindmsgtype) {
    this.bindmsgtype = bindmsgtype;
  }
  public String getBindmsg() {
    return this.bindmsg;
  }
  public void setBindmsg(String bindmsg) {
    this.bindmsg = bindmsg;
  }
  public String getBindmaterial() {
    return this.bindmaterial;
  }
  public void setBindmaterial(String bindmaterial) {
    this.bindmaterial = bindmaterial;
  }
  public String getGwhost() {
    return this.gwhost;
  }
  public void setGwhost(String gwhost) {
    this.gwhost = gwhost;
  }
  public String getWorktime() {
    return this.worktime;
  }
  public void setWorktime(String worktime) {
    this.worktime = worktime;
  }
  public String getNowork() {
    return this.nowork;
  }
  public void setNowork(String nowork) {
    this.nowork = nowork;
  }
  public Boolean getWork() {
    return this.work;
  }
  public void setWork(Boolean work) {
    this.work = work;
  }
}
