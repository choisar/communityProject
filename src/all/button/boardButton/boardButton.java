package all.button.boardButton;

import java.util.List;

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
    // Q&A 게시판 버튼
    void QAProc(Parent root);
	// 신고하기 버튼
	void reportProc(Parent root);
	// 신고화면 검색 버튼
	void reportSearchProc(Parent root);
	
	// 글쓰기 버튼
	void writingProc(Parent root);
	// 게시하기 버튼
	void uploadProc(Parent root) throws Exception;
	// 파일선택 버튼
	List<String> fileUpload(Parent root);
	// 이미지를 바이트화 시키는 함수
	byte[] convertImageToBytes(String imgAddr) throws Exception;
	
	// 테스트 버튼
	void testProc(Parent root);
	
	// 보드 디테일뷰에서 카테고리, 리스트 누르면 해당 카테고리 게시판 창 띄우는 버튼
	void categoryBoardProc(Parent detailRoot);
	
	// 보드 디테일뷰에서 다음 게시물(next ->) 버튼을 누르면 다음 게시물로 가는 버튼
	void NextProc(Parent root);
	// 보드 디테일뷰에서 이전 게시물(Prev ->) 버튼을 누르면 다음 게시물로 가는 버튼
	void PrevProc(Parent root);
	
}
