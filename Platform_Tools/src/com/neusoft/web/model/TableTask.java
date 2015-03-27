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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author jaddy0302 Rivulet TableTask.java 2010-3-21
 * 
 */
@Entity
@Table(name = "rivu_tabletask")
@org.hibernate.annotations.Proxy(lazy = false)
public class TableTask implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3728229777159531557L;
	private String id;
	private String name;
	private String tablename;
	private Database database;
	private String code;
	private String secure;
	private String tabletype = "1"; // 1:Table : 2:SQL
	private String datasql ;
	private int startindex = 0;
	private Date updatetime ;
	private long updatetimenumber ;
	// private SecureConfigure secureconfigure;
	private Date lastupdate;
	private String taskname;
	private String taskplan; //改变用处， 改为 left
	private String taskstatus ;	//改变用处 ， 改为 top
	private String tasktype; // Automated tasks : Manual tasks
	private Date createtime;
	private String configure;	//改变用处，改为   链接对象 一
	private String secureconf;	//改变用处，改为   链接对象 二
	private String userid;
	private String groupid;
	private String previewtemplet ;
	private String listblocktemplet ;
	private String orgi ;
	@Transient
	private Connection conn = null ;
	@Transient
	private ResultSet resultSet = null ;
	@Transient
	private PreparedStatement statment = null ;
	
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	
	private Set<TableProperties> tableproperty;

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
	 * @param id
	 *            the id to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the secure
	 */
	public String getSecure() {
		return secure;
	}

	/**
	 * @param secure
	 *            the secure to set
	 */
	public void setSecure(String secure) {
		this.secure = secure;
	}

	/**
	 * @return the lastupdate
	 */
	public Date getLastupdate() {
		return lastupdate;
	}

	/**
	 * @param lastupdate
	 *            the lastupdate to set
	 */
	public void setLastupdate(Date lastupdate) {
		this.lastupdate = lastupdate;
	}

	/**
	 * @return the taskname
	 */
	public String getTaskname() {
		return taskname != null ? taskname : tablename;
	}

	/**
	 * @param taskname
	 *            the taskname to set
	 */
	public void setTaskname(String taskname) {
		this.taskname = taskname;
	}

	/**
	 * @return the taskplan
	 */
	public String getTaskplan() {
		return taskplan;
	}

	/**
	 * @param taskplan
	 *            the taskplan to set
	 */
	public void setTaskplan(String taskplan) {
		this.taskplan = taskplan;
	}

	/**
	 * @return the taskstatus
	 */
	public String getTaskstatus() {
		return taskstatus;
	}

	/**
	 * @param taskstatus
	 *            the taskstatus to set
	 */
	public void setTaskstatus(String taskstatus) {
		this.taskstatus = taskstatus;
	}

	/**
	 * @return the tasktype
	 */
	public String getTasktype() {
		return tasktype;
	}

	/**
	 * @param tasktype
	 *            the tasktype to set
	 */
	public void setTasktype(String tasktype) {
		this.tasktype = tasktype;
	}

	/**
	 * @return the createtime
	 */
	public Date getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime
	 *            the createtime to set
	 */
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the userid
	 */
	public String getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
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
	 * @param groupid
	 *            the groupid to set
	 */
	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	@Transient
	public String getType() {
		return "table";
	}

	/**
	 * @return the configure
	 */
	public String getConfigure() {
		return configure;
	}

	/**
	 * @param configure
	 *            the configure to set
	 */
	public void setConfigure(String configure) {
		this.configure = configure;
	}

	/**
	 * @return the secureconf
	 */
	public String getSecureconf() {
		return secureconf;
	}

	/**
	 * @param secureconf
	 *            the secureconf to set
	 */
	public void setSecureconf(String secureconf) {
		this.secureconf = secureconf;
	}

	/**
	 * @return the tablename
	 */
	public String getTablename() {
		return tablename;
	}

	/**
	 * @param tablename
	 *            the tablename to set
	 */
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	/**
	 * @return the database
	 */
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="databasetask")
	public Database getDatabase() {
		return database;
	}

	/**
	 * @param database the database to set
	 */
	public void setDatabase(Database database) {
		this.database = database;
	}

	/**
	 * @return the tabletype
	 */
	public String getTabletype() {
		return tabletype != null ? tabletype : "1";
	}

	/**
	 * @param tabletype
	 *            the tabletype to set
	 */
	public void setTabletype(String tabletype) {
		this.tabletype = tabletype;
	}

	/**
	 * @return the tableproperty
	 */
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name = "dbtableid")
	public Set<TableProperties> getTableproperty() {
		return tableproperty;
	}

	/**
	 * @param tableproperty
	 *            the tableproperty to set
	 */
	public void setTableproperty(Set<TableProperties> tableproperty) {
		this.tableproperty = tableproperty;
	}
	
	

	
	/**
	 * @return the startindex
	 */
	public int getStartindex() {
		return startindex;
	}

	/**
	 * @param startindex the startindex to set
	 */
	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}

	/**
	 * @return the updatetime
	 */
	public Date getUpdatetime() {
		return updatetime;
	}

	/**
	 * @param updatetime the updatetime to set
	 */
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}

	/**
	 * @return the updatetimenumber
	 */
	public long getUpdatetimenumber() {
		return updatetimenumber;
	}

	/**
	 * @param updatetimenumber the updatetimenumber to set
	 */
	public void setUpdatetimenumber(long updatetimenumber) {
		this.updatetimenumber = updatetimenumber;
	}

	public String getDatasql() {
		return datasql;
	}

	public void setDatasql(String datasql) {
		this.datasql = datasql;
	}

	public String getPreviewtemplet() {
		return previewtemplet;
	}

	public void setPreviewtemplet(String previewtemplet) {
		this.previewtemplet = previewtemplet;
	}

	public String getListblocktemplet() {
		return listblocktemplet;
	}

	public void setListblocktemplet(String listblocktemplet) {
		this.listblocktemplet = listblocktemplet;
	}
	@Transient
	public Connection getConn() {
		return conn;
	}
	public void setConn(Connection conn) {
		this.conn = conn;
	}
	@Transient
	public ResultSet getResultSet() {
		return resultSet;
	}
	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	@Transient
	public PreparedStatement getStatment() {
		return statment;
	}
	public void setStatment(PreparedStatement statment) {
		this.statment = statment;
	}
	@Transient
	public void close() throws SQLException{
		if(this.resultSet!=null){
			this.resultSet.close();
		}
		if(this.statment!=null){
			this.statment.close();
		}
		if(this.conn!=null){
			this.conn.close();
		}
	}
}
