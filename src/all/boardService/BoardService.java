package all.boardService;

import java.sql.Blob;
import java.util.List;

import javafx.scene.Parent;
import javafx.scene.image.Image;

public interface BoardService {

	// 메인 화면
	void mainView(Parent root);
	// 전체 카테고리의 모든 게시물을 출력하는 테이블뷰 생성
	void createAllListView(Parent root);
	// 선택한 카테고리 <자유, 구매, 판매, 나눔> 의 모든 게시물을 출력하는 테이블뷰 생성
	void createCategoryListView(Parent root, String string);
	// 카테고리에 상관없이 검색 결과가 포함된 모든 게시물을 출력하는 테이블뷰 생성
	void serchResultListView(Parent root, String text1, String text2);
	// 신고화면 에서 입력받은 콤보박스 + 입력 내용 값 테이블 뷰
	void reportSerchResultListView(Parent root, String text1, String text2);
	
	// ### 보드 디테일 - 자주써서  찾기 쉽게 ### 달아둠
	// 게시물 목록에서 클릭하면 해당 게시물 내용이 포함된 창이 출력되는 메서드
	void openBoardDetailWindow(Parent root, Board selectedBoard, List<Image> imagelist);
	
	// ▲ ▲ ▲ 부속 메서드
	void showAlert(String title, String content);
	// 메인화면 콤보 박스
	String mainCombo(Parent root);
	// 신고하기 콤보 박스
	String reportCombo1(Parent root);
	// 신고하기 콤보 박스
	String reportCombo2(Parent root);
	// 로그인 화면 체크 박스1 (새 창 띄우기, 안 띄우기)
	boolean chk1(Parent root);
	
}
