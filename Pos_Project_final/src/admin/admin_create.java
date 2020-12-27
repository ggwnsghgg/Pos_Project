package admin;

import java.awt.Button;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import menu.*;

class admin_create extends JFrame {
	
	admin_create() {
	adminDTO dto = new adminDTO();
	JFrame log_admin = new JFrame();
	JButton btn1 = new JButton("관리자 추가");
	JButton btn2 = new JButton("뒤로 가기");
	JLabel membername = new JLabel("관리자 명");
	JLabel id= new JLabel("아이디");
	JLabel pwd = new JLabel("비밀번호");
	JLabel birth = new JLabel("생년월일");
	JLabel gender = new JLabel("성별");
	JLabel phone = new JLabel("phone");
	JLabel email = new JLabel("이메일 주소");
	JLabel l5 = new JLabel("생년월일은 앞 6자리만 적으세요!!");
	
	TextField txt_f = new TextField();
	TextField txt_f2 = new TextField();
	JPasswordField txt_f3 = new JPasswordField();
	TextField txt_f4 = new TextField();
	TextField txt_f5 = new TextField();
	TextField txt_f6 = new TextField();
	TextField txt_f7 = new TextField();
	
	JRadioButton rb1 = new JRadioButton("남자");
	JRadioButton rb2 = new JRadioButton("여자");
	


	membername.setBounds(30,20,80,20);
	id.setBounds(30,60,80,20);
	pwd.setBounds(30,100,80,20);
	birth.setBounds(30,140,80,20);
	gender.setBounds(30,180,80,20);

	phone.setBounds(30,220,80,20);
	email.setBounds(30,260,80,20);
	l5.setBounds(100,280,200,20);
	l5.setForeground(Color.RED);
	
	
	txt_f.setBounds(120,20,200,20);
	txt_f2.setBounds(120,60,200,20);
	txt_f3.setBounds(120,100,200,20);
	txt_f4.setBounds(120,140,200,20);
	txt_f5.setBounds(120,180,200,20); 
	txt_f6.setBounds(120,220,200,20);
	txt_f7.setBounds(120,260,200,20);



	
	
	btn1.setBounds(80,320,100,30);
	btn1.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			
			Object obj = e.getSource();
			
			if ((JButton) obj == btn1) {
			dto.setMembername(txt_f.getText());
			dto.setId(txt_f2.getText());
			dto.setPw(txt_f3.getText());
			dto.setBirth(txt_f4.getText());
			dto.setGender(txt_f5.getText());
			dto.setPhone(txt_f6.getText());
			dto.setEmail(txt_f7.getText());
			}
			try {
				adminDAO.create(dto);
			}catch (Exception e1) {
				e1.printStackTrace();
			}
			log_admin.setVisible(false);
			new AdminPage_Frame();
		}
	});

	
	btn2.setBounds(240,320,100,30);
	btn2.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			log_admin.setVisible(false);
			new AdminPage_Frame();
		}
	});
	
	log_admin.add(membername);
	log_admin.add(id);
	log_admin.add(pwd);
	log_admin.add(birth);
	 log_admin.add(gender); 
	log_admin.add(phone);

	log_admin.add(email);
	log_admin.add(l5);

	log_admin.add(txt_f);
	log_admin.add(txt_f2);
	log_admin.add(txt_f3);
	log_admin.add(txt_f4);
	 log_admin.add(txt_f5); 
	log_admin.add(txt_f6);
	log_admin.add(txt_f7);
	

	log_admin.add(btn1);
	log_admin.add(btn2);
	
	log_admin.setSize(400,400);
	log_admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	log_admin.getContentPane().setLayout(null);   
	log_admin.setResizable(false);
	log_admin.setTitle("관리자 페이지");
	log_admin.setVisible(true);
	log_admin.setLayout(null);
	
	
	
		
	}
}

