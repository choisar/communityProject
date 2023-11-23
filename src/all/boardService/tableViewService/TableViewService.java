package all.boardService.tableViewService;

import all.Member;
import all.boardService.Board;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;

public interface TableViewService {
	
	// 중복 코드 줄이려고 만든 메서드 - root 값, 라벨 값(회원, 비회원, 관리자 확인), 카테고리 값(자유, 구매, 판매, 나눔) 받아서 테이블 뷰 생성
	// 자유, 구매, 판매, 나눔 게시판 버튼에서 사용
	void handleBoardView(Parent root, Label logChk, String category, String memId);
	// 중복 코드 줄이려고 만든 메서드 - 전체 게시판 테이블 뷰
	void loadAllBoardListView(Parent root, String memId);
	// 중복 코드 줄이려고 만든 메서드 - 테이블 뷰에 컬럼 설정
	void configureBoardTableView(TableView<Board> listView,String memId);
	// 중복 코드 줄이려고 만든 메서드 - 테이블 뷰에 컬럼 설정 - 클릭했을 때
	public void configureBoardTableViewClick(TableView<Board> listView);
	// 중복 코드 줄이려고 만든 메서드 - 테이블 뷰에 컬럼 설정 - 신고 기능 게시물용
	void configureBoardTableView1(TableView<Board> listView, Parent root);
	// 중복 코드 줄이려고 만든 메서드 - 테이블 뷰에 컬럼 설정 - 신고 기능 게시물용
	void configureBoardTableView2(TableView<Member> listView, Parent root);
	// 중복 코드 줄이려고 만든 메서드 - 테이블 뷰에 컬럼 설정 - 신고 기능 게시물용
	void configureBoardTableView3(TableView<Member> listView, Parent root);
	
}
