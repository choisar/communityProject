package all.button.boardButton;

import javafx.scene.Parent;

public interface boardButton {

	// 검색 버튼
	void searchProc(Parent root);
	// 검색창 비어있으면 호출되는 오류
	void searchViewError2(Parent root);
	// 글쓰기 버튼
	void writingProc(Parent root);
	// 자유 개시판 버튼
	void freeBoardProc(Parent root);
	// 구매 게시판 버튼
	void buyBoardProc(Parent root);
	// 판매 게시판 버튼
	void sellBoardProc(Parent root);
	// 나눔 게시판 버튼
	void sharingBoardProc(Parent root);
	// 신고 버튼
	void reportProc(Parent root);

}
