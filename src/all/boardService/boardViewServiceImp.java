package all.boardService;

import java.util.List;

import all.Controller;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class boardViewServiceImp implements boardViewService {

	DatabaseDAO dao = new DatabaseDAOImp();
	CommonService cs = new CommonServiceImp();
	BoardService bs = new BoardServiceImp();

	// 전체 게시판
	@Override
	public void allBoardView(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/allBoardView.fxml"));
		
		root = null;
		
		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);
		
		bs.createAllListView(root);
		
		membershipForm.setTitle("자유 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	
	// 자유 게시판
	@Override
	public void freeBoardView(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/freeBoardView.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		bs.createCategoryListView(root, "자유 게시판");
		
		membershipForm.setTitle("자유 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}

	// 구매 게시판
	@Override
	public void buyBoardView(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/buyBoardView.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		bs.createCategoryListView(root, "구매 게시판");

		membershipForm.setTitle("구매 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}

	// 판매 게시판
	@Override
	public void sellBoardView(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/sellBoardView.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		bs.createCategoryListView(root, "판매 게시판");

		membershipForm.setTitle("판매 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}

	// 나눔 게시판
	@Override
	public void sharingBoardView(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/sharingBoardView.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		bs.createCategoryListView(root, "나눔 게시판");

		membershipForm.setTitle("나눔 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}

	// 검색 결과 게시판
	@Override
	public void searchResultBoardView(Parent root, String text1, String text2) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/searchResult.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		bs.serchResultListView(root, text1, text2);

		membershipForm.setTitle("검색 결과 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	
	// 신고화면 검색 결과 게시판
	@Override
	public void reportSearchResultBoardView(Parent root, String text1, String text2) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/searchResult.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		bs.reportSerchResultListView(root, text1, text2);

		membershipForm.setTitle("신고화면 검색 결과 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}

	// 글쓰기 창
	@Override
	public void uploadBoardView(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/writing.fxml"));

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);
		ComboBox<String> cmbCateg=(ComboBox<String>)root.lookup("#cmbCateg");
		
		cmbCateg.getItems().addAll("자유 게시판", "구매 게시판", "판매 게시판", "나눔 게시판");

		membershipForm.setTitle("게시글 입력");
		membershipForm.setResizable(true);
		membershipForm.show();
	}

	// 신고하기 창
    @Override
    public void reportView(Parent root) {
        // TODO Auto-generated method stub
        Stage membershipForm = new Stage();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/reportView.fxml"));

        root = null;

        try {
            root = loader.load();
            membershipForm.setScene(new Scene(root));
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        Controller ctrl = loader.getController();
        ctrl.setRoot(root);

        bs.reportCombo(root);

        membershipForm.setTitle("신고하기");
        membershipForm.setResizable(false);
        membershipForm.show();
    }
    
//    @Override
//    public void getBoardViewByCategory(Parent root, String category) {
//        TableView<Board> listView = (TableView<Board>) root.lookup("#ListView");
//        listView.getItems().clear();
//        List<Board> boardList = dao.categoryBoardAll(category);
//
//        if (boardList != null) {
//            TableColumn<Board, String> nickname = new TableColumn<>("닉네임");
//            nickname.setMinWidth(75);
//            nickname.setMaxWidth(150);
//            TableColumn<Board, String> title = new TableColumn<>("제목");
//            title.setMinWidth(423);
//            title.setMaxWidth(423);
//            TableColumn<Board, String> date = new TableColumn<>("날짜");
//            date.setMinWidth(125);
//            date.setMaxWidth(125);
//            TableColumn<Board, String> category1 = new TableColumn<>("카테고리");
//            category1.setMinWidth(75);
//            category1.setMaxWidth(75);
//
//            nickname.setCellValueFactory(new PropertyValueFactory<>("nicName"));
//            title.setCellValueFactory(new PropertyValueFactory<>("title"));
//            date.setCellValueFactory(new PropertyValueFactory<>("uploadDate"));
//            category1.setCellValueFactory(new PropertyValueFactory<>("categori"));
//
//            listView.getColumns().addAll(nickname, category1, title, date);
//            listView.setItems(FXCollections.observableArrayList(boardList));
//        } else {
//            System.out.println("게시판 목록을 가져올 수 없습니다.");
//        }
//    }
    
}
