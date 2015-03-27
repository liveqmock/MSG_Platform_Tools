package com.neusoft.util.weixin;

public class WeiXinAPIMessage {
	private String id ;
	private String fromUser ;
	private String toUser; 
	private String createTime ;
	private String msgType ;
	private String content ;
	private String xmlContent ;
	
	public WeiXinAPIMessage(String id , String fromUser , String toUser , String createTime , String msgType , String content , String xmlContent)
	{
		this.id = id ; 
		this.fromUser = fromUser ;
		this.toUser = toUser ;
		this.createTime = createTime ;
		this.msgType = msgType ;
		this.content = content ;
		this.xmlContent = xmlContent ;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFromUser() {
		return fromUser;
	}
	public void setFromUser(String fromUser) {
		this.fromUser = fromUser;
	}
	public String getToUser() {
		return toUser;
	}
	public void setToUser(String toUser) {
		this.toUser = toUser;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMsgType() {
		return msgType;
	}
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getXmlContent() {
		return xmlContent;
	}
	public void setXmlContent(String xmlContent) {
		this.xmlContent = xmlContent;
	}
}
