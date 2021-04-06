package com.juyixie.notes.util;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import com.juyixie.notes.entity.Note;
import com.juyixie.notes.entity.User;
import com.juyixie.notes.service.NoteService;
import com.juyixie.notes.service.UserService;
import com.juyixie.notes.view.View;

public class JButtonUtils {
	//���ڵõ����м���Ч���İ�ť
	public static JButton getReadButton(Note note) {
		JButton button = new JButton(note.getTitle());
		button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	NoteService.showOneNote(note.getNickname(), note.getTitle());
            	View.cl.show(View.cards, "New");
            }
        });
		return button;
	}
	
	public static JButton getChangeButton(Note note) {
		JButton button = new JButton("�޸�");
		button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	NoteService.changeNote(note);		//����showOneNote�����޸�Newҳ������
            	View.cl.show(View.cards, "New");
            }
        });
	
		return button;
	}
	
	public static JButton getDeleteButton(Note note) {
		JButton button = new JButton("ɾ��");
		button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(NoteService.deleteNote(note.getNickname(), note.getTitle()))
            		JOptionPane.showMessageDialog(null,"ɾ���ɹ�!");
            	else JOptionPane.showMessageDialog(null,"ɾ��ʧ��!�����ԣ�");
            	NoteService.showMyNote(View.nickname);
            
            	
            }
        });
		return button;
	}
	public static JButton getUserButton(User user) {
		JButton button = new JButton(user.getNickname());
		button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	UserService.showUserData(user.getNickname());
            	View.cl.show(View.cards, "New");
            }
        });
		return button;
		
	}
}
