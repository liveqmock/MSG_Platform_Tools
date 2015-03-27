package com.neusoft.util.tools;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class IOUtils {
	/**
	 * 
	 * @param fileName
	 * @param html
	 * @throws IOException 
	 */
	public static void writeFile(String fileName , String html) throws IOException{
		writeFile(new File(fileName) , html);
	}
	/**
	 * 
	 * @param templetFile
	 * @param html
	 * @throws IOException
	 */
	public static void writeFile(File templetFile , String html) throws IOException{
		if(!templetFile.getParentFile().exists()){
			templetFile.getParentFile().mkdirs();
		}
		FileOutputStream fos = null;
		OutputStreamWriter osw = null ;
		try {
			fos = new FileOutputStream(templetFile);
			osw = new OutputStreamWriter(fos, "UTF-8"); 
			org.apache.commons.io.IOUtils.write(html, osw) ;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(osw!=null){
				osw.close() ;
			}
			if(fos!=null){
				fos.close();
			}
		}
	}
}
