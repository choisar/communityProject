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
	// 전체 게시판 - 모든 카테고리의 모든 게시물
	List<Board> selectAll();
	// 해당 카테고리에 있는 모든 게시물
	List<Board> categoryBoardAll(String category);
	// 전체 카테고리에서 검색 결과가 포함된 모든 게시물
	List<Board> searchResultAll(String text1, String text2);
	
}
