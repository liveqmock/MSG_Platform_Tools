package com.neusoft.core.channel;

public class DataMessage implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6138942348082045608L;
	private String id ;
	private String userid ;				//用户ID
	private String type ;			//消息类型 ： Web IM消息
	private String time;			//时间戳
	private String sign ;			//签名
	private String orgi;			//租户
	private Channel channel ;		//渠道消息
	private boolean event ;			//事件消息
	private String eventcode ;		//事件code
	public DataMessage(){}
	public DataMessage( String type , Channel channel , String orgi , String userid){
		this.type = type ;
		this.orgi = orgi ;
		this.channel = channel ;
		this.userid = userid ;
	}
	public DataMessage(String id, String type , Channel channel , String orgi , String userid){
		this.type = type ;
		this.orgi = orgi ;
		this.channel = channel ;
		this.userid = userid ;
		this.id = id ;
	}
	public DataMessage(String type , Channel channel , String orgi , String userid , boolean event , String eventcode){
		this.type = type ;
		this.orgi = orgi ;
		this.channel = channel ;
		this.userid = userid ;
		this.event = event ;
		this.eventcode = eventcode ;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public long getLength(){
		long length = 0 ;
		if(this.channel!=null && this.channel.getText()!=null){
			length = length + this.channel.getText().getBytes().length ;
		}
		if(this.channel!=null && this.channel.getBytedata()!=null){
			length = length + this.channel.getBytedata().length ;
		}
		return length ;
	}
	public boolean isEvent() {
		return event;
	}
	public void setEvent(boolean event) {
		this.event = event;
	}
	public String getEventcode() {
		return eventcode;
	}
	public void setEventcode(String eventcode) {
		this.eventcode = eventcode;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
