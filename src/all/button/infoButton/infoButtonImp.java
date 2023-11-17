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
	public void infoProc(Parent info) {
		// TODO Auto-generated method stub
		
		Stage s = (Stage) info.getScene().getWindow();
		s.close();
		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../fxml/aaa.fxml"));
		
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

		
		Label txtlabel = (Label) root.lookup("#txtlabel");
		
		txtlabel.setOnMouseClicked(e -> {
			backProc(ctrl.getRoot());
		});
		
		
		s.setTitle("내정보");
		s.show();
		
		
	}

	@Override
	public void backProc(Parent info) {
		// TODO Auto-generated method stub
		Stage s = (Stage) info.getScene().getWindow();
		s.close();


		FXMLLoader loader = new FXMLLoader(
				getClass().getResource("../fxml/userLogin.fxml"));
		
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
		
	
		
		s.setTitle("이전화면");
		s.show();
		
	}

	@Override
	public void deleteProc(Parent info) {
		// TODO Auto-generated method stub
		List<Member> memberList = dao.selectAll1();
			memberList.remove(dao);
			System.out.println("회 원 탈 퇴");
			cs.msgBox("회원탈최","회원탈퇴 여부","탈퇴 완료했습니다.");
			
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
			
		
			
			s.setTitle("이전화면");
			s.show();
	}
	
}
