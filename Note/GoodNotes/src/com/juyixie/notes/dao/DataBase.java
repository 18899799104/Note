package com.juyixie.notes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.juyixie.notes.util.JDBCUtils;

public class DataBase {
	//导入JDBC所需的jar包到libs文件夹
	//创建数据库收集数据的3张表
	
	public static void main(String[] args) {
		//提前创建对象，方便释放
		Connection conn=null;
		PreparedStatement pstm1=null;
		PreparedStatement pstm2=null;
		PreparedStatement pstm3=null;
		try {
			//获取连接
			conn=JDBCUtils.getConnection();
			//创建管理员信息表
			pstm1=conn.prepareStatement("create table admin(gmname varchar(20) primary key,"
					  				  + "password varchar(20) NOT NULL)");

			pstm2=conn.prepareStatement("create table user(username varchar(20) primary key,"
									  + "password varchar(20) NOT NULL,"
									  + "nickname varchar(8) NOT NULL UNIQUE)");
			//创建用户信息表
			pstm2=conn.prepareStatement("create table user(username varchar(20) primary key,password varchar(20) NOT NULL,nickname varchar(8) NOT NULL UNIQUE)");

			//创建笔记表
			pstm3=conn.prepareStatement("create table note(id int NOT NULL,"
									  + "nickname varchar(20) NOT NULL,"
									  + "title varchar(20) NOT NULL,"
									  + "content text) NOT NULL");
			pstm1.execute();
			pstm2.execute();
			pstm3.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(null,pstm1,conn);
			JDBCUtils.close(null,pstm2,null);
			JDBCUtils.close(null,pstm3,null);
		}
	}

}
