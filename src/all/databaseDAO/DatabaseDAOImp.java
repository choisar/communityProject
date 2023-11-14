package all.databaseDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseDAOImp implements DatabaseDAO{

	Connection con;
	
	public DatabaseDAOImp() {
		
		String url = "";
		String user = "#sqluser";
		String pass = "1234";
		
		try {
			con = DriverManager.getConnection(url,user,pass);
			
			System.err.println("오라클 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("오라클 연결 실패");
		}
	}
	
	
}
