package all.databaseDAO;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import all.boardService.Board;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;

public class DatabaseDAOImp implements DatabaseDAO {
	CommonService cs;
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;

	// 오라클 SQL 연결
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

	// 아이디 체크
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
	
	// 아이디 찾기
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
	
	// 비밀번호 체크
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

	// 비밀번호 찾기
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

	// 이름 찾기
	@Override
	public String findUserName(String findId, String findPhoneNum) {
		String sql = "select member_Name from member " 
	+ "where member_id = ? and member_phonenum = ?";

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
	
	// 모든 카테고리의 모든 게시판
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
				b.setNickname(rs.getString(2));
				b.setTitle(rs.getString(3));
				b.setCat(rs.getString(4));
				b.setDate(rs.getTimestamp(5).toString());
				
				boardList.add(b);
				
			}
			return boardList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	// 카테고리 안에 있는 모든 게시판
	@Override
	public List<Board> categoryBoardAll(String category) {
		// TODO Auto-generated method stub
		
		List<Board> boardList = new ArrayList<Board>();
		String sql = "select * from board where ca = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board b = new Board();
				b.setNickname(rs.getString(2));
				b.setTitle(rs.getString(3));
				b.setCat(rs.getString(4));
				b.setDate(rs.getTimestamp(5).toString());
				
				boardList.add(b);
			}
			return boardList;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
	
	// 카테고리에 상관없이 검색 결과가 포함된 모든 게시물을 갖는 리스트 생성
	@Override
	public List<Board> searchResultAll(String text1,String text2) {
//		// TODO Auto-generated method stub
	    List<Board> boardList = new ArrayList<>();
		String sql = "select * from board where ? LIKE ?";
//	    String sql = "SELECT * FROM board WHERE " + text1 + " LIKE ?";
	    
	    try {
	        pstmt = con.prepareStatement(sql);
//	        pstmt.setString(1, "%" + text2 + "%");
			pstmt.setString(1, text1);
			pstmt.setString(2, "%"+text2+"%");
	        
	        rs = pstmt.executeQuery();
	        
	        while (rs.next()) {
	            Board b = new Board();
	            b.setNickname(rs.getString(2));
	            b.setTitle(rs.getString(3));
	            b.setCat(rs.getString(4));
	            b.setDate(rs.getTimestamp(5).toString());
	            boardList.add(b);
	        }
	        return boardList;
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return null;
	}

}
