package all.button;

import all.Controller;
import all.Member;
import all.boardService.BoardService;
import all.boardService.BoardServiceImp;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAOImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class loginButtonImp implements loginButton {
	DatabaseDAOImp dao;
	CommonService cs;
	BoardService bs;
	Parent root;

	public loginButtonImp() {
		// TODO Auto-generated constructor stub
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();
		bs = new BoardServiceImp();
	}

	@Override
	public void loginProc(Parent root) {
		// TODO Auto-generated method stub

		// 아이디 입력받는 텍스트 필드
		TextField id = (TextField) root.lookup("#txtId");
		// 비밀번호 입력받는 패스워드 필드
		PasswordField pw = (PasswordField) root.lookup("#txtPw");

		// 텍스트 필드에 입력받은 아이디를 문자열로 변환
		String insertId = id.getText();
		// 패스워드 필드에 입력받은 비밀번호를 문자열로 변환
		String insertPw = pw.getText();

		try {
		    if (id.getText().isEmpty() || pw.getText().isEmpty()) {
		        if (id.getText().isEmpty() && pw.getText().isEmpty()) {
		            cs.customErrorView(root, "아이디와 비밀번호가\n입력되지 않았습니다.");
		            id.requestFocus();
		        } else if (id.getText().isEmpty()) {
		            cs.customErrorView(root, "아이디를 입력해주세요.");
		            id.requestFocus();
		        } else {
		            cs.customErrorView(root, "비밀번호를 입력해주세요.");
		            pw.requestFocus();
		        }
		    } else {
		        if (dao.loginChk(insertId, insertPw)) {
		        	Member m = dao.memberInfo(insertId);
		            if (m != null) {
		                Stage membershipForm = (Stage) root.getScene().getWindow();
		                FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/userLogin.fxml"));
		                root = loader.load();
		                
		                Label memberName = (Label) root.lookup("#memberName");
		                memberName.setText("\"" + m.getName() + "\"");

		                membershipForm.setScene(new Scene(root));

		                Controller ctrl = loader.getController();
		                ctrl.setRoot(root);
		                
		                bs.chk1(root);
		                bs.mainCombo(root);
		                bs.createAllListView(root);
		                
		                membershipForm.setTitle("중고거래 커뮤니티");
		                membershipForm.show();
		                membershipForm.setResizable(false);
		                // 환영인사
		                helloView(root, m);
		            } else {
		                cs.customErrorView(root, "입력하신 정보와\n일치하는 정보가 없습니다.");
		                id.clear();
		                pw.clear();
		                id.requestFocus();
		            }
		        } else {
		            cs.customErrorView(root, "입력하신 정보와\n일치하는 정보가 없습니다.");
		            id.clear();
		            pw.clear();
		            id.requestFocus();
		        }
		    }
		} catch (Exception e) {
		    // TODO: handle exception
		}
	}

	@Override
	public void cancelProc(Parent root) {
		// TODO Auto-generated method stub
		TextField id = (TextField) root.lookup("#txtid");
		TextField pw = (TextField) root.lookup("#txtpw");

		id.clear();
		pw.clear();
		System.out.println("취소 버튼 클릭");
	}

	@Override
	public void membershipProc(Parent root) {
		// TODO Auto-generated method stub
	}

	@Override
	public void logoutProc(Parent root) {
		// TODO Auto-generated method stub
		Stage s = (Stage) root.getScene().getWindow();
		s.close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/main.fxml"));

		root = null;

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

		s.setTitle("중고거래 커뮤니티");
		s.show();
		s.setResizable(false);
	}
	
	// 로그인시 환영인사
	@Override
	public void helloView(Parent root, Member m) {
		// TODO Auto-generated method stub
		Stage loginHello = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/hello.fxml"));

		root = null;

		try {
			root = loader.load();
			loginHello.setScene(new Scene(root));
			
			Label helloNicName = (Label) root.lookup("#helloNicName");
			helloNicName.setText("\""+m.getNickName()+"\"");
			
			Label helloId1 = (Label) root.lookup("#helloId");
			
			String helloId2 = m.getId();
			int lengthToMask = helloId2.length() - 4;
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < lengthToMask; i++) {
			    sb.append("*");
			}
			String helloId3 = helloId2.substring(0, 4) + sb.toString();
			
			helloId1.setText("("+helloId3+")님 안녕하세요!");
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		loginHello.setTitle("로그인 성공");
		loginHello.setResizable(false);
		loginHello.initModality(Modality.APPLICATION_MODAL);
		loginHello.setAlwaysOnTop(true);
		loginHello.showAndWait();
	}

}
