package main;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.*;


import com.sun.net.httpserver.Authenticator.Result;
import com.sun.tools.javac.Main;

import admin.admin_login;
import menu.PosProgramFrame;
import menu.MenuPage;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// ------------------------------------------------------------------------------------------------

// 메인 화면

// ------------------------------------------------------------------------------------------------

public class main extends JFrame{

	private JFrame frame;
	private BufferedImage imag1;
	BufferedImage img;
	
	JPanel pane1 = new JPanel();

	
	
	
	  public static void main(String[] args) {
		  EventQueue.invokeLater(new Runnable() { 
			  	public void run() { 
			  	try { 
			  		main window = new main();
			  			window.frame.setVisible(true); 
	  } catch (Exception e) { 
		  e.printStackTrace(); 
		  }
	  } 	  	
		  }); 
		}
	
	public main() {
		initialize();
	}
	private void initialize() {


		
		
		JButton btn1 = new JButton("구매하러가기");
		ImageIcon img1 = new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\main\\메인.gif");

		frame = new JFrame();
		frame.setTitle("편의점 무인포스기");
		frame.setBounds(100, 100, 700, 900);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
	
		
		Color b=new Color(120,255,0);

		JButton btnNewButton = new JButton("\uAD6C\uB9E4\uD558\uB7EC \uAC00\uAE30");
		btn1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new menu.PosProgramFrame();
			}
		});
		
		btn1.setBounds(268, 800, 133, 40);

		frame.getContentPane().add(btn1);

		JLabel lblNewLabel = new JLabel();
		lblNewLabel.setIcon(img1);
		lblNewLabel.setBounds(20, 130, 640, 640);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\main\\배너.png"));
		lblNewLabel_1.setBounds(20, 21, 700, 100);
		frame.getContentPane().add(lblNewLabel_1);
		frame.setVisible(true);
		frame.setBackground(b);

		
			}

		}