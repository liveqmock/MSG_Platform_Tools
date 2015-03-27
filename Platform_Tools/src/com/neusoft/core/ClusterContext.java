package com.neusoft.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContextEvent;

public class ClusterContext {
	public static boolean isServer = false ;
	public static String DATA_FILE = "/WEB-INF/data" ;
	public static String LICENSE_FILE = "/WEB-INF/data/license" ;
	public static int serverPort = 880 ;
	public static String HTTP_PROC = "http" ;
	public static boolean started = true ;
	public static boolean connected = true ;  //连接上集群服务器 : 节点使用
	public static String shards = "" ;
	public static Map<String,Float> memberStates = new HashMap<String, Float>(); 
	public static int MAX_INDEX_DOCNUM = 10; //索引最大尺寸为10G
	public static Map<String , InetSocketAddress> serverMap = new HashMap<String, InetSocketAddress>();
	public static boolean commit = false  , optimize = false;
	
	public static void init(ServletContextEvent sce){
		ClusterContext.DATA_FILE = sce.getServletContext().getRealPath(ClusterContext.DATA_FILE) ;
		ClusterContext.LICENSE_FILE = sce.getServletContext().getRealPath(ClusterContext.LICENSE_FILE) ;
		String solrHome = sce.getServletContext().getRealPath("/WEB-INF/data/solr") ; 
		System.setProperty("solr.solr.home", solrHome);
	}
	
	/**
	 * solr.data.dir
	 * @param datadir
	 */
	public static void setDataDir(String datadir){
		System.setProperty("r3.data.dir", datadir) ;
	}
	
	/**
	 * 序列化对象
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public static byte[] toBytes(Object object) throws Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		java.io.ObjectOutputStream objectOutput = new ObjectOutputStream(out) ;
		objectOutput.writeObject(object) ;
	    return out.toByteArray();
	}
	/**
	 * 反序列化对象
	 * @param data
	 * @return
	 * @throws Exception
	 */
	public static Object toObject(byte[] data) throws Exception{
		ByteArrayInputStream input = new ByteArrayInputStream(data) ;
		java.io.ObjectInputStream objectInput = new ObjectInputStream(input) ;
		return objectInput.readObject() ;
	}
}
