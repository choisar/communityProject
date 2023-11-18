package all.boardService;

import javafx.scene.Parent;

public interface boardViewService {
	// 전체 게시판
	public void allBoardView(Parent root);
	// 자유 게시판
	public void freeBoardView(Parent root);
	// 구매 게시판
	public void buyBoardView(Parent root);
	// 판매 게시판
	public void sellBoardView(Parent root);
	// 나눔 게시판
	public void sharingBoardView(Parent root);
	// 검색 결과 게시판
	public void searchResultBoardView(Parent root, String text1, String text2);
	// 신고화면 검색 결과 게시판
	public void reportSearchResultBoardView(Parent root, String text1, String text2);
	// 글쓰기 창
	public void uploadBoardView(Parent root);
	// 신고하기 창
	public void reportView(Parent root);
	
	// public void getBoardViewByCategory(Parent root, String category); 
}
