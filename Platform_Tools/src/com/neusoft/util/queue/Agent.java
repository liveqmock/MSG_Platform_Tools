package com.neusoft.util.queue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.neusoft.core.channel.SNSUser;

/**
 * 坐席
 * @author admin
 *
 */
public class Agent implements SNSUser{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8714024825018242944L;
	private String id ;				//坐席ID
	private String agentno ;			//坐席号码
	private float score ;			//坐席质检评分
	private String userid ;		//坐席名称
	private String username ;		//坐席类型
	private String orgi ;			//租户ID
	private String contextid ;		//会话ID
	private String channel ;
	private boolean vip ;			//无用
	private String apiusername ;
	private boolean userbind ;		//无用
	private AgentStatus status = new AgentStatus();		//坐席状态
	private boolean userau;
	
	private Map<String , Object> params = new HashMap<String , Object>() ;
	private List<AgentUser> userList = new ArrayList<AgentUser>() ;
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
	public String getAgentno() {
		return agentno;
	}
	public void setAgentno(String agentno) {
		this.agentno = agentno;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getOrgi() {
		return orgi;
	}
	public void setOrgi(String orgi) {
		this.orgi = orgi;
	}
	public String getContextid() {
		return contextid;
	}
	public void setContextid(String contextid) {
		this.contextid = contextid;
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
	public AgentStatus getStatus() {
		return status;
	}
	public void setStatus(AgentStatus status) {
		this.status = status;
	}
	public Map<String, Object> getParams() {
		return params;
	}
	public void setParams(Map<String, Object> params) {
		this.params = params;
	}
	public List<AgentUser> getUserList() {
		return userList;
	}
	public void setUserList(List<AgentUser> userList) {
		this.userList = userList;
	}
	public String getApiusername() {
		return apiusername;
	}
	public void setApiusername(String apiusername) {
		this.apiusername = apiusername;
	}
	public boolean isUserbind() {
		return userbind;
	}
	public void setUserbind(boolean userbind) {
		this.userbind = userbind;
	}
}
