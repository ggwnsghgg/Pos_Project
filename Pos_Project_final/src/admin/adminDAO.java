package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class adminDAO {
	 public static void main(String[] args) {


	 }

	public static boolean create(adminDTO dto) throws Exception {

	

	boolean flag = false;

	Connection con = null;
	Statement stmt = null; // 데이터를 전송하는 객체

	String membername = dto.getMembername();
	String id = dto.getId();
	String pw = dto.getPwd();
	String birth = dto.getBirth();
	String gender = dto.getGender();
	String phone = dto.getPhone();
	String email = dto.getEmail();
	
	String sql = "INSERT INTO memberdb(membername, id, pw, birth, gender, phone, email) VALUES";

	 

	try {



	sql += "('" + new String(membername.getBytes()) + "','"  
	+ new String(id.getBytes()) + "','"
	+ new String(pw.getBytes()) + "','"
	+ new String(birth.getBytes()) + "','"
	+ new String(gender.getBytes()) + "','"
	+ new String(phone.getBytes()) + "','"
	+ new String(email.getBytes()) + "')";

	 


	Class.forName("com.mysql.cj.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://database-1.c5n30oqbrfya.ap-northeast-2.rds.amazonaws.com:3306/posdb?serverTimezone=Asia/Seoul","user","playdata1*");
	stmt = (Statement) con.createStatement();
	stmt.executeUpdate(sql);

	 

	flag = true;
	} catch (Exception e) {
	System.out.println(e);
	flag = false;
	} finally {

	 

	try {

	if (stmt != null)
	stmt.close();
	if (con != null)
	con.close();
	} catch (Exception e) {
	System.out.println(e.getMessage());
	}
	}

	 

	return flag;

	 

	}

	}