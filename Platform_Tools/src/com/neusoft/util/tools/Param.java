package com.neusoft.util.tools;

public class Param {
	
	private String key ;
	private String value ; 
	private String type ; 		//输入类型,默认是 select ， 从索引字段中选择 ，  其他： 文本框，单选，复选 
	private String typevalue ;
	private String validate ;	//输入验证 的 代码 , 支持 regex
	private boolean mapping = false ;
	public Param(){
		
	}
	
	public Param(String key , String value , boolean mapping){
		this.key = key ; 
		this.value = value ;
		this.mapping = mapping ;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isMapping() {
		return mapping;
	}

	public void setMapping(boolean mapping) {
		this.mapping = mapping;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypevalue() {
		return typevalue;
	}

	public void setTypevalue(String typevalue) {
		this.typevalue = typevalue;
	}

	public String getValidate() {
		return validate;
	}

	public void setValidate(String validate) {
		this.validate = validate;
	}
}
