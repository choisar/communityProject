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
	CommonService cs;
	
	public idFindImp() {
		// TODO Auto-generated constructor stub
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();
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
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	
	public void idFindOkProc(Parent root) {
		// TODO Auto-generated method stub

		TextField name = (TextField)root.lookup("#txtname");
		TextField phoneNum  = (TextField)root.lookup("#txtPhoneNum");
		
		
		if(dao.idChk(name.getText(), phoneNum.getText())) {
			System.out.println("아이디찾기 성공");
			try {
				userLogin(root);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} else {
			cs.msgBox("아이디 찾기", "아이디 찾기 여부", "아이디 찾기에 실패하셨습니다. 이름과 전화번호를 확인하세요");
			name.clear();
			phoneNum.clear();
			name.requestFocus();
		}
	}

	private void userLogin(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		// Stage membershipForm = (Stage)root.getScene().getWindow();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/userLogin.fxml"));

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

		membershipForm.setTitle("로그인 후 화면");
		// 항상 맨 위로 - 기본값 false
		membershipForm.setAlwaysOnTop(true);
		membershipForm.setResizable(false);
		membershipForm.show();
	}

}
