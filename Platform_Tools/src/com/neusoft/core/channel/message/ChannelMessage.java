package com.neusoft.core.channel.message;

import java.util.Map;

import com.neusoft.core.EapDataContext;
import com.neusoft.core.channel.Channel;
import com.neusoft.core.channel.DataMessage;

public abstract class  ChannelMessage {
	
	public enum MsgType{
		LOCATION,
		TEXT,
		IMAGE,
		VOICE,
		VIDEO;
		public String toString(){
			return super.toString().toLowerCase() ;
		}
	}
	
	public static ChannelMessage getInstance(String channel){
		if(EapDataContext.ChannelTypeEnum.WEIXIN.toString().equals(channel)){
			return new WeiXinChannel();
		}
		if(EapDataContext.ChannelTypeEnum.YIXIN.toString().equals(channel)){
			return new YiXinChannel();
		}
		return  new WeiXinChannel() ;
	}
	/**
	 * 获取消息内容
	 * @param channel
	 * @return
	 */
	public abstract byte[] getMediaContent(Channel channel , String msgType , String msgid , Object param) ;
	
	public abstract void send(DataMessage dataMessage) ;
	
}
