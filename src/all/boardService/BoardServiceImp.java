package all.boardService;

import java.util.List;

import all.Controller;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class BoardServiceImp implements BoardService {
	DatabaseDAO dao = new DatabaseDAOImp();

	// 메인화면
	@Override
	public void mainView(Parent root) {
		// TODO Auto-generated method stub

		Stage s = (Stage) root.getScene().getWindow();
		s.close();

		Stage rootStage = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/main.fxml"));

		root = null;
		try {
			root = loader.load();
			rootStage.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setRoot(root);
		
		ComboBox<String> combo = (ComboBox<String>)root.lookup("#searchCombo");
		String str[] = {"제목", "닉네임", "카테고리", "날짜"};
		combo.getItems().addAll(FXCollections.observableArrayList(str));
		

		createAllListView(root);

		rootStage.setTitle("회원정보");
		rootStage.show();
		rootStage.setResizable(false);
	}

	// 전체 카테고리의 모든 게시물을 출력하는 테이블뷰 생성
	public void createAllListView(Parent root) {
		TableView<Board> listView = (TableView<Board>) root.lookup("#ListView");
		listView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		List<Board> boardList = dao.selectAll();
		if (boardList != null) {
			TableColumn<Board, String> nickname = new TableColumn<Board, String>("닉네임");
			TableColumn<Board, String> title = new TableColumn<Board, String>("제목");
			TableColumn<Board, String> date = new TableColumn<Board, String>("날짜");
			nickname.setCellValueFactory(new PropertyValueFactory<Board, String>("nickname"));
			title.setCellValueFactory(new PropertyValueFactory<Board, String>("title"));
			date.setCellValueFactory(new PropertyValueFactory<Board, String>("date"));

			listView.getColumns().addAll(nickname, title, date);
			listView.setItems(FXCollections.observableArrayList(boardList));

		} else {
			System.out.println("게시판 목록을 가져올 수 없습니다.");
		}

		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				Board b = (Board) listView.getSelectionModel().getSelectedItem();
				System.out.println(b.getNickname());
				System.out.println(b.getTitle());
				System.out.println(b.getDate());

			}
		});
	}

	// 선택한 카테고리 <자유, 구매, 판매, 나눔> 의 모든 게시물을 출력하는 테이블뷰 생성
	public void createCategoryListView(Parent root, String category) {
		TableView<Board> listView = (TableView<Board>) root.lookup("#ListView");
		listView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		List<Board> boardList = dao.categoryBoardAll(category);

		if (boardList != null) {
			TableColumn<Board, String> nickname = new TableColumn<Board, String>("닉네임");
			TableColumn<Board, String> title = new TableColumn<Board, String>("제목");
			TableColumn<Board, String> date = new TableColumn<Board, String>("날짜");
			nickname.setCellValueFactory(new PropertyValueFactory<Board, String>("nickname"));
			title.setCellValueFactory(new PropertyValueFactory<Board, String>("title"));
			date.setCellValueFactory(new PropertyValueFactory<Board, String>("date"));

			listView.getColumns().addAll(nickname, title, date);
			listView.setItems(FXCollections.observableArrayList(boardList));

		} else {
			System.out.println("게시판 목록을 가져올 수 없습니다.");
		}

		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				Board b = (Board) listView.getSelectionModel().getSelectedItem();
				System.out.println(b.getNickname());
				System.out.println(b.getTitle());
				System.out.println(b.getDate());
			}
		});
	}

	// 카테고리에 상관없이 검색 결과가 포함된 모든 게시물을 출력하는 테이블뷰 생성
	public void serchResultListView(Parent root, String text1, String text2) {
		TableView<Board> listView = (TableView<Board>) root.lookup("#ListView");
		listView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		List<Board> boardList = dao.searchResultAll(text1,text2);
		
		if (boardList != null) {
			TableColumn<Board, String> nickname = new TableColumn<Board, String>("닉네임");
			TableColumn<Board, String> title = new TableColumn<Board, String>("제목");
			TableColumn<Board, String> date = new TableColumn<Board, String>("날짜");
			nickname.setCellValueFactory(new PropertyValueFactory<Board, String>("nickname"));
			title.setCellValueFactory(new PropertyValueFactory<Board, String>("title"));
			date.setCellValueFactory(new PropertyValueFactory<Board, String>("date"));

			listView.getColumns().addAll(nickname, title, date);
			listView.setItems(FXCollections.observableArrayList(boardList));

		} else {
			System.out.println("게시판 목록을 가져올 수 없습니다.");
		}

		listView.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				Board b = (Board) listView.getSelectionModel().getSelectedItem();
				System.out.println(b.getNickname());
				System.out.println(b.getTitle());
				System.out.println(b.getDate());

			}
		});
	}

}
