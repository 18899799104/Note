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
							//����Ա�˵�
	public static JPanel adminMenu() {
			JPanel jp = new JPanel();
			JButton jb1 = new JButton("�鿴���бʼ�");			
			JButton jb2 = new JButton("�鿴�����û�");
			JButton jb3 = new JButton("��������");
			JButton jb4 = new JButton("�鿴����");

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
									//�û��˵�
	public static JPanel menu() {
		JPanel jp = new JPanel(new BorderLayout());
		JPanel centerJP = new JPanel();
		JButton jb1 = new JButton("��ӱʼ�");			//��̬��������
		JButton jb2 = new JButton("�ҵ���Ϣ");			//�õ��ǿհ׽���"New"
		JButton jb3 = new JButton("�ҵıʼ�");			//�õ��ǿհ׽���"New"
		JButton jb4 = new JButton("�Ƽ��Ķ�");			//�õ��ǿհ׽���"New"
		JButton jb5 = new JButton("�����ʼ�");			//��̬��������
		JButton jb7 = new JButton("�鿴����");			//�õ��ǿհ׽���"New"
		JButton jb6 = new JButton("ע��");				
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
									//��¼����
	public static JPanel logIn() {
		JPanel jp = new JPanel();
		JTextField jtf1 = new JTextField("�������˺�",15);
		JPasswordField jtf2 = new JPasswordField("����������",15);
		JButton jb1 = new JButton("��¼");
		JButton jb2 = new JButton("����Ա��¼");
		JButton jb3 = new JButton("ע��");
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            		View.nickname = UserService.userLogOn(jtf1.getText(),jtf2.getText());
		            if(View.nickname!=null) {
		            	JOptionPane.showMessageDialog(null,"��¼�ɹ�!");
		            	View.cards.add(AddStaticView.menu(),"Menu");
		            	View.cl.show(View.cards,"Menu");
		            }
		            else JOptionPane.showMessageDialog(null,"�˺Ż��������!");
            }
        });
		jb2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
	            	if(AdminService.adminLogIn(jtf1.getText(),jtf2.getText())) {
	            		JOptionPane.showMessageDialog(null,"��¼�ɹ�!");
	            		View.cl.show(View.cards,"AdminMenu");
	            	}
	            	else JOptionPane.showMessageDialog(null,"�˺Ż��������!");
            }
        });
		jb3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            	View.cl.show(View.cards, "LogOn");
            }
        });
		jp.add(new JLabel("�˺�:"));
		jp.add(jtf1);
		jp.add(new JLabel("����:"));
		jp.add(jtf2);	
		jp.add(jb1);
		jp.add(jb2);
		jp.add(jb3);	
		return jp;
	}
								//ע�����
	public static JPanel logOn() {
		JPanel jp = new JPanel();
		JTextField username = new JTextField(30);
		JTextField password = new JTextField(30);
		JTextField nickname = new JTextField(30);
		JButton jb1 = new JButton("ע��");
		JButton jb2 = new JButton("����");
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(username.getText().length()>=12||password.getText().length()>=12||nickname.getText().length()>=12) 
            		JOptionPane.showMessageDialog(null,"�˺Ż����볤�ȹ�����������");
            	else {
	            	if(UserService.addUser(nickname.getText(), username.getText(), password.getText())) {
	            		JOptionPane.showMessageDialog(null,"ע��ɹ�!");
	            		View.cl.show(View.cards, "LogIn");
	            	}
	            	else JOptionPane.showMessageDialog(null,"ע��ʧ�����޸��˺������ǳƺ�����!");
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
		jp.add(new JLabel("�ǳƣ�"));
		jp.add(nickname);
		jp.add(new JLabel("�˺ţ�"));
		jp.add(username);
		jp.add(new JLabel("���룺"));
		jp.add(password);
		jp.add(jb1);
		jp.add(jb2);
		return jp;
	}
											//�����ʼǽ���
	public static JPanel searchNote() {
		JPanel jp = new JPanel();
		JButton jb1 = new JButton("����");	
		JButton jb2 = new JButton("����");	
		JTextField jtf = new JTextField("����������������",15);
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	NoteService.searchNote(jtf.getText());				//���õ��ıʼǱ��⼯����TitleJPanel�����У��ı��������
            	View.cl.show(View.cards, "New");									//չʾ�������ıʼ�
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
										//��ӱʼǽ���
	public static JPanel addNote() {
		JPanel jp = new JPanel();
		JButton jb1 = new JButton("����");
		JButton jb2 = new JButton("���˽�ܱʼ�");
		JButton jb3 = new JButton("��ӹ����ʼ�");
		JTextField title = new JTextField("����", 15);
		JTextArea content = new JTextArea("����",7,30);
		content.setLineWrap(true);    																//�����ı����е��ı�Ϊ�Զ�����
		JScrollPane jsp = new JScrollPane(content); 												//���ı�����ӵ��������jsp��
		jsp.setBounds(110, 90, content.getPreferredSize().width, content.getPreferredSize().height); //���ù���������ѡ��С
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
           		 	 JOptionPane.showMessageDialog(null,"��ӳɹ�!");
            	else JOptionPane.showMessageDialog(null,"���ʧ��!");
           	View.cl.show(View.cards, "Menu");
            }
        });
		jb3.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(NoteService.addNote(0, View.nickname, title.getText(), content.getText())) 
            		 JOptionPane.showMessageDialog(null,"��ӳɹ�!");
            	else JOptionPane.showMessageDialog(null,"���ʧ��!");
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
													//��������ҳ��
	public static JPanel addAnnouncement() {
		JPanel jp = new JPanel();
		JButton jb1 = new JButton("����");
		JButton jb2 = new JButton("����");	
		JTextField title = new JTextField("ʱ��:xxxx-xx-xx", 15);
		JTextArea content = new JTextArea("����",7,30);
		content.setLineWrap(true);    																//�����ı����е��ı�Ϊ�Զ�����
		JScrollPane jsp = new JScrollPane(content); 												//���ı�����ӵ��������jsp��
		jsp.setBounds(110, 90, content.getPreferredSize().width, content.getPreferredSize().height); //���ù���������ѡ��С
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
           		 	 JOptionPane.showMessageDialog(null,"��ӳɹ�!");
            	else JOptionPane.showMessageDialog(null,"���ʧ��!");
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
