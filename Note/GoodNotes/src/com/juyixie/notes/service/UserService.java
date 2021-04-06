package com.juyixie.notes.service;

import com.juyixie.notes.dao.UserUse;
import com.juyixie.notes.entity.User;
import com.juyixie.notes.view.Show;

public class UserService {
						//封装User传给处理sql的类方法中
	public static String userLogOn(String username ,String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(UserUse.userLogOn(user));
		return user.getNickname();
	}
						//修改昵称(用于接受修改界面传来的信息)
	public static boolean changeNickname(String nickname , User newUser) {
		return UserUse.changeNickname(nickname , newUser);	
	}
						//修改昵称（用于创造修改界面）
	public static void changeUsername(User user) {
		Show.changePersonalDataShow(user);
	}
						//展示用户个人信息
	public static void showUserData(String nickname) {
		Show.personalDataShow(UserUse.findPersonalData(nickname));
	}
						//注册
	public static boolean addUser(String nickname, String username, String password) {
		User user = new User();
		user.setNickname(nickname);
		user.setUsername(username);
		user.setPassword(password);
		return UserUse.addUser(user);
	}
}
