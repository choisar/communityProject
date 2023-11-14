package all;

import all.boardService.BoardService;
import all.boardService.BoardServiceImp;
import all.button.joinButton;
import all.button.joinButtonImp;
import all.button.loginButton;
import all.button.loginButtonImp;
import all.userService.admin.AdminService;
import all.userService.admin.AdminServiceImp;
import all.userService.user.UserService;
import all.userService.user.UserServiceImp;
import javafx.scene.Parent;

public class Controller{
	private Parent root;
	private UserService us;
	private AdminService as;
	private BoardService bs;
	private loginButton lb;
	private joinButton jb;
	
	public Controller() {
		// TODO Auto-generated constructor stub
		us = new UserServiceImp();
		as = new AdminServiceImp();
		bs = new BoardServiceImp();
		lb = new loginButtonImp();
		jb = new joinButtonImp();
	}
	
	public void loginProc() {
		lb.loginProc(root);
	}
	
	public void membershipProc() {
		jb.membershipProc(root);
	}

	public void setRoot(Parent root2) {
		// TODO Auto-generated method stub
		
	}
	
	
}
