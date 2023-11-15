package all.button;

import javafx.event.ActionEvent;
import javafx.scene.Parent;

public interface CommonService {
	
	public void windowClose(ActionEvent event);
	public void msgBox(String title, String subject, String content);
	public void errorView(Parent root);
}
