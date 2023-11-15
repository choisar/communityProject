package all.databaseDAO;

import java.util.List;

import all.boardService.Board;

public interface DatabaseDAO {
	
	// id 찾기
	boolean idChk(String name, String phoneNum);
	String findId(String findName, String findPhoneNum);
	// pw 찾기
	boolean pwChk(String findId, String findPhoneNum);
	String findPw(String findId, String findPhoneNum);
	// 이름 찾기
	String findUserName(String findId, String findPhoneNum);
	// 전체 리스트
	List<Board> selectAll();
	// 카테고리 리스트
	List<Board> categoryBoardAll(String category);
	// 검색 리스트
	List<Board> searchResultAll(String text1, String text2);
	
}
