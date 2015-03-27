package com.neusoft.web.model;

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

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author jaddy0302 Rivulet HistoryJobDetail.java 2010-3-1
 * 
 */
@Entity
@Table(name = "rivu_historyjobdetail")
@org.hibernate.annotations.Proxy(lazy = false)
public class HistoryJobDetail  implements Serializable {
	private String id ;
	private String name ;
	private String clazz ;
	private String taskid ;
	private String tasktype ; //file:netfile:ftp:pop3:oracle...
	private String source ;   //File source eg:e:\document
	private boolean plantask ;
	private Date taskfiretime ;
	private String crawltaskid ;
	private Date starttime ;
	private Date endtime ;
	
	@Transient
	private TableTask crawltask ;
	@Transient
	private TableTask targettask ;
	@Transient
	private boolean createtable;
	@Transient
	private String taskstatus = "0";
	@Transient
	private int startindex ;	//数据更新位置
	@Transient
	private Date lastdate ;		//数据更新时间
	
	private String reportid ;
	private String status ;
	private String userid ;
	private String groupid ;
	private String orgi ;
	
	
	
	
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
//	private ConfigureParam configure = new ConfigureParam();
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
	 * @return the clazz
	 */
	public String getClazz() {
		return clazz;
	}
	/**
	 * @param clazz the clazz to set
	 */
	public void setClazz(String clazz) {
		this.clazz = clazz;
	}
	/**
	 * @return the taskid
	 */
	public String getTaskid() {
		return taskid;
	}
	/**
	 * @param taskid the taskid to set
	 */
	public void setTaskid(String taskid) {
		this.taskid = taskid;
	}
	/**
	 * @return the tasktype
	 */
	public String getTasktype() {
		return tasktype;
	}
	/**
	 * @param tasktype the tasktype to set
	 */
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}
	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the taskfiretime
	 */
	public Date getTaskfiretime() {
		return taskfiretime;
	}
	/**
	 * @param taskfiretime the taskfiretime to set
	 */
	public void setTaskfiretime(Date taskfiretime) {
		this.taskfiretime = taskfiretime;
	}
	
	/**
	 * @return the configure
	 */
//	@Transient
//	public ConfigureParam getConfigure() {
//		return configure;
//	}
//	/**
//	 * @param configure the configure to set
//	 */
//	
//	public void setConfigure(ConfigureParam configure) {
//		this.configure = configure;
//	}
	/**
	 * @return the crawltask
	 */
	@Transient
	public TableTask getCrawltask() {
		return crawltask;
	}
	/**
	 * @return the crawltaskid
	 */
	public String getCrawltaskid() {
		return crawltaskid;
	}
	/**
	 * @param crawltaskid the crawltaskid to set
	 */
	public void setCrawltaskid(String crawltaskid) {
		this.crawltaskid = crawltaskid;
	}
	/**
	 * @return the starttime
	 */
	public Date getStarttime() {
		return starttime;
	}
	/**
	 * @param starttime the starttime to set
	 */
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	/**
	 * @return the endtime
	 */
	public Date getEndtime() {
		return endtime;
	}
	/**
	 * @param endtime the endtime to set
	 */
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
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
	/**
	 * @return the reportid
	 */
	public String getReportid() {
		return reportid;
	}
	/**
	 * @param reportid the reportid to set
	 */
	public void setReportid(String reportid) {
		this.reportid = reportid;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the plantask
	 */
	@Transient
	public boolean isPlantask() {
		return plantask;
	}
	/**
	 * @param plantask the plantask to set
	 */
	public void setPlantask(boolean plantask) {
		this.plantask = plantask;
	}
	@Transient
	public TableTask getTargettask() {
		return targettask;
	}
	public void setTargettask(TableTask targettask) {
		this.targettask = targettask;
	}
	@Transient
	public boolean isCreatetable() {
		return createtable;
	}
	public void setCreatetable(boolean createtable) {
		this.createtable = createtable;
	}
	@Transient
	public String getTaskstatus() {
		return taskstatus;
	}
	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}
	@Transient
	public int getStartindex() {
		return startindex;
	}
	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}
	@Transient
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	public void setCrawltask(TableTask crawltask) {
		this.crawltask = crawltask;
	}
}
