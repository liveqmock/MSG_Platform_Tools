package com.neusoft.util.tools;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jsoup.Jsoup;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.core.EapDataContext;
import com.neusoft.core.channel.DataMessage;
import com.neusoft.core.channel.WeiXin;
import com.neusoft.core.channel.WeiXinGroup;
import com.neusoft.core.channel.WeiXinUser;
import com.neusoft.util.PlatformMsgTools;
import com.neusoft.util.weixin.WeiXinAPIMessage;
import com.neusoft.web.model.SNSAccount;


public class WeiXinTools {
	private static String token ;
	private static String URL_CHARACTER = "UTF-8" ;
	private static Map<String, WeiXinToken> weixinTokens = new HashMap<String, WeiXinToken>();
	/**
	 * 
	 * @param orgi
	 * @return
	 */
	public static SNSAccount getSnsAccount(String orgi){
		SNSAccount weixinAccount = null ;
		List<SNSAccount> accountlist=null;
		List<SNSAccount> gwaccountlist=EapDataContext.getAccountList();
		if(gwaccountlist!=null && gwaccountlist.size()>0){
			accountlist=gwaccountlist;
		}else{
			accountlist=EapDataContext.getService().findAllByCriteria(DetachedCriteria.forClass(SNSAccount.class).add(Restrictions.eq("snstype", EapDataContext.ChannelTypeEnum.WEIXIN.toString())));
			EapDataContext.setAccountList(accountlist);
		}
		for(SNSAccount account : accountlist){
			if(account.getSnstype()!= null && orgi.equals(account.getOrgi()) && EapDataContext.SNSTypeEnum.WEIXIN.toString().equals(account.getSnstype())){
				weixinAccount = account ;
			}
		}
		return weixinAccount ;
	}
	private static WeiXinToken getWeixinToken(String orgi) throws Exception{
		SNSAccount weixinAccount = getSnsAccount(orgi) ;
		HttpGet httpget = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+weixinAccount.getAppkey()+"&secret="+weixinAccount.getToken());
		HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
		WeiXinToken	token = JSON.parseObject(infoMessage(response.getEntity()), WeiXinToken.class);
		weixinTokens.put(orgi, token) ;
    	return token;
	}
	public static void login(String orgi){
		try {
			SNSAccount weixinAccount = getSnsAccount(orgi) ;
//			System.out.println("account:"+weixinAccount.getUsername()+" pass:"+weixinAccount.getPassword());
			if(weixinAccount!=null){
				login(weixinAccount.getUsername(),weixinAccount.getPassword());
			}
//			login("nancy@rivues.com","rivues123");
//			login("bigocean.an@gmail.com","Ocean123123");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * @param userName
	 * @param password
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void login(String userName , String password) throws ClientProtocolException, IOException{
		HttpPost httpost = new HttpPost("https://mp.weixin.qq.com/cgi-bin/login?lang=zh_CN");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("username", userName));
        nvps.add(new BasicNameValuePair("pwd", getMD5Str(password)));
        nvps.add(new BasicNameValuePair("imgcode", "Xn5K"));
        nvps.add(new BasicNameValuePair("f", "json"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, URL_CHARACTER));
        
        httpost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8") ;
        httpost.setHeader("Origin", "https://mp.weixin.qq.com") ;
        httpost.setHeader("Referer", "https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN") ;
        httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31") ;
        
        //sig=h01a4f7f483617e0c95ac326ca0ffc937db10e16e51f535714210065ae7b188499fecd6167ae644f2a4; cert=x7UjzxjLYrZMnuT_SGrKJbsFRHzwArk5;
        HttpResponse response = HttpClientTools.getHttpClient().execute(httpost);
        
        String json = infoMessage(response.getEntity()) ;
        JSONObject array = JSON.parseObject(json) ; 
        String url = array.getString("ErrMsg") ;
        if(url!=null && url.indexOf("token=") > 0){
        	token = url.substring(url.indexOf("token=")+6, url.length()) ;
        }
        
	}
	/**
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	static int totalcount=0;
	static int failcount=0;
	static int sucesscount=0;
	public static void sentTextMsg(String orgi , String toUser , String message , DataMessage dataMessage) throws ClientProtocolException, IOException,Exception{
		SNSAccount account=WeiXinTools.getSnsAccount(orgi);
	    boolean hightAPI=account!=null?"1".equals(account.getApipoint()):false;
	    System.out.println("消息内容："+message+"=====orgi======"+orgi+"====hightAPI====="+hightAPI+"====toUser====="+toUser);
	    if(hightAPI && message!=null && message.length()>0){
	    	String sendmsg=message;
	    	if(sendmsg.indexOf("touser")<0){
	    		sendmsg="{\"touser\":\""+toUser+"\",\"msgtype\":\"text\",\"text\":{\"content\":\""+message+"\"}}";
	    	}
	    	String result=PlatformMsgTools.postString("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+getToken(orgi).getAccess_token(), sendmsg, "utf-8");
	    	if(result.indexOf("ok")<0){
	    		++failcount;
	    	}else{
	    		++sucesscount;
	    	}
	    	++totalcount;
	    	System.out.println("消息发送返回结果："+result+"==总数==="+totalcount+"==成功数==="+sucesscount+"==失败数==="+failcount);
	    }else{
	    	login(orgi);
			HttpPost httpost = new HttpPost("https://mp.weixin.qq.com/cgi-bin/singlesend");
	        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	        if(dataMessage!=null && dataMessage.getChannel()!=null && message.startsWith(EapDataContext.MessageType.NEWS.toString())){
	        	dataMessage.getChannel().setMessagetype(EapDataContext.MessageType.NEWS.toString()) ;
	        	if(message.length()>5){
	        		dataMessage.getChannel().setText(message.substring(5)) ;
				}
	        }
	        if(dataMessage!=null && EapDataContext.MessageType.NEWS.toString().equals(dataMessage.getChannel().getMessagetype())){
	        	nvps.add(new BasicNameValuePair("type", "10"));
	        	nvps.add(new BasicNameValuePair("fid", message));
	        	nvps.add(new BasicNameValuePair("appmsgid", message));
	        	nvps.add(new BasicNameValuePair("error", "false"));
	        	nvps.add(new BasicNameValuePair("imgcode", ""));
		        nvps.add(new BasicNameValuePair("ajax", "1"));
		        nvps.add(new BasicNameValuePair("tofakeid", toUser));
		        nvps.add(new BasicNameValuePair("token", token));
	        }else{
		        nvps.add(new BasicNameValuePair("type", "1"));
		        nvps.add(new BasicNameValuePair("t", "ajax-response"));
		        nvps.add(new BasicNameValuePair("lang", "zh_CN"));
		        nvps.add(new BasicNameValuePair("imgcode", ""));
		        nvps.add(new BasicNameValuePair("tofakeid", toUser));
		        nvps.add(new BasicNameValuePair("token", token));
		        nvps.add(new BasicNameValuePair("content", message));
		        nvps.add(new BasicNameValuePair("random", String.valueOf(Math.random())));
	        }
	        httpost.setEntity(new UrlEncodedFormEntity(nvps, URL_CHARACTER));
	        httpost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8") ;
	        httpost.setHeader("Origin", "https://mp.weixin.qq.com") ;
	        httpost.setHeader("Referer", "https://mp.weixin.qq.com/cgi-bin/singlesendpage?tofakeid=1147704322&t=message/send&action=index&token="+token+"&lang=zh_CN") ;
	        httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31") ;
	        HttpResponse response = HttpClientTools.getHttpClient().execute(httpost);
	        infoMessage(response.getEntity()) ;
	    }
		
	}
	
	/**
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void sendImage(String orgi , String toUser , String message , DataMessage dataMessage) throws ClientProtocolException, IOException{
		login(orgi);
		HttpPost httpost = new HttpPost("https://mp.weixin.qq.com/cgi-bin/uploadmaterial?cgi=uploadmaterial&type=0&token="+token+"&t=iframe-uploadfile&lang=zh_CN");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        if(dataMessage!=null && EapDataContext.MessageType.IMAGE.toString().equals(dataMessage.getChannel().getMessagetype())){
        	nvps.add(new BasicNameValuePair("type", "2"));
        	nvps.add(new BasicNameValuePair("fid", message));
        	nvps.add(new BasicNameValuePair("fileid", message));
        	nvps.add(new BasicNameValuePair("error", "false"));
        	nvps.add(new BasicNameValuePair("imgcode", ""));
	        nvps.add(new BasicNameValuePair("ajax", "1"));
	        nvps.add(new BasicNameValuePair("tofakeid", toUser));
	        nvps.add(new BasicNameValuePair("token", token));
	        nvps.add(new BasicNameValuePair("content", ""));
        }
        httpost.setEntity(new UrlEncodedFormEntity(nvps, URL_CHARACTER));
        httpost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8") ;
        httpost.setHeader("Origin", "https://mp.weixin.qq.com") ;
        httpost.setHeader("Referer", "https://mp.weixin.qq.com/cgi-bin/singlemsgpage?token="+token+"&fromfakeid="+toUser+"&msgid=&source=&count=20&t=ajax-singlechat&lang=zh_CN") ;
        httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31") ;
        HttpResponse response = HttpClientTools.getHttpClient().execute(httpost);
        infoMessage(response.getEntity()) ;
	}
	
	@SuppressWarnings("unused")
	public static void getWeixinGroupListall(String orgi) throws ClientProtocolException, IOException{
		//login(orgi);
		HttpGet ghttpget = new HttpGet("https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&pagesize=10&pageidx=0&type=0&groupid=0&token="+token+"&lang=zh_CN");
		HttpResponse gresponse = HttpClientTools.getHttpClient().execute(ghttpget);
        String gtem=infoMessage(gresponse.getEntity());
        String groupstr="";
        if(gtem.indexOf("\"groups\":[{")>0 && gtem.indexOf("}).groups")>0){
        	groupstr=gtem.substring(gtem.indexOf("\"groups\":[{")+9, gtem.indexOf("}).groups"));
        }
        System.out.println(groupstr);
        int pageCount=0;
        if(gtem.indexOf("pageCount :")>0 && gtem.indexOf(",\npageSize")>0){
        	pageCount=Integer.parseInt(gtem.substring(gtem.indexOf("pageCount :")+12,gtem.indexOf(",\npageSize")));
        }
        List<WeiXinGroup> groups=JSON.parseArray(groupstr, WeiXinGroup.class) ;
        for (WeiXinGroup group : groups) {
        	List<WeiXinUser> users=null;
			if(pageCount>0){
				for (int i = 0; i < pageCount; i++) {
					HttpGet httpget = new HttpGet("https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&pagesize=100&pageidx="+i+"&type=0&groupid="+group.getId()+"&token="+token+"&lang=zh_CN");
					HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
			        String tem=infoMessage(response.getEntity());
			        if(tem.indexOf("\"contacts\":[{")>0 && tem.indexOf("}).contacts")>0){
			        	tem=tem.substring(tem.indexOf("\"contacts\":[{")+11, tem.indexOf("}).contacts"));
			        	tem=tem.replaceAll("\"id\"", "\"fakeId\"");
			        	tem=tem.replaceAll("\"nick_name\"", "\"orgi\":\""+orgi+"\",\"nickName\"");
				        tem=tem.replaceAll("\"remark_name\"", "\"reMarkName\"");
				        tem=tem.replaceAll("\"group_id\"", "\"groupID\"");
				        System.out.println(group.getName()+"的粉丝列表为："+tem);
				        users= JSON.parseArray(tem, WeiXinUser.class) ;
			        }
				}
			}
		}
        
	}
	public static List<WeiXinGroup> getWeixinGroupList(String orgi) throws ClientProtocolException, IOException{
		login(orgi);
		HttpGet ghttpget = new HttpGet("https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&pagesize=10&pageidx=0&type=0&groupid=0&token="+token+"&lang=zh_CN");
		HttpResponse gresponse = HttpClientTools.getHttpClient().execute(ghttpget);
        String gtem=infoMessage(gresponse.getEntity());
        if(gtem.indexOf("\"groups\":[{")>0 && gtem.indexOf("}).groups")>0){
        	gtem=gtem.substring(gtem.indexOf("\"groups\":[{")+9, gtem.indexOf("}).groups"));
        }
        System.out.println("分组信息："+gtem);
        return JSON.parseArray(gtem, WeiXinGroup.class) ;
	}
	public static List<WeiXinUser> getWeixinUserList(String orgi ,WeiXinGroup group,int pageidx, int len) throws ClientProtocolException, IOException{
		//login(orgi);
		HttpGet httpget = new HttpGet("https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&pagesize="+len+"&pageidx="+pageidx+"&type=0&groupid="+group.getId()+"&token="+token+"&lang=zh_CN");
		HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
        String tem=infoMessage(response.getEntity());
        if(tem.indexOf("\"contacts\":[{")>0 && tem.indexOf("}).contacts")>0){
        	tem=tem.substring(tem.indexOf("\"contacts\":[{")+11, tem.indexOf("}).contacts"));
        	tem=tem.replaceAll("\"id\"", "\"fakeId\"");
            tem=tem.replaceAll("\"nick_name\"", "\"memo\":\""+group.getName()+"\",\"orgi\":\""+orgi+"\",\"nickName\"");
            tem=tem.replaceAll("\"remark_name\"", "\"reMarkName\"");
            tem=tem.replaceAll("\"group_id\"", "\"groupID\"");
            System.out.println(group.getName()+"分组下第"+pageidx+"页用户信息："+tem);
            return JSON.parseArray(tem, WeiXinUser.class) ;
        }
        return null;
	}
	/**
	 * 
	 * @return 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static List<WeiXin> getMessageList(String orgi) throws ClientProtocolException, IOException{
        return getMessageList(orgi , 500) ;
	}
	/**
	 * 
	 * @return 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	/*public static List<WeiXin> getMessageList(String orgi , int len) throws ClientProtocolException, IOException{
		login(orgi);
		HttpGet httpget = new HttpGet("https://mp.weixin.qq.com/cgi-bin/getmessage?t=ajax-message&lang=zh_CN&count="+len+"&token="+token);
        HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
        System.out.println(JSON.toJSON(response));
        return JSON.parseArray(infoMessage(response.getEntity()), WeiXin.class) ;
	}*/
	public static List<WeiXin> getMessageList(String orgi , int len) throws ClientProtocolException, IOException{
		login(orgi);
		HttpGet httpget = new HttpGet("https://mp.weixin.qq.com/cgi-bin/message?t=message/list&count="+len+"&day=7&token="+token+"&lang=zh_CN");
		System.out.println("==================httpget=============="+httpget.getURI());
		//HttpGet httpget = new HttpGet("https://mp.weixin.qq.com/cgi-bin/getmessage?t=ajax-message&lang=zh_CN&count="+len+"&token="+token);
		HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
        String tem=infoMessage(response.getEntity());
        if(tem.indexOf(":[{")>0 && tem.indexOf("}).msg_item")>0){
        	tem=tem.substring(tem.indexOf(":[{")+1, tem.indexOf("}).msg_item"));
        }
        System.out.println(tem);
        return JSON.parseArray(tem, WeiXin.class) ;
	}
	/**
	 * 
	 * @param message
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static WeiXin getWeiXin(String orgi , WeiXinAPIMessage message) throws ClientProtocolException, IOException{
		List<WeiXin> messageList = getMessageList( orgi , 500) ;
		WeiXin weiXinMessage = null;
		if(message!=null){
			for(WeiXin msg : messageList){
				long time = Long.parseLong(message.getCreateTime()) - Long.parseLong(msg.getDateTime());
				if(message.getContent()!=null && message.getContent().equals(StringEscapeUtils.unescapeHtml(msg.getContent())) || (message.getContent()==null || message.getContent().length()==0) && (time< 10 && time>-10)){
//				if(message.getContent()!=null && message.getContent().equals(StringEscapeUtils.unescapeHtml(msg.getContent())) || (message.getContent()==null || message.getContent().length()==0)){
					msg.setFromUserName(message.getFromUser()) ;
					msg.setToUserName(message.getToUser()) ;
					msg.setMsgid(message.getId()) ;
					msg.setMessagetype(message.getMsgType()) ;
					msg.setXmlMessage(message.getXmlContent()) ;
					weiXinMessage = msg ;
					break ;
				}
			}
		}
		return weiXinMessage ;
	}
	
	/**
	 * 
	 * @param message
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static byte[] getMedia(String orgi , String msgid) throws ClientProtocolException, IOException{
		login(orgi);
        return PlatformMsgTools.getURLData("https://mp.weixin.qq.com/cgi-bin/getvoicedata?token="+token+"&&msgid="+msgid) ;
	}
	
	/**
	 * 
	 * @param message
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static byte[] getMediaVideo(String orgi , String msgid) throws ClientProtocolException, IOException{
		login(orgi);
        return PlatformMsgTools.getURLData("https://mp.weixin.qq.com/cgi-bin/getvideodata?token="+token+"&msgid="+msgid) ;
	}
	/**
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static WeiXinUser getUserInfo(String orgi , String user) throws ClientProtocolException, IOException,Exception{
		SNSAccount account=WeiXinTools.getSnsAccount(orgi);
	    boolean hightAPI=account!=null?"1".equals(account.getApipoint()):false;
	    String tem=null;
	    if(hightAPI){
	    	HttpGet httpget = new HttpGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+getToken(orgi).getAccess_token()+"&openid="+user+"&lang=zh_CN");
	        HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
	        tem=infoMessage(response.getEntity()) ;
	        tem=tem.replaceAll("openid", "userid");
	    }else{
	    	login(orgi);
			
			HttpPost httpost = new HttpPost("https://mp.weixin.qq.com/cgi-bin/getcontactinfo");
	        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
	        if(true){
	        	nvps.add(new BasicNameValuePair("t", "ajax-getcontactinfo"));
		        nvps.add(new BasicNameValuePair("lang", "zh_CN"));
		        nvps.add(new BasicNameValuePair("random", String.valueOf(Math.random())));
		        nvps.add(new BasicNameValuePair("token", token));
		        nvps.add(new BasicNameValuePair("fakeid", user));
	        }
	        httpost.setEntity(new UrlEncodedFormEntity(nvps, URL_CHARACTER));
	        httpost.setHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8") ;
	        httpost.setHeader("Origin", "https://mp.weixin.qq.com") ;
	        httpost.setHeader("Referer", "https://mp.weixin.qq.com/cgi-bin/contactmanage?t=user/index&pagesize=10&pageidx=0&type=0&groupid=0&token="+token+"&lang=zh_CN") ;
	        httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31") ;
	        HttpResponse response = HttpClientTools.getHttpClient().execute(httpost);
			tem=infoMessage(response.getEntity());
			if(tem!=null && tem.indexOf("{\"fake_id\":")>0){
				tem=tem.substring(tem.indexOf("{\"fake_id\":"),tem.indexOf(",\"groups\":{"));
				tem=tem.replaceAll("_id", "Id");
				tem=tem.replaceAll("_name", "Name");
			}
	    }
	    System.out.println("UserInfo============:"+tem);
	    return JSON.parseObject(tem , WeiXinUser.class) ;
	}
	
	/**
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void getUserMessageList(String orgi ,  String user) throws ClientProtocolException, IOException{
		login(orgi);
		HttpGet httpget = new HttpGet("https://mp.weixin.qq.com/cgi-bin/singlemsgpage?fromfakeid="+user+"&msgid=&source=&count=2000&t=ajax-message&lang=zh_CN&token="+token);
        HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
        infoMessage(response.getEntity()) ;
	}
	 /** 
     * MD5 加密 
     */  
    public static String getMD5Str(String str) {  
        MessageDigest messageDigest = null;  
  
        try {  
            messageDigest = MessageDigest.getInstance("MD5");  
  
            messageDigest.reset();  
  
            messageDigest.update(str.getBytes("UTF-8"));  
        } catch (NoSuchAlgorithmException e) {  
            System.out.println("NoSuchAlgorithmException caught!");  
            System.exit(-1);  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
  
        byte[] byteArray = messageDigest.digest();  
  
        StringBuffer md5StrBuff = new StringBuffer();  
  
        for (int i = 0; i < byteArray.length; i++) {              
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)  
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));  
            else  
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));  
        }  
  
        return md5StrBuff.toString();  
    }  
    /**
     * 输出entity内容，获取和输出返回的HTML文
     * @param entity
     * @throws IllegalStateException
     * @throws IOException
     */
    @SuppressWarnings("deprecation")
	private static String infoMessage(HttpEntity entity)
            throws IllegalStateException, IOException {

        if (entity == null) {
            return "";
        }
        InputStream is = entity.getContent();
        int len = 0 ;
        byte[] data = new byte[1024] ;
        StringBuffer strb = new StringBuffer();
        while((len = is.read(data))>0){
        	strb.append(new String(data, 0 , len , "UTF-8")) ;
        }
        entity.consumeContent(); // entity销毁
//        System.out.println(strb.toString());
        return strb.toString() ;
    }
