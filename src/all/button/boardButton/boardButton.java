package all.button.boardButton;

import javafx.scene.Parent;

public interface boardButton {

	// 검색 버튼
	void searchProc(Parent root);
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
	
	
	// 게시하기 버튼
	void uploadProc(Parent root) throws Exception;
	// 파일선택 버튼
	String fileUpload(Parent root);
	// 이미지를 바이트화 시키는 함수
	byte[] convertImageToBytes(String imgAddr) throws Exception;
}
