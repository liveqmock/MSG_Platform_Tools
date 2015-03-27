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
package com.neusoft.web.listener;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.neusoft.core.ProjectDataContext;
import com.neusoft.core.EapDataContext;

/**
 * @author jaddy0302 Rivulet WebApplicationContextListener.java 2010-3-3
 * 
 */
public class WebApplicationContextListener implements HttpSessionListener,
		ServletContextListener, ServletContextAttributeListener {
	/**
* 
*/
	public void contextInitialized(ServletContextEvent sce) {
		EapDataContext.DATA_DIR = (sce.getServletContext().getRealPath(EapDataContext.DATA_DIR)).replaceAll("\\\\", "/") ;
		EapDataContext.TPL_DIR = (sce.getServletContext().getRealPath(EapDataContext.TPL_DIR)).replaceAll("\\\\", "/") ;
		ProjectDataContext.setPlatformserver(sce.getServletContext().getInitParameter("server")) ;
	}

	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void contextDestroyed(ServletContextEvent arg0) {
	}

	public void attributeAdded(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void attributeRemoved(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void attributeReplaced(ServletContextAttributeEvent arg0) {
		// TODO Auto-generated method stub

	}
}
