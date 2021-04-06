package com.juyixie.notes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.juyixie.notes.util.JDBCUtils;

public class AdminUse {
	public static boolean adminLogIn(String gmName,String password) {
		String sql="select * from admin where gmname=? and password=?";
		ResultSet rs=null;
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,gmName);
			pstm.setString(2,password);
			rs = pstm.executeQuery();
			if(rs.next()) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return false;
	}
}	