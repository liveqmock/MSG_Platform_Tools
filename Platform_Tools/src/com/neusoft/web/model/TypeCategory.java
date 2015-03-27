package com.neusoft.web.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Sort;

@Entity
@Table(name = "rivu_typecategory")
@org.hibernate.annotations.Proxy(lazy = false)
public class TypeCategory  implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8699781935163431952L;
	private String id ;
	private String name ;
	private String title ;
	private String code ;
	private String orgi ;
	private String ctype ;
	private String parentid;
	private String iconstr ;
	private String iconskin ;
	private String description;
	private String catetype ;
	private String memo ;
	private List<SearchResultTemplet> templetList ;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getCtype() {
		return ctype;
	}
	public void setCtype(String ctype) {
		this.ctype = ctype;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getIconstr() {
		return iconstr;
	}
	public void setIconstr(String iconstr) {
		this.iconstr = iconstr;
	}
	public String getIconskin() {
		return iconskin;
	}
	public void setIconskin(String iconskin) {
		this.iconskin = iconskin;
	}
	@Transient
	public List<SearchResultTemplet> getTempletList() {
		return templetList;
	}
	public void setTempletList(List<SearchResultTemplet> templetList) {
		this.templetList = templetList;
	}
	public String getCatetype() {
		return catetype;
	}
	public void setCatetype(String catetype) {
		this.catetype = catetype;
	}
}
