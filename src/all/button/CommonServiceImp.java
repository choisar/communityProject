package all.button;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CommonServiceImp implements CommonService{
	
	public void windowClose(ActionEvent event) {
		// TODO Auto-generated method stub
		// ActionEvent.getSource() -> 실제 이벤트 발생한 객체
		Parent p = (Parent) event.getSource();
		Stage s = (Stage) p.getScene().getWindow();
		s.close();
	}

	public void msgBox(String title, String subject, String content) {
		// TODO Auto-generated method stub
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle(title);
		alert.setHeaderText(subject);
		alert.setContentText(content);
		alert.showAndWait();
	}
}
