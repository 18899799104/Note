package com.juyixie.notes.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	private static String url;
	private static String user;
	private static String password;
	private static String driver;
	
	static {
		try {
			Properties pro=new Properties();
			//找到配置文件
			InputStream is=JDBCUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
			pro.load(is);
			//增加数据
			url=pro.getProperty("url");
			user=pro.getProperty("user");
			password=pro.getProperty("password");
			driver=pro.getProperty("driver");
			//注册驱动
			Class.forName(driver);
			
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	 public static Connection getConnection() throws SQLException {	 
		 return DriverManager.getConnection(url,user,password);
	 }
	 
	 public static void close(ResultSet rs,PreparedStatement state,Connection conn) {
		 if(conn!=null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(state!=null) {
				try {
					state.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(rs!=null) {
				try {
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	 
	 }
}
