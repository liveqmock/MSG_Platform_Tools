package com.neusoft.util.dataenum;

public enum TIME_LINE_TYPE{
	
	USER_TIMELINE("1") ,PUB_TIMELINE("2") ,TOPIC("0") ;
	private String value ;
	
	TIME_LINE_TYPE(String value){
		this.value = value ;
	}
	public String toString(){
		return this.value ;
	}
}
