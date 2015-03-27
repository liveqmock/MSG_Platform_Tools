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
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author jaddy0302 Rivulet JobDetail.java 2010-3-1
 * 
 */
@Entity
@Table(name = "rivu_jobdetail")
@org.hibernate.annotations.Proxy(lazy = false)
public class JobDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3038981315537092556L;
	private String id ;
	private String name ;
	private String clazz ;
	private String taskid ;
	private String tasktype ; //file:netfile:ftp:pop3:oracle...
	private boolean plantask = false;    // Auto Exec
	private String source ;   //File source eg:e:\document
	
	private String crawltaskid ;
	
	@Transient
	private int fetchNum ;
	@Transient
	private int docNum ;

	private Date taskfiretime ;
	private TableTask crawltask ;
	private TableTask targettask ;
	private boolean createtable;
	private String taskstatus = "0";
	private int startindex ;	//数据更新位置
	private Date lastdate ;		//数据更新时间
	private Date nextfiretime ;		//数据更新时间
	private String cronexp ;
	private boolean fetcher = true ;
	private boolean pause = false;
	private boolean plantaskreadtorun = false ;
	@Transient
	private Reporter report ;
	@Transient
	private boolean metadata = false;
	
	private ConfigureParam configure ;
	private TaskPlanConfigure taksplan  ;
	private String orgi ;
	private String memo ;
	
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
	 * @return the report
	 */
	@Transient
	public Reporter getReport() {
		return report;
	}
	/**
	 * @param report the report to set
	 */
	public void setReport(Reporter report) {
		this.report = report;
	}
	/**
	 * @return the configure
	 */
	@OneToOne(optional = true)  
	public ConfigureParam getConfigure() {
		return configure;
	}
	/**
	 * @param configure the configure to set
	 */
	public void setConfigure(ConfigureParam configure) {
		this.configure = configure;
	}
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="targettask")
	public TableTask getTargettask() {
		return targettask;
	}
	public void setTargettask(TableTask targettask) {
		this.targettask = targettask;
	}
	public int getStartindex() {
		return startindex;
	}
	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}
	public Date getLastdate() {
		return lastdate;
	}
	public void setLastdate(Date lastdate) {
		this.lastdate = lastdate;
	}
	/**
	 * @return the crawltask
	 */
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="crawltask")
	public TableTask getCrawltask() {
		return crawltask;
	}
	/**
	 * @param crawltask the crawltask to set
	 */
	public void setCrawltask(TableTask crawltask) {
		this.crawltask = crawltask;
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
	 * @return the taksplan
	 */
	@OneToOne(optional = true) 
	public TaskPlanConfigure getTaksplan() {
		return taksplan;
	}
	/**
	 * @param taksplan the taksplan to set
	 */
	
	public void setTaksplan(TaskPlanConfigure taksplan) {
		this.taksplan = taksplan;
	}
	/**
	 * @return the fetcher
	 */
	public boolean isFetcher() {
		return fetcher;
	}
	/**
	 * @param fetcher the fetcher to set
	 */
	public void setFetcher(boolean fetcher) {
		this.fetcher = fetcher;
	}
	/**
	 * @return the pause
	 */
	public boolean isPause() {
		return pause;
	}
	/**
	 * @param pause the pause to set
	 */
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	/**
	 * @return the plantask
	 */
	public boolean isPlantask() {
		return plantask;
	}
	/**
	 * @param plantask the plantask to set
	 */
	public void setPlantask(boolean plantask) {
		this.plantask = plantask;
	}
	/**
	 * @return the plantaskreadtorun
	 */
	@Transient
	public boolean isPlantaskreadtorun() {
		return plantaskreadtorun;
	}
	/**
	 * @param plantaskreadtorun the plantaskreadtorun to set
	 */
	public void setPlantaskreadtorun(boolean plantaskreadtorun) {
		this.plantaskreadtorun = plantaskreadtorun;
	}
	@Transient
	public int getFetchNum() {
		return fetchNum;
	}
	public void setFetchNum(int fetchNum) {
		this.fetchNum = fetchNum;
	}
	@Transient
	public int getDocNum() {
		return docNum;
	}
	public void setDocNum(int docNum) {
		this.docNum = docNum;
	}
	@Transient
	public boolean isMetadata() {
		return metadata;
	}
	public void setMetadata(boolean metadata) {
		this.metadata = metadata;
	}
	public boolean isCreatetable() {
		return createtable;
	}
	public void setCreatetable(boolean createtable) {
		this.createtable = createtable;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Date getNextfiretime() {
		return nextfiretime;
	}
	public void setNextfiretime(Date nextfiretime) {
		this.nextfiretime = nextfiretime;
	}
	public String getCronexp() {
		return cronexp;
	}
	public void setCronexp(String cronexp) {
		this.cronexp = cronexp;
	}
	public String getTaskstatus() {
		return taskstatus;
	}
	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}
}
