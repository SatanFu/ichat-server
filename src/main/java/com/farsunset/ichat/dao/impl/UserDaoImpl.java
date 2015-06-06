package com.farsunset.ichat.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.farsunset.ichat.dao.BaseDao;
import com.farsunset.ichat.dao.UserDao;
import com.farsunset.ichat.model.User;

public class UserDaoImpl implements UserDao {

	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;

	@Override
	public User login(String account, String password) {
		// TODO Auto-generated method stub
		conn = BaseDao.getConnection();
		String sql = "select * from user where account=? and password=?";
		User user = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setAccount(rs.getString("account"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return user;
	}

	@Override
	public List<User> getFriend(int id) {
		// TODO Auto-generated method stub
		conn = BaseDao.getConnection();
		String sql = "select * from user join friend_map on user.id=friend_map.friend_id where friend_map.user_id=?";
		List<User> list = new ArrayList<User>();
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setAccount(rs.getString("account"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}

		return list;
	}

	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		conn = BaseDao.getConnection();
		String sql = "select * from user";
		List<User> list = new ArrayList<User>();
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("id"));
				user.setAccount(rs.getString("account"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				list.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}

		return list;
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		conn = BaseDao.getConnection();
		String sql = "select * from user where id=?";
		User user = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setAccount(rs.getString("account"));
				user.setUserName(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				System.out.println(user.toString());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return user;
	}

	@Override
	public User register(String userName, String password) {
		// TODO Auto-generated method stub
		conn = BaseDao.getConnection();
		String sql = "insert into user (account,username,password) values (?,?,?)";
		User user = null;
		int result;
		try {
			String account = String.valueOf(System.currentTimeMillis());
			ps = conn.prepareStatement(sql);
			ps.setString(1, account);
			ps.setString(2, userName);
			ps.setString(3, password);
			result = ps.executeUpdate();
			if (result > 0) {
				user = new User();
				user.setAccount(account);
				user.setUserName(userName);
				user.setPassword(password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			BaseDao.closeAll(conn, ps, rs);
		}
		return user;
	}

	@Override
	public boolean addFriend(int userId, int friendId) {
		// TODO Auto-generated method stub
		conn = BaseDao.getConnection();
		String sql = "insert into friend_map (user_id,friend_id) values (?,?)";
		int result1;
		int result2;
		boolean falg = false;
		try {
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, friendId);
			result1 = ps.executeUpdate();

			ps.setInt(1, friendId);
			ps.setInt(2, userId);
			result2 = ps.executeUpdate();

			if (result1 > 0 && result2 > 0) {
				falg = true;
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BaseDao.closeAll(conn, ps, rs);
		}
		return falg;

	}

}
