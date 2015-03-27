/**
 * 
 */
package com.neusoft.util.tools;

import java.util.Date;

import com.neusoft.web.model.TableProperties;


/**
 * @author iceworld
 *
 */
public class TabelSql {
	private String sql ;
	private boolean parame ;
	private int paramnum ;
	private Date updatetime ;
	private int startindex ;
	private TableProperties moditp ;
	public TabelSql(String sql , boolean param , int paramnum , Date updatetime , TableProperties moditp){
		this.sql = sql ;
		this.parame = param ;
		this.paramnum = paramnum ;
		this.updatetime = updatetime ;
		this.moditp = moditp ;
	}
	public TabelSql(String sql , boolean param , int paramnum , int startindex , TableProperties moditp){
		this.sql = sql ;
		this.parame = param ;
		this.paramnum = paramnum ;
		this.startindex = startindex ;
		this.moditp = moditp ;
	}
	public String getSql() {
		return sql;
	}
	public void setSql(String sql) {
		this.sql = sql;
	}
	public boolean isParame() {
		return parame;
	}
	public void setParame(boolean parame) {
		this.parame = parame;
	}
	public int getParamnum() {
		return paramnum;
	}
	public void setParamnum(int paramnum) {
		this.paramnum = paramnum;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	public TableProperties getModitp() {
		return moditp;
	}
	public void setModitp(TableProperties moditp) {
		this.moditp = moditp;
	}
	public int getStartindex() {
		return startindex;
	}
	public void setStartindex(int startindex) {
		this.startindex = startindex;
	}
}
