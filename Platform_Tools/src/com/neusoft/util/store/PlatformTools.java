package com.neusoft.util.store;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.hadoop.io.MD5Hash;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jsoup.Jsoup;
import org.quartz.CronExpression;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.neusoft.core.EapDataContext;
import com.neusoft.tools.TempletLoader;
import com.neusoft.util.tools.HttpClientTools;
import com.neusoft.web.model.SearchResultTemplet;
import com.neusoft.web.model.UserTemplet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class PlatformTools implements ITools{

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
	public static Map<String, String> fieldMap = new HashMap<String,String>();
	private static HttpClient httpclient = initHttpClientPool();
	private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(EapDataContext.getDateformat());

	private static HttpClient initHttpClientPool() {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params, 40000);
		HttpConnectionParams.setSoTimeout(params, 40000);
		ConnManagerParams.setMaxTotalConnections(params, 500);
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
	
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
	
		ClientConnectionManager cm = new ThreadSafeClientConnManager(params, schemeRegistry);
		return new DefaultHttpClient(cm, params);
	}

	public static boolean isSystemField(String field) {
		return (field != null) && (fieldMap.get(field.toLowerCase()) != null);
	}

	public static String getAnalyzerTime(int ta) {
		Calendar c = Calendar.getInstance();
		c.add(5, -ta);
		Date monday = c.getTime();
		String preMonday = sdf.format(monday);
		return preMonday;
	}

	public static String getAnalyzerFirstDay() {
		Calendar c = Calendar.getInstance();
		c.set(5, c.getActualMinimum(5));
		return sdf.format(c.getTime());
	}

	public static String getRegexGroup(String text, String regex) {
		String value = null;
		Matcher matcher = Pattern.compile(regex).matcher(text);
		if ((matcher.find()) && (matcher.groupCount() >= 1)) {
			value = matcher.group(1);
		}
		return value;
	}

	public static String md5(String str) {
		return String.valueOf(MD5Hash.digest(str).halfDigest()).replace("-", "a");
	}

	public static String getId(String str) {
		return md5(str);
	}

	public static String formatDate(Date date) {
		return dateFormat.format(date);
	}

	public static byte[] toBytes(Object object) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream objectOutput = new ObjectOutputStream(out);
		objectOutput.writeObject(object);
		return out.toByteArray();
	}

	public static Object toObject(byte[] data) throws Exception {
		ByteArrayInputStream input = new ByteArrayInputStream(data);
		ObjectInputStream objectInput = new ObjectInputStream(input);
		return objectInput.readObject();
	}

	public static String getURL(String url, String param, String enc) {
		StringBuffer strb = new StringBuffer().append(url);
		if (param != null && param.length() > 0) {
			strb.append("?").append(param);
		}
		HttpGet httpget = new HttpGet(strb.toString());
		try {
			HttpEntity resEntity = HttpClientTools.getHttpClient().execute(httpget).getEntity();
			BufferedReader reader = null;
			if (resEntity != null) {
				reader = new BufferedReader(new InputStreamReader(resEntity
						.getContent(), enc!=null && enc.length()>0 ? enc : "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append("\r\n");
				}
				return sb.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param url
	 * @return
	 */
	public static byte[] getURLData(String url) {
		HttpGet httpget = new HttpGet(url);
		java.io.ByteArrayOutputStream output = new ByteArrayOutputStream() ;
		try {
			HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
			HttpEntity entity = response.getEntity(); 
			if (entity != null && entity.isStreaming()) { 
				 int len = 0 ;
				 byte[] data = new byte[1024] ;
				 InputStream input = entity.getContent() ;
				 while((len = input.read(data))>0){
					 output.write(data, 0, len) ;
				 }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}finally{
			try {
				output.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return output.toByteArray();
	}

	public static String getURL(String url, String enc) {
		return getURL(url, null , enc);
	}

	public static String postData(String url, List<NameValuePair> nvps) {
		HttpPost httpost = new HttpPost(url);
		try {
			httpost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
			ResponseHandler responseHandler = new BasicResponseHandler();
			return (String) httpclient.execute(httpost, responseHandler);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	public static String postFileData(String url, String file, String enc) {
		HttpPost httpost = new HttpPost(url);
		try {
			MultipartEntity mpEntity = new MultipartEntity();
			FileBody fileBody = new FileBody(new File(file) , "image/jpeg");
	        mpEntity.addPart("uploadfile",fileBody);
	        StringBody formid = new StringBody("");  
	        mpEntity.addPart("formId", formid) ;
	        httpost.setEntity(mpEntity) ;
	        HttpEntity resEntity = HttpClientTools.getHttpClient().execute(httpost).getEntity();
			BufferedReader reader = null;
			if (resEntity != null) {
				reader = new BufferedReader(new InputStreamReader(resEntity.getContent() , enc!=null && enc.length()>0 ? enc : "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append("\r\n");
				}
				return sb.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	public static String postString(String url, String text, String enc) {
		HttpPost httpost = new HttpPost(url);
		try {
			StringEntity myEntity = new StringEntity(text, "UTF-8");
	        httpost.setEntity(myEntity) ;
			HttpEntity resEntity = HttpClientTools.getHttpClient().execute(httpost).getEntity();
			BufferedReader reader = null;
			if (resEntity != null) {
				reader = new BufferedReader(new InputStreamReader(resEntity.getContent() , enc!=null && enc.length()>0 ? enc : "UTF-8"));
				StringBuffer sb = new StringBuffer();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
					sb.append("\r\n");
				}
				return sb.toString();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	public static String getSSLURL(String url) {
		HttpGet httpget = new HttpGet(url);
		try {
			ResponseHandler responseHandler = new BasicResponseHandler();
			return (String) HttpClientTools.getHttpClient().execute(httpget, responseHandler);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param job
	 * @return
	 */
	public static List <NameValuePair> createNamePair(Object data) {
		List<NameValuePair> nvps=new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("sign", "")) ;
		nvps.add(new BasicNameValuePair("data", JSON.toJSONString(data,SerializerFeature.WriteClassName))) ;
		return nvps ;
	}

	/**
	 * 根据ID或代码获取模板
	 * @param code
	 * @return
	 */
	public static SearchResultTemplet getSearchResultTemplet(String code) {
		List<SearchResultTemplet> tableViewList = EapDataContext.getService().findAllByCriteria(DetachedCriteria.forClass(SearchResultTemplet.class).add(Restrictions.or(Restrictions.eq("id", code), Restrictions.eq("code", code)) ));
		return tableViewList.size()>0?tableViewList.get(0) : null;
		
	}

	/**
	 * 
	 * @param templetid
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public static String getTemplet(SearchResultTemplet resultTemplet, Map<String , Object> values)
			throws IOException, TemplateException {
				StringWriter writer = new StringWriter(); 
				Configuration cfg = null;
				Template template = null ;
				if(resultTemplet!=null){
					cfg = new Configuration();
					TempletLoader loader = new TempletLoader(resultTemplet.getTemplettext()!=null ? resultTemplet.getTemplettext() : "") ;
					cfg.setTemplateLoader(loader);   
					cfg.setDefaultEncoding("UTF-8");  
					template = cfg.getTemplate("");
					template.process(values, writer);  
				}
				return writer.toString() ;
			}

	/**
	 * 
	 * @param templetid
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public static String getTemplet(UserTemplet userTemplet, Map<String , Object> values)
			throws IOException, TemplateException {
				StringWriter writer = new StringWriter(); 
				Configuration cfg = null;
				Template template = null ;
				if(userTemplet!=null){
					cfg = new Configuration();
					TempletLoader loader = new TempletLoader(userTemplet.getTemplettext()!=null ? userTemplet.getTemplettext() : "") ;
					cfg.setTemplateLoader(loader);   
					cfg.setDefaultEncoding("UTF-8");  
					template = cfg.getTemplate("");
					template.process(values, writer);  
				}
				return writer.toString() ;
			}

	/**
	 * 
	 * @param templetid
	 * @throws IOException 
	 * @throws TemplateException 
	 */
	public static String getTemplet(String templet, Map<String , Object> values)
			throws IOException, TemplateException {
				StringWriter writer = new StringWriter(); 
				Configuration cfg = null;
				Template template = null ;
				if(templet!=null){
					cfg = new Configuration();
					TempletLoader loader = new TempletLoader(templet) ;
					cfg.setTemplateLoader(loader);   
					cfg.setDefaultEncoding("UTF-8");  
					template = cfg.getTemplate("");
					template.process(values, writer);  
				}
				return writer.toString() ;
			}

	/**
	 * 
	 * @param token
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String token, String signature, String timestamp,
			String nonce) {  
			    String[] arr = new String[]{token,timestamp,nonce};  
			    Arrays.sort(arr);  
			    StringBuilder content = new StringBuilder();  
			    for(int i=0;i<arr.length;i++){  
			        content.append(arr[i]);  
			    }  
			    MessageDigest md = null;  
			    String tmpStr = null;  
			      
			    try {  
			        md = MessageDigest.getInstance("SHA-1");  
			        byte[] digest = md.digest(content.toString().getBytes());  
			        tmpStr = byteToStr(digest);  
			    } catch (NoSuchAlgorithmException e) {  
			        e.printStackTrace();  
			    }  
			      
			    content = null;  
			    return tmpStr!=null?tmpStr.equals(signature.toUpperCase()):false;  
			}

	private static String byteToHexStr(byte ib) {  
	    char[] Digit = {  
	            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',  
	            'D', 'E', 'F'  
	        };  
	    char[] ob = new char[2];  
	    ob[0] = Digit[(ib >>> 4) & 0X0F];  
	    ob[1] = Digit[ib & 0X0F];  
	
	    String s = new String(ob);  
	    return s;  
	}

	private static String byteToStr(byte[] bytearray) {  
	    String strDigest = "";  
	    for (int i = 0; i < bytearray.length; i++) {  
	        strDigest += byteToHexStr(bytearray[i]);  
	    }  
	    return strDigest;  
	}

	/**
	 * HTML内容处理
	 * @param html
	 * @return
	 */
	public static String htmlProcess(String html, String host, String...tags) {
		return html!=null?Jsoup.parse(html.replaceAll("<br[ /]{0,}>", "---r3---").replaceAll("<BR[ /]{0,}>", "---r3---")).text(host,tags).replaceAll("---r3---", "\n"):"";
	}

	public static String compress(String str) throws IOException {
	  if (str == null || str.length() == 0) {
	    return str;
	  }
	  ByteArrayOutputStream out = new ByteArrayOutputStream();
	  GZIPOutputStream gzip = new GZIPOutputStream(out);
	  gzip.write(str.getBytes());
	  gzip.close();
	  return out.toString("ISO-8859-1");
	}

	public static String uncompress(String str) throws IOException {
	  if (str == null || str.length() == 0) {
	    return str;
	  }
	  ByteArrayOutputStream out = new ByteArrayOutputStream();
	  ByteArrayInputStream in = new ByteArrayInputStream(str
	      .getBytes("ISO-8859-1"));
	  GZIPInputStream gunzip = new GZIPInputStream(in);
	  byte[] buffer = new byte[256];
	  int n;
	  while ((n = gunzip.read(buffer)) >= 0) {
	    out.write(buffer, 0, n);
	  }
	  // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
	  return out.toString();
	}

	public static byte[] compress(byte[] data) throws IOException {
	  ByteArrayOutputStream out = new ByteArrayOutputStream();
	  GZIPOutputStream gzip = new GZIPOutputStream(out);
	  gzip.write(data);
	  gzip.close();
	  return out.toByteArray();
	}

	public static byte[] uncompress(byte[] input) throws IOException {
	  ByteArrayOutputStream out = new ByteArrayOutputStream();
	  ByteArrayInputStream in = new ByteArrayInputStream(input);
	  GZIPInputStream gunzip = new GZIPInputStream(in);
	  byte[] buffer = new byte[256];
	  int n;
	  while ((n = gunzip.read(buffer)) >= 0) {
	    out.write(buffer, 0, n);
	  }
	  // toString()使用平台默认编码，也可以显式的指定如toString("GBK")
	  return out.toByteArray();
	}

	/**
	 * 
	 * @param userName
	 * @param password
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void speed(File flac) throws ClientProtocolException,
			IOException {
				HttpPost httpost = new HttpPost("http://www.google.com/speech-api/v1/recognize?xjerr=1&client=chromium&lang=zh-CN&maxresults=1");
			    
			    httpost.setHeader("Content-Type", "audio/flac; rate=16000") ;
			    httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31") ;
			    
			    InputStreamEntity reqEntity = new InputStreamEntity(new FileInputStream(flac), -1);
			    reqEntity.setContentType("binary/octet-stream");
			    reqEntity.setChunked(true);
			    httpost.setEntity(reqEntity) ;
			    
			    HttpResponse response = HttpClientTools.getHttpClient().execute(httpost);
			    InputStream input = response.getEntity().getContent();
			    byte[] data = new byte[1024] ;
			    int len = 0 ;
			    StringBuffer strb = new StringBuffer() ;
			    while((len = input.read(data))>0){
			    	strb.append(new String(data , 0 , len)) ;
			    }
			    System.out.println(strb.toString());
			    
			}

	/**
	 * 
	 * @param crontabExp
	 * @return
	 * @throws ParseException
	 */
	public static CronExpression getFireTime(String crontabExp) throws ParseException {
		return new CronExpression(crontabExp);
		
	}

	public void index() throws IOException {
		File indexDir = new File(EapDataContext.REAL_PATH,DATA_INDEX_PATH)  ;
		if(indexDir.exists()){
			indexDir.mkdirs() ;
		}
		File updateTimeFile = new File(indexDir,UPDATE_TIMEFILE_NAME) ;
		if(!updateTimeFile.exists()){
			updateTimeFile.createNewFile();
		}
		String lastUpdateTime = FileUtils.readFileToString(updateTimeFile);
	}

	/**
	 * 
	 * @param crontabExp
	 * @return
	 * @throws ParseException
	 */
	public static Date getFinalFireTime(String crontabExp, Date date)
			throws ParseException {
				CronExpression expression = new CronExpression(crontabExp) ;
				return expression.getNextValidTimeAfter(date!=null ? date:new Date());
				
			}

	public PlatformTools() {
		super();
	}

}