package all.button.find_idpw;

import all.Controller;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class IdFindButtonImp implements IdFindButton {
	DatabaseDAO dao;
	CommonService cs;
	
	public IdFindButtonImp() {
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();
	}

	// 아이디 찾기 버튼을 눌렀을 때 아이디 찾기 창 호출
	@Override
	public void idFindProc(Parent root) {
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/idFind.fxml"));

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

		membershipForm.setTitle("아이디찾기");
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	
	// 호출된 아이디 찾기 창에서 확인 버튼 눌렀을 때
	@Override
	public void idFindOkProc(Parent root) {
		TextField name = (TextField)root.lookup("#txtName");
		TextField phoneNum  = (TextField)root.lookup("#txtPhoneNum");
		String findName = name.getText();
		String findPhoneNum = phoneNum.getText();
		
		if(dao.idChk(name.getText(), phoneNum.getText())) {
				String id = dao.findId(findName,findPhoneNum);
				findIdResult(root, id, findName);
		} else {
			cs.msgBox("아이디 찾기", "아이디 찾기 오류", "이름과 전화번호를 확인해주세요.");
			name.clear();
			phoneNum.clear();
			name.requestFocus();
		}
	}

	// 아이디 찾기 결과창 호출
	@Override
	public void findIdResult(Parent root, String id, String findName) {
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/idFindResult.fxml"));
		
		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setRoot(root);
		
		TextField findId = (TextField) root.lookup("#findId");
		findId.setText(id);
		
		Label findNameLb = (Label) root.lookup("#findName");
		findNameLb.setText("\""+findName+"\"");

		membershipForm.setTitle("아이디 찾기");
		membershipForm.setAlwaysOnTop(true);
		membershipForm.setResizable(false);
		membershipForm.show();
	}

}
