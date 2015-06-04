package com.farsunset.ichat.nio.handler;

import com.farsunset.ichat.nio.mutual.ReplyBody;
import com.farsunset.ichat.nio.mutual.SentBody;
import com.farsunset.ichat.nio.session.CIMSession;

/**
 * 请求处理接口,所有的请求实现必须实现此接口
 * 
 * @author 3979434@qq.com
 */
public interface CIMRequestHandler {

	public abstract ReplyBody process(CIMSession session, SentBody message);
}