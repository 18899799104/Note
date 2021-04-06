package com.juyixie.notes.service;


import com.juyixie.notes.dao.NoteUse;
import com.juyixie.notes.entity.Note;
import com.juyixie.notes.view.Show;

public class NoteService {
					//��ӱʼ�
	public static boolean addNote(int id, String nickname , String title ,String content) {
		Note note = new Note();
		note.setId(id); 				//�ʼǵ�����
		note.setNickname(nickname);		//�ʼǵ�������
		note.setTitle(title);			//�ʼǵı���
		note.setText(content);			//�ʼǵ�����	
		note.setComment("������:");		//��ʼ��������
		return NoteUse.addNote(note);
	}
					//�����ʼǣ��������߻���⣩
	public static void searchNote(String titleOrNickname) {
		Note note = new Note();
		note.setNickname(titleOrNickname);
		note.setTitle(titleOrNickname);
		Show.titleShow(NoteUse.searchNote(note));		
	}
					//ɾ���ʼ�
	public static boolean deleteNote(String nickname ,String title) {
		Note note = new Note();
		note.setNickname(nickname);
		note.setTitle(title);
		return NoteUse.deleteNote(note);
	}
					//չʾ�ɼ��ʼ�
	public static void showNote(){
		Show.titleShow(NoteUse.showNote());
	}
					//չʾ�Լ��ıʼ�
	public static void showMyNote(String nickname){
		Show.myNoteShow(NoteUse.showMyNote(nickname));
	}
					//չʾĳ���ʼ�
	public static void showOneNote(String nickname,String title) {
		Show.contentShow(NoteUse.showOneNote(nickname,title));
	}
					//�޸�ĳ���ʼǵ���Ϣ(�����޸�ҳ�洫��������)
	public static void changeNote(Note note , Note newNote) {
		NoteUse.changeNote(note, newNote);
	}
					//					(����Ҫ�޸ĵıʼǣ������޸�ҳ��)
	public static void changeNote(Note note) {
		Show.changeShow(note);
	}
					//չʾ����
	public static void showAnnouncement() {
		Show.showAnnouncement(NoteUse.showAnnouncement());
	}
					//�������
	public static boolean discuss(Note note) {
		return NoteUse.discuss(note);
	}
					//չʾ����
	public static String showComment(Note note) {
		return NoteUse.showComment(note);
	}
}
