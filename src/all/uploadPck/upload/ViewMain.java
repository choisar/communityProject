package all.uploadPck.upload;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewMain extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../imageView.fxml"));
		
		Parent root=loader.load();

		ViewController ctrl=loader.getController();
		ctrl.setRoot(root);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("이미지 보기");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
