package all.databaseDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import all.button.CommonService;
import all.button.CommonServiceImp;

public class DatabaseDAOImp implements DatabaseDAO {
	CommonService cs;
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;

	public DatabaseDAOImp() {
		cs = new CommonServiceImp();
		String url = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
		String user = "c##sqluser";
		String pass = "1234";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pass);
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println("오라클 연결 실패");
			e.printStackTrace();
		}
	}

	public boolean idChk(String name, String phoneNum) {
		// TODO Auto-generated method stub
		String sql = "select decode(count(*), 1, 'true', 'false') "
				+ "from member where member_name=? and member_phonenum=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, phoneNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				// Boolean.parseBoolean("문자열") : 문자열을 boolean 으로
				return Boolean.parseBoolean(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public String findId(String findName, String findPhoneNum) {
		String sql = "select member_id from member " + "where member_name = ? and member_phonenum = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, findName);
			pstmt.setString(2, findPhoneNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("member_id");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public boolean pwChk(String findId, String findPhoneNum) {
		String sql = "select decode(count(*), 1, 'true', 'false') "
				+ "from member where member_id=? and member_phonenum=?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, findId);
			pstmt.setString(2, findPhoneNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return Boolean.parseBoolean(rs.getString(1));
			}
		} catch (Exception e) {
		}
		return false;
	}

	@Override
	public String findPw(String findId, String findPhoneNum) {
		String sql = "select member_pw from member " 
		+ "where member_id = ? and member_phonenum = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, findId);
			pstmt.setString(2, findPhoneNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("member_pw");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	@Override
	public String findUserName(String findId, String findPhoneNum) {
		String sql = "select member_Name from member " + "where member_id = ? and member_phonenum = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, findId);
			pstmt.setString(2, findPhoneNum);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				return rs.getString("member_name");
			}

		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

}
