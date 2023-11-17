package all.button;

import all.Controller;
import all.boardService.BoardService;
import all.boardService.BoardServiceImp;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAOImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class loginButtonImp implements loginButton {
	DatabaseDAOImp dao;
	CommonService cs;
	BoardService bs;
	public loginButtonImp() {
		// TODO Auto-generated constructor stub
		dao =new DatabaseDAOImp();
		cs = new CommonServiceImp();
		bs = new BoardServiceImp();
	}
	@Override
	public void loginProc(Parent root) {
		TextField id = (TextField)root.lookup("#txtId"); 
		PasswordField pw = (PasswordField)root.lookup("#txtPw");
		// TODO Auto-generated method stub
		try {
		
		if(dao.loginChk(id.getText(), pw.getText())) {
			System.out.println("로그인 성공");
			Stage membershipForm = (Stage)root.getScene().getWindow();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/userLogin.fxml"));
			Parent member = null;
			try {
				member = loader.load();
				membershipForm.setScene(new Scene(member));
			} catch (Exception e) {
				// TODO:handle exception
				e.printStackTrace();
			}
			
			Controller ctrl = loader.getController();
			ctrl.setMember(member);
			
			bs.mainCombo(member);
			bs.createAllListView(member);
			
			membershipForm.setTitle("로그인");
			membershipForm.show();
			membershipForm.setResizable(false);

		} else {
			System.out.println("로그인 실패");
			cs.msgBox("로그인","로그인 여부","로그인에 실패했습니다. 다시 입력하세요.");
			id.clear();
			pw.clear();
			id.requestFocus();
			
		}
		}catch (Exception e) {
			// TODO: handle exception
			
		}
//		System.out.println("로그인로그인");
		
		
			
		
	}



	@Override
	public void cancelProc(Parent root) {
		// TODO Auto-generated method stub
		TextField id = (TextField)root.lookup("#txtid"); 
		TextField pw = (TextField)root.lookup("#txtpw");
	
		id.clear();
		pw.clear();
		System.out.println("취소 버튼 클릭");
	}

	@Override
	public void membershipProc(Parent root) {
		// TODO Auto-generated method stub
	}	
		

	@Override
	public void logoutProc(Parent info) {
		// TODO Auto-generated method stub
		Stage s = (Stage) info.getScene().getWindow();
		s.close();
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../fxml/main.fxml"));
		
		Parent root = null;
		
		try {
			root = loader.load();
			s.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);
		
		bs.mainCombo(root);
		bs.createAllListView(root);
	
		
		s.setTitle("로그아웃완료");
		s.show();
		s.setResizable(false);
		
	}
	
	
}
