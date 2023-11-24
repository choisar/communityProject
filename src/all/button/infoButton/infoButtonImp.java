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
	proButton pb;
	
	public infoButtonImp() {
		// TODO Auto-generated constructor stub
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();
		pb = new proButtonImp();
		}

	@Override
	public void infoProc(Parent root) {
	    Stage currentStage = (Stage) root.getScene().getWindow();

	    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/infoupdate.fxml"));
	    Parent newRoot = null;

	    try {
	        newRoot = loader.load();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }

	    Stage newStage = new Stage(); // 새 Stage 생성
	    newStage.setScene(new Scene(newRoot));

	    Controller ctrl = loader.getController();
	    ctrl.setRoot(newRoot);

	    newStage.setTitle("Info");
	    newStage.show();

	    pb.profileProc(newRoot);

	    // 이전 Stage는 닫지 않고 그대로 유지
	    // currentStage.close(); // 기존 Stage를 닫고 싶다면 이 줄의 주석을 해제하세요.
	}


	@Override
	public void deleteProc(Parent root) {
		// TODO Auto-generated method stub
		boolean deleteResult = dao.removeMem();
		if(deleteResult) {
			cs.customErrorView(root, "회원탈퇴 완료");
		} else {
			cs.customErrorView(root, "회원탈퇴 실패");
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
			
			s.setTitle("KG - Trading Comunity");
			s.show();
	}
	
//	@Override
//	public void deleteProc(Parent root) {
//	    boolean deleteResult = dao.removeMem();
//	    if (deleteResult) {
//	        cs.customErrorView(root, "회원탈퇴 되었습니다.");
//	    } else {
//	        cs.customErrorView(root, "회원탈퇴 실패했습니다. 관리자에게 문의하세요.");
//	    }
//
//	    try {
//	        // 현재 실행 중인 모든 창을 닫는다
//	        Stage currentStage = (Stage) root.getScene().getWindow();
//	        currentStage.close();
//
//	        // 새로운 창을 열어서 보여준다
//	        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/main.fxml"));
//	        Parent newRoot = loader.load();
//	        Stage newStage = new Stage();
//	        newStage.setScene(new Scene(newRoot));
//
//	        Controller ctrl = loader.getController();
//	        ctrl.setRoot(newRoot);
//
//	        newStage.setTitle("이전화면");
//	        newStage.show();
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//	}
	
}
