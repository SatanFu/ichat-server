package com.farsunset.ichat.dao;

import java.util.List;

import com.farsunset.ichat.model.User;

public interface UserDao {

	// 登录
	public User login(String account, String password);

	// 获取我的朋友
	public List<User> getFriend(int id);

	// 获取所有用户
	public List<User> getAllUser();

	// 根据用户ID获取用户
	public User getUserById(int id);

	// 注册
	public User register(String userName, String password);

	public boolean addFriend(int userId, int friendId);
}
