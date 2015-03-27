package com.neusoft.core.channel;

public interface SNSUser  extends java.io.Serializable{
	public String getId() ;
	public String getChannel() ;
	public String getOrgi() ;
	public String getUserid() ;
	public void setUserid(String userid) ;
	public void setChannel(String channel) ;
	public void setOrgi(String orgi) ;
	public String getUsername() ;
	public boolean isVip() ;
	public String getApiusername() ;
	public void setApiusername(String apiusername) ;
	public boolean isUserbind() ;
	public void setUserbind(boolean userbind) ;
	public boolean isUserau() ;
	public void setUserau(boolean userau) ;
}
