package com.neusoft.core;


public class ProjectDataContext {
	private static String platformserver ;
	static{
//		Thread init = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				while(platformserver==null){
//					platformserver = (String) Hazelcast.getMap(EapDataContext.SERVER_ADDRESS).get(EapDataContext.SERVER_ADDRESS)  ;
//					EapDataContext.getLogger(ProjectDataContext.class).info("监测系统状态 , PLATFORM地址:"+platformserver+"......") ;
//					if(platformserver==null){
//						try {
//							Thread.sleep(1000) ;
//						} catch (InterruptedException e) {
//							e.printStackTrace();
//						}
//					}
//				}
//			}
//		});
//		init.start();
	}
	public static String getPlatformserver() {
		return platformserver;
	}
	/**
	 * 覆盖设置
	 * @param platformserver
	 */
	public static void setPlatformserver(String platformserver) {
		if(platformserver!=null){
			ProjectDataContext.platformserver = platformserver;
		}
	}
}
