package com.neusoft.util.store;

import java.text.ParseException;
import java.util.Date;

import org.quartz.CronExpression;


public class EapTools {
	/**
	 * 
	 * @param crontabExp
	 * @return
	 * @throws ParseException
	 */
	
	public static CronExpression getFireTime(String crontabExp) throws ParseException{
		return new CronExpression(crontabExp);
		
	}
	/**
	 * 
	 * @param crontabExp
	 * @return
	 * @throws ParseException
	 */
	
	public static Date getFinalFireTime(String crontabExp , Date date) throws ParseException{
		CronExpression expression = new CronExpression(crontabExp) ;
		return expression.getNextValidTimeAfter(date!=null ? date:new Date());
		
	}
}