//    private String getImage(String ){
//    	
//    }
    @SuppressWarnings("unused")
	public static void main(String[] args) throws Exception{
//    	getMedia("rivues" , "NESRpbPZAGKVu_aEMX20qv1r19JNFnvd_YTZ9EUjykluE3XuWgKis3zm7W6CA6qb");
//    	sentTextMsg("rivues" , "1147704322" , "test" , null);
//    	login("rivues");
//    	DataMessage dm = new DataMessage();
//    	dm.setChannel(new WeiXin()) ;
//    	dm.getChannel().setMessagetype("text") ;
//    	sentTextMsg("rivues", "1147704322", "10000068", dm);
//    	String content = EapTools.postFileData( "https://mp.weixin.qq.com/cgi-bin/uploadmaterial?cgi=uploadmaterial&type=0&token="+token+"&t=iframe-uploadfile&lang=zh_CN" ,"C:\\Users\\admin\\Desktop\\v4\\img\\glyphicons_halflings.png") ;
//    	System.out.println(content);
//    	getWeiXinUser("rivues","oqa_Sjo7sJ5ZeYySUmsVcjjBtj1s");
    	/*String tem= ":[{'id':10104,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377450570,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''},{'id':10101,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377447023,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''},{'id':10100,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377446899,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''},{'id':10099,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377446883,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''},{'id':10098,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377446288,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''},{'id':10097,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377445727,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''},{'id':10096,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377444856,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''},{'id':10095,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377444500,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''},{'id':10094,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377444491,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''},{'id':10093,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377444067,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''}]}).msg_item";
//    	String tem= "[{'id':10104,'type':1,'fakeid':'1352667822','nick_name':'kerwin','date_time':1377450570,'content':'?','source':'','msg_status':4,'has_reply':0,'refuse_reason':''}]";
        System.out.println(tem.substring(tem.indexOf(":[{")+1, tem.indexOf("}).msg_item")));
       List<WeiXin> list=JSON.parseArray(tem.substring(tem.indexOf(":[{")+1, tem.indexOf("}).msg_item")) , WeiXin.class) ;
       System.out.println(list.get(0).getDateTime());*/
//        JSON.parseArray(tem , WeiXinUser.class) ;
//    	login("dianzishangwu-ghq@sinosig.com","ygbxgw-ghq123!");
    	/*WeiXinGroup group =new WeiXinGroup();
    	group.setId("0");
    	group.setName("未分组");
    	List<WeiXinUser> list=getWeixinUserList("rivues",group,0,10);*/
//    	WeiXinUser user=getUserInfo("test", "1352667822");
//    	List<WeiXinGroup> list=getWeixinGroupList("rivues");
    	//getWeixinGroupListall("rivues");
    	
    	/*HttpGet httpget = new HttpGet("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx518f6ac1c229a2b8&secret=23ee51161641f56c0c19dbe7f801629c");
        HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
        WeiXinToken token = JSON.parseObject(infoMessage(response.getEntity()), WeiXinToken.class) ;

    	String menutext="{\"touser\":\"ocP-Sjp4LscPJoOe0kUFk7fr2BDE\",\"template_id\":\"xaub6tanVtyaB8J6TTV8q5xzJd97AbaCq34Lw5jIkak\",\"url\":\"www.sinosig.com\",\"data\":{\"name\":{\"value\":\"阳光保险一路随行旅行测试版\",\"color\":\"#173177\"},\"remark\":{\"value\":\"有效期：2013年11月23号-2013年12月23号券号为QQ5024813399,密码为123456890,测试随时可以发消息，确保消息和模板的使用场景是一致的.内部测试！\",\"color\":\"#ff0000\"}}}";
    	System.out.println(EapTools.postString("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token.getAccess_token(), menutext , "UTF-8") ) ;
    	*/
        String text="{\"touser\":\"oqa_SjtOLvWAyS8ynNtvJjWzIzwg\",\"msgtype\":\"text\",\"text\":{ \"content\":\"无对话\"}}";
    	System.out.println(PlatformMsgTools.postString("https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=b3WBXAbUOMiIdr9p4T7BPqa1Jqewn131yS1AeGjdYQmPVFMur5OjpOqnm6Otmkih7uZMc6HDahMSilrtlo1t0GYudE6mKw2JDscy-7em8gngnFVnNsHwUd5e-A9DZU_YZG6N5y7ZuCUCS7_WoMHoxQ", text , "UTF-8") ) ;
    	
    	
    	
    	/*String access_token="lyDeHERbFaA2OmSoKZoKqyVyH03vN8DlVFrSANdcbb_6Oa2DfyqbPNzuladSpjTj6M6ExpktaSyRk-4vPVnFM_ZN8PBzVn5zOJkSpUU4ScptPh1aOVNPpdGb8ziUSYMaoaFqKFwyLrPReA7FYiraSQ";
    	HttpGet httpget = new HttpGet("https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid=oqa_SjtOLvWAyS8ynNtvJjWzIzwg&lang=zh_CN");
        HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
        String tem=infoMessage(response.getEntity());
        tem=tem.replaceAll("openid", "userid");
        WeiXinUser wu=JSON.parseObject(tem , WeiXinUser.class) ;
        System.out.println(wu) ;
    	*/
//    	login("aia2013222@qq.com","A123456A123");
    }
   
	public static String findDateFromXml(String xml,String target){
		String result=null;
		if(xml!=null && target!=null && xml.indexOf(target)>=0){
			int start_index=xml.indexOf("<"+target+">")+target.length()+2;
			int end_index=xml.indexOf("</"+target+">");
			if(start_index>0 && end_index>0){
				result=xml.substring(start_index,end_index);
			}
		}
		return result;
	}
    public static WeiXinToken getToken(String orgi) throws Exception{
    	WeiXinToken token = weixinTokens.get(orgi) ;
    	if(token==null || !token.isAva()){
    		try {
				token = getWeixinToken(orgi);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
        return token ;
    }
    /**
     * 创建企业菜单
     * @param orgi
     * @param menutext
     * @throws Exception
     */
    public static void createMenu(String orgi, String menutext) throws Exception{
    	WeiXinToken token = weixinTokens.get(orgi) ;
    	if(token==null || !token.isAva()){
    		token = getToken(orgi);
    	}
    	/**
    	 * 先删除
    	 */
    	PlatformMsgTools.getSSLURL("https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+token.getAccess_token());
    	/**
    	 * 然后创建
    	 */
    	System.out.println(PlatformMsgTools.postString("https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+token.getAccess_token(), menutext , "UTF-8") ) ;
    }
    
    public static WeiXinUser getWeiXinUser(String orgi , String openid) throws Exception{
//    	WeiXinToken token = weixinTokens.get(orgi) ;
//    	if(token==null || !token.isAva()){
//    		token = getToken(orgi);
//    	}
    	String text =   "{"+
				    	"    \"touser\":\"oqa_Sjo7sJ5ZeYySUmsVcjjBtj1s\","+
				    	"    \"msgtype\":\"text\","+
				    	"    \"text\":"+
				    	"    {"+
				    	"        \"content\":\"Hello World\""+
				    	"    }"+
				    	"}" ;
    	String value = PlatformMsgTools.getSSLURL("https://api.weixin.qq.com/cgi-bin/user/get?access_token=3bTxac7WEZ61_RtR3QnGqs_yoFuVkzOhGCqEnJC5r4SwTUeNA5rCD5oBVwTgW1C8C7i2QxbxyjoE1e3UAxXqjc0Q4n9aTA3CnOZnKyObPBAd6v8F9RqwM6reTShWwcflFZpd9Oeh0oROWMhe8sDm0A");
    	System.out.println(value);
    	return null ;
//    	return JSON.parseObject(, WeiXinUser.class) ;
    }
}
