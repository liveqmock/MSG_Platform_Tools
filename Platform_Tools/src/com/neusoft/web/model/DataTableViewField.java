package com.neusoft.web.model;

import java.lang.reflect.InvocationTargetException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.beanutils.PropertyUtils;
import org.hibernate.annotations.GenericGenerator;

import com.neusoft.core.EapDataContext;

@Entity
@Table(name = "rivu_datatableviewfield")
@org.hibernate.annotations.Proxy(lazy = false)
public class DataTableViewField implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
    
	private String id;               //主键ID
	private String viewid;           //视图表ID
	private String viewname;         //视图表名称
	private String code;             //视图表字段代码
	private String name;             //视图表字段名称 关联多语言表
	private TableProperties field;        //关联表字段
	private String datatype;         //数据类型
	private boolean display;           //是否显示
	private String viewtype;         //组件类型
	private boolean head;           //是否表头
	private boolean addfield;       //是否添加
	private boolean edit;           //是否修改
	private String validateadd;     //新增验证
	private String validateedit;    //修改验证
	private String validateother;   //其他验证
	private int length;             //字段长度
	private String classname;       //样式名称
	private String cssstyle;        //样式
	private boolean phonenumber=false; //是否电话号码
	private boolean readonly=false;    //是否只读
	private boolean querylistlshow=false;//是否果查询条件
	private String defaultvalue;     //默认值
	private boolean orderfield;      //是否排序字段
	private int ordersequence;       //排序序号
	private String ordertype;        //排序规则：正序-ASC；倒序-DESC
	private String demo;             //备注
	private String orgi;             //租户
	private String fieldtype ;		 //获得字段对应的 JAVABean数据类型
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	} 
	
	public String getDatatype() {
		return datatype;
	}
	@OneToOne
	@JoinColumn(name = "field", referencedColumnName = "id") 
	public TableProperties getField() {
		return field;
	}
	public void setField(TableProperties field) {
		this.field = field;
	}
	public void setDatatype(String datatype) {
		this.datatype = datatype;
	}
	public String getViewtype() {
		return viewtype;
	}
	public void setViewtype(String viewtype) {
		this.viewtype = viewtype;
	}
	
	public String getViewid() {
		return viewid;
	}
	public void setViewid(String viewid) {
		this.viewid = viewid;
	}
	public String getViewname() {
		return viewname;
	}
	public void setViewname(String viewname) {
		this.viewname = viewname;
	} 
	public boolean isDisplay() {
		return display;
	}
	public void setDisplay(boolean display) {
		this.display = display;
	}
	public boolean isHead() {
		return head;
	}
	public void setHead(boolean head) {
		this.head = head;
	}
	public boolean isAddfield() {
		return addfield;
	}
	public void setAddfield(boolean addfield) {
		this.addfield = addfield;
	}
	public boolean isEdit() {
		return edit;
	}
	public void setEdit(boolean edit) {
		this.edit = edit;
	}
	public String getValidateadd() {
		return validateadd;
	}
	public void setValidateadd(String validateadd) {
		this.validateadd = validateadd;
	}
	public String getValidateedit() {
		return validateedit;
	}
	public void setValidateedit(String validateedit) {
		this.validateedit = validateedit;
	}
	public String getValidateother() {
		return validateother;
	}
	public void setValidateother(String validateother) {
		this.validateother = validateother;
	}
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String getCssstyle() {
		return cssstyle;
	}
	public void setCssstyle(String cssstyle) {
		this.cssstyle = cssstyle;
	}
	public boolean isPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(boolean phonenumber) {
		this.phonenumber = phonenumber;
	}
	public boolean isReadonly() {
		return readonly;
	}
	public void setReadonly(boolean readonly) {
		this.readonly = readonly;
	}
	public boolean isQuerylistlshow() {
		return querylistlshow;
	}
	public void setQuerylistlshow(boolean querylistlshow) {
		this.querylistlshow = querylistlshow;
	}
	public String getDefaultvalue() {
		return defaultvalue;
	}
	public void setDefaultvalue(String defaultvalue) {
		this.defaultvalue = defaultvalue;
	}
	public boolean isOrderfield() {
		return orderfield;
	}
	public void setOrderfield(boolean orderfield) {
		this.orderfield = orderfield;
	}
	public int getOrdersequence() {
		return ordersequence;
	}
	public void setOrdersequence(int ordersequence) {
		this.ordersequence = ordersequence;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	@Transient
	public String getFieldtype() {
		return EapDataContext.getFieldType(this.getDatatype());
	}
	@Transient
	public Object getValue(Object obj ) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		Object value = PropertyUtils.getProperty(obj, this.code) ;
		return value!=null ? value : "" ;
	}
	
}
