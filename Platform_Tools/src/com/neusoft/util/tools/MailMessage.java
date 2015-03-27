package com.neusoft.util.tools;

/**
 * 邮件内容
 * @author Administrator
 *
 */
public class MailMessage implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2336747863761009768L;
	private String from ;
	private String to ;
	private String title ;
	private String cc ;
	private String body ;
	private String callback ;
	public MailMessage(){}
	/**
	 * 
	 * @param form
	 * @param to
	 * @param title
	 * @param cc
	 * @param body
	 */
	public MailMessage(String to , String title, String cc,String body , String callback){
		this.title = title ;
		this.to = to ; 
		this.cc = cc ;
		this.body = body ;
		this.callback = callback ;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCallback() {
		return callback;
	}
	public void setCallback(String callback) {
		this.callback = callback;
	}
}
