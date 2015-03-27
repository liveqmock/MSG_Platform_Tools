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

/**
 * @author jaddy0302 Rivulet Task.java 2010-3-1
 * 
 */
public abstract class Task implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1197892854256529004L;
	/**
	 * 2012-4-17日增加功能，用userid作为默认值的字段
	 * @return
	 */
	public abstract String getUserid() ;
	/**
	 * 
	 * @return
	 */
	public abstract String getId() ;
	/**
	 * 
	 * @return
	 */
	public abstract String getTaskstatus() ;
	
	/**
	 * 
	 * @param status
	 */
	public abstract void setTaskstatus(String status) ;
	
	/**
	 * 
	 * @return
	 */
	public abstract String getTaskname() ;
	
	
	/**
	 * 
	 * @return
	 */
	public abstract String getTaskplan() ;
	
	/**
	 * 
	 * @param lastupdate
	 */
	public abstract void setLastupdate(Date lastupdate) ;
	
	
	public abstract Date getLastupdate() ;
	/**
	 * 
	 * @return
	 */
	public abstract String getConfigure() ;
	
	/**
	 * 
	 * @return
	 */
	public abstract String getSecureconf() ;
	
	
	/**
	 * 
	 * @return
	 */
	public abstract String getSecure() ;
	
	
	/**
	 * 
	 * @return
	 */
	public abstract String getType() ;
	
	
	/**
	 * Search Result Preview page HTML Templet
	 * @return
	 */
	public abstract String getPreviewtemplet() ;
	
	
	/**
	 * Search Result List page HTML Templet
	 * @return
	 */
	public abstract String getListblocktemplet() ;
	
	
	public abstract String getCode() ;
	
	public abstract String getOrgi();
	
	public abstract void setOrgi(String orgi) ;
	/**
	 * 
	 * @return
	 */
	public boolean isRun(){
		return "1".equals(getTaskstatus())||"2".equals(getTaskstatus()) ;
	}
}
