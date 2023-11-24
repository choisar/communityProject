package all.boardService;

import java.io.IOException;
import java.sql.Blob;
import java.util.List;

import all.Controller;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class boardViewServiceImp implements boardViewService {

	DatabaseDAO dao;
	CommonService cs = new CommonServiceImp();
	BoardService bs;
	
    public boardViewServiceImp() {
    	dao = new DatabaseDAOImp();
    	bs = new BoardServiceImp();
    }

	// 전체 게시판
	@Override
	public void allBoardView(Parent root, String memId) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/boardView/allBoardView.fxml"));
		
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
		
		bs.createAllListView(root,memId);
		
		membershipForm.setTitle("자유 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	
	// 자유 게시판
	@Override
	public void freeBoardView(Parent root, String memId) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/boardView/freeBoardView.fxml"));

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
		
		

		bs.createCategoryListView(root, "자유 게시판", memId);
		
		membershipForm.setTitle("자유 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}

	// 구매 게시판
	@Override
	public void buyBoardView(Parent root, String memId) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/boardView/buyBoardView.fxml"));

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

		bs.createCategoryListView(root, "구매 게시판", memId);

		membershipForm.setTitle("구매 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}

	// 판매 게시판
	@Override
	public void sellBoardView(Parent root, String memId) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/boardView/sellBoardView.fxml"));

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

		bs.createCategoryListView(root, "판매 게시판", memId);

		membershipForm.setTitle("판매 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}

	// 나눔 게시판
	@Override
	public void sharingBoardView(Parent root, String memId) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/boardView/sharingBoardView.fxml"));

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

		bs.createCategoryListView(root, "나눔 게시판", memId);

		membershipForm.setTitle("나눔 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}
	
	// Q&A 게시판
	@Override
	public void QABoardView(Parent root, String memId) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/boardView/Q&ABoardView.fxml"));

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

		bs.createCategoryListView(root, "QA 게시판", memId);

		membershipForm.setTitle("Q&A 게시판");
		membershipForm.setResizable(false);
		membershipForm.show();
	}

	// 검색 결과 게시판
	@Override
	public void searchResultBoardView(Parent root, String text1, String text2, String memId) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/boardView/searchResultView.fxml"));

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
		bs.serchResultListView(root, text1, text2, memId);
		
		membershipForm.setTitle("검색 결과 게시판");
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
		ComboBox<String> cmbCateg = (ComboBox<String>) root.lookup("#cmbCateg");

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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/boardView/reportView.fxml"));

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
        Label logChk = (Label) root.lookup("#logChk");
        
        // 콤보박스의 선택 변경 이벤트 처리
        reportCombo2.setOnAction(event -> {
            String selectedValue = reportCombo2.getValue(); // 선택된 값 가져오기
            
            // 확인되면 문자열을 추가하기 전에 맨 윗줄이 #으로 시작하는지 확인
            String[] lines = reportContentsText.getText().split("\n");
            if (lines.length > 0 && lines[0].startsWith("#")) {
                // 맨 윗줄이 #으로 시작하는 경우 해당 줄 삭제
                StringBuilder newText = new StringBuilder();
                for (int i = 1; i < lines.length; i++) {
                    newText.append(lines[i]).append("\n");
                }
                reportContentsText.setText(newText.toString());
            }
            
            // 선택된 값에 따라 원하는 작업 수행
            switch (selectedValue) {
                case "게시물 신고":
                    // 게시물 신고에 대한 처리
                	reportComboResultText.setText("게시물 신고 - 광고글, 욕설글, 부적절한글 등");
                	reportContentsText.insertText(0, "### 게시물 신고 ###\n");
                    break;
                case "유저 신고":
                    // 유저 신고에 대한 처리ㅇ
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
    
    
    // 보드 디테일뷰에서 다음, 이전 게시물 창을 띄어주는 메서드
    @Override
    public void loadNextBoardInCategoryView(Parent root, String strPostNum, String category, String Sorting) {
        // 여기서 strPostNum보다 크거나 작은 가장 가까운 값을 가진 다음 게시물을 찾아야 함
        Board next_prev_Board = dao.getNextPrevBoard(strPostNum, category, Sorting);

        // 다음, 이전 게시물이 존재한다면 해당 게시물의 openBoardDetailWindow 호출 + 현재 창 닫기
        if (next_prev_Board != null) {
            try {
                Parent newRoot = FXMLLoader.load(getClass().getResource("../fxml/boardDetail.fxml"));
                List<Image> imagelist = dao.getAllImages(next_prev_Board.getNo());
                bs.openBoardDetailWindow(newRoot, next_prev_Board,imagelist);
                
                // 현재 창을 닫기
                Stage currentStage = (Stage) root.getScene().getWindow();
                currentStage.close();
            } catch (IOException e) {
                e.printStackTrace();
                // 예외 처리: FXML 파일을 로드하는 동안 예외가 발생했을 때 실행할 내용
            } catch(Exception e) {
                e.printStackTrace();
            }
        // 다음, 이전 게시물이 없다면 커스텀 에러창 호출
        } else {
        	// Sorting 값이 ASC일 때 (다음 게시물 값일 때)
        	if(Sorting == "ASC") {
        		try {
        			Parent detailRoot = FXMLLoader.load(getClass().getResource("../fxml/boardDetail.fxml"));
        			cs.customErrorView(detailRoot, "다음 게시물이 없습니다.");
        		} catch (IOException e) {
        			e.printStackTrace();
        			// 예외 처리: FXML 파일을 로드하는 동안 예외가 발생했을 때 실행할 내용
        		}
        		// Sorting 값이 DESC일 때 (이전 게시물 값일 때)
        	} else if(Sorting == "DESC") {
        		try {
        			Parent detailRoot = FXMLLoader.load(getClass().getResource("../fxml/boardDetail.fxml"));
        			cs.customErrorView(detailRoot, "이전 게시물이 없습니다.");
        		} catch (IOException e) {
        			e.printStackTrace();
        			// 예외 처리: FXML 파일을 로드하는 동안 예외가 발생했을 때 실행할 내용
        		}
        	}
        }
    }
    
    
    
    
}
