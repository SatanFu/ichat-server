 
package com.farsunset.ichat.cim.push;

import com.farsunset.ichat.nio.mutual.Message;




/**
 * 
 * @author farsunset (3979434@qq.com)
 */
public class ChatMessage  extends DefaultMessagePusher{


 
	/**
	 * Constructor.
	 */
	public ChatMessage() {
		super();
	}
	
	@Override
	public void pushMessageToUser(Message MessageMO){
		super.pushMessageToUser(MessageMO);
		
	}
}
