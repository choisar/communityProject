package all;

import all.boardService_hong.BoardService;
import all.boardService_hong.BoardServiceImp;
import all.button.idFind;
import all.button.idFindImp;
import all.button.joinButton;
import all.button.joinButtonImp;
import all.button.loginButton;
import all.button.loginButtonImp;
import all.button.pwFind;
import all.button.pwFindImp;
import all.userService.admin.AdminService;
import all.userService.admin.AdminServiceImp;
import all.userService.user.UserService;
import all.userService.user.UserServiceImp;
import javafx.scene.Parent;

public class Controller_hong{
	private Parent root;
	private UserService us;
	private AdminService as;
	private BoardService bs;
	private loginButton lb;
	private joinButton jb;
	
	private idFind idF;
	private pwFind pwF;
	
	public Controller_hong() {
		// TODO Auto-generated constructor stub
		us = new UserServiceImp();
		as = new AdminServiceImp();
		bs = new BoardServiceImp();
		lb = new loginButtonImp();
		jb = new joinButtonImp();
		
		idF = new idFindImp();
		pwF = new pwFindImp();
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
	
	public void boardListView() {
		bs.boardListView(root);
	}
	
	
	
	
	public void idFindProc() {
		idF.idFindProc(root);
	}
	
	public void pwFindProc() {
		pwF.pwFindProc(root);
	}
	
}
