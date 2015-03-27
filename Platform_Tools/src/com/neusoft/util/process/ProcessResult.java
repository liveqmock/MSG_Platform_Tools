package com.neusoft.util.process;

import java.util.Map;

import com.neusoft.core.channel.SNSUser;

public class ProcessResult implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type  = 0 ;
	private Map<String,Object> resultVal ;
	private String responseBody;
	private SNSUser snsUser ;
	
	
	public String getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(String responseBody) {
		this.responseBody = responseBody;
	}

	public ProcessResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Map<String, Object> getResultVal() {
		return resultVal;
	}

	public ProcessResult(int type, Map<String, Object> resultVal, String responseBody) {
		super();
		this.type = type;
		this.resultVal = resultVal;
		this.responseBody = responseBody;
	}
	
	public ProcessResult(int type, Map<String, Object> resultVal) {
		super();
		this.type = type;
		this.resultVal = resultVal;
	}

	public void setResultVal(Map<String, Object> resultVal) {
		this.resultVal = resultVal;
	}

	public SNSUser getSnsUser() {
		return snsUser;
	}

	public void setSnsUser(SNSUser snsUser) {
		this.snsUser = snsUser;
	}
}
