package all;

import all.boardService.BoardService;
import all.boardService.BoardServiceImp;
import all.boardService.buyBoardService;
import all.boardService.buyBoardServiceImp;
import all.boardService.freeBoardService;
import all.boardService.freeBoardServiceImp;
import all.boardService.reportservice;
import all.boardService.reportserviceImp;
import all.boardService.sellBoardService;
import all.boardService.sellBoardServiceImp;
import all.boardService.sharingBoardService;
import all.boardService.sharingBoardServiceImp;
import all.boardService.writingService;
import all.boardService.writingServiceImp;
import all.button.CommonService;
import all.button.CommonServiceImp;
import all.button.JoinButton;
import all.button.JoinButtonImp;
import all.button.loginButton;
import all.button.loginButtonImp;
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
	// 게시물 작성, 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼
	private writingService ws;
	private freeBoardService fB;
	private buyBoardService bB;
	private sellBoardService slB;
	private sharingBoardService sgB;
	private reportservice rp;
	
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
		// 게시물 작성, 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼
		ws = new writingServiceImp();
		fB = new freeBoardServiceImp();
		bB = new buyBoardServiceImp();
		slB = new sellBoardServiceImp();
		sgB = new sharingBoardServiceImp();
		rp = new reportserviceImp();
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
	// 게시물 작성, 자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판, 신고하기 버튼
	public void writingProc() {
		ws.writingProc(root);
	}
	public void freeBoardProc() {
		fB.freeBoardProc(root);
	}
	public void buyBoardProc() {
		bB.buyBoardProc(root);
	}
	public void sellBoardProc() {
		slB.sellBoardProc(root);
	}
	public void sharingBoardProc() {
		sgB.sharingBoardProc(root);
	}
	public void reportProc() {
		rp.reportProc(root);
	}
}
