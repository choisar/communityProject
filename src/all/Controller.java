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
	private BoardService bs;
	private loginButton lb;
	private JoinButton jb;
	// 윈도우창 닫기, 에러 창 호출
	private CommonService cs;
	// id, pw 찾기
	private IdFindButton idF;
	private PwFindButton pwF;
	// 검색 버튼, 게시물 작성, 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼
	private boardButton bBt;
	
	public Controller() {
		// TODO Auto-generated constructor stub
		us = new UserServiceImp();
		as = new AdminServiceImp();
		bs = new BoardServiceImp();
		lb = new loginButtonImp();
		jb = new JoinButtonImp();
		// 윈도우창 닫기, 에러 창 호출
		cs = new CommonServiceImp();
		// id, pw 찾기
		idF = new IdFindButtonImp();
		pwF = new PwFindButtonImp();
		// 검색 버튼, 게시물 작성, 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼 
		bBt = new boardButtonImp();
	}
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	public void loginProc() {
		lb.loginProc(root);
	}
	
	public void membershipProc() {
		jb.membershipProc(root);
	}
	
	public void cancelProc(ActionEvent event) {
		cs.windowClose(event);
	}
	
	public void boardListView() {
		bs.boardListView(root);
	}
	public void boardListView2() {
		bs.boardListView2(root);
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
	// 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼
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
}
