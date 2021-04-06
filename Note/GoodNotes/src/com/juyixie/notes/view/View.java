package com.juyixie.notes.view;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.juyixie.notes.dao.AdminUse;
import com.juyixie.notes.dao.UserUse;
import com.juyixie.notes.util.JButtonUtils;

public class View extends JFrame{
	public static String nickname = null;
	public static JPanel cards = new JPanel(new CardLayout());
	public static CardLayout cl = (CardLayout)(cards.getLayout());				
	public static JScrollPane jsp=new JScrollPane();		//作可变组件 提前在View中添加 由逻辑代码根据信息来改变面板内容
	public View() {
		setTitle("Note");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,400,300);		
		cards.add(AddStaticView.logIn(),"LogIn");						//添加登录界面(静态页面)
//		cards.add(AddStaticView.menu(),"Menu");							//添加菜单界面(静态页面) 于登陆后添加实现伪动态
		cards.add(AddStaticView.searchNote(),"SearchNote");				//添加搜索界面(静态页面)	
		cards.add(AddStaticView.addNote(),"AddNote"); 					//添加笔记界面(静态界面)
		cards.add(AddStaticView.logOn(),"LogOn"); 						//添加注册界面(静态页面)
		cards.add(AddStaticView.adminMenu(), "AdminMenu");  			//添加管理界面(静态页面)
		cards.add(AddStaticView.addAnnouncement(), "AddAnnouncement");  //添加发布公告界面(静态页面)
		cards.add(jsp, "New"); 											//添加空白界面(动态页面)
		cl.show(cards,"LogIn");				
		add(cards);
	}
	public static void main(String[] args) {
		View view = new View();
		view.setVisible(true);
	}
	
}
