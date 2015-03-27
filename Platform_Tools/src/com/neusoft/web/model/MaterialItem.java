package com.neusoft.web.model;

import java.io.File;
import java.io.Serializable;
import java.util.Date;

import org.jsoup.Jsoup;

public class MaterialItem implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String title;	//标题
	private Date createtime = new Date();
	private String createuser;//创建人
	private String description; //正文
	private String descriptionurl;//正文链接
	private String picurl; //图片
	private byte[] image ;		//发布读取的图片文件内容
	private byte[] html ;		//发布生成的HTML代码
	private String materialid;
	private String hispicurl;  //之前的url，修改时删除之前的图片
	public String getHispicurl() {
		return hispicurl;
	}
	public void setHispicurl(String hispicurl) {
		this.hispicurl = hispicurl;
	}
	public String getMaterialid() {
		return materialid;
	}
	public void setMaterialid(String materialid) {
		this.materialid = materialid;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCreateuser() {
		return createuser;
	}
	public void setCreateuser(String createuser) {
		this.createuser = createuser;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescriptionurl() {
		return descriptionurl;
	}
	public void setDescriptionurl(String descriptionurl) {
		this.descriptionurl = descriptionurl;
	}
	public String getPicurl() {
		return picurl;
	}
	public void setPicurl(String picurl) {
		this.picurl = picurl;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	public byte[] getHtml() {
		return html;
	}
	public void setHtml(byte[] html) {
		this.html = html;
	}
	public String getImageID(){
		return this.getMaterialid()+"_"+(this.picurl!=null && this.picurl.lastIndexOf(File.separator)>=0 ? this.picurl.substring(this.picurl.lastIndexOf(File.separator)+1,this.picurl.lastIndexOf(".")):"")+ (this.picurl!=null && this.picurl.lastIndexOf(".")>=0 ? this.picurl.substring(this.picurl.lastIndexOf(".")):""); 
	}
	
	public String getHtmlID(){
		return this.getMaterialid()+"_"+this.getId()+ ".htm"; 
	}
	
	public String getSubText(){
		String text = Jsoup.parse(this.description!=null ? this.description : "").text() ;
		return text.length()>100 ? text.substring(0, 100) : text ;
	}
}
