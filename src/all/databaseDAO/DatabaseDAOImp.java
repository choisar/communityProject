package all.databaseDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import all.boardService.Board;
import all.Member;
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
	
	// 입력받은 아이디와 비밀번호가 dao에 있는 아이디, 비밀번호와 일치하면 true를 일치하지 않으면 false를 반환
	@Override
	public boolean loginChk(String id,String pw) {
		// TODO Auto-generated method stub
		String sql = "select decode(count(*), 1, 'true', 'false') from member where member_id=? and member_pw=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return Boolean.parseBoolean(rs.getString(1));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean insertMember(Member m) {
		// TODO Auto-generated method stub
		 if(idChkCom) {
			 String sql = "insert into member values(mem_seq.nextval,?,?,?,?,?,?,?,?)";
			 
			 try {
				 pstmt = con.prepareStatement(sql);
				
				 pstmt.setString(1, m.getId());
	             pstmt.setString(2, m.getPw());
	             pstmt.setString(3, m.getName());
	             pstmt.setString(4, m.getNickName());            
	             pstmt.setDate(5, m.getBirthDate());
	             pstmt.setString(6, m.isGender());
	             pstmt.setString(7,m.getEmail());
	             pstmt.setString(8, m.getPhoneNum());

				 int result = pstmt.executeUpdate();
				 
				 if(result == 1) {
					 cs.msgBox("회원가입","회원가입여부","회원가입에 성공하셨습니다.");
					 return true;
				 	}
			 	} catch(Exception e) {
			 		// TODO: handle exception
			 		e.printStackTrace();
			 		}
		 } else { // 아이디 중복
			 cs.msgBox("아이디","아이디중복","아이디 중복 확인하세요");
		 	}
		return false;
	}
	
	public String getGender(boolean gender) {
		// TODO Auto-generated method stub
		if(gender) {
			return "여성";
		} 	
		return "남성";
	}

	private boolean chkId(String id) {
		// TODO Auto-generated method stub
			boolean result = false;
			String sql = "select decode(count(*),1,'false','true')" + "from member where member_id=?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1,id);
				
				rs = pstmt.executeQuery();
		
				if(rs.next()) {
					result = Boolean.parseBoolean(rs.getString(1));
				} 
				return result;
			} catch(Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			 } 
			 return true;
	
	}
	
	@Override
	public boolean dupID(String txtId) {
		// TODO Auto-generated method stub
		if(txtId.isEmpty()) {

			cs.msgBox("아이디","아이디중복","아이디를 입력하세요");

			return false;

		}else {

			if(!chkId(txtId)) {

				cs.msgBox("아이디","아이디중복","같은 아이디가 존재합니다. 다시 입력하세요");
				idChkCom = false;
				return false;

			}else {

				cs.msgBox("아이디","아이디중복","사용가능한 아이디입니다.");
				idChkCom = true;
				
				return true;


			}
		}
	}
	
	public List<Member> selectAll1() {
		// TODO Auto-generated method stub
		List<Member> memberList = new ArrayList<Member>();
		String sql = "select*from member";
		
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Member m =new Member();
				pstmt.setString(1, m.getName());
				pstmt.setString(2, m.getId());
				pstmt.setString(3, m.getNickName());
				pstmt.setString(4, m.getPw());
				pstmt.setDate(5, m.getBirthDate());
				pstmt.setString(6, m.isGender());
				pstmt.setString(7,m.getEmail());
				pstmt.setString(8, m.getPhoneNum());

				memberList.add(m);
			}
			return memberList;
		} catch (Exception e) {
			//TODO: handle exception
			e.printStackTrace();
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
				b.setNicName(rs.getString(2));
				b.setTitle(rs.getString(3));
				b.setCategori(rs.getString(4));
				
				// 타임스탬프 분까지만 자리수 끊기
				Timestamp timestamp = rs.getTimestamp(5);
				
	            if (timestamp != null) {
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	                String dateStr = sdf.format(timestamp);
	                b.setUploadDate(dateStr);
	            }
				
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
		String sql = "select * from board where category = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, category);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				Board b = new Board();
				b.setNicName(rs.getString(2));
				b.setTitle(rs.getString(3));
				b.setCategori(rs.getString(4));
				
				// 타임스탬프 분까지만 자리수 끊기
				Timestamp timestamp = rs.getTimestamp(5);
	            if (timestamp != null) {
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	                String dateStr = sdf.format(timestamp);
	                b.setUploadDate(dateStr);
	            }
				
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
	            b.setNicName(rs.getString(2));
	            b.setTitle(rs.getString(3));
	            b.setCategori(rs.getString(4));
	            
				// 타임스탬프 분까지만 자리수 끊기
				Timestamp timestamp = rs.getTimestamp(5);
	            if (timestamp != null) {
	                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	                String dateStr = sdf.format(timestamp);
	                b.setUploadDate(dateStr);
	            }
	            
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
	public boolean uploadBoard(Board b) {
		// TODO Auto-generated method stub
		String sql="insert into board values(board_seq.nextval, ?, ?, ?, ?)";
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

	public Member memberInfo(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from member where member_id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();

			if(rs.next()) {
				Member m = new Member();
				m.setNum(rs.getInt(1));
				m.setId(rs.getString(2));
				m.setPw(rs.getString(3));
				m.setName(rs.getString(4));
				m.setNickName(rs.getString(5));
				m.setBirthDate(rs.getDate(6));
				m.setGender(rs.getString(7));
				m.setEmail(rs.getString(8));
				m.setPhoneNum(rs.getString(9));
				return m;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	

}
