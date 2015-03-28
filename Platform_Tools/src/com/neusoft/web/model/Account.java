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
//@Table(name = "rivu_account")
@Table(name = "EAP_ACCOUNT")
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
	private String signature;//账户RSA签名
	private String signatureType;//签名类型
	private String spare1;// 备份字段

	@Id
	@Column(name="ACCOUNT_ID",length = 32)
	@GeneratedValue(generator = "system-uuid")
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/***
	 * 账户名称
	 * @return
	 */
	@Column(name="ACCOUNT_NAME")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 账户代码
	 * @return
	 */
	@Column(name="ACCOUNT_CODE")
	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	/***
	 * 租户ID
	 * @return
	 */
	@Column(name="TENANTS_ID")
	public String getTid() {
		return this.tid;
	}
	
	
	
	public void setTid(String tid) {
		this.tid = tid;
	}

	/***
	 * 实例ID
	 * @return
	 */
	@Column(name="ORIGIN_ID")
	public String getOrgi() {
		return this.orgi;
	}

	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	/***
	 * 创建人ID
	 * @return
	 */
	@Column(name="USER_ID")
	public String getUserid() {
		return this.userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	/**
	 * 用户组
	 * @return
	 */
	@Column(name="GROUP_ID")
	public String getGroupid() {
		return this.groupid;
	}

	public void setGroupid(String groupid) {
		this.groupid = groupid;
	}

	/**
	 * 账户状态
	 * 1：正常；0：冻结
	 * @return
	 */
	@Column(name="ACCOUNT_STATUS")
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Column(name="ACCOUNT_SIGNATURE")
	public String getSignature() {
		return signature;
	}

	public void setSignature(String spare) {
		this.signature = spare;
	}

	@Column(name="RESERVE_1")
	public String getSpare1() {
		return spare1;
	}

	public void setSpare1(String spare1) {
		this.spare1 = spare1;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	@Column(name="FUNDS_BALANCE")
	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	@Column(name="TOTAL_AMOUNT")
	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	/**
	 * 最后一次操作时间
	 * @return
	 */
	@Column(name="LAST_OPERA_TIME")
	public Date getLastop() {
		return lastop;
	}

	public void setLastop(Date lastop) {
		this.lastop = lastop;
	}

	/***
	 * 账户创建时间
	 * @return
	 */
	@Column(name="CREATE_TIME")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	/**
	 * @return the signatureType
	 */
	@Column(name="SIGNATURE_TYPE")
	public String getSignatureType() {
		return signatureType;
	}

	/**
	 * @param signatureType the signatureType to set
	 */
	public void setSignatureType(String signatureType) {
		this.signatureType = signatureType;
	}
}