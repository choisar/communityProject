package all.databaseDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import all.Board_s;
import all.boardService.Board;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;

public class DatabaseDAOImp implements DatabaseDAO {
	CommonService cs;
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	
	// 합치기
	boolean idChkCom;

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
	
	// 전체 게시판 - 모든 카테고리의 모든 게시물
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
	
	// 해당 카테고리에 있는 모든 게시물
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
	
	// 전체 카테고리에서 검색 결과가 포함된 모든 게시물
	@Override
	public List<Board> searchResultAll(String text1,String text2) {
//		// TODO Auto-generated method stub
	    List<Board> boardList = new ArrayList<>();
//		String sql = "select * from board where ? LIKE ?";
	    String sql = "SELECT * FROM board WHERE " + text1 + " LIKE ?";
	    
	    try {
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, "%" + text2 + "%");
//		     pstmt.setString(1, text1);
//			pstmt.setString(2, "%"+text2+"%");
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

	// db에 게시글 등록하기
	@Override
	public boolean uploadBoard(Board_s b) {
		// TODO Auto-generated method stub
		String sql="insert into board values(board_seq.nextval, ?, ?, ?,?)";
		try {
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, b.getTitle());
			pstmt.setString(2, b.getCategori());
			pstmt.setString(3, b.getContents());
			pstmt.setBytes(4, b.getImagePath());
			
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

	

}
