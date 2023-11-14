package all.databaseDAO;

import java.sql.Connection;
import java.sql.DriverManager;

import all.button.CommonService;
import all.button.CommonServiceImp;
import exam03.service.CommonServiceImpl;

public class DatabaseDAOImp implements DatabaseDAO{
	CommonService cs;
	Connection con;
	
	public DatabaseDAOImp() {
		cs = new CommonServiceImp();
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "#sqluser";
		String pass = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,user,pass);
			
			System.err.println("오라클 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("오라클 연결 실패");
		}
	}
	
	
}
