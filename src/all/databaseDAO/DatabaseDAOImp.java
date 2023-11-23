package all.databaseDAO;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import all.Member;
import all.boardService.Board;
import all.boardService.ImagePath;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class DatabaseDAOImp implements DatabaseDAO {
	CommonService cs;
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	static String nowId = "";
	static int nowBoardNum = 0;
	Parent root;
	
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
			cs.msgBox("Oracle", "오라클 연결 실패", "DAOImp를 확인하세요");
			e.printStackTrace();
		}

	}

	// 아이디 찾기 과정1 - 이름과 핸드폰 번호를 받아 일치하는 정보가 있으면 true, 없으면 false 반환
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

	// 아이디 찾기 과정2 - 과정1이 true이면 (일치하는 정보가 있으면) 일치하는 정보중 member_id를 문자열 값으로 반환,
	// false면 null값 반환
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

	// 비밀번호 찾기 과정1 - 아이디와 핸드폰 번호를 받아 일치하는 정보가 있으면 true, 없으면 false 반환
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

	// 비밀번호 찾기 과정2 - 과정1이 true이면 (일치하는 정보가 있으면)
	// 입력받은 아이디, 핸드폰번호와 일치하는 정보중 비밀번호(member_pw)를 문자열 값으로 반환, false면 null값 반환
	@Override
	public String findPw(String findId, String findPhoneNum) {
		String sql = "select member_pw from member " + "where member_id = ? and member_phonenum = ?";

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

	// 비밀번호 찾기 결과창에 이름 띄우기 - 과정 1이 true이면 (일치하는 정보가 있으면)
	// 입력받은 아이디, 핸드폰번호와 일치하는 정보중 이름(member_name)을 문자열 값으로 반환, false면 null값 반환
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

	// 입력받은 아이디와 비밀번호가 dao에 있는 아이디, 비밀번호와 일치하는 정보가 있으면 true를 일치하지 않으면 false를 반환
	@Override
	public boolean loginChk(String id, String pw) {
		String sql = "select decode(count(*), 1, 'true', 'false') from member where member_id=? and member_pw=?";
		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					if (Boolean.parseBoolean(rs.getString(1))) {
						nowId = id;
					}
					return Boolean.parseBoolean(rs.getString(1));
				}
			}
		} catch (Exception e) {
			// Exception handling (you might want to log the exception or perform other
			// meaningful actions)
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertMember(Member m) {
		// TODO Auto-generated method stub
			String sql = "insert into member values(mem_seq.nextval,?,?,?,?,?,?,?,?)";

			try {
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, m.getId());
				pstmt.setString(2, m.getPw());
				pstmt.setString(3, m.getName());
				pstmt.setString(4, m.getNickName());
				pstmt.setDate(5, m.getBirthDate());
				pstmt.setString(6, m.isGender());
				pstmt.setString(7, m.getEmail());
				pstmt.setString(8, m.getPhoneNum());

				int result = pstmt.executeUpdate();

				if (result == 1) {
					cs.customErrorView(root, "회원가입이 완료되었습니다.");
					return true;
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		return false;
	}

	public String getGender(boolean gender) {
		// TODO Auto-generated method stub
		if (gender) {
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
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				result = Boolean.parseBoolean(rs.getString(1));
			}
			return result;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;

	}

	// 회원가입 아이디 중복확인
		@Override
		public boolean dupID(Parent root,String txtId) {
			// TODO Auto-generated method stub
			if(txtId.isEmpty()) {
				cs.customErrorView(root, "아이디 중복 확인하세요.");
				return false;
			}else {
				if(!chkId(txtId)) {
					cs.customErrorView(root, "같은 아이디가 존재합니다. 다시 입력하세요");
					idChkCom = false;
					return false;
				}else {
					cs.customErrorView(root,  "사용가능한 아이디 입니다.");
					idChkCom = true;
					return true;
				}
			}
		}


	// 전체 멤버 - 모든 멤버객체
	public List<Member> selectAll1() {
		// TODO Auto-generated method stub
		List<Member> memberList = new ArrayList<Member>();
		String sql = "select*from member";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Member m = new Member();
				pstmt.setString(1, m.getName());
				pstmt.setString(2, m.getId());
				pstmt.setString(3, m.getNickName());
				pstmt.setString(4, m.getPw());
				pstmt.setDate(5, m.getBirthDate());
				pstmt.setString(6, m.isGender());
				pstmt.setString(7, m.getEmail());
				pstmt.setString(8, m.getPhoneNum());

				memberList.add(m);
			}
			return memberList;
		} catch (Exception e) {
			// TODO: handle exception
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
				b.setNo(rs.getInt(1));
				b.setNicName(rs.getString(2));
				b.setTitle(rs.getString(3));
				b.setCategori(rs.getString(4));
				b.setContents(rs.getString(6));
				b.setId(rs.getString(7));
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
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	// 카테고리 값을 정해주면 해당 카테고리에 있는 모든 게시물 정보를 갖는 리스트 생성
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
				b.setNo(rs.getInt(1));
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
				b.setContents(rs.getString(6));
				b.setId(rs.getString(7));
				boardList.add(b);
			}
			return boardList;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	// 메인화면 검색 - 전체 카테고리에서 검색 결과가 포함된 모든 게시물
	@Override
	public List<Board> searchResultAll(String text1, String text2) {
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
			
			if(!(rs.next())) {
				cs.customErrorView(root, "검색결과가 없습니다");
			}
			
			while (rs.next()) {
				Board b = new Board();
				b.setNo(rs.getInt(1));
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
				b.setContents(rs.getString(6));
				b.setId(rs.getString(7));
				
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
		String sql = "INSERT INTO board VALUES (board_seq.nextval, ?, ?, ?, SYSDATE, ?, ?)";
		Member m = new Member();

		try (PreparedStatement pstmt = con.prepareStatement(sql);) {
			m = memberInfo(nowId);
			
			pstmt.setString(1, m.getNickName());
			pstmt.setString(2, b.getTitle());
			pstmt.setString(3, b.getCategori());
			pstmt.setString(4, b.getContents());
			pstmt.setString(5, nowId);

			int result = pstmt.executeUpdate();

			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			// 예외 처리 (더 의미 있는 작업이나 로깅을 위해 더 추가할 수 있습니다)
			cs.customErrorView(root, "DAO uploadBoard 메서드에서 문제 발생");
			e.printStackTrace();
		}
		return false;
	}
	
	// db에 해당 게시물의 이미지 저장하기
	@Override
	public boolean uploadImg(ImagePath ip, Board b) {
		String sql1 = "SELECT board_no FROM board WHERE title=?";
		String sql2 = "INSERT INTO board_img VALUES (?, ?)";

		try (PreparedStatement pstmt1 = con.prepareStatement(sql1);) {
			try {
				pstmt1.setString(1, b.getTitle());
				try (ResultSet rs = pstmt1.executeQuery()) {
					if (rs.next()) {
						ip.setImg_no(rs.getInt(1));
						nowBoardNum = rs.getInt(1);
					}
				}
			} catch (Exception e) {
				// 예외 처리 (더 의미 있는 작업이나 로깅을 위해 더 추가할 수 있습니다)
				cs.customErrorView(root, "uploadImg - 1 메서드에서 문제 발생");
				e.printStackTrace();
			}

			try (PreparedStatement pstmt2 = con.prepareStatement(sql2);) {
				pstmt2.setInt(1, ip.getImg_no());
				pstmt2.setBytes(2, ip.getImageByte());

				int result = pstmt2.executeUpdate();

				if (result == 1) {
					return true;
				}
			} catch (Exception e) {
				// 예외 처리 (더 의미 있는 작업이나 로깅을 위해 더 추가할 수 있습니다)
				cs.customErrorView(root, "uploadImg - 2 메서드에서 문제 발생");
				e.printStackTrace();
			}
		} catch (Exception e) {
			// 예외 처리 (더 의미 있는 작업이나 로깅을 위해 더 추가할 수 있습니다)
			cs.customErrorView(root, "uploadImg - 메서드에서 전체적으로 문제 발생");
			e.printStackTrace();
		}
		return false;
	}
	
	// DB - 게시물 번호를 주면 해당 게시물 번호에 저장된 모든 이미지를 리스트에 넣어줌
	@Override
	public List<Image> getAllImages(int boardNo) {
	    List<Image> images = new ArrayList<>();
	    String sql = "SELECT imagepath FROM board_img WHERE board_no = ?";
	    
	    try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	        pstmt.setInt(1, boardNo);
	        
	        try (ResultSet rs = pstmt.executeQuery()) {
	            while (rs.next()) {
	                Blob imageBlob = rs.getBlob("imagepath");
	                Image image = convertBlobToImage(imageBlob);
	                if (image != null) {
	                    images.add(image);
	                }
	            } 	
	        }
	    } catch (SQLException e) {
	        // 예외 처리
	        e.printStackTrace();
	    }
	    return images;
	}
	
	// Blob 데이터를 JavaFX Image로 변환하는 메서드
	@Override
	public Image convertBlobToImage(Blob blob) {
	    try (InputStream inputStream = blob.getBinaryStream()) {
	        // BLOB 데이터를 바이트 배열로 읽음
	        byte[] data = new byte[(int) blob.length()];
	        inputStream.read(data);

	        // 바이트 배열을 JavaFX Image로 변환
	        ByteArrayInputStream bis = new ByteArrayInputStream(data);
	        return new Image(bis);
	    } catch (SQLException | IOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}

	
	// 게시물 업로드에 문제가 생겼을 시, 이전까지 db에 업로드한 내용 삭제하기
	@Override
	public void imgDelete(ImagePath ip) {
		String sql1 = "DELETE FROM board WHERE board_no=?";
		String sql2 = "DELETE FROM board_img WHERE board_no=?";

		try (PreparedStatement pstmt1 = con.prepareStatement(sql1);) {
			try {
				pstmt1.setInt(1, nowBoardNum);
				pstmt1.executeUpdate();
			} catch (Exception e) {
				// 예외 처리 (더 의미 있는 작업이나 로깅을 위해 더 추가할 수 있습니다)
				cs.customErrorView(root, "DAO imgDelete - 1 메서드에서 문제 발생");
				e.printStackTrace();
			}

			try (PreparedStatement pstmt2 = con.prepareStatement(sql2);) {
				pstmt2.setInt(1, nowBoardNum);
				pstmt2.executeUpdate();
			} catch (Exception e) {
				// 예외 처리 (더 의미 있는 작업이나 로깅을 위해 더 추가할 수 있습니다)
				cs.customErrorView(root, "DAO imgDelete - 2 메서드에서 문제 발생");
				e.printStackTrace();
			}
		} catch (Exception e) {
			// 예외 처리 (더 의미 있는 작업이나 로깅을 위해 더 추가할 수 있습니다)
			cs.customErrorView(root, "DAO imgDelete - 3 메서드에서 문제 발생");
			e.printStackTrace();
		}
	}
	

	// 로그인하면 입력받은 아이디와 같은 정보를 가지고 있는 Member m 객체 생성
	public Member memberInfo(String id) {
		// TODO Auto-generated method stub
		String sql = "select * from member where member_id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {
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

	public Member memberInfo() {
		// TODO Auto-generated method stub
		String sql = "select * from member where member_id = ?";

		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nowId);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				Member m = new Member();
				m.setNum(rs.getInt(1));
				m.setId(nowId);
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
	
	// 신고화면 검색 (게시물/board) - 검색 결과가 포함된 모든 정보
	@Override
	public List<Board> reportSearchResultAll1(String text1, String text2) {
//		// TODO Auto-generated method stub

		try {
			List<Board> reportBoardList = new ArrayList<>();
			String sql = "SELECT * FROM board WHERE " + text1 + " LIKE ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + text2 + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Board b = new Board();

				b.setNo(rs.getInt(1));
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
				b.setContents(rs.getString(6));
				b.setId(rs.getString(7));

				reportBoardList.add(b);
			}
			return reportBoardList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	// 신고화면 검색 (회원/member) - 검색 결과가 포함된 모든 정보
	@Override
	public List<Member> reportSearchResultAll2(String text1, String text2) {
		try {
			List<Member> reportBoardList = new ArrayList<>();
			String sql = "select * from member where " + text1 + " LIKE ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + text2 + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
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

				reportBoardList.add(m);
			}
			return reportBoardList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// 메인화면에 띄울 가장 최신글 2개값만 가져오기
	@Override
	public List<Board> getLatestBoardList(String Category) {
		List<Board> latestList = new ArrayList<>();
		String sql = "SELECT * FROM (SELECT * FROM board WHERE category = '" + Category
				+ "' ORDER BY board_no DESC) WHERE ROWNUM <= 2";

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "c##sqluser", "1234");
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {

			while (rs.next()) {
				Board board = new Board();
				board.setNo(rs.getInt("board_no"));
				board.setNicName(rs.getString("board_memnick"));
				board.setTitle(rs.getString("title"));
				board.setCategori(rs.getString("category"));
				// 타임스탬프 분까지만 자리수 끊기
				Timestamp timestamp = rs.getTimestamp("contents_date");
				if (timestamp != null) {
					SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
					String dateStr = sdf.format(timestamp);
					board.setUploadDate(dateStr);
				}
				board.setContents(rs.getString("contents"));
				board.setId(rs.getString("board_memid"));
				latestList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return latestList;
	}

	// 다음, 이전 게시물을 찾는 메서드
	@Override
	public Board getNextPrevBoard(String postNum, String category, String Sorting) {
		category = category.substring(0, category.length() - 2);

		String sql = null;
		if (Sorting == "ASC") {
			sql = "SELECT * FROM board WHERE board_no > " + postNum + " AND category = '" + category
					+ "' AND ROWNUM <= 1 ORDER BY board_no " + Sorting;
		} else if (Sorting == "DESC") {
			sql = "SELECT * FROM (\r\n"
					+ "    SELECT * \r\n"
					+ "    FROM board \r\n"
					+ "    WHERE board_no < " + postNum + " AND category = '" + category + "' \r\n"
					+ "    ORDER BY board_no DESC\r\n"
					+ ") ";
		}

		try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "c##sqluser", "1234");
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					Board board = new Board();
					board.setNo(rs.getInt("board_no"));
					board.setNicName(rs.getString("board_memnick"));
					board.setTitle(rs.getString("title"));
					board.setCategori(rs.getString("category"));
					board.setUploadDate(rs.getDate("contents_date").toString());
					// 타임스탬프 분까지만 자리수 끊기
					Timestamp timestamp = rs.getTimestamp("contents_date");
					if (timestamp != null) {
						SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
						String dateStr = sdf.format(timestamp);
						board.setUploadDate(dateStr);
					}
					board.setContents(rs.getString("contents"));
					board.setId(rs.getString("board_memid"));
					return board;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Board nextBoard = null;
		return nextBoard;
	}
	
	// 메인화면에서 
	public Board mainReturnBoard(String a) {
		
		try {
			Board b = new Board();
			b.setNo(rs.getInt("board_no"));
			b.setNicName(rs.getString("board_memnick"));
			b.setTitle(rs.getString("title"));
			b.setCategori(rs.getString("category"));
			b.setUploadDate(rs.getDate("contents_date").toString());
			
			// 타임스탬프 분까지만 자리수 끊기
			Timestamp timestamp = rs.getTimestamp("contents_date");
			if (timestamp != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
				String dateStr = sdf.format(timestamp);
				b.setUploadDate(dateStr);
			}
			
			b.setContents(rs.getString("contents"));
			b.setId(rs.getString("board_memid"));
			return b;
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return null;
	}

	// 탈퇴 버튼을 눌렀을때 정보가 삭제되게끔 해주는 메서드
	public boolean removeMem() {
		// TODO Auto-generated method stub
		String sql1 = "select * from board where board_memid=?";
		String sql2 = "delete from board where board_memid=?";
		String sql4 = "delete from board_img where board_no=?";
		try(PreparedStatement pstmt = con.prepareStatement(sql1)) {
			pstmt.setString(1, nowId);
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					int boaredNum = rs.getInt("board_no");
					
					try(PreparedStatement pstmt1 = con.prepareStatement(sql4)) {
						pstmt1.setInt(1, boaredNum);
						
						pstmt1.executeQuery();

					} catch(Exception e) {
						System.out.println("게시글삭제");
						e.printStackTrace();
					}
					
					try(PreparedStatement pstmt1 = con.prepareStatement(sql2)) {
						pstmt1.setString(1, nowId);
						
						pstmt1.executeQuery();
						
					} catch(Exception e) {
						System.out.println("게시글삭제");
						e.printStackTrace();
					}
				}
			}
		}catch(Exception e) {
			System.out.println("1");
			e.printStackTrace();
		}
		
		System.out.println(nowId);
		
	
		String sql3 = "delete from member where member_id=?";
		try(PreparedStatement pstmt = con.prepareStatement(sql3)) {
			System.out.println(nowId);
			pstmt.setString(1, nowId);

			int removeResult = pstmt.executeUpdate();
			System.out.println(removeResult);
			if(removeResult > 0) {
				return true;
			} else {
				return false;
			}


		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// 나의 정보 창 왼쪽 상단 닉네임 부분에 해당 아이디의 닉네임이 뜬다.
	public String selectNick() {
		// TODO Auto-generated method stub
		String sql = "select member_nickname from member where member_id='"+nowId+"'";

		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rs.next();
			String nickname = rs.getString(1);
			return nickname;

		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 프로필 수정에서 변경된 정보를 저장해주는 메서드
	public boolean updateMember(Parent root, Member m) {
		// TODO Auto-generated method stub
			String sql = "update member set member_name=?,member_nickname=?,member_pw=?,member_birthdate=?,member_gender=?,member_email=?,member_phonenum=? where member_id=?"; 
			try {
				pstmt = con.prepareStatement(sql);

				pstmt.setString(1, m.getName());
				pstmt.setString(2, m.getNickName());
				pstmt.setString(3, m.getPw());
				pstmt.setDate(4, m.getBirthDate());
				pstmt.setString(5, m.isGender());
				pstmt.setString(6,m.getEmail());
				pstmt.setString(7, m.getPhoneNum());
				pstmt.setString(8, m.getId());

				int result = pstmt.executeUpdate();

				if(result == 1) {
					System.out.println("수정완료");
					cs.customErrorView(root,"프로필이 수정되었습니다." );
					return true;
				}
			} catch(Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		
		return false;
	}

	

}
