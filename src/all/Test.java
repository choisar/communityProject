package all;

import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Test {
	
	CommonService cs = new CommonServiceImp();

	// 테스트 버튼 1
	public void testProc1(Parent root) {
		// TODO Auto-generated method stub
		
		Stage testView = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/test1.fxml"));
		
		try {
			root = loader.load();
			testView.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);
		
		testView.setTitle("Test1");
		testView.setResizable(false);
		testView.show();
	}
	
	// 테스트 버튼 2
	public void testProc2(Parent root) {
		// TODO Auto-generated method stub
		
		// 텍스트필드 아이다가 text5인 t5 텍스트필드 객체 지정
		TextField t5 = (TextField)root.lookup("#text5");
		
		try {
			// t5의 내용을 정수형으로 변환하여 정수 num1에 저장
			int num1 = Integer.parseInt(t5.getText());
			
			// num1이 1 ~ 12 값이 아니면 1 ~ 12 값만 입력하세요 출력
			if(num1 < 1 || num1 > 12) {
				cs.customErrorView(root, "1 ~ 12 값만 입력하세요");
				t5.clear();
				t5.requestFocus();
			} else {
				cs.customErrorView(root, t5.getText());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			cs.customErrorView(root, "숫자만 입력해주세요.");
		}
		TextField t6 = (TextField)root.lookup("#text6");
		
		try {
			int num2 = Integer.parseInt(t6.getText());
			
			if(num2 < 1 || num2 > 31) {
				cs.customErrorView(root, "1 ~ 31 값만 입력하세요");
				t6.clear();
				t6.requestFocus();
			} else {
				cs.customErrorView(root, t6.getText());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			cs.customErrorView(root, "숫자만 입력해주세요.");
		}
		
	}
	
	
	
}
