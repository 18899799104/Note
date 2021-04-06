package com.juyixie.notes.view;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.juyixie.notes.entity.Note;
import com.juyixie.notes.entity.User;
import com.juyixie.notes.service.NoteService;
import com.juyixie.notes.service.UserService;
import com.juyixie.notes.util.JButtonUtils;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.Iterator;
public class Show {
					//该方法意在快速创造一个界面填充到可变界面New中
	
					//显示个人资料
	public static void personalDataShow(User user) {
		View.jsp.getViewport().removeAll();
		JPanel jp = new JPanel();
		JLabel jl = new JLabel("   ID:"+user.getNickname()+"   ");
		JButton jb1 = new JButton("返回");
		JButton jb2 = new JButton("修改");
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(View.nickname!=null) View.cl.show(View.cards, "Menu");	//辨别是管理员操作还是用户操作
            	else View.cl.show(View.cards, "AdminMenu");
            }
        });
		jb2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	UserService.changeUsername(user);
            }
        });
		jp.add(jl);
		jp.add(jb1);
		jp.add(jb2);
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
					//修改个人资料界面
	public static void changePersonalDataShow(User user) {
		View.jsp.getViewport().removeAll();
		JPanel jp = new JPanel();
		JButton jb = new JButton("确认修改");
		JTextField jtf = new JTextField(user.getNickname(), 10);
		jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            	User newUser = new User();
            	newUser.setNickname(jtf.getText());
            	if(UserService.changeNickname(user.getNickname(), newUser))	//传入修改个人信息的方法
            	{
	            	if(View.nickname!=null) {
	            		JOptionPane.showMessageDialog(null,"修改成功!请重新登陆！");
		            	View.cl.show(View.cards, "LogIn");
	            	}
	            	else View.cl.show(View.cards, "AdminMenu");						//辨别是管理员操作还是用户操作
            	}
            	else JOptionPane.showMessageDialog(null,"名字超长或重复请修改后重试！");
            }
        });
		jp.add(jtf);
		jp.add(jb);
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
					//展示标题
	public static void titleShow(HashSet<Note> noteSet) {
		View.jsp.getViewport().removeAll();					//直接使用removeAll和revalidate无法正常显示刷新后的页面，得用getViewport得到jsp的视图才能直接刷新页面
		JPanel jp = new JPanel();
		JButton jb = new JButton("返回");
		jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(View.nickname!=null)View.cl.show(View.cards, "Menu");
            	else View.cl.show(View.cards, "AdminMenu");
            }
        });
		jp.add(jb);
		Iterator<Note> iterator= noteSet.iterator();			//创建迭代器遍历noteSet
		while(iterator.hasNext()){
			Note note = iterator.next();										
			jp.add(JButtonUtils.getReadButton(note));
			jp.add(new JLabel("作者ID："+note.getNickname()));
			}
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
					//展示具体笔记内容
	public static void contentShow(Note note) {
		View.jsp.getViewport().removeAll();
		JPanel jp = new JPanel();
		JTextField jtf = new JTextField("输入评论");								//添加评论的输入框
		JTextArea commentArea = new JTextArea(note.getComment(),7,30);			//评论区
		commentArea.setEditable(false); 						
		commentArea.setLineWrap(true);
		JTextArea jta = new JTextArea(note.getText(),7,30);						//创建文本域放入笔记内容
		jta.setEditable(false); 												//关闭文本域的输入仅进行文本展示
		jta.setLineWrap(true);													//开启自动换行
		JButton jb = new JButton("返回");
		JButton discussButton = new JButton("评论");
		JButton delete = new JButton("删除");
		jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(View.nickname!=null)View.cl.show(View.cards, "Menu");
            	else View.cl.show(View.cards, "AdminMenu");
            }
        });
		delete.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(NoteService.deleteNote(note.getNickname(), note.getTitle())) {
            		JOptionPane.showMessageDialog(null,"删除成功!");
            		View.cl.show(View.cards, "AdminMenu");
            	}
            	else JOptionPane.showMessageDialog(null,"删除失败!");
            }
        });
		discussButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            	note.setComment(note.getComment()+"\n"+View.nickname+":"+jtf.getText());		//将评论框中的评论添加到这条笔记的评论区中
            	NoteService.discuss(note);
            	Show.contentShow(note);										//重置页面
            	View.cl.show(View.cards, "New");
            }
        });
		
		jp.add(jb);
		if(View.nickname!=null)jp.add(jtf);				//用户笔记专属
		if(View.nickname!=null)jp.add(discussButton);	//用户笔记专属
		if(View.nickname==null) jp.add(delete);			//管理员专属
		jp.add(jta);
		jp.add(commentArea);
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
					//展示自己笔记
	public static void myNoteShow(HashSet<Note> noteSet) {
		View.jsp.getViewport().removeAll();					
		JPanel jp = new JPanel();
		JButton jb = new JButton("返回");
		jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	View.cl.show(View.cards, "Menu");
            }
        });
		jp.add(jb);
		Iterator<Note> iterator= noteSet.iterator();			
		while(iterator.hasNext()){
			Note note = iterator.next();										
			jp.add(JButtonUtils.getReadButton(note));
			jp.add(JButtonUtils.getChangeButton(note));
			jp.add(JButtonUtils.getDeleteButton(note));
			}
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
						//笔记修改页面
	public static void changeShow(Note note) {
		Note newNote = new Note();
		View.jsp.getViewport().removeAll();
		JPanel jp = new JPanel();
		JButton jb1 = new JButton("修改为公开笔记");
		JButton jb2 = new JButton("修改为私密笔记");
		JTextField title = new JTextField(note.getTitle());
		JTextArea content = new JTextArea(note.getText(),7,30);
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	newNote.setId(0);
            	newNote.setText(content.getText());
            	newNote.setTitle(title.getText());
            	NoteService.changeNote(note, newNote);
            	View.cl.show(View.cards, "Menu");
            }
        });
		jb2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            	newNote.setId(1);
            	newNote.setText(content.getText());
            	newNote.setTitle(title.getText());
            	NoteService.changeNote(note, newNote);		//此处待加提示
            	View.cl.show(View.cards, "Menu");
            }
        });
		jp.add(title);
		jp.add(content);
		jp.add(jb1);
		jp.add(jb2);
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
									//展示所有用户界面
	public static void allUserShow(HashSet<User> userSet) {
		View.jsp.getViewport().removeAll();
		JPanel jp = new JPanel();
		JButton jb = new JButton("返回");
		jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	View.cl.show(View.cards, "AdminMenu");
            }
        });
		jp.add(jb);
		Iterator<User> iterator= userSet.iterator();			//创建迭代器遍历noteSet
		while(iterator.hasNext()){
			User user = iterator.next();										
			jp.add(JButtonUtils.getUserButton(user));
			}
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
						//展示公告
	public static void showAnnouncement(HashSet<Note> noteSet) {
		View.jsp.getViewport().removeAll();					
		JPanel jp = new JPanel();
		JButton jb = new JButton("返回");
		jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(View.nickname!=null)View.cl.show(View.cards, "Menu");
            	else View.cl.show(View.cards, "AdminMenu");
            }
        });
		jp.add(jb);
		Iterator<Note> iterator= noteSet.iterator();			//创建迭代器遍历noteSet
		while(iterator.hasNext()){
			Note note = iterator.next();										
			jp.add(JButtonUtils.getReadButton(note));
			}
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
	
}
