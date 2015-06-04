package com.farsunset.ichat.cim.handler;

import com.farsunset.ichat.common.util.ContextHolder;
import com.farsunset.ichat.nio.constant.CIMConstant;
import com.farsunset.ichat.nio.handler.CIMRequestHandler;
import com.farsunset.ichat.nio.mutual.ReplyBody;
import com.farsunset.ichat.nio.mutual.SentBody;
import com.farsunset.ichat.nio.session.CIMSession;
import com.farsunset.ichat.nio.session.DefaultSessionManager;

/**
 * 退出连接实现
 * 
 * @author 3979434@qq.com
 */
public class LogoutHandler implements CIMRequestHandler {

	public ReplyBody process(CIMSession ios, SentBody message) {

		DefaultSessionManager sessionManager = ((DefaultSessionManager) ContextHolder
				.getBean("defaultSessionManager"));

		String account = ios.getAttribute(CIMConstant.SESSION_KEY).toString();
		ios.removeAttribute(CIMConstant.SESSION_KEY);
		ios.close(true);

		sessionManager.removeSession(account);

		return null;
	}

}