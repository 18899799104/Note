package com.juyixie.notes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.juyixie.notes.util.JDBCUtils;

public class DataBase {
	//����JDBC�����jar����libs�ļ���
	//�������ݿ��ռ����ݵ�3�ű�
	
	public static void main(String[] args) {
		//��ǰ�������󣬷����ͷ�
		Connection conn=null;
		PreparedStatement pstm1=null;
		PreparedStatement pstm2=null;
		PreparedStatement pstm3=null;
		try {
			//��ȡ����
			conn=JDBCUtils.getConnection();
			//��������Ա��Ϣ��
			pstm1=conn.prepareStatement("create table admin(gmname varchar(20) primary key,"
					  				  + "password varchar(20) NOT NULL)");

			pstm2=conn.prepareStatement("create table user(username varchar(20) primary key,"
									  + "password varchar(20) NOT NULL,"
									  + "nickname varchar(8) NOT NULL UNIQUE)");
			//�����û���Ϣ��
			pstm2=conn.prepareStatement("create table user(username varchar(20) primary key,password varchar(20) NOT NULL,nickname varchar(8) NOT NULL UNIQUE)");

			//�����ʼǱ�
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
