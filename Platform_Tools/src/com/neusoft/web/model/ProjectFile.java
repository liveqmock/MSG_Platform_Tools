package com.neusoft.web.model;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.activation.FileTypeMap;
import javax.activation.MimetypesFileTypeMap;

import com.neusoft.util.tools.FileUtil;

public class ProjectFile implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6966524827314066788L;
	private ProjectFile parent ;
	private String name ;
	private long lastmodi ;
	private File file ;
	private String type ;
	private int sortno ;
	private String contentType ;
	private int level = 0;
	private String filepath ;
	private List<ProjectFile> fileList = new ArrayList<ProjectFile>();
	
	public ProjectFile(String name , String type, long lastmodi, ProjectFile parent,File file , int level , int sortno){
		this.name = name ;
		this.type = type ;
		this.lastmodi = lastmodi ;
		this.parent = parent ;
		this.file = file ;
		this.level = level ;
		this.sortno = sortno ;
		this.filepath = file.getAbsolutePath();
		this.contentType = FileUtil.getContextType(file.getName());
	}
	public ProjectFile getParent() {
		return parent;
	}
	public void setParent(ProjectFile parent) {
		this.parent = parent;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public long getLastmodi() {
		return lastmodi;
	}
	public void setLastmodi(long lastmodi) {
		this.lastmodi = lastmodi;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<ProjectFile> getFileList() {
		return fileList;
	}
	public void setFileList(List<ProjectFile> fileList) {
		this.fileList = fileList;
	}
	public File getFile() {
		return file;
	}
	public void setFile(File file) {
		this.file = file;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getSortno() {
		return sortno;
	}
	public void setSortno(int sortno) {
		this.sortno = sortno;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
