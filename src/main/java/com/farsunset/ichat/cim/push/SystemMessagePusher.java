 
package com.farsunset.ichat.cim.push;

import com.farsunset.ichat.nio.mutual.Message;




/**
 * 
 * @author farsunset (3979434@qq.com)
 */
public class SystemMessagePusher  extends DefaultMessagePusher{


 
	/**
	 * Constructor.
	 */
	public SystemMessagePusher() {
		super();
	}
	
	@Override
	public void pushMessageToUser(Message MessageMO){
		
		MessageMO.setSender("system");
		super.pushMessageToUser(MessageMO);
		
	}
}
