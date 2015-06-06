package com.farsunset.ichat.api.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.farsunset.ichat.dao.UserDao;
import com.farsunset.ichat.dao.impl.UserDaoImpl;
import com.farsunset.ichat.model.User;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getAllUser() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		UserDao userDao = new UserDaoImpl();
		List<User> users = userDao.getAllUser();
		Map<String, Object> map = new HashMap<String, Object>();
		if (users == null) {
			map.put("status", "error");
			map.put("message", "获取所有用户失败");
			map.put("data", "");
		} else {
			map.put("status", "success");
			map.put("message", "获取所有用户成功");
			map.put("data", users);
		}
		response.getWriter().print(new Gson().toJson(map));
		return null;
	}

	public String getFriend() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String id = (String) request.getParameter("id");

		UserDao userDao = new UserDaoImpl();
		List<User> users = userDao.getFriend(Integer.valueOf(id));

		Map<String, Object> map = new HashMap<String, Object>();
		if (users == null) {
			map.put("status", "error");
			map.put("message", "获取朋友失败");
			map.put("data", "");
		} else {
			map.put("status", "success");
			map.put("message", "获取朋友成功");
			map.put("data", users);
		}
		response.getWriter().print(new Gson().toJson(map));
		return null;
	}

	public String login() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String account = (String) request.getParameter("account");
		String password = (String) request.getParameter("password");

		UserDao userDao = new UserDaoImpl();
		User user = userDao.login(account, password);

		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			map.put("status", "error");
			map.put("message", "登录失败");
			map.put("data", "");
		} else {
			map.put("status", "success");
			map.put("message", "登录成功");
			map.put("data", user);
		}
		response.getWriter().print(new Gson().toJson(map));
		return null;
	}

	public String register() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String userName = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");

		UserDao userDao = new UserDaoImpl();
		User user = userDao.register(userName, password);
		Map<String, Object> map = new HashMap<String, Object>();
		if (user == null) {
			map.put("status", "error");
			map.put("message", "注册失败");
			map.put("data", "");
		} else {
			map.put("status", "success");
			map.put("message", "注册成功");
			map.put("data", user);
		}
		response.getWriter().print(new Gson().toJson(map));
		return null;
	}

	public String addFriend() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");

		String userId = (String) request.getParameter("user_id");
		String friendId = request.getParameter("friend_id");
		UserDao userDao = new UserDaoImpl();
		boolean result = userDao.addFriend(Integer.valueOf(userId),
				Integer.valueOf(friendId));
		Map<String, Object> map = new HashMap<String, Object>();
		if (result) {
			map.put("status", "success");
			map.put("message", "添加好友成功");
			map.put("data", "");
		} else {
			map.put("status", "error");
			map.put("message", "添加好友失败");
			map.put("data", "");
		}
		response.getWriter().print(new Gson().toJson(map));
		return null;
	}

}
