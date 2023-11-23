package all.databaseDAO;

import java.sql.Blob;
import java.util.List;

import all.boardService.Board;
import all.boardService.ImagePath;
import javafx.scene.image.Image;
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
	// db에 해당 게시물의 이미지 저장하기
	boolean uploadImg(ImagePath ip, Board b);
	// 게시물 업로드에 문제가 생겼을 시, 이전까지 db에 업로드한 내용 삭제하기
	void imgDelete(ImagePath ip);
	
	// 선준
	// DB - 게시물 번호를 주면 해당 게시물 번호에 저장된 모든 이미지를 리스트에 넣어줌
	List<Image> getAllImages(int boardNo);
	// Blob 데이터를 JavaFX Image로 변환하는 메서드
	Image convertBlobToImage(Blob blob);
	
	// 로그인
	boolean loginChk(String id,String pw);
	boolean insertMember(Member m);
	boolean dupID(String txtId);
	List<Member> selectAll1();
	
	// 로그인하면 입력받은 아이디와 같은 정보를 가지고 있는 Member m 객체 생성
	Member memberInfo(String id);
	
	// 다음, 이전 게시물을 찾는 메서드
	Board getNextPrevBoard(String postNum, String category, String Sorting);
	
}
