package com.neusoft.core.channel;

public class Attachment implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8651282240838403444L;
	private String id ;
	private String fileName ;
	private byte[] data ;
	private long length ;
	
	public Attachment(){}
	
	public Attachment(String fileName , long length , byte[] data){
		this.fileName = fileName ;
		this.length = length ;
		this.data = data ;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
}
