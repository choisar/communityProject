package all;

import all.boardService.BoardService;
import all.boardService.BoardServiceImp;
import all.button.JoinButton;
import all.button.JoinButtonImp;
import all.button.loginButton;
import all.button.loginButtonImp;
import all.button.boardButton.boardButton;
import all.button.boardButton.boardButtonImp;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.button.find_IdPw.IdFindButton;
import all.button.find_IdPw.IdFindButtonImp;
import all.button.find_IdPw.PwFindButton;
import all.button.find_IdPw.PwFindButtonImp;
import all.button.infoButton.infoButton;
import all.button.infoButton.infoButtonImp;
import all.button.infoButton.proButton;
import all.button.infoButton.proButtonImp;
import all.button.mainViewButon.MainViewButton;
import all.button.mainViewButon.MainViewButtonImp;
import all.userService.admin.AdminService;
import all.userService.admin.AdminServiceImp;
import all.userService.user.UserService;
import all.userService.user.UserServiceImp;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class Controller{
	private Parent root;
	// 테이블 뷰 관련된 기능
	private BoardService bs;
	// 로그인 버튼
	private loginButton lb;
	// 회원가입 버튼
	private JoinButton jb;
	// 윈도우창 닫기, 에러 창 호출
	private CommonService cs;
	// id, pw 찾기
	private IdFindButton idF;
	private PwFindButton pwF;
	// 검색 버튼, 게시물 작성, 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼, 업로드 버튼
	private boardButton bBt;
	// 메인화면에 게시물 제목 버튼
	private MainViewButton mv;
	// 
	private infoButton ib;
	private proButton pb;
	
	// 테스트용
	private Test tt = new Test();

	
	
	public Controller() {
		// TODO Auto-generated constructor stub
		// 테이블 뷰 관련된 기능
		bs = new BoardServiceImp();
		// 로그인 버튼
		lb = new loginButtonImp();
		// 회원가입 버튼
		jb = new JoinButtonImp();
		// 윈도우창 닫기, 에러 창 호출
		cs = new CommonServiceImp();
		// id 찾기
		idF = new IdFindButtonImp();
		// pw 찾기
		pwF = new PwFindButtonImp();
		// 검색 버튼, 게시물 작성, 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼, 업로드 버튼
		bBt = new boardButtonImp();
		// 메인화면에 게시물 제목 버튼
		mv = new MainViewButtonImp();
		// 
		ib = new infoButtonImp();
		pb = new proButtonImp();
		
	}
	
	// =================================================== 화면 관련 =================================================== //
	
	// root 설정
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	// root 가져오기
	public Parent getRoot() {
		return root;
	}
	
	// 테스트 버튼 1
	public void testProc1() {
		tt.testProc1(root);
	}
	
	// 테스트 버튼 2
	public void testProc2() {
		tt.testProc2(root);
	}
	
	// main 화면
	public void boardListView2() {
		bs.mainView(root);
	}
	
	// =================================================== 회원 관련 =================================================== //
	
	// 로그인 버튼
	public void loginProc() {
		lb.loginProc(root);
	}
	
	// 로그아웃 버튼
	public void logoutProc() {
		lb.logoutProc(root);
	}
	
	// 회원가입 버튼
	public void membershipProc() {
		jb.membershipProc(root);
	}
	
	// 회원가입 
	public void joinMember(ActionEvent event) {
		// TODO Auto-generated method stub
		jb.joinMember(event);
	}
	
	// 아이디 찾기
	public void idFindProc() {
		idF.idFindProc(root);
	}
	
	// 비밀번호 찾기
	public void pwFindProc() {
		pwF.pwFindProc(root);
	}
	
	// 아이디 찾기 확인 버튼
	public void idFindOkProc() {
		idF.idFindOkProc(root);
	}
	
	// 비밀번호 찾기 확인 버튼
	public void pwFindOkProc() {
		pwF.pwFindOkProc(root);
	}
	
	// =================================================== 게시물 관련 =================================================== //
	
	// 게시물 작성
	public void writingProc() {
		bBt.writingProc(root);
	}
	// 전체 게시판 버튼
	public void allBoardProc() {
		bBt.allBoardProc(root);
	}
	// 자유 게시판 버튼
	public void freeBoardProc() {
		bBt.freeBoardProc(root);
	}
	// 구매 게시판 버튼
	public void buyBoardProc() {
		bBt.buyBoardProc(root);
	}
	// 판매 게시판 버튼
	public void sellBoardProc() {
		bBt.sellBoardProc(root);
	}
	// 나눔 게시판 버튼
	public void sharingBoardProc() {
		bBt.sharingBoardProc(root);
	}
	// Q&A 게시판 버튼
	public void QAProc() {
		bBt.QAProc(root);
	}
	// 신고하기 버튼
	public void reportProc() {
		bBt.reportProc(root);
	}
	
	// 검색 버튼 
	public void searchProc() {
		bBt.searchProc(root);
	}
	
	// 게시하기 버튼
	public void uploadProc() throws Exception {
		bBt.uploadProc(root);
	}

	// 파일 선택 버튼
	public void selOpenFile(ActionEvent e) {
		bBt.fileUpload(root);
	}
	
	// 신고화면 검색 버튼
	public void reportSearchProc() {
		bBt.reportSearchProc(root);
	}
	
	// =================================================== 보드 디테일 뷰 =================================================== //
	
	// 보드 디테일뷰에서 카테고리, 리스트 누르면 해당 카테고리 게시판 창 띄우는 버튼
	public void categoryBoardProc() {
		bBt.categoryBoardProc(root);
	}
	// 보드 디테일뷰에서 Next -> (다음 게시물) 버튼
	public void NextProc() {
		bBt.NextProc(root);
	}
	// 보드 디테일뷰에서 Prev <- (이전 게시물) 버튼
	public void PrevProc() {
		bBt.PrevProc(root);
	}
	
	// ========================================== 메인화면 하단에 게시물 제목을 눌렀을 때 ========================================== //
	
	// 메인 화면 하단 구매 게시판의 첫 번째 게시물 제목을 눌렀을 때
	public void mainBuyDetailView1Proc() {
		mv.mainBuyDetailView1Proc(root);
	}
	// 메인 화면 하단 구매 게시판의 두 번째 게시물 제목을 눌렀을 때
	public void mainBuyDetailView2Proc() {
		mv.mainBuyDetailView2Proc(root);
	}
	
	// 메인 화면 하단 판매 게시판의 첫 번째 게시물 제목을 눌렀을 때
	public void mainSellDetailView1Proc() {
		mv.mainSellDetailView1Proc(root);
	}
	// 메인 화면 하단 판매 게시판의 두 번째 게시물 제목을 눌렀을 때
	public void mainSellDetailView2Proc() {
		mv.mainSellDetailView2Proc(root);
	}
	
	// 메인 화면 하단 나눔 게시판의 첫 번째 게시물 제목을 눌렀을 때
	public void mainSharingDetailView1Proc() {
		mv.mainSharingDetailView1Proc(root);
	}
	// 메인 화면 하단 나눔 게시판의 두 번째 게시물 제목을 눌렀을 때
	public void mainSharingDetailView2Proc() {
		mv.mainSharingDetailView2Proc(root);
	}
	
	// 메인 화면 하단 자유 게시판의 첫 번째 게시물 제목을 눌렀을 때
	public void mainFreeDetailView1Proc() {
		mv.mainFreeDetailView1Proc(root);
	}
	// 메인 화면 하단 자유 게시판의 두 번째 게시물 제목을 눌렀을 때
	public void mainFreeDetailView2Proc() {
		mv.mainFreeDetailView2Proc(root);
	}
	
	// ======================================================= 기타 ======================================================= //
	
	// 취소 버튼
	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
	}
	
	//
	public void infoProc() {
		ib.infoProc(root);
	}
	// 
	public void backProc() {
		ib.backProc(root);
	}
	// 
	public void deleteProc() {
		ib.deleteProc(root);
	}
	// 
	public void profileProc() {
		pb.profileProc();
	}
	
}
