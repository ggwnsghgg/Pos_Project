package menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import admin.admin_login;

public class PosProgramFrame extends JFrame {
   
   public PosProgramFrame() {
   
	 JFrame frame = new JFrame();
	 frame.setTitle("편의점 무인 시스템");
	 frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 frame.setContentPane(new MenuPage());
	 frame.setSize(1600,1000);
	 frame.setVisible(true);
	 
     JButton btnNewButton = new JButton("관리자");
     
     btnNewButton.setBounds(1030, 900, 80, 23);
     btnNewButton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			frame.setVisible(false);
			new admin_login();
			
		}
	});
     frame.add(btnNewButton);
   }
}