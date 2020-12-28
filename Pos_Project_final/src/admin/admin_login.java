package admin;

import java.awt.Color;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import menu.MenuPage;

public class admin_login extends JFrame {


	String col;

	public admin_login() {

		JFrame login = new JFrame("로그인");
		login.setSize(300, 300);
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.getContentPane().setLayout(null);
		login.setResizable(false);
		login.setTitle("관리자 페이지");
		login.setVisible(true);
		login.setLayout(null);

		JLabel title = new JLabel("관리자 로그인 페이지입니다.");
		title.setBounds(80, 10, 200, 30);
		title.setOpaque(true);
		title.setForeground(Color.RED);

		// id , pw 라벨 생성

		JLabel id = new JLabel("ID ");
		id.setBounds(40, 60, 40, 30);
		JLabel pw = new JLabel("pw ");
		pw.setBounds(40, 110, 40, 30);

		// ID , PW 입력 구간

		TextField id_f = new TextField();
		id_f.setBounds(90, 60, 160, 30);

		TextField pw_f = new TextField();
		pw_f.setBounds(90, 110, 160, 30);
		pw_f.setEchoChar('*');

		// 로그인 버튼 구간

		JButton login_button = new JButton("로그인");
		login_button.setBounds(90, 150, 150, 40);
		login_button.addActionListener(new ActionListener() {
		
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Connection conn;
				Statement stmt;
				ResultSet rs;
				Properties info;
				
			
				String url = "jdbc:mysql://database-1.c5n30oqbrfya.ap-northeast-2.rds.amazonaws.com:3306/posdb";
				String id = "user";
				String pw = "playdata1*";
				String sql;


				try {
					Class.forName("com.mysql.jdbc.Driver");
					conn = DriverManager.getConnection(url, id, pw);

					System.out.println("DB 연결 완료");
					stmt = conn.createStatement();
					sql = "SELECT * FROM posdb.memberdb where id='" + id_f.getText() + "'";
					rs = stmt.executeQuery(sql);
					 
					if(rs.next() == true) {
						sql = "SELECT * FROM posdb.memberdb where pw='" + pw_f.getText() + "'";
						rs = stmt.executeQuery(sql);
						if(rs.next() == true) {
							JOptionPane.showMessageDialog(null, "로그인이 되었습니다.");
							login.setVisible(false);
							new AdminPage_Frame();
						}else {
							JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다.");
						}
					}else {
						JOptionPane.showMessageDialog(null, "아이디가 틀렸습니다.");
					}
				} catch (ClassNotFoundException e1) {
					// TODO: handle exception
					System.out.println("JDBC 드라이버 로드 에러");
				} catch (SQLException e2) {
					System.out.println("DB 연결 에러");
				}
				
			}
		});

/*		
		  try { 
			  String s; String[] array; BufferedReader bos = new BufferedReader(new FileReader(
		  "C:\\Users\\shin\\Desktop\\JavaProject-main\\Pos_Project_final\\src\\admin\\회원명단.txt"
		  )); while((s = bos.readLine()) != null) { array=s.split("/");
		  if(id_f.getText().equals(array[1]) && pw_f.getText().equals(array[2])) {
		  JOptionPane.showMessageDialog(null, "로그인이 되었습니다."); login.setVisible(false);
		  new AdminPage_Frame(); } else { JOptionPane.showMessageDialog(null,
		  "로그인이 실패했습니다."); } } }catch (Exception e1) { e1.printStackTrace(); }
		 
		  } 
			});
		 
*/
		// 뒤로가기 버튼 구간
	JButton back_button = new JButton("뒤로 가기");
	back_button.setBounds(180, 220, 100, 30);
	back_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				login.setVisible(false);
				new menu.PosProgramFrame();
			}
		});

		login.add(title);
		login.add(login_button);
		login.add(back_button);
		login.add(id_f);
		login.add(pw_f);
		login.add(id);
		login.add(pw);
		
		login.setLocationRelativeTo(null);
	}
}


class admin_page extends JFrame {

	// 프레임 정의 구문 입니다.
	admin_page() {
		JTabbedPane t = new JTabbedPane();

		JPanel p = new JPanel();
		JPanel p1 = new JPanel();
		JButton btn = new JButton();

		// 관리자 테이블 내용
		String header[] = { "관리자 명", "생년월일", "관리자 ID", "관리자 PW" };
		String contents[][] = { { "신준호", "951211", "admin", "xxx" }, { "테스트", "951211", "admin1", "xxx1" }, };

		// 상품 테이블 내용
		String header1[] = { "상품명", "개수", "금액", "총금액" };
		String contents1[][] = { { "칸쵸", "2", "2000", "4000" },

		};

		// 관리자 테이블 정의
		JTable table = new JTable(contents, header);
		JScrollPane scroll = new JScrollPane(table);

		// 상품 테이블 정의
		JTable table1 = new JTable(contents1, header1);
		JScrollPane scroll1 = new JScrollPane(table1);

		btn.setBounds(30, 30, 30, 30);

		t.add("어드민 관리", scroll);
		t.add("판매 관리", scroll1);
		t.add("로그아웃", btn);

	}

}