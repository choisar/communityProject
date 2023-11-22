package all.button.find_IdPw;

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

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/find_IdPw/idFind.fxml"));

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

		membershipForm.setResizable(false);
		membershipForm.initModality(Modality.APPLICATION_MODAL);
		membershipForm.setAlwaysOnTop(true);
		membershipForm.showAndWait();
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
			Stage currentStage = (Stage) name.getScene().getWindow();
			currentStage.close();
		} else if(name.getText().isEmpty() && phoneNum.getText().isEmpty()){
			cs.customErrorView(root, "정보를 입력하세요.");
			name.requestFocus();
		} else if(name.getText().isEmpty() && !(phoneNum.getText().isEmpty())){
			cs.customErrorView(root, "이름을 입력하세요.");
			name.requestFocus();
		} else if(!(name.getText().isEmpty()) && phoneNum.getText().isEmpty()){
			cs.customErrorView(root, "핸드폰 번호를 입력하세요.");
			phoneNum.requestFocus();
		}else if(!(name.getText().isEmpty()) && !(phoneNum.getText().isEmpty())) {
			if(!(dao.idChk(name.getText(), phoneNum.getText()))) {
				cs.customErrorView(root,"일치하는 정보가 없습니다.");
				name.clear();
				phoneNum.clear();
				name.requestFocus();
			} else {
				System.err.println("이름, 전화번호 둘 다 입력했고 dao에서 일치하는 정보도 없는데 오류 발생");
			}
		}
	}

	// 아이디 찾기 결과창 호출
	@Override
	public void findIdResult(Parent root, String id, String findName) {
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/find_IdPw/idFindResult.fxml"));
		
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
		findNameLb.setText(findName);

		membershipForm.setResizable(false);
		membershipForm.initModality(Modality.APPLICATION_MODAL);
		membershipForm.setAlwaysOnTop(true);
		membershipForm.showAndWait();
	}

}
