package all.boardService;

import all.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class freeBoardServiceImp implements freeBoardService{

	@Override
	public void freeBoardView(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/freeBoardView.fxml"));

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

		membershipForm.setTitle("자유 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	
}
