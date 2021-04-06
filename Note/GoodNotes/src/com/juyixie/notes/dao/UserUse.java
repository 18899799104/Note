package com.juyixie.notes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.juyixie.notes.entity.Note;
import com.juyixie.notes.entity.User;
import com.juyixie.notes.util.JDBCUtils;

public class UserUse {
						//登录验证
	public static String userLogOn(User user) {
		String sql="select * from user where username=? and password=?";
		ResultSet rs=null;
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,user.getUsername());
			pstm.setString(2,user.getPassword());
			rs = pstm.executeQuery();
			if(rs.next())  {
				return rs.getString(3);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		
		return null;
	}
						//修改昵称--可拓展
	public static boolean changeNickname(String nickname,User newUser) {	
		String sql="update user set nickname=? where nickname=?";
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, newUser.getNickname());
			pstm.setString(2, nickname);
			if(pstm.executeUpdate()==0) return false;
			else return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(null, pstm, conn);
		}
		return false;
	}
					//通过昵称查找个人信息
	public static User findPersonalData(String nickname) {
		String sql="select * from user where nickname=?";
		ResultSet rs=null;
		PreparedStatement pstm = null;
		Connection conn = null;
		User user = new User();
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,nickname);
			rs = pstm.executeQuery();
			if(rs.next())  {
				user.setNickname(rs.getString(3));
				return user ;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		
		return user;
	}
	public static boolean addUser(User user) {
		String sql="insert into user values(?,?,?) ";
		ResultSet rs=null;
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(3,user.getNickname());
			pstm.setString(1,user.getUsername());
			pstm.setString(2,user.getPassword());
			if(pstm.executeUpdate()!=0)  return true ;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return false;
	}
								//查看所有用户(管理员)
	public static HashSet<User> showAllUser() {
		String sql="select * from user ";
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs=null;
		HashSet<User> userSet = new HashSet<User>();	//建立HashSet存放Note对象
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				User user = new User();
				user.setNickname(rs.getString(3));
				userSet.add(user);
			}
			return userSet;
		} catch (SQLException e) {
			System.out.println("6出错了");
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return userSet;
	}
}
