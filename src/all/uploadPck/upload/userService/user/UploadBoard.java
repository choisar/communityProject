package all.uploadPck.upload.userService.user;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public interface UploadBoard {
	public void uploadProc(Parent root) throws Exception;
	public void chekID(String id);
	public String fileUpload(Parent root);
//	public String fileUpload(AnchorPane root);
}
