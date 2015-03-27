package com.neusoft.util.dataenum;

public enum WEIBO_DATA_TYPE {
	
	KEYWORD("keyword") ,USER("user") ,TOPIC("topic") ;
	private String value ;
	
	WEIBO_DATA_TYPE(String value){
		this.value = value ;
	}
	public String toString(){
		return this.value ;
	}
}
