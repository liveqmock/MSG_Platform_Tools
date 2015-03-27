/*
 * Comet4J Copyright(c) 2011, http://code.google.com/p/comet4j/ This code is
 * licensed under BSD license. Use it as you wish, but keep this copyright
 * intact.
 */
package com.neusoft.util.comet.core.event;

import com.neusoft.util.comet.core.CometConnection;
import com.neusoft.util.comet.core.CometEngine;
import com.neusoft.util.comet.event.Event;

/**
 * 连接被移除前的事件对象
 */

public class BeforeRemoveEvent extends Event<CometEngine> {

	private CometConnection conn;

	public BeforeRemoveEvent(CometEngine target, CometConnection aConn) {
		super(target);
		conn = aConn;
	}

	public CometConnection getConn() {
		return conn;
	}

	public void setConn(CometConnection conn) {
		this.conn = conn;
	}

	@Override
	public void destroy() {
		super.destroy();
		conn = null;
	}
}
