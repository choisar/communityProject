package all.button;

import all.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class pwFindImp implements pwFind {
	
	
	public pwFindImp() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void pwFindProc(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		// Stage membershipForm = (Stage)root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/pwFind.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setMember(root);



		membershipForm.setTitle("비밀번호찾기");
		// 항상 맨 위로 - 기본값 false
		membershipForm.setAlwaysOnTop(true);
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	

}
