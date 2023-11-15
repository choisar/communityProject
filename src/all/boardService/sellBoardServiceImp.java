package all.boardService;

import all.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class sellBoardServiceImp implements sellBoardService {

	@Override
	public void sellBoardProc(Parent root) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/notLoginError.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		membershipForm.setTitle("비회원 오류");
		membershipForm.setResizable(false);
		membershipForm.setAlwaysOnTop(true);
		membershipForm.show();
	}

}
