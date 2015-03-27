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
package com.neusoft.util.tools;

import java.util.Properties;

import java.util.* ;

import com.alibaba.fastjson.JSON;

/**
 * @author jaddy0302 Rivulet ParamTools.java 2010-3-21
 * 
 */
public class ParamTools {
	/**
	 * 
	 * @param properties
	 * @param paramsStr
	 * @param user
	 * @return
	 */
	public static Properties getProperties(Properties properties,String paramsStr,String user){
		String[] params = new String[]{};
		if(paramsStr !=null){
			params = paramsStr.split("[,， \r\n]") ;
		}
		//params = (paramsStr!=null?paramsStr:"").split("[,， \r\n]") ;
		properties = properties!=null ? properties : new java.util.Properties();
		for(String param:params){
			if(param!=null && param.length()>0){
				String[] paramField = param.split("[=:]") ;
				if(paramField.length==2){
					properties.put(paramField[0], paramField[1]);
				}
			}
		}
		Iterator iterator = properties.keySet().iterator() ;
		while(iterator.hasNext()){
			String key = (String)iterator.next() ; //((String)properties.get(iterator.next())) ;
			String value = ((String)properties.get(key)) ;
			if(value!=null && value.indexOf("{user}")>=0){
				properties.remove(key) ;
				if("true".equalsIgnoreCase((String)properties.get("upcase"))){					
					properties.put(key, user.toUpperCase());
				}else{
					properties.put(key, user);
				}
			}
		}
		
		return properties ;
	}
	/**
	 * 
	 * @param moreparam
	 * @return
	 */
	public static Properties parseParam(String moreparam){
		Properties properties = new Properties() ;
		if(moreparam!=null && !"".equals(moreparam)){
			String[] parames = moreparam.split("[,， \r\n]");
			for(String param:parames){
				String[] value = param.split("[=:]") ;
				if(value.length==2){
					properties.put(value[0], escape(value[1].replaceAll("\"",""))) ;
				}
			}
		}
		return properties ;
	}
	/**
	 * 
	 * @param moreparam
	 * @return
	 */
	public static Properties parseParamWidthOutWhiteSpace(String moreparam){
		Properties properties = new Properties() ;
		if(moreparam!=null && !"".equals(moreparam)){
			String[] parames = moreparam.split("[\r\n]");
			for(String param:parames){
				String[] value = param.split("=") ;
				if(value.length==2){
					properties.put(value[0], escape(value[1])) ;
				}
			}
		}
		return properties ;
	}
	/**
	 * 处理特殊字符，将 @@ 替换回 = ， ^^替换回 ：
	 * @param input
	 * @return
	 */
	public static String escape(String input){
		return input.replaceAll("<1>", "=").replaceAll("<2>", ":").replaceAll("$1", "=").replaceAll("$2", ":") ;
	}
	/**
	 * 
	 * @param moreparam
	 * @return
	 */
	public static Properties parseParamWithOutD(String moreparam){
		Properties properties = new Properties() ;
		if(moreparam!=null && !"".equals(moreparam)){
			String[] parames = moreparam.split("[ \r\n]");
			for(String param:parames){
				String[] value = param.split("[=:]") ;
				if(value.length==2){
					properties.put(value[0], escape(value[1].replaceAll("\"",""))) ;
				}
			}
		}
		return properties ;
	}
	/**
	 * 
	 * @param moreparam
	 * @return
	 */
	public static List<Param> parseParamToObject(String moreparam){
		List<Param> properties = new ArrayList<Param>() ;
		if(moreparam!=null && !"".equals(moreparam)){
			String[] parames = moreparam.split("[\r\n]");
			for(String text:parames){
				Param param = JSON.parseObject(text, Param.class) ;
				if(param.getKey()!=null && param.getKey().length()>0){
					properties.add(param) ;
				}
			}
		}
		return properties ;
	}
	/**
	 * 只支持 param=value 的形式
	 * @param moreparam
	 * @return
	 */
	public static Properties parseParamO(String moreparam){
		Properties properties = new Properties() ;
		if(moreparam!=null && !"".equals(moreparam)){
			String[] parames = moreparam.split("[,， \r\n]");
			for(String param:parames){
				String[] value = param.split("[=]") ;
				if(value.length==2){
					properties.put(value[0], value[1].replaceAll("\"","")) ;
				}
			}
		}
		return properties ;
	}
	/**
	 * 
	 * @param paramsStr
	 * @return
	 */
	public static Properties getProperties(String paramsStr){
		Properties properties = new Properties();
		String[] params = paramsStr.split("[,， \r\n]") ;
		for(String param:params){
			if(param!=null && param.length()>0){
				String[] paramField = param.split("[=:]") ;
				if(paramField.length==2){
					properties.put(paramField[0], paramField[1]);
				}
			}
		}
		return properties ;
	}
}
