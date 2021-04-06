package com.juyixie.notes.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.juyixie.notes.dao.AdminUse;
import com.juyixie.notes.service.AdminService;
import com.juyixie.notes.service.NoteService;
import com.juyixie.notes.service.UserService;

public class AddStaticView {
							//管理员菜单
	public static JPanel adminMenu() {
			JPanel jp = new JPanel();
			JButton jb1 = new JButton("查看所有笔记");			
			JButton jb2 = new JButton("查看所有用户");
			JButton jb3 = new JButton("发布公告");
			JButton jb4 = new JButton("查看公告");

			jb1.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	AdminService.showAllNote();
	            	View.cl.show(View.cards, "New");	
	            }
	        });
			jb2.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	AdminService.showAllUser();
	            	View.cl.show(View.cards, "New");	
	            }
	        });	
			jb3.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	View.cl.show(View.cards, "AddAnnouncement");	
	            }
	        });	
			jb4.addActionListener(new ActionListener()
	        {
	            public void actionPerformed(ActionEvent e)
	            {
	            	NoteService.showAnnouncement();
	            	View.cl.show(View.cards, "New");	
	            }
	        });	
			jp.add(jb1);
			jp.add(jb2);
			jp.add(jb3);
			jp.add(jb4);
			return jp;
		}
									//用户菜单
	public static JPanel menu() {
		JPanel jp = new JPanel(new BorderLayout());
		JPanel centerJP = new JPanel();
		JButton jb1 = new JButton("添加笔记");			//静态独立界面
		JButton jb2 = new JButton("我的信息");			//用的是空白界面"New"
		JButton jb3 = new JButton("我的笔记");			//用的是空白界面"New"
		JButton jb4 = new JButton("推荐阅读");			//用的是空白界面"New"
		JButton jb5 = new JButton("搜索笔记");			//静态独立界面
		JButton jb7 = new JButton("查看公告");			//用的是空白界面"New"
		JButton jb6 = new JButton("注销");				
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	View.cl.show(View.cards, "AddNote");	
            }
        });
		jb2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	UserService.showUserData(View.nickname);
            	View.cl.show(View.cards, "New");	
            }
        });
		jb3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	NoteService.showMyNote(View.nickname);
            	View.cl.show(View.cards, "New");		
            }
        });
		jb4.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	NoteService.showNote();
            	View.cl.show(View.cards, "New");	
            }
        });
		jb5.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	View.cl.show(View.cards, "SearchNote");
            }
        });
		jb6.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            	View.nickname = null;
            	View.cl.show(View.cards, "LogIn");
            }
        });
		jb7.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	NoteService.showAnnouncement();
            	View.cl.show(View.cards, "New");
            }
        });
		centerJP.add(jb1);
		centerJP.add(jb2);
		centerJP.add(jb3);
		centerJP.add(jb4);
		centerJP.add(jb5);
		centerJP.add(jb7);
		jp.add(new JLabel("                                                    Hello!"+View.nickname), BorderLayout.NORTH);
		jp.add(centerJP, BorderLayout.CENTER);
		jp.add(jb6, BorderLayout.SOUTH);
		return jp;
	}
									//登录界面
	public static JPanel logIn() {
		JPanel jp = new JPanel();
		JTextField jtf1 = new JTextField("请输入账号",15);
		JPasswordField jtf2 = new JPasswordField("请输入密码",15);
		JButton jb1 = new JButton("登录");
		JButton jb2 = new JButton("管理员登录");
		JButton jb3 = new JButton("注册");
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            		View.nickname = UserService.userLogOn(jtf1.getText(),jtf2.getText());
		            if(View.nickname!=null) {
		            	JOptionPane.showMessageDialog(null,"登录成功!");
		            	View.cards.add(AddStaticView.menu(),"Menu");
		            	View.cl.show(View.cards,"Menu");
		            }
		            else JOptionPane.showMessageDialog(null,"账号或密码错误!");
            }
        });
		jb2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
	            	if(AdminService.adminLogIn(jtf1.getText(),jtf2.getText())) {
	            		JOptionPane.showMessageDialog(null,"登录成功!");
	            		View.cl.show(View.cards,"AdminMenu");
	            	}
	            	else JOptionPane.showMessageDialog(null,"账号或密码错误!");
            }
        });
		jb3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            	View.cl.show(View.cards, "LogOn");
            }
        });
		jp.add(new JLabel("账号:"));
		jp.add(jtf1);
		jp.add(new JLabel("密码:"));
		jp.add(jtf2);	
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);	
		return jp;
	}
								//注册界面
	public static JPanel logOn() {
		JPanel jp = new JPanel();
		JTextField username = new JTextField(30);
		JTextField password = new JTextField(30);
		JTextField nickname = new JTextField(30);
		JButton jb1 = new JButton("注册");
		JButton jb2 = new JButton("返回");
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(username.getText().length()>=12||password.getText().length()>=12||nickname.getText().length()>=12) 
            		JOptionPane.showMessageDialog(null,"账号或密码长度过长，请重试");
            	else {
	            	if(UserService.addUser(nickname.getText(), username.getText(), password.getText())) {
	            		JOptionPane.showMessageDialog(null,"注册成功!");
	            		View.cl.show(View.cards, "LogIn");
	            	}
	            	else JOptionPane.showMessageDialog(null,"注册失败请修改账号密码昵称后重试!");
            	}
            }
        });
		jb2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            		View.cl.show(View.cards, "LogIn");
            }
        });
		jp.add(new JLabel("昵称："));
		jp.add(nickname);
		jp.add(new JLabel("账号："));
		jp.add(username);
		jp.add(new JLabel("密码："));
		jp.add(password);
		jp.add(jb1);
		jp.add(jb2);
		return jp;
	}
											//搜索笔记界面
	public static JPanel searchNote() {
		JPanel jp = new JPanel();
		JButton jb1 = new JButton("搜索");	
		JButton jb2 = new JButton("返回");	
		JTextField jtf = new JTextField("请输入标题或作者名",15);
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	NoteService.searchNote(jtf.getText());				//将得到的笔记标题集传入TitleJPanel方法中，改变面板内容
            	View.cl.show(View.cards, "New");									//展示搜索到的笔记
            }
        });
		jb2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	View.cl.show(View.cards, "Menu");
            }
        });
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jtf);
		return jp;
	}
										//添加笔记界面
	public static JPanel addNote() {
		JPanel jp = new JPanel();
		JButton jb1 = new JButton("返回");
		JButton jb2 = new JButton("添加私密笔记");
		JButton jb3 = new JButton("添加公开笔记");
		JTextField title = new JTextField("标题", 15);
		JTextArea content = new JTextArea("内容",7,30);
		content.setLineWrap(true);    																//设置文本域中的文本为自动换行
		JScrollPane jsp = new JScrollPane(content); 												//将文本域添加到滚动面板jsp中
		jsp.setBounds(110, 90, content.getPreferredSize().width, content.getPreferredSize().height); //设置滚动面板的首选大小
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	View.cl.show(View.cards, "Menu");
            }
        });
		jb2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            	if(NoteService.addNote(1, View.nickname, title.getText(), content.getText())) 
           		 	 JOptionPane.showMessageDialog(null,"添加成功!");
            	else JOptionPane.showMessageDialog(null,"添加失败!");
           	View.cl.show(View.cards, "Menu");
            }
        });
		jb3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(NoteService.addNote(0, View.nickname, title.getText(), content.getText())) 
            		 JOptionPane.showMessageDialog(null,"添加成功!");
            	else JOptionPane.showMessageDialog(null,"添加失败!");
            	View.cl.show(View.cards, "Menu");
            }
        });
		
		jp.add(title);
		jp.add(jb1);
		jp.add(jsp);
		jp.add(jb2);
		jp.add(jb3);
		return jp;
	}
													//发布公告页面
	public static JPanel addAnnouncement() {
		JPanel jp = new JPanel();
		JButton jb1 = new JButton("返回");
		JButton jb2 = new JButton("发布");	
		JTextField title = new JTextField("时间:xxxx-xx-xx", 15);
		JTextArea content = new JTextArea("内容",7,30);
		content.setLineWrap(true);    																//设置文本域中的文本为自动换行
		JScrollPane jsp = new JScrollPane(content); 												//将文本域添加到滚动面板jsp中
		jsp.setBounds(110, 90, content.getPreferredSize().width, content.getPreferredSize().height); //设置滚动面板的首选大小
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	View.cl.show(View.cards, "AdminMenu");
            }
        });
		jb2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            	if(NoteService.addNote(2, "admin", title.getText(), content.getText())) 
           		 	 JOptionPane.showMessageDialog(null,"添加成功!");
            	else JOptionPane.showMessageDialog(null,"添加失败!");
           	View.cl.show(View.cards, "AdminMenu");
            }
        });

		jp.add(title);
		jp.add(jsp);
		jp.add(jb1);
		jp.add(jb2);
		return jp;
	}
}
