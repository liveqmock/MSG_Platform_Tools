/*
 * Comet4J Copyright(c) 2011, http://code.google.com/p/comet4j/ This code is
 * licensed under BSD license. Use it as you wish, but keep this copyright
 * intact.
 */
package com.neusoft.util.comet.demo.talker;

import com.neusoft.util.comet.core.CometConnection;
import com.neusoft.util.comet.core.event.DropEvent;
import com.neusoft.util.comet.core.listener.DropListener;
import com.neusoft.util.comet.demo.talker.dto.LeftDTO;

/**
 * 下线侦听
 * @author 
 * @date 2011-3-3
 */

public class LeftListener extends DropListener {

	/*
	 * (non-Jsdoc)
	 * @see org.comet4j.event.Listener#handleEvent(org.comet4j.event.Event)
	 */
	@Override
	public boolean handleEvent(DropEvent anEvent) {
		CometConnection conn = anEvent.getConn();
		if (conn != null) {
			String userName = AppStore.getInstance().get(conn.getId());
			LeftDTO dto = new LeftDTO(conn.getId(), userName);
			AppStore.getInstance().getMap().remove(conn.getId());
			anEvent.getTarget().sendToAll(Constant.APP_CHANNEL, dto);
		}
		return true;
	}

}
