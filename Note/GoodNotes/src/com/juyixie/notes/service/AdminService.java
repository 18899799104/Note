package com.juyixie.notes.service;


import com.juyixie.notes.dao.AdminUse;
import com.juyixie.notes.dao.NoteUse;
import com.juyixie.notes.dao.UserUse;
import com.juyixie.notes.entity.Note;
import com.juyixie.notes.view.Show;


public class AdminService {
	public static void showAllNote() {
		Show.titleShow(NoteUse.showAllNote());
	}
	public static void showAllUser() {
		Show.allUserShow(UserUse.showAllUser());
	}
	public static boolean adminLogIn(String gmname , String password) {
		return AdminUse.adminLogIn(gmname,password);
	}
}
