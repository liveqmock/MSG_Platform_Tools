package com.neusoft.util.tools;


import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
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
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.neusoft.core.EapDataContext;
import com.neusoft.core.channel.DataMessage;
import com.neusoft.core.channel.WeiXin;
import com.neusoft.core.channel.WeiXinUser;
import com.neusoft.util.PlatformMsgTools;
import com.neusoft.util.weixin.WeiXinAPIMessage;
import com.neusoft.web.model.SNSAccount;


public class YiXinTools {
	private static String token ;
	private static String URL_CHARACTER = "UTF-8" ;
	private static Map<String, WeiXinToken> weixinTokens = new HashMap<String, WeiXinToken>();
	/**
	 * 
	 * @param orgi
	 * @return
	 */
	private static SNSAccount getSnsAccount(String orgi){
		SNSAccount yixinAccount = null ;
		for(SNSAccount account : EapDataContext.getAccountList()){
			if((account.isDefaultaccount() || yixinAccount==null) && account.getOrgi().equals(orgi) && EapDataContext.SNSTypeEnum.YIXIN.toString().equals(account.getSnstype())){
				yixinAccount = account ;
			}
		}
		return yixinAccount ;
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
		HttpPost httpost = new HttpPost("https://plus.yixin.im/rest/login");
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("account", userName));
        nvps.add(new BasicNameValuePair("password", getMD5Str(password)));
        nvps.add(new BasicNameValuePair("loginType", "YiXinUserId"));
        httpost.setEntity(new UrlEncodedFormEntity(nvps, URL_CHARACTER));
        httpost.setHeader("Content-Type", "application/x-www-form-urlencoded") ;
        httpost.setHeader("Origin", "https://plus.yixin.im") ;
        httpost.setHeader("Referer", "https://plus.yixin.im/logout") ;
        httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/29.0.1547.66 Safari/537.36") ;
        BasicClientCookie cookie = new BasicClientCookie ("hdmsg2Close","1");
        HttpClientTools.getHttpClient().getCookieStore().addCookie(cookie) ;
        
