package all.boardService;

import javafx.scene.Parent;
import javafx.scene.control.TextField;

public interface BoardService {

	// 메인 화면
	void mainView(Parent root);
	// 전체 카테고리의 모든 게시물을 출력하는 테이블뷰 생성
	void createAllListView(Parent root);
	// 선택한 카테고리 <자유, 구매, 판매, 나눔> 의 모든 게시물을 출력하는 테이블뷰 생성
	void createCategoryListView(Parent root, String string);
	// 카테고리에 상관없이 검색 결과가 포함된 모든 게시물을 출력하는 테이블뷰 생성
	void serchResultListView(Parent root, String text1, String text2);
}
