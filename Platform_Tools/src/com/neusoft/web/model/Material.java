package com.neusoft.web.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.alibaba.fastjson.JSON;
@Entity
@Table(name = "rivu_material")
@org.hibernate.annotations.Proxy(lazy = false)
public class Material  implements Serializable {
	private static final long serialVersionUID = 1L;
	private String id;
	private String orgi; //租户标识
	private String title;	//标题
	private String code; //编码
	private String materialtype;
	private Date createtime = new Date();
	private String createuser;//创建人
	private String description; //正文
	private String descriptionurl;//正文链接
	private String picurl; //顶部图片
	private String childitems;
	private boolean status=false;
	@Transient
	private List<MaterialItem> mItems;
	
	@Transient
	private List<MaterialItem> tempMaterialItems;
	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Transient
	public List<MaterialItem> getmItems() {
		if(this.description!=null&&!"".equals(this.description)){
			return JSON.parseArray(this.description, MaterialItem.class);
		}
		return new ArrayList<MaterialItem>();
	}
	public void setmItems(List<MaterialItem> mItems) {
		this.mItems = mItems;  
	}
	public String getMaterialtype() {
		return materialtype;
	}
	public void setMaterialtype(String materialtype) {
		this.materialtype = materialtype;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getChilditems() {
		return childitems;
	}
	public void setChilditems(String childitems) {
		this.childitems = childitems;
	}
	@Transient
	public List<MaterialItem> getTempMaterialItems() {
		return tempMaterialItems;
	}
	public void setTempMaterialItems(List<MaterialItem> tempMaterialItems) {
		this.tempMaterialItems = tempMaterialItems;
	}
	
}
