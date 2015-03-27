/**
 * Licensed to the Rivulet under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     webapps/LICENSE-Rivulet
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.neusoft.task.resource;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.tika.metadata.Metadata;

import com.neusoft.util.PlatformMsgTools;
import com.neusoft.web.model.JobDetail;
/**
 * @author jaddy0302 Rivulet OutputTextFormat.java 2010-3-6
 * 
 */
public class OutputTextFormat implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6332226544447338993L;
	private String id ;
	private List<Object> metadata = new ArrayList<Object>();
	private String text ;
	private String sql ;
	private JobDetail job ;
	private String title ;
	private String parent ;
	private String path ;
	private Object data ;
	private String type ;
	private String contentType ;
	private boolean split = false ;
	public OutputTextFormat(JobDetail job){
		this.job = job ;
		this.data = "" ;
	}
	/**
	 * 
	 * @param id
	 * @param data
	 */
	public OutputTextFormat(String id , Object data){
		this.id = id ;
		this.data = data ;
	}
	/**
	 * 
	 * @param id
	 * @param path
	 * @param data
	 */
	public OutputTextFormat(String id ,String path, Object data){
		this.id = id ;
		this.path = path ;
		this.data = data ;
	}
	
	public List<Object> getMetadata() {
		return metadata;
	}
	public void setMetadata(List<Object> metadata) {
		this.metadata = metadata;
	}
	/**
	 * @return the text
	 */
	public String getText() {
		return text!=null?text:"";
	}
	/**
	 * @param text the text to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**
	 * @return the job
	 */
	public JobDetail getJob() {
		return job;
	}
	/**
	 * @param job the job to set
	 */
	public void setJob(JobDetail job) {
		this.job = job;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return this.title!=null?title:"";
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	
	public String getId() {
		return id!=null?id:"";
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getBytes() throws Exception{
		return PlatformMsgTools.toBytes(this).length;
	}
	/**
	 * @return the parent
	 */
	public String getParent() {
		return parent!=null ? parent : "";
	}
	/**
	 * @param parent the parent to set
	 */
	public void setParent(String parent) {
		this.parent = parent;
	}
	/**
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}
	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}
	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}
	/**
	 * 
	 */
	public void finalize(){
		try {
			super.finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public boolean isSplit() {
		return split;
	}
	public void setSplit(boolean split) {
		this.split = split;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
}
