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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;

import org.apache.tika.config.TikaConfig;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.mime.MimeType;
import org.apache.tika.mime.MimeTypeException;

import com.neusoft.core.EapDataContext;
import com.neusoft.tools.CompressFile;
import com.neusoft.tools.ZipData;
import com.neusoft.web.model.JobDetail;

/**
 * @author jaddy0302 Rivulet Resource.java 2010-3-6
 * 
 */
public abstract class Resource {
	
	public static Logger log = Logger.getLogger(Resource.class.getName()) ;

	public static TikaConfig tikaConfig = TikaConfig.getDefaultConfig() ;
	
	/**
	 * Re connection
	 */
	public abstract JobDetail getJob();
	
	/**
	 * Re connection
	 */
	public abstract void process(OutputTextFormat meta , JobDetail job);

	/**
	 * synchronized
	 * Single-mode single-threaded access to records under a record
	 * 
	 * @return
	 */
	public abstract  OutputTextFormat next() throws Exception;

	/**
	 * 
	 * @return
	 */
	public abstract boolean isAvailable() ;
	
	
	/**
	 * 
	 * @return
	 */
	public abstract OutputTextFormat getText(OutputTextFormat object) throws Exception;
	
	/**
	 * 
	 * @return
	 */
	public abstract long getBytes(OutputTextFormat object) throws Exception;

	/**
	 * Close Resource , eg:database connection
	 * @throws Exception
	 */
	public abstract void close() throws Exception;
	
	/**
	 * ID
	 * @param object
	 * @return
	 */
	public abstract String getId(OutputTextFormat object) ;
	
	/**
	 * 
	 */
	public abstract void updateTask() ;
	
	
	/**
	 * 
	 * @param job
	 * @return
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws IllegalArgumentException
	 */
	public static Resource getResource(JobDetail job)
			throws Exception{
//		if(!LicenseHelper.vertifyNum()){
//			LicenseHelper.traceMessage() ;
//			job.setFetcher(false) ;
//			return null;
//		}
		return job != null
				&& EapDataContext.getResource(job.getTasktype()) != null ? (Resource) EapDataContext
				.getResource(job.getTasktype()).getConstructor(
						new Class[] { JobDetail.class }).newInstance(
						new Object[] { job })
				: null;

	}
	
		
	/**
	 * 
	 * @param documentFile
	 * @return
	 */
	public static String getMime(File documentFile){
		MimeType mimeType = null ;
		try {
			mimeType = tikaConfig.getMimeRepository().getMimeType(documentFile) ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mimeType!=null?mimeType.getName():"";
	}
	
	/**
	 * Filter
	 * @param file
	 * @param netFile
	 * @return
	 */
	public boolean val(String inputFile , String acceptDocType){
		String file = inputFile!=null ? inputFile.toLowerCase() :null ;
		return file!=null && acceptDocType!=null && ((acceptDocType.indexOf(file.substring(file.lastIndexOf(".")+1))>=0||acceptDocType.indexOf("all")>=0)) ;
	}
	
}
