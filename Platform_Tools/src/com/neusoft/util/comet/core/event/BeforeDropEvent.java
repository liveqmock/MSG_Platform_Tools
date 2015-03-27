/*
 * Comet4J Copyright(c) 2011, http://code.google.com/p/comet4j/ This code is
 * licensed under BSD license. Use it as you wish, but keep this copyright
 * intact.
 */
package com.neusoft.util.comet.core.event;

import javax.servlet.http.HttpServletRequest;

import com.neusoft.util.comet.core.CometEngine;
import com.neusoft.util.comet.event.Event;

/**
 * 即将断开前的事件对象
 */

public class BeforeDropEvent extends Event<CometEngine> {

	private HttpServletRequest request;

	public BeforeDropEvent(CometEngine target, HttpServletRequest aRequest) {
		super(target);
		request = aRequest;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void destroy() {
		super.destroy();
		request = null;
	}
}
