package com.neusoft.web.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Proxy;

@Entity
@Table(name = "rivu_account")
@Proxy(lazy = false)
public class Account implements Serializable {
	private static final long serialVersionUID = 6885780733229621972L;
	private String id;// 主键id
	private String name; // 账户名称
	private String code; // 代码
	private String tid; // 租户ID
	private String orgi; // 实例ID
	private String userid; // 创建用户ID
	private String groupid; // groupid
	private int status; // 账户状态 ， 1：正常使用， 0：冻结
	private Date createtime ;
	private float balance; // 余额
	private float amount; // 总共
	private Date lastop; // 上次充值时间
	private String spare;// 备份字段				,变更用处，修改为   账户RSA签名
	private String spare1;// 备份字段

	@Id
	@Column(length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTid() {
		return this.tid;
	}

	public void setTid(String tid) {
		this.tid = tid;
	}

	public String getOrgi() {
		return this.orgi;
	}

	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}

	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getGroupid() {
		return this.groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getSpare() {
		return spare;
	}

	public void setSpare(String spare) {
		this.spare = spare;
	}

	public String getSpare1() {
		return spare1;
	}

	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Date getLastop() {
		return lastop;
	}

	public void setLastop(Date lastop) {
		this.lastop = lastop;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
}