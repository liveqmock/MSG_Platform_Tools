package com.neusoft.web.handler;

public class RequestData implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3803986571881734610L;
	private int p = 1;
	private int ps = 20 ;
	private String text ;
	private String q ;
	private String id;

	public int getP() {
		return p;
	}

	public void setP(int p) {
		this.p = p;
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public int getPs() {
		return ps;
	}

	public void setPs(int ps) {
		this.ps = ps;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
