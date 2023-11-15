package all.button.common;

import javafx.event.ActionEvent;
import javafx.scene.Parent;

public interface CommonService {
	
	public void windowClose(ActionEvent event);
	public void msgBox(String title, String subject, String content);
	public void errorView1(Parent root);
	public void errorView2(Parent root);
}
