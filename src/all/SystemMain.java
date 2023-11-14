package all;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SystemMain extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		// fxml 파일 읽어오기
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));

		// 읽어온 소스를 객체화 - scene 객체
		Parent root = loader.load();

		// 이 fxml 파일의 Controller 를 지정 - 권한 부여
		Controller ctrl = loader.getController();
		// 페이지 정보 전달
		ctrl.setRoot(root);

		// scene 설정
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("메인화면");
		primaryStage.show();
	}
	public static void main(String[] args) {
		launch(args);
	}
	
}
