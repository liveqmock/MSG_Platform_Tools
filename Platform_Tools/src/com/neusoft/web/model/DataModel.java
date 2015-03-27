package com.neusoft.web.model;

import java.util.Date;

public interface DataModel extends java.io.Serializable{
	/**
	 * 
	 * @return
	 */
	public String getId() ;
	
	/**
	 * 
	 * @return
	 */
	public String getClazz() ;
	
	/**
	 * 
	 * @return
	 */
	public String getUsername() ;
	
	public String getFreq () ;
	
	/**
	 * 
	 * @return
	 */
	public String getGroupname();
	
	public String getGroupid() ;
	
	
	public String getName() ;
	
	
	public String getType() ;
	
	public Date getLastcrawltime();
	
	public void setLastcrawltime(Date date) ;
	
	public Date getNextcrawl() ;
	
	public void setNextcrawl(Date date) ;
	
	public long getSince() ;
	
	
	public void setSince(long since) ;
	
}
