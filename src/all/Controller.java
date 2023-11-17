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
import all.button.find_idpw.IdFindButton;
import all.button.find_idpw.IdFindButtonImp;
import all.button.find_idpw.PwFindButton;
import all.button.find_idpw.PwFindButtonImp;
import all.button.infoButton.infoButton;
import all.button.infoButton.infoButtonImp;
import all.button.infoButton.proButton;
import all.button.infoButton.proButtonImp;
import all.userService.admin.AdminService;
import all.userService.admin.AdminServiceImp;
import all.userService.user.UserService;
import all.userService.user.UserServiceImp;
import javafx.event.ActionEvent;
import javafx.scene.Parent;

public class Controller{
	private Parent root;
	private UserService us;
	private AdminService as;
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
	// 검색 버튼, 게시물 작성, 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼
	// 업로드 버튼
	private boardButton bBt;
	private proButton pb;
	private infoButton ib;
	
	
	public Controller() {
		// TODO Auto-generated constructor stub
		us = new UserServiceImp();
		as = new AdminServiceImp();
		// 테이블 뷰 관련된 기능
		bs = new BoardServiceImp();
		// 로그인 버튼
		lb = new loginButtonImp();
		// 회원가입 버튼
		jb = new JoinButtonImp();
		// 윈도우창 닫기, 에러 창 호출
		cs = new CommonServiceImp();
		// id, pw 찾기
		idF = new IdFindButtonImp();
		pwF = new PwFindButtonImp();
		// 검색 버튼, 게시물 작성, 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼 
		// 업로드 버튼
		bBt = new boardButtonImp();
		ib = new infoButtonImp();
		pb = new proButtonImp();
	}
	public void setRoot(Parent root) {
		this.root = root;
	}
	// 로그인 버튼
	public void loginProc() {
		lb.loginProc(root);
	}
	// 회원가입 버튼
	public void membershipProc() {
		jb.membershipProc(root);
	}
	// 취소 버튼
	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
	}
	// main 화면
	public void boardListView2() {
		bs.mainView(root);
	}
	// id, pw 찾기
	public void idFindProc() {
		idF.idFindProc(root);
	}
	public void pwFindProc() {
		pwF.pwFindProc(root);
	}
	public void idFindOkProc() {
		idF.idFindOkProc(root);
	}
	public void pwFindOkProc() {
		pwF.pwFindOkProc(root);
	}
	// 검색 버튼 
	public void searchProc() {
		bBt.searchProc(root);
	}
	// 게시물 작성
	public void writingProc() {
		bBt.writingProc(root);
	}
	// 전체 게시판, 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼
	public void allBoardProc() {
		bBt.allBoardProc(root);
	}
	public void freeBoardProc() {
		bBt.freeBoardProc(root);
	}
	public void buyBoardProc() {
		bBt.buyBoardProc(root);
	}
	public void sellBoardProc() {
		bBt.sellBoardProc(root);
	}
	public void sharingBoardProc() {
		bBt.sharingBoardProc(root);
	}
	public void reportProc() {
		bBt.reportProc(root);
	}
	
	// 회원가입
	public void joinMember(ActionEvent event) {
		// TODO Auto-generated method stub
		jb.joinMember(event);
	}
	
	// 로그아웃
	public void logoutProc() {
		lb.logoutProc(root);
	}
	
	public void idChkProc(ActionEvent e) {
		jb.idChkProc(root);
	}
	
	public void infoProc() {
		ib.infoProc(root);
	}
	
	public void backProc() {
		ib.backProc(root);
	}
	
	public void deleteProc() {
		ib.deleteProc(root);
	}
	
	public void profileProc() {
		pb.profileProc();
	}
	
	public Parent getRoot() {
		return root;
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
	
}
