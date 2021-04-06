package com.juyixie.notes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.juyixie.notes.entity.Note;
import com.juyixie.notes.util.JDBCUtils;

public class NoteUse {
	/*	      
	   д�ʼ�  
	  		  */
	public static boolean addNote(Note note) {
		String sql="insert into note values(?,?,?,?,?)";
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1,note.getId());
			pstm.setString(2, note.getNickname());
			pstm.setString(3,note.getTitle());
			pstm.setString(4,note.getText());
			pstm.setString(5, note.getComment());
			if(pstm.executeUpdate()==0) return false;
			else return true;
		} catch (SQLException e) {
			System.out.println("1������");
			e.printStackTrace();
		}finally {
			JDBCUtils.close(null, pstm, null);
		}
												//��ֹSQL�쳣�޷���ֵ �������
		return false;	
	}
	
	/*			
	  ɾ���ʼ�	
	  			*/
	public static boolean deleteNote(Note note) {
		String sql="delete from note where nickname=? and title=? ";
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, note.getNickname());
			pstm.setString(2,note.getTitle());
			if(pstm.executeUpdate()==0) return false;
			else return true;
		} catch (SQLException e) {
			System.out.println("2������");
			e.printStackTrace();
		}finally {
			JDBCUtils.close(null, pstm, null);
		}
		return false;	
	}
	/*
	 * �޸ıʼ�
	 */
	public static boolean changeNote(Note note , Note newNote) {
		String sql = "update note set id=?,title= ?,content=? where nickname=? and title=?";
		PreparedStatement pstm = null;
		Connection conn = null;
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setInt(1, newNote.getId());
			pstm.setString(2,newNote.getTitle());
			pstm.setString(3, newNote.getText());
			pstm.setString(4,note.getNickname());
			pstm.setString(5, note.getTitle());
			if(pstm.executeUpdate()==0) return false;
			else return true;
		} catch (SQLException e) {
			System.out.println("3������");
			e.printStackTrace();
		}finally {
			JDBCUtils.close(null, pstm, null);
		}
						
		return false;
	}
	/*�����ʼ�
	  
	  		 */
	public static HashSet<Note> searchNote(Note note) {
		String sql="select * from note where title=? or nickname=? and id=0";
		ResultSet rs=null;
		PreparedStatement pstm = null;
		Connection conn = null;
		HashSet<Note> noteSet = new HashSet<Note>();	//����HashSet���Note����
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1,note.getTitle());
			pstm.setString(2, note.getNickname());
			rs = pstm.executeQuery();
			while(rs.next()) {
				Note note1 = new Note();
				note1.setId(rs.getInt(1));
				note1.setNickname(rs.getString(2));
				note1.setTitle(rs.getString(3));
				note1.setText(rs.getString(4));
				note1.setComment(rs.getString(5));
				noteSet.add(note1);
			}
			return noteSet;
		} catch (SQLException e) {
			System.out.println("5������");
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return noteSet;
		
	}
	/*չʾ�ɼ��ʼ�
	  
	  			 */
	public static HashSet<Note> showNote() {
		String sql="select * from note where id=0 ";
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs=null;
		HashSet<Note> noteSet = new HashSet<Note>();	//����HashSet���Note����
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Note note1 = new Note();
				note1.setId(rs.getInt(1));
				note1.setNickname(rs.getString(2));
				note1.setTitle(rs.getString(3));
				note1.setText(rs.getString(4));
				note1.setComment(rs.getString(5));
				noteSet.add(note1);
			}
			return noteSet;
		} catch (SQLException e) {
			System.out.println("6������");
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return noteSet;
	}
	/*	�鿴�Լ��ıʼ�
	 * 
	 */
	public static HashSet<Note> showMyNote(String nickname) {
		String sql="select * from note where nickname=? and id=0 or id=1";
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs=null;
		HashSet<Note> noteSet = new HashSet<Note>();	//����HashSet���Note����
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nickname);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Note note1 = new Note();
				note1.setId(rs.getInt(1));
				note1.setNickname(rs.getString(2));
				note1.setTitle(rs.getString(3));
				note1.setText(rs.getString(4));
				note1.setComment(rs.getString(5));
				noteSet.add(note1);
			}
			return noteSet;
		} catch (SQLException e) {
			System.out.println("8������");
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return noteSet;
	}
	/*
	 * չʾĳ���ʼ�
	 */
	public static Note showOneNote(String nickname , String title) {
		String sql="select * from note where nickname=? and title=? ";
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs=null;
		Note note = new Note();
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, nickname);
			pstm.setString(2, title);
			rs = pstm.executeQuery();
			while(rs.next()) {
				note.setId(rs.getInt(1));
				note.setNickname(rs.getString(2));
				note.setTitle(rs.getString(3));
				note.setText(rs.getString(4));
				note.setComment(rs.getString(5));
			}
			return note;
		} catch (SQLException e) {
			System.out.println("9������");
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return null;
	}
						//����Աʹ�ã��鿴���бʼ�
	public static HashSet<Note> showAllNote(){
		String sql="select * from note where id=0 or id=1";
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs=null;
		HashSet<Note> noteSet = new HashSet<Note>();	//����HashSet���Note����
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Note note1 = new Note();
				note1.setId(rs.getInt(1));
				note1.setNickname(rs.getString(2));
				note1.setTitle(rs.getString(3));
				note1.setText(rs.getString(4));
				note1.setComment(rs.getString(5));
				noteSet.add(note1);
			}
			return noteSet;
		} catch (SQLException e) {
			System.out.println("10������");
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return noteSet;
	}
									//�鿴����
	public static HashSet<Note> showAnnouncement(){
		String sql = "select * from note where id=2";
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs=null;
		HashSet<Note> noteSet = new HashSet<Note>();	//����HashSet���Note����
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			rs = pstm.executeQuery();
			while(rs.next()) {
				Note note1 = new Note();
				note1.setId(rs.getInt(1));
				note1.setNickname(rs.getString(2));
				note1.setTitle(rs.getString(3));
				note1.setText(rs.getString(4));
				noteSet.add(note1);
			}
			return noteSet;
		} catch (SQLException e) {
			System.out.println("11������");
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return noteSet;
	}
									//�������
	public static boolean discuss(Note note) {
		String sql = "update note set comment=? where nickname=? and title=?";
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs=null;
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, note.getComment());
			pstm.setString(2, note.getNickname());
			pstm.setString(3, note.getTitle());
			if(pstm.executeUpdate()!=0) return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return false;
	}
									//�鿴����
	public static String showComment(Note note) {
		String sql="select * from note where nickname=? and title=?";
		PreparedStatement pstm = null;
		Connection conn = null;
		ResultSet rs=null;
		try {
			conn = JDBCUtils.getConnection();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, note.getNickname());
			pstm.setString(2, note.getTitle());
			rs = pstm.executeQuery();
			return rs.getString(5);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(rs, pstm, conn);
		}
		return "";
	}
}
