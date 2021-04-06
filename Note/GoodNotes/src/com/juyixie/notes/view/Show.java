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
					//�÷������ڿ��ٴ���һ��������䵽�ɱ����New��
	
					//��ʾ��������
	public static void personalDataShow(User user) {
		View.jsp.getViewport().removeAll();
		JPanel jp = new JPanel();
		JLabel jl = new JLabel("   ID:"+user.getNickname()+"   ");
		JButton jb1 = new JButton("����");
		JButton jb2 = new JButton("�޸�");
		jb1.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(View.nickname!=null) View.cl.show(View.cards, "Menu");	//����ǹ���Ա���������û�����
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
					//�޸ĸ������Ͻ���
	public static void changePersonalDataShow(User user) {
		View.jsp.getViewport().removeAll();
		JPanel jp = new JPanel();
		JButton jb = new JButton("ȷ���޸�");
		JTextField jtf = new JTextField(user.getNickname(), 10);
		jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            	User newUser = new User();
            	newUser.setNickname(jtf.getText());
            	if(UserService.changeNickname(user.getNickname(), newUser))	//�����޸ĸ�����Ϣ�ķ���
            	{
	            	if(View.nickname!=null) {
	            		JOptionPane.showMessageDialog(null,"�޸ĳɹ�!�����µ�½��");
		            	View.cl.show(View.cards, "LogIn");
	            	}
	            	else View.cl.show(View.cards, "AdminMenu");						//����ǹ���Ա���������û�����
            	}
            	else JOptionPane.showMessageDialog(null,"���ֳ������ظ����޸ĺ����ԣ�");
            }
        });
		jp.add(jtf);
		jp.add(jb);
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
					//չʾ����
	public static void titleShow(HashSet<Note> noteSet) {
		View.jsp.getViewport().removeAll();					//ֱ��ʹ��removeAll��revalidate�޷�������ʾˢ�º��ҳ�棬����getViewport�õ�jsp����ͼ����ֱ��ˢ��ҳ��
		JPanel jp = new JPanel();
		JButton jb = new JButton("����");
		jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(View.nickname!=null)View.cl.show(View.cards, "Menu");
            	else View.cl.show(View.cards, "AdminMenu");
            }
        });
		jp.add(jb);
		Iterator<Note> iterator= noteSet.iterator();			//��������������noteSet
		while(iterator.hasNext()){
			Note note = iterator.next();										
			jp.add(JButtonUtils.getReadButton(note));
			jp.add(new JLabel("����ID��"+note.getNickname()));
			}
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
					//չʾ����ʼ�����
	public static void contentShow(Note note) {
		View.jsp.getViewport().removeAll();
		JPanel jp = new JPanel();
		JTextField jtf = new JTextField("��������");								//������۵������
		JTextArea commentArea = new JTextArea(note.getComment(),7,30);			//������
		commentArea.setEditable(false); 						
		commentArea.setLineWrap(true);
		JTextArea jta = new JTextArea(note.getText(),7,30);						//�����ı������ʼ�����
		jta.setEditable(false); 												//�ر��ı��������������ı�չʾ
		jta.setLineWrap(true);													//�����Զ�����
		JButton jb = new JButton("����");
		JButton discussButton = new JButton("����");
		JButton delete = new JButton("ɾ��");
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
            		JOptionPane.showMessageDialog(null,"ɾ���ɹ�!");
            		View.cl.show(View.cards, "AdminMenu");
            	}
            	else JOptionPane.showMessageDialog(null,"ɾ��ʧ��!");
            }
        });
		discussButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {	
            	note.setComment(note.getComment()+"\n"+View.nickname+":"+jtf.getText());		//�����ۿ��е�������ӵ������ʼǵ���������
            	NoteService.discuss(note);
            	Show.contentShow(note);										//����ҳ��
            	View.cl.show(View.cards, "New");
            }
        });
		
		jp.add(jb);
		if(View.nickname!=null)jp.add(jtf);				//�û��ʼ�ר��
		if(View.nickname!=null)jp.add(discussButton);	//�û��ʼ�ר��
		if(View.nickname==null) jp.add(delete);			//����Աר��
		jp.add(jta);
		jp.add(commentArea);
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
					//չʾ�Լ��ʼ�
	public static void myNoteShow(HashSet<Note> noteSet) {
		View.jsp.getViewport().removeAll();					
		JPanel jp = new JPanel();
		JButton jb = new JButton("����");
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
						//�ʼ��޸�ҳ��
	public static void changeShow(Note note) {
		Note newNote = new Note();
		View.jsp.getViewport().removeAll();
		JPanel jp = new JPanel();
		JButton jb1 = new JButton("�޸�Ϊ�����ʼ�");
		JButton jb2 = new JButton("�޸�Ϊ˽�ܱʼ�");
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
            	NoteService.changeNote(note, newNote);		//�˴�������ʾ
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
									//չʾ�����û�����
	public static void allUserShow(HashSet<User> userSet) {
		View.jsp.getViewport().removeAll();
		JPanel jp = new JPanel();
		JButton jb = new JButton("����");
		jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	View.cl.show(View.cards, "AdminMenu");
            }
        });
		jp.add(jb);
		Iterator<User> iterator= userSet.iterator();			//��������������noteSet
		while(iterator.hasNext()){
			User user = iterator.next();										
			jp.add(JButtonUtils.getUserButton(user));
			}
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
						//չʾ����
	public static void showAnnouncement(HashSet<Note> noteSet) {
		View.jsp.getViewport().removeAll();					
		JPanel jp = new JPanel();
		JButton jb = new JButton("����");
		jb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
            	if(View.nickname!=null)View.cl.show(View.cards, "Menu");
            	else View.cl.show(View.cards, "AdminMenu");
            }
        });
		jp.add(jb);
		Iterator<Note> iterator= noteSet.iterator();			//��������������noteSet
		while(iterator.hasNext()){
			Note note = iterator.next();										
			jp.add(JButtonUtils.getReadButton(note));
			}
		View.jsp.getViewport().add(jp);
		View.jsp.getViewport().revalidate();
	}
	
}
