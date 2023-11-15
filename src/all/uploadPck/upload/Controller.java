package all.uploadPck.upload;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import upload.userService.user.UploadBoard;
import upload.userService.user.UploadBoardImpl;

public class Controller {
	private Parent root;
	private Parent Board;
	private Parent info;
	private UploadBoard ub;
	
	public Controller() {
		// TODO Auto-generated constructor stub
		ub=new UploadBoardImpl();
	}
	
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void setBoard(Parent Board) {
		// TODO Auto-generated method stub
		this.Board = Board;
	}
	
	public void uploadProc() throws Exception {
		ub.uploadProc(root);
	}
	
	public void selOpenFile(ActionEvent e) {
		ub.fileUpload(root);
	}
	
}
