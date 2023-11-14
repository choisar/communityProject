package all.button.find_idpw;

import all.Controller;
import all.button.CommonService;
import all.button.CommonServiceImp;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PwFindButtonImp implements PwFindButton {
	DatabaseDAO dao;
	CommonService cs;

	public PwFindButtonImp() {
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();
	}

	@Override
	public void pwFindProc(Parent root) {
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/pwFind.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		membershipForm.setTitle("비밀번호찾기");
		membershipForm.setResizable(false);
		membershipForm.show();
	}

	@Override
	public void pwFindOkProc(Parent root) {
		TextField id = (TextField) root.lookup("#txtId");
		TextField phoneNum = (TextField) root.lookup("#txtPhoneNum");
		String findId = id.getText();
		String findPhoneNum = phoneNum.getText();

		if (dao.pwChk(id.getText(), phoneNum.getText())) {
			String pw = dao.findPw(findId, findPhoneNum);
			String findName = dao.findUserName(findId, findPhoneNum);
			findPwResult(root, pw, findName);
		} else {
			cs.msgBox("비밀번호 찾기", "비밀번호 찾기 오류", "아이디와 전화번호를 확인해주세요.");
			id.clear();
			phoneNum.clear();
			id.requestFocus();
		}
	}

	@Override
	public void findPwResult(Parent root, String pw, String findName) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/pwFindResult.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		TextField findId = (TextField) root.lookup("#findPw");
		findId.setText(pw);
		
		Label findNameLb = (Label) root.lookup("#findName");
		findNameLb.setText("\""+findName+"\"");

		membershipForm.setTitle("비밀번호 찾기");
		membershipForm.setAlwaysOnTop(true);
		membershipForm.setResizable(false);
		membershipForm.show();

	}

}
