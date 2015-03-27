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
 * @author jaddy0302 Rivulet TaskPlanConfigure.java 2010-3-8
 * 
 */
@Entity
@Table(name = "rivu_taskplanconfigure")
@org.hibernate.annotations.Proxy(lazy = false)
public class TaskPlanConfigure implements Serializable{
	private String id ;
	private String name ;
	private String plantype = "day";
	private String  rit = "1" ; //runinterval
	private String runondate ;
	private String rhours ;
	private String rmin ;
	private String rsec ;
	private String rweek ;
	private String rmonth ;
	
	private String begintime ;
	private String rpt ; //repeat
	private String repeatterval = "5";
	private String crt = "1440"; //continuous running time
	private String crondstr ;
	private String taskplan ;
	private String configure ;
	private String userid ;
	private String groupid ;	
	private String orgi ;
	
	
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
	 * @return the plantype
	 */
	public String getPlantype() {
		return plantype!=null?plantype:"day";
	}

	/**
	 * @return the rit
	 */
	public String getRit() {
		return rit;
	}

	/**
	 * @param rit the rit to set
	 */
	public void setRit(String rit) {
		this.rit = rit;
	}

	/**
	 * @return the runondate
	 */
	public String getRunondate() {
		return runondate;
	}

	/**
	 * @param runondate the runondate to set
	 */
	public void setRunondate(String runondate) {
		this.runondate = runondate;
	}

	/**
	 * @return the rhours
	 */
	public String getRhours() {
		return rhours;
	}

	/**
	 * @param rhours the rhours to set
	 */
	public void setRhours(String rhours) {
		this.rhours = rhours;
	}

	/**
	 * @return the rmin
	 */
	public String getRmin() {
		return rmin;
	}

	/**
	 * @param rmin the rmin to set
	 */
	public void setRmin(String rmin) {
		this.rmin = rmin;
	}

	/**
	 * @return the rsec
	 */
	public String getRsec() {
		return rsec;
	}

	/**
	 * @param rsec the rsec to set
	 */
	public void setRsec(String rsec) {
		this.rsec = rsec;
	}

	/**
	 * @return the rweek
	 */
	public String getRweek() {
		return rweek;
	}

	/**
	 * @param rweek the rweek to set
	 */
	public void setRweek(String rweek) {
		this.rweek = rweek;
	}

	/**
	 * @return the rmonth
	 */
	public String getRmonth() {
		return rmonth;
	}

	/**
	 * @param rmonth the rmonth to set
	 */
	public void setRmonth(String rmonth) {
		this.rmonth = rmonth;
	}

	/**
	 * @return the begintime
	 */
	public String getBegintime() {
		return begintime;
	}

	/**
	 * @param begintime the begintime to set
	 */
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}

	/**
	 * @return the rpt
	 */
	public String getRpt() {
		return rpt;
	}

	/**
	 * @param rpt the rpt to set
	 */
	public void setRpt(String rpt) {
		this.rpt = rpt;
	}

	/**
	 * @return the repeatterval
	 */
	public String getRepeatterval() {
		return repeatterval;
	}

	/**
	 * @param repeatterval the repeatterval to set
	 */
	public void setRepeatterval(String repeatterval) {
		this.repeatterval = repeatterval;
	}

	/**
	 * @return the crt
	 */
	public String getCrt() {
		return crt;
	}

	/**
	 * @param crt the crt to set
	 */
	public void setCrt(String crt) {
		this.crt = crt;
	}

	/**
	 * @param plantype the plantype to set
	 */
	public void setPlantype(String plantype) {
		this.plantype = plantype;
	}
	
	/**
	 * @return the crondstr
	 */
	public String getCrondstr() {
		return crondstr;
	}

	/**
	 * @param crondstr the crondstr to set
	 */
	public void setCrondstr(String crondstr) {
		this.crondstr = crondstr;
	}
	
	/**
	 * @return the taskplan
	 */
	@Transient
	public String getTaskplan() {
		return id;
	}
	
	/**
	 * @param taskplan the taskplan to set
	 */
	public void setTaskplan(String taskplan) {
		this.taskplan = taskplan;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the configure
	 */
	@Transient
	public String getConfigure() {
		return configure;
	}

	/**
	 * @param configure the configure to set
	 */
	public void setConfigure(String configure) {
		this.configure = configure;
	}

	public String toString(){
		return "From March 2, the day from 10:00 onwards, at intervals of 20 minutes for a period of 24 hours " ;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * @return the groupid
	 */
	public String getGroupid() {
		return groupid;
	}

	/**
	 * @param groupid the groupid to set
	 */
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}
	
}
