package all.databaseDAO;

import java.util.List;

import all.boardService.Board;
import all.Member;
import all.boardService.Board;

public interface DatabaseDAO {
	
	// ### 아이디 찾기
	boolean idChk(String name, String phoneNum);
	String findId(String findName, String findPhoneNum);
	
	// ### 비밀번호 찾기
	boolean pwChk(String findId, String findPhoneNum);
	String findPw(String findId, String findPhoneNum);
	String findUserName(String findId, String findPhoneNum);
	
	// 전체 게시판 - 모든 카테고리의 모든 게시물
	List<Board> selectAll();
	
	// 해당 카테고리에 있는 모든 게시물
	List<Board> categoryBoardAll(String category);
	
	// 전체 카테고리에서 검색 결과가 포함된 모든 게시물
	List<Board> searchResultAll(String text1, String text2);
	
	// 신고화면 검색 (게시물/board) - 검색 결과가 포함된 모든 정보
	List<Board> reportSearchResultAll1(String text1,String text2);
	
	// 신고화면 검색 (회원/member) - 검색 결과가 포함된 모든 정보
	List<Member> reportSearchResultAll2(String text1, String text2);
	
	// 메인화면에 띄울 가장 최신글 2개값만 가져오기 
	List<Board> getLatestBoardList(String Category);
	
	// db에 게시글 등록하기
	boolean uploadBoard(Board b);
	
	// 로그인
	boolean loginChk(String id,String pw);
	boolean insertMember(Member m);
	boolean dupID(String txtId);
	List<Member> selectAll1();
	
	// 로그인하면 입력받은 아이디와 같은 정보를 가지고 있는 Member m 객체 생성
	Member memberInfo(String id);
	
	// 다음, 이전 게시물을 찾는 메서드
	public Board getNextPrevBoard(String postNum, String category, String Sorting);
	
}
