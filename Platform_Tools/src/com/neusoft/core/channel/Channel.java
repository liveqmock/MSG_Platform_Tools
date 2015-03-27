package com.neusoft.core.channel;

import java.util.Date;

import javax.persistence.Transient;

public interface Channel extends java.io.Serializable{
	public String getId() ;
	public String getChannel() ;
	public String getOrgi() ;
	public void setOrgi(String orgi) ;
	public void setText(String text) ;
	public String getText() ;
	public String getUser() ;
	public Date getCreatetime() ;
	@Transient
	public SNSUser getSnsuser() ;
	public void setSnsuser(SNSUser snsuser) ;
	public String getUsername() ;
	public void setUsername(String username) ;
	public String getUserid() ;
	public void setUserid(String userid);
	public String getContextid() ;
	public void setContextid(String contextid) ;
	public String getSource() ;
	public void setSource(String source) ;
	
	
	public void setTouser(String touser) ;
	public String getTouser() ;
	
	public String getMessagetype() ;
	
	public boolean isTopmessage() ;
	
	public void setMessagetype(String messagetype) ;
	
	public void setBytedata(byte[] data) ;
	
	public byte[] getBytedata() ;
	
	public void setReplytype(String type) ;	//回复类型	人工，指令，机器人
	
	public void setChannel(String channel) ;
	/**
	 * 回复类型
	 * @return
	 */
	public String getReplytype() ;				//获取回复类型
	
	
	public void setInstruct(String insid) ; //设置指令ID
	
	public String getInstruct() ;		//获取指令ID
	
	public String getPipeline() ;		//获得 管道ID，处理的Servlet
}
