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

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author jaddy0302 Rivulet Reporter.java 2010-3-7
 * 
 */
public class Reporter implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1620523470991367318L;
	private String id ;
	private String status ;
	private String amount ;
	private long currentmin ;
	
	private AtomicInteger recivepages = new AtomicInteger();
	private AtomicInteger sendpages = new AtomicInteger();
	private AtomicInteger automessagepage  = new AtomicInteger();
	private AtomicLong sendbytes = new AtomicLong() ;
	private AtomicLong recivebytes  = new AtomicLong();
	private AtomicLong automessagebytes  = new AtomicLong();
	
	private AtomicInteger threads  = new AtomicInteger();
	
	private int lastminpages ;
	private long lastminbytes ;
	private int lastminthreads ;
	private int lastminautomessagepages ;
	
	
	private int agentnum ;
	private int usernum ;
	private int inserviceuser;
	private int lineupuser ;
	private int viplineupuser ;
	
	private int errors ;
	private String type ;
	private double speed ;
	private int filter = 0 ;
	private long bytespeed ;
	private Date createtime  = new Date();
	private Date starttime ;
	private Date endtime ;
	private String errormsg ;
	private String orgi ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public long getCurrentmin() {
		return currentmin;
	}
	public void setCurrentmin(long currentmin) {
		this.currentmin = currentmin;
	}
	
	public AtomicInteger getThreads() {
		return threads;
	}
	public void setThreads(AtomicInteger threads) {
		this.threads = threads;
	}
	public int getLastminpages() {
		return lastminpages;
	}
	public void setLastminpages(int lastminpages) {
		this.lastminpages = lastminpages;
	}
	public long getLastminbytes() {
		return lastminbytes;
	}
	public void setLastminbytes(long lastminbytes) {
		this.lastminbytes = lastminbytes;
	}
	public int getLastminthreads() {
		return lastminthreads;
	}
	public void setLastminthreads(int lastminthreads) {
		this.lastminthreads = lastminthreads;
	}
	public int getErrors() {
		return errors;
	}
	public void setErrors(int errors) {
		this.errors = errors;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getSpeed() {
		return speed;
	}
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	public int getFilter() {
		return filter;
	}
	public void setFilter(int filter) {
		this.filter = filter;
	}
	public long getBytespeed() {
		return bytespeed;
	}
	public void setBytespeed(long bytespeed) {
		this.bytespeed = bytespeed;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public AtomicInteger getAutomessagepage() {
		return automessagepage;
	}
	public void setAutomessagepage(AtomicInteger automessagepage) {
		this.automessagepage = automessagepage;
	}
	public int getLastminautomessagepages() {
		return lastminautomessagepages;
	}
	public void setLastminautomessagepages(int lastminautomessagepages) {
		this.lastminautomessagepages = lastminautomessagepages;
	}
	public int getAgentnum() {
		return agentnum;
	}
	public void setAgentnum(int agentnum) {
		this.agentnum = agentnum;
	}
	public int getUsernum() {
		return usernum;
	}
	public void setUsernum(int usernum) {
		this.usernum = usernum;
	}
	public int getInserviceuser() {
		return inserviceuser;
	}
	public void setInserviceuser(int inserviceuser) {
		this.inserviceuser = inserviceuser;
	}
	public int getLineupuser() {
		return lineupuser;
	}
	public void setLineupuser(int lineupuser) {
		this.lineupuser = lineupuser;
	}
	public int getViplineupuser() {
		return viplineupuser;
	}
	public void setViplineupuser(int viplineupuser) {
		this.viplineupuser = viplineupuser;
	}
	public AtomicInteger getRecivepages() {
		return recivepages;
	}
	public void setRecivepages(AtomicInteger recivepages) {
		this.recivepages = recivepages;
	}
	public AtomicInteger getSendpages() {
		return sendpages;
	}
	public void setSendpages(AtomicInteger sendpages) {
		this.sendpages = sendpages;
	}
	public AtomicLong getSendbytes() {
		return sendbytes;
	}
	public void setSendbytes(AtomicLong sendbytes) {
		this.sendbytes = sendbytes;
	}
	public AtomicLong getRecivebytes() {
		return recivebytes;
	}
	public void setRecivebytes(AtomicLong recivebytes) {
		this.recivebytes = recivebytes;
	}
	public AtomicLong getAutomessagebytes() {
		return automessagebytes;
	}
	public void setAutomessagebytes(AtomicLong automessagebytes) {
		this.automessagebytes = automessagebytes;
	}
}
