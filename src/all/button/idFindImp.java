package all.button;

import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import all.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class idFindImp implements idFind {
	
	DatabaseDAO dao;
	
	public idFindImp() {
		// TODO Auto-generated constructor stub
		dao = new DatabaseDAOImp();
	}

	@Override
	public void idFindProc(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		// Stage membershipForm = (Stage)root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../membershipForm.fxml"));

		Parent member = null;

		try {
			member = loader.load();
			membershipForm.setScene(new Scene(member));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setMember(member);



		membershipForm.setTitle("아이디찾기");
		// 항상 맨 위로 - 기본값 false
		membershipForm.setAlwaysOnTop(true);
		membershipForm.show();
	}

}
