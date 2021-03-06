/*
 * Comet4J Copyright(c) 2011, http://code.google.com/p/comet4j/ This code is
 * licensed under BSD license. Use it as you wish, but keep this copyright
 * intact.
 */
package com.neusoft.util.comet.demo.talker.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neusoft.util.comet.demo.talker.Constant;

/**
 * 下线通知传输对象
 * @author 
 * @date 2011-3-3
 */
public class LeftDTO {

	private final String transtime;
	private String type;
	private String id;
	private String name;

	public LeftDTO(String id, String name) {
		this.type = Constant.DOWN;
		this.id = id;
		this.name = name;
		Date d = new Date(System.currentTimeMillis());
		SimpleDateFormat f = new SimpleDateFormat("HH:mm");
		transtime = f.format(d);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTranstime() {
		return transtime;
	}
}
