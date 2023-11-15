package all.databaseDAO_hong;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import all.boardService_hong.Board;

public class DatabaseDAOImp implements DatabaseDAO{

	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	
	public DatabaseDAOImp() {
		
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "system";
		String pass = "1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pass);
			
			System.err.println("오라클 연결 성공");
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("오라클 연결 실패");
			e.printStackTrace();
		}
	}

	@Override
	public List<Board> selectAll() {
		// TODO Auto-generated method stub
		
		List<Board> boardList = new ArrayList<Board>();
		String sql = "select * from board";
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board b = new Board();
//				b.setId(rs.getString(1));
				b.setNickname(rs.getString(2));
				b.setTitle(rs.getString(3));
				b.setDate(rs.getTimestamp(4).toString());
				
				boardList.add(b);
				
			}
			return boardList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	
}
