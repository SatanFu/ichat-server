package com.farsunset.ichat.api.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.farsunset.ichat.common.util.ContextHolder;
import com.farsunset.ichat.nio.session.CIMSession;
import com.farsunset.ichat.nio.session.DefaultSessionManager;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class FriendAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getAll() throws Exception {

		Collection<CIMSession> list = ((DefaultSessionManager) ContextHolder
				.getBean("defaultSessionManager")).getSessions();
		List<String> accounts = new ArrayList<String>();
		for (CIMSession session : list) {
			String account = session.getAccount();
			accounts.add(account);
		}

		ServletActionContext.getResponse().getWriter()
				.print(new Gson().toJson(accounts));
		return null;
	}

}
