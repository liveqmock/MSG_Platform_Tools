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

import org.hibernate.annotations.GenericGenerator;

/**
 * @author jaddy0302 Rivulet TableProperties.java 2010-3-22
 * 
 */
@Entity
@Table(name = "rivu_tableproperties")
@org.hibernate.annotations.Proxy(lazy = false)
public class TableProperties implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3601436061896426576L;


	public TableProperties(){}
	public TableProperties(String fieldname , String datatypename , int datatypecode , String tablename){
		this.fieldname = fieldname ;
		this.name = fieldname ;
		this.datatypecode = datatypecode ;
		this.datatypename = datatypename ;
		this.tablename = tablename;
	}
	
	private String id ;
	private String tablename ;
	private String dbtableid ;
	private String name ;
	private String code ;
	private String fieldname ;
	private int datatypecode ;
	private String datatypename ;
	private String indexdatatype ;
	private String groupid ;
	private String userid ;
	private TableProperties fkproperty ;
	private Boolean pk = false;
	private Boolean modits = false ;
	private String orgi ;
	private String viewtype;
	
	
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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * @return the fieldname
	 */
	public String getFieldname() {
		return fieldname;
	}
	/**
	 * @param fieldname the fieldname to set
	 */
	public void setFieldname(String fieldname) {
		this.fieldname = fieldname;
	}
	
	/**
	 * @return the datatypecode
	 */
	public int getDatatypecode() {
		return datatypecode;
	}
	/**
	 * @param datatypecode the datatypecode to set
	 */
	public void setDatatypecode(int datatypecode) {
		this.datatypecode = datatypecode;
	}
	/**
	 * @return the datatypename
	 */
	public String getDatatypename() {
		return datatypename;
	}
	/**
	 * @param datatypename the datatypename to set
	 */
	public void setDatatypename(String datatypename) {
		this.datatypename = datatypename;
	}
	
	/**
	 * @return the dbtableid
	 */
	public String getDbtableid() {
		return dbtableid;
	}
	/**
	 * @param dbtableid the dbtableid to set
	 */
	public void setDbtableid(String dbtableid) {
		this.dbtableid = dbtableid;
	}
	/**
	 * @return the indexdatatype
	 */
	public String getIndexdatatype() {
		return indexdatatype;
	}
	/**
	 * @param indexdatatype the indexdatatype to set
	 */
	public void setIndexdatatype(String indexdatatype) {
		this.indexdatatype = indexdatatype;
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
	 * @return the pk
	 */
	public Boolean getPk() {
		return pk;
	}
	/**
	 * @param pk the pk to set
	 */
	public void setPk(Boolean pk) {
		this.pk = pk;
	}
	/**
	 * @return the modits
	 */
	public Boolean getModits() {
		return modits;
	}
	/**
	 * @param modits the modits to set
	 */
	public void setModits(Boolean modits) {
		this.modits = modits;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="fkproperty")
	public TableProperties getFkproperty() {
		return fkproperty;
	}
	public void setFkproperty(TableProperties fkproperty) {
		this.fkproperty = fkproperty;
	}
	public String getTablename() {
		return tablename;
	}
	public void setTablename(String tablename) {
		this.tablename = tablename;
	}
	public String getViewtype() {
		return viewtype;
	}
	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}
	
}
