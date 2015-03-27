/*
 * Comet4J Copyright(c) 2011, http://code.google.com/p/comet4j/ This code is
 * licensed under BSD license. Use it as you wish, but keep this copyright
 * intact.
 */
package com.neusoft.util.comet.core.event;

import com.neusoft.util.comet.core.CometConnection;
import com.neusoft.util.comet.core.CometEngine;
import com.neusoft.util.comet.core.CometMessage;
import com.neusoft.util.comet.event.Event;

/**
 * 发送消息事件对象
 */

public class MessageEvent extends Event<CometEngine> {

	private CometConnection conn;
	private CometMessage data;

	public MessageEvent(CometEngine target, CometConnection aConn, CometMessage aData) {
		super(target);
		conn = aConn;
		data = aData;
	}

	public CometConnection getConn() {
		return conn;
	}

	public void setConn(CometConnection conn) {
		this.conn = conn;
	}

	public CometMessage getData() {
		return data;
	}

	public void setData(CometMessage data) {
		this.data = data;
	}

	@Override
	public void destroy() {
		super.destroy();
		conn = null;
		data = null;
	}
}
