package all.button.infoButton;

import java.util.List;

import all.Controller;
import all.Member;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAOImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class infoButtonImp implements infoButton {

	DatabaseDAOImp dao;
	CommonService cs;
	
	public infoButtonImp() {
		// TODO Auto-generated constructor stub
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();
	}

	@Override
	public void infoProc(Parent root) {
	    Stage s = (Stage) root.getScene().getWindow();
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/aaa.fxml"));
	    Parent newRoot = null;

	    try {
	        newRoot = loader.load();
	        s.close(); // 이전 root를 닫는 부분을 root를 로드한 후에 실행하도록 변경
	        s.setScene(new Scene(newRoot));
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    Controller ctrl = loader.getController();
	    ctrl.setRoot(newRoot);

	    Label txtlabel = (Label) newRoot.lookup("#txtlabel");
	    if (txtlabel != null) { // txtlabel이 null인지 안전하게 체크
	        txtlabel.setOnMouseClicked(e -> {
	            backProc(ctrl.getRoot());
	        });
	    }
	    
	 // aaa.fxml(나의정보) 창의 상단 왼쪽부분에 로그인된 닉네임이 뜨는 것과 관련됨
	    Label txtlabel2 = (Label) newRoot.lookup("#NickName");
	    if (txtlabel2 != null) { 
	    	txtlabel2.setText(dao.selectNick());
	    }

	    s.setTitle("내정보");
	    s.show();
	}

	@Override
	public void backProc(Parent root) {
		// TODO Auto-generated method stub
		Stage s = (Stage) root.getScene().getWindow();
		s.close();


		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../../fxml/userLogin1.fxml"));
		
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
		
	
		
		s.setTitle("이전화면");
		s.show();
		
	}

	@Override
	public void deleteProc(Parent root) {
		// TODO Auto-generated method stub
		boolean deleteResult = dao.removeMem();
		if(deleteResult) {
			cs.customErrorView(root, "회원탈퇴 되었습니다.");
		} else {
			cs.customErrorView(root, "회원탈퇴 실패했습니다. 관리자에게 문의하세요.");
		}
			
			Stage s = (Stage) root.getScene().getWindow();
			s.close();
			FXMLLoader loader = new FXMLLoader(
					getClass().getResource("../../fxml/main.fxml"));
			
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
			
			s.setTitle("이전화면");
			s.show();
	}
	
}
