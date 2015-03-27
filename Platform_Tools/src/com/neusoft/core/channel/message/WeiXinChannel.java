package com.neusoft.core.channel.message;

import java.io.IOException;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.client.ClientProtocolException;

import com.neusoft.core.EapDataContext;
import com.neusoft.core.channel.Channel;
import com.neusoft.core.channel.DataMessage;
import com.neusoft.core.channel.WeiXin;
import com.neusoft.util.PlatformMsgTools;
import com.neusoft.util.tools.WeiXinTools;

import org.dom4j.Element;

public class WeiXinChannel extends ChannelMessage{
	
	@Override
	public byte[] getMediaContent(Channel channel , String msgType ,String msgid ,  Object param) {
		byte[] data = null ;
		if(EapDataContext.ChannelTypeEnum.WEIXIN.toString().equals(channel.getChannel())){
			if(msgType.equalsIgnoreCase(MsgType.IMAGE.toString())){
				String picurl = ((Element)param).element("PicUrl").getTextTrim();
				data = geMedia(picurl);
				channel.setMessagetype(MsgType.IMAGE.toString()) ;
			}else if(msgType.equalsIgnoreCase(MsgType.VOICE.toString())){
				try {
					data = WeiXinTools.getMedia(channel.getOrgi(), msgid);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				channel.setMessagetype(MsgType.VOICE.toString()) ;
			}else if(msgType.equalsIgnoreCase(MsgType.LOCATION.toString())){
				((WeiXin)channel).setLon(((Element)param).element("Location_X").getTextTrim()) ;
				((WeiXin)channel).setLat(((Element)param).element("Location_Y").getTextTrim()) ;
				channel.setMessagetype(MsgType.LOCATION.toString()) ;
			}else if(msgType.equalsIgnoreCase(MsgType.VIDEO.toString())){
				try {
					data = WeiXinTools.getMediaVideo(channel.getOrgi(), msgid);
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	/**
	 * 发送消息回复给渠道
	 */
	@Override
	public void send(DataMessage dataMessage) {
		
	}
	
	private byte[] geMedia(String url){
		return PlatformMsgTools.getURLData(url);
	}
	
	public static void main(String[] args) throws IOException{
		System.out.println(PlatformMsgTools.compress(Base64.encodeBase64String(PlatformMsgTools.getURLData("http://mmsns.qpic.cn/mmsns/jp4wCXqjic8Ie6vfsrLMMgsWlbtTVm2y5ws60P0423f4tjOPjn7dsoQ/0")))) ;
	}
}
