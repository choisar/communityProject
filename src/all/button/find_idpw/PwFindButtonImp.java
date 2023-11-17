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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PwFindButtonImp implements PwFindButton {
	DatabaseDAO dao;
	CommonService cs;

	public PwFindButtonImp() {
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();
	}

	// 비밀번호 찾기 버튼을 눌렀을 때 비밀번호 찾기 창 호출
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
		membershipForm.initModality(Modality.APPLICATION_MODAL);
		membershipForm.setAlwaysOnTop(true);
		membershipForm.showAndWait();
	}

	// 호출된 비밀번호 찾기 창에서 확인 버튼 눌렀을 때
	@Override
	public void pwFindOkProc(Parent root) {
		TextField id = (TextField) root.lookup("#txtId");
		TextField phoneNum = (TextField) root.lookup("#txtPhoneNum");
		String findId = id.getText();
		String findPhoneNum = phoneNum.getText();

		if(dao.pwChk(findId, findPhoneNum)) {
			String pw = dao.findPw(findId,findPhoneNum);
			String findName = dao.findUserName(findId, findPhoneNum);
			findPwResult(root, pw, findName);
			Stage currentStage = (Stage) id.getScene().getWindow();
			currentStage.close();
		} else if(id.getText().isEmpty() && phoneNum.getText().isEmpty()){
			cs.customErrorView(root, "정보를 입력해주세요.");
			id.requestFocus();
		} else if(id.getText().isEmpty() && !(phoneNum.getText().isEmpty())){
			cs.customErrorView(root, "아이디를 입력해주세요.");
			id.requestFocus();
		} else if(!(id.getText().isEmpty()) && phoneNum.getText().isEmpty()){
			cs.customErrorView(root, "전화번호를 입력해주세요.");
			phoneNum.requestFocus();
		}else if(!(id.getText().isEmpty()) && !(phoneNum.getText().isEmpty())) {
			if(!(dao.idChk(id.getText(), phoneNum.getText()))) {
				cs.customErrorView(root,"입력하신 내용과\n 일치하는 정보가 없습니다.");
				id.clear();
				phoneNum.clear();
				id.requestFocus();
			} else {
				System.err.println("아이디, 전화번호 둘 다 입력했고 dao에서 일치하는 정보도 없는데 오류 발생");
			}
		}
	}

	// 비밀번호 찾기 결과창 호출
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

		TextField findPw = (TextField) root.lookup("#findPw");
		findPw.setText(pw);
		
		Label findNameLb = (Label) root.lookup("#findName");
		findNameLb.setText("\""+findName+"\"");

		membershipForm.setResizable(false);
		membershipForm.initModality(Modality.APPLICATION_MODAL);
		membershipForm.setAlwaysOnTop(true);
		membershipForm.showAndWait();
	}

}
