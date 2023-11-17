package all.button.boardButton;

import javafx.scene.Parent;
import javafx.scene.control.Label;

public interface boardButton {

	// 검색 게시판 버튼 - 메인화면 게시물 검색 버튼
	void searchProc(Parent root);
	// 전체 게시판 버튼
	void allBoardProc(Parent root);
	// 자유 게시판 버튼
	void freeBoardProc(Parent root);
	// 구매 게시판 버튼
	void buyBoardProc(Parent root);
	// 판매 게시판 버튼
	void sellBoardProc(Parent root);
	// 나눔 게시판 버튼
	void sharingBoardProc(Parent root);
	// 신고하기 버튼
	void reportProc(Parent root);
	// 신고화면 검색 버튼
	void reportSearchProc(Parent root);
	// 코드 줄이려고 만든 메서드
	void handleBoardView(Parent root, Label logChk, String category);
	
	// 글쓰기 버튼
	void writingProc(Parent root);
	// 게시하기 버튼
	void uploadProc(Parent root) throws Exception;
	// 파일선택 버튼
	String fileUpload(Parent root);
	// 이미지를 바이트화 시키는 함수
	byte[] convertImageToBytes(String imgAddr) throws Exception;
	
}
