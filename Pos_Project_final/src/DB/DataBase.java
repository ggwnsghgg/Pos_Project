//이곳은 포스기에서 찍힌 품목들을 mysql로 보내는 클래스 입니다.
//mysql의 데이터를 받아오는 클래스는 AdminPage_Panel입니다.

package DB;

import java.sql.*;
import menu.MenuPage;


public class DataBase {	
		public DataBase() {
		Connection conn = null;
		Statement stmt = null;
		//연결할 DBMS의 주소
		String url = "jdbc:mysql://database-1.c5n30oqbrfya.ap-northeast-2.rds.amazonaws.com:3306/posdb";
		// 접속할 mysql 아이디와 패스워드
		String id = "user";
		String pw = "playdata1*";
		
		//menu.MenuPage의 어레이리스트들(중복값없는)의 데이터를 mysql로 보내는 구간
		for(int i=0;i<menu.MenuPage.ProductList.size();i++) {			
			String menudb = menu.MenuPage.ProductList.get(i); //상품 이름
			int countdb = menu.MenuPage.CntList.get(i);	// 카운트
			int pricedb = menu.MenuPage.PriceList.get(i);	// 가격
			int sumdb = countdb * pricedb; // 합계
			try {
				 //클래스 로딩함
				Class.forName("com.mysql.jdbc.Driver");
				//mysql과 연결
				conn = DriverManager.getConnection(url,id,pw);
				//mysql과 연결된 conn객체로부터 구문객체를 얻음
				stmt = conn.createStatement();
				
				//mysql에서 미리 만들어진 테이블에(salesdata) 쿼리문 만들기
				StringBuilder sb = new StringBuilder();
				String SQL = sb.append("INSERT INTO salesdata VALUES (")
						.append("NULL")
						.append(",")
						.append("'")
						.append(menudb)
						.append("'")
						.append(",")
						.append(countdb)
						.append(",")
						.append(pricedb)
						.append(",")
						.append(sumdb)
						.append(",")
						.append("default")
						.append(");").toString();
				//mysql에서 쿼리문 실행하기
				stmt.execute(SQL);
				
				}						
			catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					// 사용한 connection 닫기
					if(conn != null && !conn.isClosed()) conn.close();
				}
				catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		}
}