        //sig=h01a4f7f483617e0c95ac326ca0ffc937db10e16e51f535714210065ae7b188499fecd6167ae644f2a4; cert=x7UjzxjLYrZMnuT_SGrKJbsFRHzwArk5;
        HttpResponse response = HttpClientTools.getHttpClient().execute(httpost);
        String json = infoMessage(response.getEntity()) ;
        JSONObject array = JSON.parseObject(json) ; 
        if(array.getIntValue("code") == 200){
//        	token = url.substring(url.indexOf("token=")+6, url.length()) ;
        }
        
	}
	/**
	 * 
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public static void sentTextMsg(String orgi , String toUser , String message , DataMessage dataMessage) throws ClientProtocolException, IOException{
		login(orgi);
		HttpPost httpost = new HttpPost("https://plus.yixin.im/rest/messages/");
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
        	StringEntity myEntity = new StringEntity("{contactId:'"+toUser+"' ,message:'"+message+"'}", "UTF-8");
	        httpost.setEntity(myEntity) ;
        }
        httpost.setEntity(new UrlEncodedFormEntity(nvps, URL_CHARACTER));
        httpost.setHeader("Content-Type", "application/json") ;
        httpost.setHeader("Origin", "https://plus.yixin.im") ;
        httpost.setHeader("Referer", "https://plus.yixin.im/message/chat?contactId="+toUser) ;
        httpost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31") ;
        HttpResponse response = HttpClientTools.getHttpClient().execute(httpost);
        infoMessage(response.getEntity()) ;
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
		List<WeiXin> weixinList = new ArrayList<WeiXin>();
		HttpGet httpget = new HttpGet("https://plus.yixin.im/rest/messages?limit=30&offset=0");
		HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
        String json = infoMessage(response.getEntity());
        System.out.println("易信："+json);
        JSONObject array = JSON.parseObject(json) ; 
        if(array.getJSONObject("result")!=null && array.getJSONObject("result").getJSONArray("list")!=null){
        	JSONArray jsonArray = array.getJSONObject("result").getJSONArray("list") ;
        	for(int i=0 ; i<jsonArray.size() ;i++){
        		JSONObject object = jsonArray.getJSONObject(i) ;
        		JSONObject contentObject = object.getJSONObject("messageContent") ;
        		WeiXin weixin = new WeiXin();
        		if(contentObject!=null){
	        		weixin.setChannel(EapDataContext.ChannelTypeEnum.YIXIN.toString()) ;
	        		weixin.setType(contentObject.getString("type")) ;
	        		weixin.setSubtype(contentObject.getString("subType")) ;
	        		weixin.setContent(contentObject.getString("content")) ;
	        		weixin.setCreatedate(contentObject.getString("time")) ;
	        		weixin.setDateTime(contentObject.getString("time")) ;
	        		weixin.setCreatetime(new Date(contentObject.getLongValue("time"))) ;
	        		weixin.setType(contentObject.getString("type")) ;
	        		weixin.setOrgi(orgi) ;
        		}
        		
        		JSONObject userInfo = object.getJSONObject("userInfo") ;
        		if(userInfo!=null){
	        		weixin.setUserid(userInfo.getString("id")) ;
	        		weixin.setNickName(userInfo.getString("nick")) ;
	        		weixin.setUsername(userInfo.getString("id")) ;
	        		weixin.setFakeId(userInfo.getString("id")) ;
	        		weixin.setFromUserName(object.getString("nfrom")) ;
	        		weixin.setTouser(object.getString("nto"));
	        		weixin.setMsgid(object.getString("id")) ;
	        		weixin.setStarred(object.getString("star")) ;
	        		weixin.setOrgi(orgi) ;
        		}
        		
        		WeiXinUser user = new WeiXinUser() ;
        		user.setChannel(EapDataContext.ChannelTypeEnum.YIXIN.toString()) ;
        		user.setUserid(weixin.getUserid()) ;
        		user.setFakeId(weixin.getUserid()) ;
        		user.setNickName(weixin.getNickName()) ;
        		user.setApiusername(weixin.getUserid()) ;
        		user.setSex(userInfo.getString("sex")) ;
        		user.setCity(userInfo.getString("city")) ;
        		user.setSignature(userInfo.getString("signature")) ;
        		weixin.setSnsuser(user) ;
        		weixinList.add(weixin) ;
        	}
        }
        return weixinList ;
	}
	/**
	 * 
	 * @param message
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static WeiXin getWeiXin(String orgi , WeiXinAPIMessage message) throws ClientProtocolException, IOException{
		WeiXin weiXinMessage = null;
		int i=0;
		System.out.println(message.getXmlContent());
		while(weiXinMessage==null && i<3){
			List<WeiXin> messageList = getMessageList( orgi , 10) ;
			if(message!=null){
				for(WeiXin msg : messageList){
					long time = Long.parseLong(message.getCreateTime()) - Long.parseLong(msg.getDateTime());
						System.out.println("time:"+time+"  message.getco:"+message.getContent() + " msg:"+msg.getContent());
					if(message.getContent()!=null && message.getContent().equals(StringEscapeUtils.unescapeHtml(msg.getContent())) && (time<=50 && time>=-50)){
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
			if(weiXinMessage == null){
				try {
					System.out.println("未找到，线程等待");
					Thread.sleep(1500) ;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				i++;
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
	public static byte[] getMedia(String orgi , String uri) throws ClientProtocolException, IOException{
		login(orgi);
        return PlatformMsgTools.getURLData(uri) ;
	}
	
	/**
	 * 
	 * @param message
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static byte[] getMediaVideo(String orgi , String url) throws ClientProtocolException, IOException{
		login(orgi);
        return PlatformMsgTools.getURLData(url) ;
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
	public static void main(String[] args) throws Exception{
    	login("zuozhh@bjtelecom.net","test2013");
    	List<WeiXin> weixinList = getMessageList("rivues",30) ;
    	for(WeiXin weixin  : weixinList){
    		System.out.println(weixin.getUsername());
    		System.out.println(weixin.getSnsuser().getUsername());
    	}
    }
    
    
    public static WeiXinToken getToken(String orgi) throws Exception{
    	SNSAccount weixinAccount = getSnsAccount(orgi) ;
    	HttpGet httpget = new HttpGet("https://api.yixin.im/cgi-bin/token?grant_type=client_credential&appid="+weixinAccount.getAppkey()+"&secret="+weixinAccount.getToken());
        HttpResponse response = HttpClientTools.getHttpClient().execute(httpget);
        WeiXinToken token = JSON.parseObject(infoMessage(response.getEntity()), WeiXinToken.class) ;
        if(token!=null){
        	weixinTokens.put(orgi, token) ;
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
    	PlatformMsgTools.getSSLURL("https://api.yixin.im/cgi-bin/menu/create?access_token="+token.getAccess_token());
    	/**
    	 * 然后创建
    	 */
    	System.out.println(PlatformMsgTools.postString("https://api.yixin.im/cgi-bin/menu/create?access_token="+token.getAccess_token(), menutext , "UTF-8") ) ;
    }
}
