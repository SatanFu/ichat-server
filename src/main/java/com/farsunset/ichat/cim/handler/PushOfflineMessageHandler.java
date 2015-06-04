/**
 * probject:cim
 * @version 1.1.0
 * 
 * @author 3979434@qq.com
 */
package com.farsunset.ichat.cim.handler;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.farsunset.ichat.nio.constant.CIMConstant;
import com.farsunset.ichat.nio.handler.CIMRequestHandler;
import com.farsunset.ichat.nio.mutual.Message;
import com.farsunset.ichat.nio.mutual.ReplyBody;
import com.farsunset.ichat.nio.mutual.SentBody;
import com.farsunset.ichat.nio.session.CIMSession;

/**
 * 推送离线消息
 */
public class PushOfflineMessageHandler implements CIMRequestHandler {

	protected final Logger logger = Logger
			.getLogger(PushOfflineMessageHandler.class);

	public ReplyBody process(CIMSession ios, SentBody message) {

		ReplyBody reply = new ReplyBody();
		reply.setCode(CIMConstant.ReturnCode.CODE_200);
		try {
			String account = message.get("account");
			// 获取到存储的离线消息
//			 List<Message> list =
//			 messageService.queryOffLineMessages(account);
			List<Message> list = new ArrayList<Message>();
			for (Message m : list) {

				ios.write(m);
			}

		} catch (Exception e) {
			reply.setCode(CIMConstant.ReturnCode.CODE_500);
			e.printStackTrace();
			logger.error("拉取离线消息失败", e);
		}
		return reply;
	}
}