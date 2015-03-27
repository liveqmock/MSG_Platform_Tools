package com.neusoft.tools;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.tools.zip.ZipOutputStream;
import org.apache.tools.zip.ZipFile;
/**
 * 
 * @author iceworld
 *
 */
public class ZipData {
	private String id ;
	private String name ;
	private InputStream input ;
	private ZipOutputStream zipOut ;
	private OutputStream output ;
	private Map<String,String> fileMap = new HashMap<String,String>();
	private ZipFile zipFile = null ;
	private int files = 0 ;
	private Enumeration<?> en ;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public InputStream getInput() {
		return input;
	}
	public void setInput(InputStream input) {
		this.input = input;
	}
	public ZipOutputStream getZipOut() {
		return zipOut;
	}
	public void setZipOut(ZipOutputStream zipOut) {
		this.zipOut = zipOut;
	}
	public int getFiles() {
		return files;
	}
	public void setFiles(int files) {
		this.files = files;
	}
	
	
	public Map<String, String> getFileMap() {
		return fileMap;
	}
	public void setFileMap(Map<String, String> fileMap) {
		this.fileMap = fileMap;
	}
	public ZipFile getZipFile() {
		return zipFile;
	}
	public void setZipFile(ZipFile zipFile) {
		this.zipFile = zipFile;
	}
	public Enumeration<?> getEn() {
		return en;
	}
	public void setEn(Enumeration<?> en) {
		this.en = en;
	}
	public OutputStream getOutput() {
		return output;
	}
	public void setOutput(OutputStream output) {
		this.output = output;
	}
	public void close(){
		if(output!=null){
			try {
				output.close() ;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(this.zipOut!=null){
			try {
				this.zipOut.close() ;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
