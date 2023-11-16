package all.boardService;


import all.Controller;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class boardViewServiceImp implements boardViewService{
	
	DatabaseDAO dao;
	CommonService cs;
	BoardService bs;
	
	public boardViewServiceImp() {
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();
		bs = new BoardServiceImp();
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
		
		bs.createCategoryListView(root,"자유");

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
		
		bs.createCategoryListView(root,"구매");

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
		
		bs.createCategoryListView(root,"판매");

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
		
		bs.createCategoryListView(root,"나눔");

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

		membershipForm.setTitle("검샐 결과 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	
	// 글쓰기 창
	@Override
	public void writingView(Parent root) {
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

		membershipForm.setTitle("게시물 작성");
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	
}
