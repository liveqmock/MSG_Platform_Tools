package com.neusoft.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "rivu_pagedataview")
@org.hibernate.annotations.Proxy(lazy = false)
public class PageDataView implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1733044416265737502L;
	private String id ;
	private String pageview;
	private DataTableView dataview;
	
	/**
	 * @return the id
	 */
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
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="dataview")
	public DataTableView getDataview() {
		return dataview;
	}
	public void setDataview(DataTableView dataview) {
		this.dataview = dataview;
	}
	public String getPageview() {
		return pageview;
	}
	public void setPageview(String pageview) {
		this.pageview = pageview;
	}
	
}
