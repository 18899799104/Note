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
	public static JScrollPane jsp=new JScrollPane();		//���ɱ���� ��ǰ��View����� ���߼����������Ϣ���ı��������
	public View() {
		setTitle("Note");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100,100,400,300);		
		cards.add(AddStaticView.logIn(),"LogIn");						//��ӵ�¼����(��̬ҳ��)
//		cards.add(AddStaticView.menu(),"Menu");							//��Ӳ˵�����(��̬ҳ��) �ڵ�½�����ʵ��α��̬
		cards.add(AddStaticView.searchNote(),"SearchNote");				//�����������(��̬ҳ��)	
		cards.add(AddStaticView.addNote(),"AddNote"); 					//��ӱʼǽ���(��̬����)
		cards.add(AddStaticView.logOn(),"LogOn"); 						//���ע�����(��̬ҳ��)
		cards.add(AddStaticView.adminMenu(), "AdminMenu");  			//��ӹ������(��̬ҳ��)
		cards.add(AddStaticView.addAnnouncement(), "AddAnnouncement");  //��ӷ����������(��̬ҳ��)
		cards.add(jsp, "New"); 											//��ӿհ׽���(��̬ҳ��)
		cl.show(cards,"LogIn");				
		add(cards);
	}
	public static void main(String[] args) {
		View view = new View();
		view.setVisible(true);
	}
	
}
