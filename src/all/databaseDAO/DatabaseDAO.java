package all.databaseDAO;

import java.util.List;

import all.boardService.Board;

public interface DatabaseDAO {

	boolean idChk(String name, String phoneNum);
	String findId(String findName, String findPhoneNum);
	boolean pwChk(String findId, String findPhoneNum);
	String findPw(String findId, String findPhoneNum);
	String findUserName(String findId, String findPhoneNum);
	List<Board> selectAll();
	List<Board> categoryBoardAll(String category);
	
}
