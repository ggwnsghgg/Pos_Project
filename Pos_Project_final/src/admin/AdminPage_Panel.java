package admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Container;
import java.awt.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.*;

import java.sql.*;


//판매관리 회원관리 탭 패널
public class AdminPage_Panel extends JPanel {
	//탭 생성
	JTabbedPane tPane = new JTabbedPane();
	
	//판매관리, 회원관리 공통 필드 값
	String[] Headings;
	String[][] Data;
	Dimension dim = new Dimension(1200,850);
		
	//판매관리, 회원관리에서 mysql 연동을 위한 공통 필드 값
	Connection conn = null; //mysql에 연결을 위한 필드값
	Statement stmt = null;  //mysql 쿼리문 생성을 위한 필드값
	ResultSet rs = null;
	ResultSet rs1 = null;
	String url = "jdbc:mysql://database-1.c5n30oqbrfya.ap-northeast-2.rds.amazonaws.com:3306/posdb"; //연결할 DBMS의 주소
	String id = "user"; // 접속할 mysql 아이디
	String pw = "playdata1*"; // 접속할 mysql 패스워드
	
	//판매관리 패널
	class SalesTab extends JPanel {
		public SalesTab() {				
			setBackground(Color.WHITE); // 판매관리 패널 바탕색
			String[] Headings = {"번호", "품목명", "수량", "가격", "합계"}; //테이블 column
			DefaultTableModel model = new DefaultTableModel(Data, Headings); //테이블에 들어갈 내용 
			JTable table = new JTable(model);	//테이블 생성		
			DefaultTableModel m = (DefaultTableModel)table.getModel(); //생성된 테이블 다운캐스팅
			JTextField tf = new JTextField(10); // 총 매출액 표시를 위한 JTextField
			
			//mysql 테이블(salesdata)에서 데이터 가져오는 구간
			try {
				//클래스 로딩함
				Class.forName("com.mysql.cj.jdbc.Driver");
				//mysql과 연결
				conn = DriverManager.getConnection(url,id,pw);  	
				//mysql과 연결된 conn객체로부터 구문객체를 얻음
				stmt = conn.createStatement();			
				//mysql에 쿼리문 만들기(중복값을 없애줌)
				rs = stmt.executeQuery("SELECT menudb, SUM(countdb), pricedb, SUM(sumdb) FROM salesdata GROUP BY menudb");
				//mysql 테이블(salesdata)--> 판매관리 Jtable로 데이터를 보냄 
				int number = 0;
				int totalsum = 0;
				while(rs.next()) {
					number = number + 1;
					String Menu = rs.getString("menudb");
					int count = rs.getInt("SUM(countdb)");
					int price = rs.getInt("pricedb");
					int sum = rs.getInt("SUM(sumdb)");
					m.addRow(new Object[]{number,Menu,count,price,sum});
					totalsum = totalsum + sum;
				}
				//총 매출액 출력
				tf.setBackground(Color.WHITE);
				tf.setFont(new Font("맑은 고딕", Font.BOLD, 15));
				add(tf);
				tf.setText(String.valueOf("매출액 : " + totalsum + "원"));
				}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					// 사용한 connection 닫기
					if(conn != null && !conn.isClosed()) conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
									
			//테이블 서식			
			table.setRowHeight(30);
			table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 15));
			table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			table.setPreferredScrollableViewportSize(dim);
			table.setFillsViewportHeight(true);
			
			//테이블 텍스트 가운데 정렬
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcm = table.getColumnModel();
			for(int i =0; i<tcm.getColumnCount(); i=i+1) {
				tcm.getColumn(i).setCellRenderer(dtcr);
			}
			//테이블 스크롤기능 추가 및 판매관리탭에 붙이기
			add(new JScrollPane(table));			
		}
	}
	
	//회원관리 패널
	class MemberTab extends JPanel {		
		public MemberTab() {							
			setBackground(Color.WHITE); // 회원관리 패널 바탕색			
			String[] Headings = {"번호", "이름", "아이디", "비밀번호", "생년월일", "성별", "전화번호", "이메일"}; //테이블 column
			DefaultTableModel model = new DefaultTableModel(Data, Headings); //테이블에 들어갈 내용
			JTable table = new JTable(model); //테이블 생성
			DefaultTableModel m = (DefaultTableModel)table.getModel(); //생성된 테이블 다운캐스팅
			JTextField tf = new JTextField(10); // 총 회원수 표시를 위한 JTextField
			
			//mysql 테이블(memberdb)에서 데이터 가져오는 구간
			try {
				//클래스 로딩함
				Class.forName("com.mysql.cj.jdbc.Driver");
				//mysql과 연결
				conn = DriverManager.getConnection(url,id,pw);
				//mysql과 연결된 conn객체로부터 구문객체를 얻음
				stmt = conn.createStatement();			
				//mysql에 쿼리문 만들기(중복값을 없애줌)
				rs = stmt.executeQuery("SELECT membername, id, pw, birth, gender, phone, email FROM memberdb");
				//mysql 테이블(memberdb)--> 판매관리 Jtable로 데이터를 보냄 
				int number = 0;
				while(rs.next()) {
					number = number + 1;
					String Name = rs.getString("membername");
					String ID = rs.getString("id");
					String PW = rs.getString("pw");
					String Birth = rs.getString("birth");
					String Gender = rs.getString("gender");
					String Phone = rs.getString("phone");
					String Email = rs.getString("email");
					m.addRow(new Object[]{number,Name,ID,PW,Birth,Gender,Phone,Email});
				}
				//총 회원 수 출력을 위한 mysql문
				rs1 = stmt.executeQuery("WITH abc (total) AS (SELECT COUNT(*) FROM memberdb) SELECT total FROM abc");
				int memberTotal = 0;
				while(rs1.next()) {
					memberTotal = memberTotal + rs1.getInt("total");					
				}
				//총 회원 수 출력
				tf.setBackground(Color.WHITE);
				add(tf);
				setBackground(Color.WHITE);
				tf.setText(String.valueOf("관리자 수 : " + memberTotal + "명"));
				tf.setFont(new Font("맑은 고딕", Font.BOLD, 15));							
				}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			finally {
				try {
					// 사용한 connection 닫기
					if(conn != null && !conn.isClosed()) conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
							

						
			//테이블 서식			
			table.setRowHeight(30);
			table.getTableHeader().setFont(new Font("맑은 고딕", Font.BOLD, 15));
			table.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
			table.setPreferredScrollableViewportSize(dim);
			table.setFillsViewportHeight(true);
			
			//테이블 텍스트 가운데 정렬
			DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
			dtcr.setHorizontalAlignment(SwingConstants.CENTER);
			TableColumnModel tcm = table.getColumnModel();
			for(int i =0; i<tcm.getColumnCount(); i=i+1) {
				tcm.getColumn(i).setCellRenderer(dtcr);
			}
			
			//테이블 스크롤기능 추가 및 회원관리탭에 붙이기
			add(new JScrollPane(table));
		}
	}
	
	//회원관리 패널, 판매관리 패널 탭에 붙이기
	public AdminPage_Panel() {
		//회원관리 패널 탭에 붙이기
		MemberTab mt = new MemberTab(); 		
		tPane.addTab("회원관리", mt);
		
		//판매관리 패널 탭에 붙이기
		SalesTab st = new SalesTab();
		tPane.addTab("판매관리", st);
		
		//전체 탭을 붙이기
		add(tPane);		

	}
}