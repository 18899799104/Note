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
	//便于得到具有监听效果的按钮
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
		JButton button = new JButton("修改");
		button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	NoteService.changeNote(note);		//调用showOneNote方法修改New页面内容
            	View.cl.show(View.cards, "New");
            }
        });
	
		return button;
	}
	
	public static JButton getDeleteButton(Note note) {
		JButton button = new JButton("删除");
		button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(NoteService.deleteNote(note.getNickname(), note.getTitle()))
            		JOptionPane.showMessageDialog(null,"删除成功!");
            	else JOptionPane.showMessageDialog(null,"删除失败!请重试！");
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
