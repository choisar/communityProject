package all.uploadPck.upload.databaseDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import upload.Board;

public class DatabaseDAOImp1 implements DatabaseDAO1{

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	
	public DatabaseDAOImp1() {
		String url="jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user="c##sqluser";
		String pass="1234";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con=DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("오라클 연결 실패");
			e.printStackTrace();
		}
	}
	
	public boolean uploadBoard(Board b) {
		String sql="insert into board3 values(board_seq.nextval, ?, ?, ?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getContents());
			pstmt.setBytes(3, b.getImagePath());
			
			int result=pstmt.executeUpdate();
			
			if(result==1) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public byte[] getImageData(int no) {
		String sql = "SELECT imagepath FROM board3 WHERE board_no = ?";
		Board b=new Board();
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			rs=pstmt.executeQuery();
			if (rs.next()) {
                return rs.getBytes("imagepath");
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
    }
	
	
}
