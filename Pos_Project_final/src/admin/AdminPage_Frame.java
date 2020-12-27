package admin;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import menu.MenuPage;
import admin.admin_create;


public class AdminPage_Frame extends JFrame {
	public AdminPage_Frame() {
		
		JButton btn = new JButton("로그아웃");
		JButton btn1 = new JButton("관리자 추가");
		JFrame frame = new JFrame();
		
		btn.setBounds(10,10,30,30);
		btn.addActionListener(new ActionListener() {
			
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				new menu.PosProgramFrame();
			}
		});

		
		btn1.setBounds(10,30,30,30);
		
		
		
		btn1.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new admin.admin_create();
			}
		});
		
		frame.setTitle("Admin");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setContentPane(new AdminPage_Panel());
		
		frame.setSize(1600,1000);
		frame.setVisible(true);
		
		frame.add(btn);
		frame.add(btn1);

	}
}	