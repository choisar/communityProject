package all.uploadPck.upload;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UploadMain extends Application{
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		FXMLLoader loader=new FXMLLoader(getClass().getResource("../writing.fxml"));
		
		Parent root=loader.load();

		Controller ctrl=loader.getController();
		ctrl.setRoot(root);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("게시글 작성");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
