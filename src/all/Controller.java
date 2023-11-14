package all;

import all.boardService.BoardService;
import all.boardService.BoardServiceImp;
import all.button.CommonService;
import all.button.CommonServiceImp;
import all.button.IdFindButton;
import all.button.IdFindButtonImp;
import all.button.joinButton;
import all.button.joinButtonImp;
import all.button.loginButton;
import all.button.loginButtonImp;
import all.button.PwFindButton;
import all.button.PwFindButtonImp;
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
	private joinButton jb;
	private CommonService cs;
	private IdFindButton idF;
	private PwFindButton pwF;
	
	public Controller() {
		// TODO Auto-generated constructor stub
		us = new UserServiceImp();
		as = new AdminServiceImp();
		bs = new BoardServiceImp();
		lb = new loginButtonImp();
		jb = new joinButtonImp();
		cs = new CommonServiceImp();
		idF = new IdFindButtonImp();
		pwF = new PwFindButtonImp();
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
	
	public void idFindOkProc() {
		idF.idFindOkProc(root);
	}
	
	public void pwFindOkProc() {
		pwF.pwFindOkProc(root);
	}
	
	public void idFindProc() {
		idF.idFindProc(root);
	}
	
	public void pwFindProc() {
		pwF.pwFindProc(root);
	}

	
}
