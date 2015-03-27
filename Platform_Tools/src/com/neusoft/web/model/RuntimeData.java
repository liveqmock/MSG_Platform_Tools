/**
 * 
 */
package com.neusoft.web.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author iceworld
 *
 */
public class RuntimeData implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3578328546410630835L;
	private long allocateMemory = Runtime.getRuntime().maxMemory();
	private long remainingMemory  = Runtime.getRuntime().maxMemory() - Runtime.getRuntime().totalMemory();
	private long usingMemory  = Runtime.getRuntime().totalMemory();
	private String operatingSystem = System.getProperty("os.name");
	private String jdkPath = System.getProperty("java.home");
	private String userPath = System.getProperty("user.dir");
	private String osUser = System.getProperty("user.name");
	private String jvm = System.getProperty("java.vm.name");
	private String jreVersion = System.getProperty("java.class.version");
	private String jvmVersion = System.getProperty("java.version");
	private List rpcServer = new ArrayList();
	private Date starttime = new Date();
	private Reporter report = new Reporter();
	public long getAllocateMemory() {
		return allocateMemory;
	}
	public void setAllocateMemory(long allocateMemory) {
		this.allocateMemory = allocateMemory;
	}
	public long getRemainingMemory() {
		return remainingMemory;
	}
	public void setRemainingMemory(long remainingMemory) {
		this.remainingMemory = remainingMemory;
	}
	public long getUsingMemory() {
		return usingMemory;
	}
	public void setUsingMemory(long usingMemory) {
		this.usingMemory = usingMemory;
	}
	public String getOperatingSystem() {
		return operatingSystem;
	}
	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}
	public String getJdkPath() {
		return jdkPath;
	}
	public void setJdkPath(String jdkPath) {
		this.jdkPath = jdkPath;
	}
	public String getUserPath() {
		return userPath;
	}
	public void setUserPath(String userPath) {
		this.userPath = userPath;
	}
	public String getOsUser() {
		return osUser;
	}
	public void setOsUser(String osUser) {
		this.osUser = osUser;
	}
	public String getJvm() {
		return jvm;
	}
	public void setJvm(String jvm) {
		this.jvm = jvm;
	}
	public String getJreVersion() {
		return jreVersion;
	}
	public void setJreVersion(String jreVersion) {
		this.jreVersion = jreVersion;
	}
	public String getJvmVersion() {
		return jvmVersion;
	}
	public void setJvmVersion(String jvmVersion) {
		this.jvmVersion = jvmVersion;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Reporter getReport() {
		return report;
	}
	public void setReport(Reporter report) {
		this.report = report;
	}
	public List getRpcServer() {
		return rpcServer;
	}
	public void setRpcServer(List rpcServer) {
		this.rpcServer = rpcServer;
	}
}
