package all.uploadPck.upload;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import upload.userService.user.UploadBoard;
import upload.userService.user.UploadBoardImpl;
import upload.userService.user.ViewService;
import upload.userService.user.ViewServiceImpl;

public class ViewController {
	private Parent root;
	private Parent Board;
	private Parent info;
	private UploadBoard ub;
	private ViewService vi;
	
	public ViewController() {
		// TODO Auto-generated constructor stub
		vi=new ViewServiceImpl();
	}
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void viewProc(ActionEvent e) throws Exception {
		vi.viewProc(root);
	}
}
