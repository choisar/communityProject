package all.boardService_hong;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BSIM extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/userLogin.fxml"));
        Parent root = loader.load();

        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("User Login");

        // Create an instance of BoardServiceImp
        BoardServiceImp boardService = new BoardServiceImp();

        // Call the boardListView method
        boardService.boardListView(root);

//        primaryStage.show();
    }
}
