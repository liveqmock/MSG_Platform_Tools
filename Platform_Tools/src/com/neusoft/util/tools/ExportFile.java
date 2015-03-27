package com.neusoft.util.tools;

import java.io.OutputStream;

public interface ExportFile {
	public String getHeadTitle() ;
	public String getStartTime() ;
	public String getEndTime() ;
	
	public void setHeadTitle(String title) ;
	public void setStartTime(String startTime) ;
	public void setEndTime(String endtime);
	
	public void createFile(OutputStream out) throws Exception;
}
