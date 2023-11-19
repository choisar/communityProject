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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class boardViewServiceImp implements boardViewService {

	DatabaseDAO dao = new DatabaseDAOImp();
	CommonService cs = new CommonServiceImp();
	BoardService bs;
//	BoardService bs = new BoardServiceImp();
	
    public boardViewServiceImp() {
    	bs = new BoardServiceImp();
    }

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

        bs.reportCombo1(root);
        bs.reportCombo2(root);
        
        ComboBox<String> reportCombo2 = (ComboBox<String>) root.lookup("#searchReportCombo2");
        Label reportComboResultText = (Label) root.lookup("#comboResultText");
        TextArea reportContentsText = (TextArea) root.lookup("#reportContentsText");
        
        // 콤보박스의 선택 변경 이벤트 처리
        reportCombo2.setOnAction(event -> {
            String selectedValue = reportCombo2.getValue(); // 선택된 값 가져오기
            // 선택된 값에 따라 원하는 작업 수행
            switch (selectedValue) {
                case "게시물 신고":
                    // 게시물 신고에 대한 처리
                	reportComboResultText.setText("게시물 신고 - 광고글, 욕설글, 부적절한글 등");
                	reportContentsText.insertText(0, "### 게시물 신고 ###\n");
                    break;
                case "유저 신고":
                    // 유저 신고에 대한 처리
                	reportComboResultText.setText("유저 신고 - 사기, 비매너, 욕설 등");
                	reportContentsText.insertText(0, "### 유저 신고 ###\n");
                    break;
                case "닉네임 신고":
                    // 닉네임 신고에 대한 처리
                	reportComboResultText.setText("닉네임 신고 - 부적절한 닉네임 등");
                	reportContentsText.insertText(0, "### 닉네임 신고 ###\n");
                    break;
                default:
                    // 기본적으로 처리할 내용
                	reportComboResultText.setText("신고 사유를 선택해주세요.");
                    break;
            }
        });
        

        membershipForm.setTitle("신고하기");
        membershipForm.setResizable(false);
        membershipForm.show();
    }
    
    
}
