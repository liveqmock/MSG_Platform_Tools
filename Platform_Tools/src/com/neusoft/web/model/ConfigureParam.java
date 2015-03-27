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
 * @author jaddy0302 Rivulet ConfigureParam.java 2010-3-1
 * 
 */
@Entity
@Table(name = "rivu_configureparam")
@org.hibernate.annotations.Proxy(lazy = false)
public class ConfigureParam implements Serializable{
	private String id ;
	private String name ; 
	private int defaultthreads = 20;
	private int operationmode = 2;  //Thread Mode ： Multithreading OR Single-threaded
	private int maxthreads = 200;
	private int countthreads = 200 ; //All task max Thread ; 
	private int cacherec = 50000 ;
	private int cachememory = 32;  //Max Cache Memory /MB
	private int maxcachememory = 200 ; //All Task Max Memory 
	private String configure ;
	private String taskplan ;
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
	 * @return the maxthreads
	 */
	public int getMaxthreads() {
		return maxthreads;
	}

	/**
	 * @param maxthreads the maxthreads to set
	 */
	public void setMaxthreads(int maxthreads) {
		this.maxthreads = maxthreads;
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
	 * @return the operationmode
	 */
	public int getOperationmode() {
		return operationmode;
	}

	/**
	 * @param operationmode the operationmode to set
	 */
	public void setOperationmode(int operationmode) {
		this.operationmode = operationmode;
	}

	/**
	 * @return the countthreads
	 */
	public int getCountthreads() {
		return countthreads;
	}

	/**
	 * @param countthreads the countthreads to set
	 */
	public void setCountthreads(int countthreads) {
		this.countthreads = countthreads;
	}

	/**
	 * @return the cacherec
	 */
	public int getCacherec() {
		return cacherec;
	}

	/**
	 * @param cacherec the cacherec to set
	 */
	public void setCacherec(int cacherec) {
		this.cacherec = cacherec;
	}

	/**
	 * @return the cachememory
	 */
	public int getCachememory() {
		return cachememory;
	}

	/**
	 * @param cachememory the cachememory to set
	 */
	public void setCachememory(int cachememory) {
		this.cachememory = cachememory;
	}

	/**
	 * @return the maxcachememory
	 */
	public int getMaxcachememory() {
		return maxcachememory;
	}

	/**
	 * @param maxcachememory the maxcachememory to set
	 */
	public void setMaxcachememory(int maxcachememory) {
		this.maxcachememory = maxcachememory;
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
		return id;
	}

	/**
	 * @return the taskplan
	 */
	@Transient
	public String getTaskplan() {
		return taskplan;
	}

	/**
	 * @param taskplan the taskplan to set
	 */
	public void setTaskplan(String taskplan) {
		this.taskplan = taskplan;
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
	 * @return the defaultthreads
	 */
	public int getDefaultthreads() {
		return defaultthreads;
	}

	/**
	 * @param defaultthreads the defaultthreads to set
	 */
	public void setDefaultthreads(int defaultthreads) {
		this.defaultthreads = defaultthreads;
	}
	
	
}
