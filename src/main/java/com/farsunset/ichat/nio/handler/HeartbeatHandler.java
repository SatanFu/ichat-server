package com.farsunset.ichat.nio.handler;

import org.apache.log4j.Logger;

import com.farsunset.ichat.nio.constant.CIMConstant;
import com.farsunset.ichat.nio.mutual.ReplyBody;
import com.farsunset.ichat.nio.mutual.SentBody;
import com.farsunset.ichat.nio.session.CIMSession;

/**
 * 客户端心跳实现
 * 
 * @author
 */
public class HeartbeatHandler implements CIMRequestHandler {

	protected final Logger logger = Logger.getLogger(HeartbeatHandler.class);

	public ReplyBody process(CIMSession session, SentBody message) {

		logger.warn("heartbeat... from "
				+ session.getRemoteAddress().toString());
		ReplyBody reply = new ReplyBody();
		reply.setKey(CIMConstant.RequestKey.CLIENT_HEARTBEAT);
		reply.setCode(CIMConstant.ReturnCode.CODE_200);
		session.setHeartbeat(System.currentTimeMillis());
		return reply;
	}

}