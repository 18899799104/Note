package com.juyixie.notes.service;


import com.juyixie.notes.dao.NoteUse;
import com.juyixie.notes.entity.Note;
import com.juyixie.notes.view.Show;

public class NoteService {
					//添加笔记
	public static boolean addNote(int id, String nickname , String title ,String content) {
		Note note = new Note();
		note.setId(id); 				//笔记的属性
		note.setNickname(nickname);		//笔记的作者名
		note.setTitle(title);			//笔记的标题
		note.setText(content);			//笔记的内容	
		note.setComment("评论区:");		//初始化评论区
		return NoteUse.addNote(note);
	}
					//搜索笔记（根据作者或标题）
	public static void searchNote(String titleOrNickname) {
		Note note = new Note();
		note.setNickname(titleOrNickname);
		note.setTitle(titleOrNickname);
		Show.titleShow(NoteUse.searchNote(note));		
	}
					//删除笔记
	public static boolean deleteNote(String nickname ,String title) {
		Note note = new Note();
		note.setNickname(nickname);
		note.setTitle(title);
		return NoteUse.deleteNote(note);
	}
					//展示可见笔记
	public static void showNote(){
		Show.titleShow(NoteUse.showNote());
	}
					//展示自己的笔记
	public static void showMyNote(String nickname){
		Show.myNoteShow(NoteUse.showMyNote(nickname));
	}
					//展示某条笔记
	public static void showOneNote(String nickname,String title) {
		Show.contentShow(NoteUse.showOneNote(nickname,title));
	}
					//修改某条笔记的信息(接收修改页面传来的内容)
	public static void changeNote(Note note , Note newNote) {
		NoteUse.changeNote(note, newNote);
	}
					//					(接收要修改的笔记，传入修改页面)
	public static void changeNote(Note note) {
		Show.changeShow(note);
	}
					//展示公告
	public static void showAnnouncement() {
		Show.showAnnouncement(NoteUse.showAnnouncement());
	}
					//添加评论
	public static boolean discuss(Note note) {
		return NoteUse.discuss(note);
	}
					//展示评论
	public static String showComment(Note note) {
		return NoteUse.showComment(note);
	}
}
