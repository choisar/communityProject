package all.button;

import java.util.List;

import all.Controller;
import all.Member;
import all.boardService.Board;
import all.boardService.BoardService;
import all.boardService.BoardServiceImp;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAOImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class loginButtonImp implements loginButton {
	DatabaseDAOImp dao;
	CommonService cs;
	BoardService bs;
	Parent root;

	public loginButtonImp() {
		// TODO Auto-generated constructor stub
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();
		bs = new BoardServiceImp();
	}
	
	// 로그인 버튼
	@Override
	public void loginProc(Parent root) {
    	
	    TextField id = (TextField) root.lookup("#txtId");
	    PasswordField pw = (PasswordField) root.lookup("#txtPw");

	    String insertId = id.getText();
	    String insertPw = pw.getText();

	    try {
	        if (id.getText().isEmpty() || pw.getText().isEmpty()) {
	            if (id.getText().isEmpty() && pw.getText().isEmpty()) {
	                cs.customErrorView(root, "아이디와 비밀번호가\n입력되지 않았습니다.");
	                id.requestFocus();
	            } else if (id.getText().isEmpty()) {
	                cs.customErrorView(root, "아이디를 입력해주세요.");
	                id.requestFocus();
	            } else {
	                cs.customErrorView(root, "비밀번호를 입력해주세요.");
	                pw.requestFocus();
	            }
	        } else {
	            if (dao.loginChk(insertId, insertPw)) {
	            	
	            	
	                Member m = dao.memberInfo(insertId);
	                if (m != null) {
	                	
	                	Stage s = (Stage) root.getScene().getWindow();
	                	s.close();
		            	
	            		try {
	            			Stage LoginView = new Stage();
	            			
	            			FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/userLogin1.fxml"));
	            			
	            			root = loader.load();
	            			LoginView.setScene(new Scene(root));
	            			
	            			Controller ctrl = loader.getController();
		            		ctrl.setRoot(root);
		                    
		                    Label memberName = (Label) root.lookup("#memberName");
		                    memberName.setText("\"" + m.getName() + "\"");

		                    bs.chk1(root);
		                    bs.mainCombo(root);
		                    bs.createAllListView(root);

		                    // ######## 최신글 ###########	
			    			// 구매 게시판, 판매 게시판, 나눔 게시판, 자유 게시판의 최신글 각각 2개씩 정보 가져옴
			    			List<Board> buyLatestBoard = dao.getLatestBoardList("구매 게시판");
			    			
			    			// 메인화면에 이미지 넣어주기
			    			int buyBoard1seq = buyLatestBoard.get(0).getNo();
			    			int buyBoard2seq = buyLatestBoard.get(1).getNo();
			    			List<Image> buyLatestImage1 = dao.getAllImages(buyBoard1seq);
			    			List<Image> buyLatestImage2 = dao.getAllImages(buyBoard2seq);
			    			ImageView image1 = (ImageView)root.lookup("#BuyBoardImage1");
			    			ImageView image2 = (ImageView)root.lookup("#BuyBoardImage2");
			    			if(!(buyLatestImage1.isEmpty())) {
			    				image1.setImage(buyLatestImage1.get(0));
			    			}
			    			if(!(buyLatestImage2.isEmpty())) {
			    				image2.setImage(buyLatestImage2.get(0));
			    			}
			    			
			    			Label BuyBoardTitle1 = (Label)root.lookup("#BuyBoardTitle1");
			    			Label BuyBoardTitle2 = (Label)root.lookup("#BuyBoardTitle2");
			    			Label BuyBoardNickName1 = (Label)root.lookup("#BuyBoardNickName1");
			    			Label BuyBoardNickName2 = (Label)root.lookup("#BuyBoardNickName2");
			    			Label BuyBoardTime1 = (Label)root.lookup("#BuyBoardTime1");
			    			Label BuyBoardTime2 = (Label)root.lookup("#BuyBoardTime2");
			    			
			    			if (buyLatestBoard.size() >= 2) {
			    			    // 첫 번째 줄 정보 가져오기
			    			    Board buyBoard1 = buyLatestBoard.get(0);
			    			    BuyBoardTitle1.setText(buyBoard1.getTitle());
			    			    BuyBoardNickName1.setText(buyBoard1.getNicName());
			    			    BuyBoardTime1.setText(buyBoard1.getUploadDate());
			    			    // 두 번째 줄 정보 가져오기
			    			    Board buyBoard2 = buyLatestBoard.get(1);
			    			    BuyBoardTitle2.setText(buyBoard2.getTitle());
			    			    BuyBoardNickName2.setText(buyBoard2.getNicName());
			    			    BuyBoardTime2.setText(buyBoard2.getUploadDate());

			    			}
			    			
			    			List<Board> sellLatestBoard = dao.getLatestBoardList("판매 게시판");
			    			
			    			// 메인화면에 이미지 넣어주기
			    			int sellBoard1seq = sellLatestBoard.get(0).getNo();
			    			int sellBoard2seq = sellLatestBoard.get(1).getNo();
			    			List<Image> sellLatestImage1 = dao.getAllImages(sellBoard1seq);
			    			List<Image> sellLatestImage2 = dao.getAllImages(sellBoard2seq);
			    			ImageView sellImageView1 = (ImageView)root.lookup("#SellBoardImage1");
			    			ImageView sellIimageView2 = (ImageView)root.lookup("#SellBoardImage2");
			    			if(!(sellLatestImage1.isEmpty())) {
			    				sellImageView1.setImage(sellLatestImage1.get(0));
			    			}
			    			if(!(sellLatestImage2.isEmpty())) {
			    				sellIimageView2.setImage(sellLatestImage2.get(0));
			    			}
			    			
			    			Label SellBoardTitle1 = (Label)root.lookup("#SellBoardTitle1");
			    			Label SellBoardTitle2 = (Label)root.lookup("#SellBoardTitle2");
			    			Label SellBoardNickName1 = (Label)root.lookup("#SellBoardNickName1");
			    			Label SellBoardNickName2 = (Label)root.lookup("#SellBoardNickName2");
			    			Label SellBoardTime1 = (Label)root.lookup("#SellBoardTime1");
			    			Label SellBoardTime2 = (Label)root.lookup("#SellBoardTime2");
			    			
			    			if (sellLatestBoard.size() >= 2) {
			    			    // 첫 번째 줄 정보 가져오기
			    			    Board sellBoard1 = sellLatestBoard.get(0);
			    			    SellBoardTitle1.setText(sellBoard1.getTitle());
			    			    SellBoardNickName1.setText(sellBoard1.getNicName());
			    			    SellBoardTime1.setText(sellBoard1.getUploadDate());
			    			    // 두 번째 줄 정보 가져오기
			    			    Board sellBoard2 = sellLatestBoard.get(1);
			    			    SellBoardTitle2.setText(sellBoard2.getTitle());
			    			    SellBoardNickName2.setText(sellBoard2.getNicName());
			    			    SellBoardTime2.setText(sellBoard2.getUploadDate());

			    			}
			    			
			    			List<Board> sharingLatestBoard = dao.getLatestBoardList("나눔 게시판");
			    			
			    			// 메인화면에 이미지 넣어주기
			    			int sharingBoard1seq = sharingLatestBoard.get(0).getNo();
			    			int sharingBoard2seq = sharingLatestBoard.get(1).getNo();
			    			List<Image> sharingLatestImage1 = dao.getAllImages(sharingBoard1seq);
			    			List<Image> sharingLatestImage2 = dao.getAllImages(sharingBoard2seq);
			    			ImageView sharingImageView1 = (ImageView)root.lookup("#SharingBoardImage1");
			    			ImageView sharingImageView2 = (ImageView)root.lookup("#SharingBoardImage2");
			    			if(!(sharingLatestImage1.isEmpty())) {
			    				Image sharingImage1 = sharingLatestImage1.get(0);
			    				sharingImageView1.setImage(sharingImage1);
			    			}
			    			if(!(sharingLatestImage2.isEmpty())) {
			    				Image shairngImage2 = sharingLatestImage2.get(0);
			    				sharingImageView2.setImage(shairngImage2);
			    			}
			    			
			    			Label SharingBoardTitle1 = (Label) root.lookup("#SharingBoardTitle1");
			    			Label SharingBoardTitle2 = (Label) root.lookup("#SharingBoardTitle2");
			    			Label SharingBoardNickName1 = (Label) root.lookup("#SharingBoardNickName1");
			    			Label SharingBoardNickName2 = (Label) root.lookup("#SharingBoardNickName2");
			    			Label SharingBoardTime1 = (Label) root.lookup("#SharingBoardTime1");
			    			Label SharingBoardTime2 = (Label) root.lookup("#SharingBoardTime2");
			    			
			    			
			    			
			    			if (sharingLatestBoard.size() >= 2) {
			    			    // 첫 번째 줄 정보 가져오기
			    			    Board sharingBoard1 = sharingLatestBoard.get(0);
			    			    SharingBoardTitle1.setText(sharingBoard1.getTitle());
			    			    SharingBoardNickName1.setText(sharingBoard1.getNicName());
			    			    SharingBoardTime1.setText(sharingBoard1.getUploadDate());

			    			    // 두 번째 줄 정보 가져오기
			    			    Board sharingBoard2 = sharingLatestBoard.get(1);
			    			    SharingBoardTitle2.setText(sharingBoard2.getTitle());
			    			    SharingBoardNickName2.setText(sharingBoard2.getNicName());
			    			    SharingBoardTime2.setText(sharingBoard2.getUploadDate());
			    			}
			    			
			    			List<Board> freeLatestBoard = dao.getLatestBoardList("자유 게시판");
			    			
			    			// 메인화면에 이미지 넣어주기
			    			int freeBoard1seq = freeLatestBoard.get(0).getNo();
			    			int freeBoard2seq = freeLatestBoard.get(1).getNo();
			    			List<Image> freeLatestImage1 = dao.getAllImages(freeBoard1seq);
			    			List<Image> freeLatestImage2 = dao.getAllImages(freeBoard2seq);
			    			ImageView freeImageView1 = (ImageView)root.lookup("#FreeBoardImage1");
			    			ImageView freeImageView2 = (ImageView)root.lookup("#FreeBoardImage2");
			    			if(!(freeLatestImage1.isEmpty())) {
			    				Image freeImage1 = freeLatestImage1.get(0);
			    				freeImageView1.setImage(freeImage1);
			    			}
			    			if(!(freeLatestImage2.isEmpty())) {
			    				Image freeImage2 = freeLatestImage2.get(0);
			    				freeImageView2.setImage(freeImage2);
			    			}
			    			
			    			Label freeBoardTitle1 = (Label)root.lookup("#FreeBoardTitle1");
			    			Label freeBoardTitle2 = (Label)root.lookup("#FreeBoardTitle2");
			    			Label freeBoardNickName1 = (Label)root.lookup("#FreeBoardNickName1");
			    			Label freeBoardNickName2 = (Label)root.lookup("#FreeBoardNickName2");
			    			Label freeBoardTime1 = (Label)root.lookup("#FreeBoardTime1");
			    			Label freeBoardTime2 = (Label)root.lookup("#FreeBoardTime2");
			    			
			    			if (freeLatestBoard.size() >= 2) {
			    			    // 첫 번째 줄 정보 가져오기
			    			    Board freeBoard1 = freeLatestBoard.get(0);
			    			    freeBoardTitle1.setText(freeBoard1.getTitle());
			    			    freeBoardNickName1.setText(freeBoard1.getNicName());
			    			    freeBoardTime1.setText(freeBoard1.getUploadDate());
			    			    // 두 번째 줄 정보 가져오기
			    			    Board freeBoard2 = freeLatestBoard.get(1);
			    			    freeBoardTitle2.setText(freeBoard2.getTitle());
			    			    freeBoardNickName2.setText(freeBoard2.getNicName());
			    			    freeBoardTime2.setText(freeBoard2.getUploadDate());

			    			}

			    			// ScrollPane 설정
			    			ScrollPane scrollPane = new ScrollPane();
			    			scrollPane.setContent(root);
			    			scrollPane.setPannable(true);
			    			
			    			Scene scene = new Scene(scrollPane, 1010, 950);
			    			LoginView.setScene(scene);
			    			
			    			LoginView.setTitle("KG - Trading Comunity");
			    			LoginView.setResizable(false);
			    			LoginView.show();
			    			
	            		} catch (Exception e) {
	            			e.printStackTrace();
	            		}
	            		
	            		
	            		// 환영인사
	            		helloView(root, m);
	            		
	                } else {
	                    cs.customErrorView(root, "입력하신 정보와\n일치하는 정보가 없습니다.");
	                    id.clear();
	                    pw.clear();
	                    id.requestFocus();
	                }
	            } else {
	                cs.customErrorView(root, "입력하신 정보와\n일치하는 정보가 없습니다.");
	                id.clear();
	                pw.clear();
	                id.requestFocus();
	            }
	        }
	    } catch (Exception e) {
	        // Exception handling
	    }
	}


	// 로그아웃 버튼
	@Override
	public void logoutProc(Parent root) {
		// TODO Auto-generated method stub
		Stage s = (Stage) root.getScene().getWindow();
		s.close();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/main.fxml"));

		root = null;

		try {
			root = loader.load();
			s.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		bs.mainCombo(root);
		bs.createAllListView(root);

		s.setTitle("KG - Trading Comunity");
		s.show();
		s.setResizable(false);
	}
	
	// 회원가입 버튼
	@Override
	public void membershipProc(Parent root) {
		// TODO Auto-generated method stub
	}
	
	// 로그인시 환영인사
	@Override
	public void helloView(Parent root, Member m) {
		// TODO Auto-generated method stub
		Stage loginHello = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/hello.fxml"));

		root = null;

		try {
			root = loader.load();
			loginHello.setScene(new Scene(root));
			
			Label helloNicName = (Label) root.lookup("#helloNicName");
			helloNicName.setText("*** "+m.getNickName()+" ***");
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		loginHello.setTitle("Login");
		loginHello.setResizable(false);
		loginHello.initModality(Modality.APPLICATION_MODAL);
		loginHello.setAlwaysOnTop(true);
		loginHello.showAndWait();
	}

}
