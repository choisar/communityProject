package all;

import all.boardService.BoardServiceImp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SystemMain extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));
		Parent root = loader.load();

		primaryStage.setScene(new Scene(root));
		primaryStage.setTitle("User Login");

		// Create an instance of BoardServiceImp
		BoardServiceImp boardService = new BoardServiceImp();

		// Call the boardListView method
		boardService.boardListView2(root);
	}
	
}
