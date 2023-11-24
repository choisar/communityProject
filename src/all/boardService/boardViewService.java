package all.boardService;

import javafx.scene.Parent;

public interface boardViewService {
	// 전체 게시판
	public void allBoardView(Parent root, String memId);
	// 자유 게시판
	public void freeBoardView(Parent root, String memId);
	// 구매 게시판
	public void buyBoardView(Parent root, String memId);
	// 판매 게시판
	public void sellBoardView(Parent root, String memId);
	// 나눔 게시판
	public void sharingBoardView(Parent root, String memId);
	// Q&A 게시판
	public void QABoardView(Parent root, String memId);
	// 검색 결과 게시판
	public void searchResultBoardView(Parent root, String text1, String text2, String memId);

	// 글쓰기 창
	public void uploadBoardView(Parent root);
	// 신고하기 창
	public void reportView(Parent root);
	
	// 보드 디테일뷰에서 다음, 이전 게시물 창을 띄어주는 메서드
	public void loadNextBoardInCategoryView(Parent root, String strpostNum, String category, String Sorting);
	
}
