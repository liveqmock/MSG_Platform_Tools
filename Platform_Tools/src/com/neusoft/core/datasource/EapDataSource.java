package com.neusoft.core.datasource;

import com.neusoft.core.EapDataContext;

public class EapDataSource extends org.springframework.jdbc.datasource.DriverManagerDataSource{
	private String url ;
	
	@Override
	public String getUrl(){
		if(this.url==null){
			url = "jdbc:derby:"+EapDataContext.DATA_DIR+"/query;create=false;" ;
			super.setUrl(url) ;
		}
		return url ;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}	
