package com.neusoft.core.channel;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.solr.client.solrj.beans.Field;
import org.hibernate.annotations.GenericGenerator;

import com.neusoft.core.EapDataContext;

@Entity
@Table(name = "rivu_weixinuser")
@org.hibernate.annotations.Proxy(lazy = false)
public class WeiXinUser  implements SNSUser{
	private String channel = EapDataContext.ChannelTypeEnum.WEIXIN.toString();
	/**
	 * 
	 */
	private static final long serialVersionUID = -4769154805678542687L;
	@Field
	private String id ;
	@Field("str_userid")
	private String userid ;
	@Field("str_fakeid")
	private String fakeId ;
	@Field("str_apiusername")
	private String apiusername ;
	@Field("str_nickName")
	private String nickName ;
	@Field("str_reMarkName")
	private String reMarkName ;
	@Field("str_username")
	private String username ;
	@Field("str_signature")
	private String signature ;
	@Field("str_country")
	private String country ;
	@Field("str_province")
	private String province ;
	@Field("str_city")
	private String city ;
	@Field("str_sex")
	private String sex ;
	@Field("str_groupID")
	private String groupID ;
	@Field("orgi")
	private String orgi ;
	@Field("createtime")
	private Date createtime = new Date();
	@Field("createdate")
	private String createdate ;
	@Field("str_cardno")
	private String cardno ;
	@Field("str_email")
	private String email ;
	@Field("str_memo")
	private String memo ;			//用作于分组名称
	@Field("str_mobile")
	private String mobile ;
	@Field("bl_vip")
	private boolean vip ;
	
	private boolean userbind = false ;
	private String name 	;		//姓名
	private String crmid;			//crm id
	private String hrid ;			//HR 系统id
	private String thirdpartid ;	//第三方系统ID
	private boolean userau;
	
	
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
	public boolean isUserau() {
		return userau;
	}
	public void setUserau(boolean userau) {
		this.userau = userau;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getReMarkName() {
		return reMarkName;
	}
	public void setReMarkName(String reMarkName) {
		this.reMarkName = reMarkName;
	}
	public String getUsername() {
		return nickName;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getGroupID() {
		return groupID;
	}
	public void setGroupID(String groupID) {
		this.groupID = groupID;
	}
	public String getApiusername() {
		return apiusername;
	}
	public void setApiusername(String apiusername) {
		this.apiusername = apiusername;
	}
	public String getUserid() {
		return this.fakeId;
	}
	public void setUserid(String userid) {
		this.userid = userid;
		if(this.fakeId==null){
			this.fakeId = userid ;
		}
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getFakeId() {
		return fakeId;
	}
	public void setFakeId(String fakeId) {
		this.fakeId = fakeId;
	}
	public String getCardno() {
		return cardno;
	}
	public void setCardno(String cardno) {
		this.cardno = cardno;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public boolean isVip() {
		return vip;
	}
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	public boolean isUserbind() {
		return userbind;
	}
	public void setUserbind(boolean userbind) {
		this.userbind = userbind;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCrmid() {
		return crmid;
	}
	public void setCrmid(String crmid) {
		this.crmid = crmid;
	}
	public String getHrid() {
		return hrid;
	}
	public void setHrid(String hrid) {
		this.hrid = hrid;
	}
	public String getThirdpartid() {
		return thirdpartid;
	}
	public void setThirdpartid(String thirdpartid) {
		this.thirdpartid = thirdpartid;
	}
}
