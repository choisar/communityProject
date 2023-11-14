package all.button;

import all.Controller;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
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

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/idFind.fxml"));

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



		membershipForm.setTitle("아이디찾기");
		// 항상 맨 위로 - 기본값 false
		membershipForm.setAlwaysOnTop(true);
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	
	public void idFindOkProc(Parent root) {
		// TODO Auto-generated method stub

		TextField name = (TextField)root.lookup("#txtname");
		TextField PhoneNum  = (TextField)root.lookup("#txtPhoneNum");

		
		
	}

}
