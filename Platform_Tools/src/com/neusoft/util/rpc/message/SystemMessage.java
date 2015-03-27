package com.neusoft.util.rpc.message;

public class SystemMessage implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2783954509107640119L;
	private String type ;
	private Object message ;
	private String pipeline;
	public SystemMessage(){}
	public SystemMessage(String type , Object message){
		this.type = type ;
		this.message = message ;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public String getPipeline() {
		return pipeline;
	}
	public void setPipeline(String pipeline) {
		this.pipeline = pipeline;
	}
	
}
