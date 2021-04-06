package com.juyixie.notes.service;

import com.juyixie.notes.dao.UserUse;
import com.juyixie.notes.entity.User;
import com.juyixie.notes.view.Show;

public class UserService {
						//��װUser��������sql���෽����
	public static String userLogOn(String username ,String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(UserUse.userLogOn(user));
		return user.getNickname();
	}
						//�޸��ǳ�(���ڽ����޸Ľ��洫������Ϣ)
	public static boolean changeNickname(String nickname , User newUser) {
		return UserUse.changeNickname(nickname , newUser);	
	}
						//�޸��ǳƣ����ڴ����޸Ľ��棩
	public static void changeUsername(User user) {
		Show.changePersonalDataShow(user);
	}
						//չʾ�û�������Ϣ
	public static void showUserData(String nickname) {
		Show.personalDataShow(UserUse.findPersonalData(nickname));
	}
						//ע��
	public static boolean addUser(String nickname, String username, String password) {
		User user = new User();
		user.setNickname(nickname);
		user.setUsername(username);
		user.setPassword(password);
		return UserUse.addUser(user);
	}
}
