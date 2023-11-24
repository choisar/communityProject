package all.button.boardButton;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import all.Controller;
import all.Member;
import all.boardService.Board;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import all.boardService.BoardService;
import all.boardService.BoardServiceImp;
import all.boardService.boardViewService;
import all.boardService.boardViewServiceImp;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.boardService.tableViewService.TableViewService;
import all.boardService.tableViewService.TableViewServiceImp;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class boardButtonImp implements boardButton {

	DatabaseDAO dao;
	CommonService cs = new CommonServiceImp();
	BoardService bs = new BoardServiceImp();
	TableViewService tvs = new TableViewServiceImp();
	boardViewService bvs = new boardViewServiceImp();

	public boardButtonImp() {
		dao = new DatabaseDAOImp();
	}

	// 검색 게시판 버튼 - 메인화면 게시물 검색 버튼
	@Override
	public void searchProc(Parent root) {
		// TODO Auto-generated method stub

		// 회원인 지 비회원인 지 확인하는 레이블
		Label logChk = (Label) root.lookup("#logChk");

		try {
			// 콤보박스 - 카테고리값
			String text1 = bs.mainCombo(root);

			// 텍스트 필드 - 검색내용
			TextField searchBoard = (TextField) root.lookup("#boardNameSearch");
			String text2 = searchBoard.getText();

			// 카테고리 선택 X, 검색내용 X
			if (text1 == null && text2.isEmpty()) {
				cs.customErrorView(root, "검색할 내용이 없습니다.");
				// 카테고리 선택만 X
			} else if (text1 == null && !text2.isEmpty()) {
				cs.customErrorView(root, "카테고리를 선택하세요.");
				// 검색내용만 X
			} else if (text2.isEmpty() && text1 != null) {
				cs.customErrorView(root, "검색할 내용을 입력하세요.");
			} else if (!(text1 == null && text2.isEmpty())) {

				// 입력받은 콤보박스 값을 dao에 있는 열(컬럼)이름으로 변경
				if (text1.equals("제목")) {
					text1 = "title";
				} else if (text1.equals("닉네임")) {
					text1 = "board_memnick";
				} else if (text1.equals("카테고리")) {
					text1 = "category";
				}

				// 비회원일 때
				if (logChk.getText().equals("비회원")) {
					cs.errorView1(root);
					// 회원일 때
				} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
					Label memIdLabel = (Label) root.lookup("#memberId");
					String memId = memIdLabel.getText().toString();
					// 새창 띄우기 값 true 일 때
					if (bs.chk1(root)) {
						bvs.searchResultBoardView(root, text1, text2, memId);
					// 새창 띄우기 값 false 일 때
					} else {
						TableView<Board> listView = (TableView<Board>) root.lookup("#ListView");
						listView.getItems().clear();
						listView.getColumns().clear();
						List<Board> boardList = dao.searchResultAll(text1, text2);
						if(boardList.isEmpty()) {
							cs.customErrorView(root, "검색결과가 없습니다.");
						} else {
							Board b = listView.getSelectionModel().getSelectedItem();
							tvs.configureBoardTableView(listView, memId);
							listView.setItems(FXCollections.observableArrayList(boardList));
						}
					}
				}
			}
		} catch (NullPointerException e) {
			// TODO: handle exception
		} catch (Exception e) {
			System.out.println("기타오류 - 관리자에게 문의하세요.");
		}
	}

	// 전체 게시판 버튼
	@Override
	public void allBoardProc(Parent root) {
		Label logChk = (Label) root.lookup("#logChk");
		

		if (logChk.getText().equals("비회원")) {
			cs.errorView1(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			Label memIdLabel = (Label) root.lookup("#memberId");
			String memId = memIdLabel.getText().toString();
			if (bs.chk1(root)) {
				bvs.allBoardView(root, memId);
			} else {
				tvs.loadAllBoardListView(root, memId);
			}
		}
	}

	// 자유 게시판 버튼
	@Override
	public void freeBoardProc(Parent root) {
		Label logChk = (Label) root.lookup("#logChk");

		if (logChk.getText().equals("비회원")) {
			cs.errorView1(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			Label memIdLabel = (Label) root.lookup("#memberId");
			String memId = memIdLabel.getText().toString();
			if (bs.chk1(root)) {
				bvs.freeBoardView(root, memId);
			} else {
				tvs.handleBoardView(root, logChk, "자유 게시판", memId);
			}
		}
	}

	// 구매 게시판 버튼
	@Override
	public void buyBoardProc(Parent root) {
		Label logChk = (Label) root.lookup("#logChk");

		if (logChk.getText().equals("비회원")) {
			cs.errorView1(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			Label memIdLabel = (Label) root.lookup("#memberId");
			String memId = memIdLabel.getText().toString();
			if (bs.chk1(root)) {
				bvs.buyBoardView(root, memId);
			} else {
				tvs.handleBoardView(root, logChk, "구매 게시판", memId);

			}
		}
	}

	// 판매 게시판 버튼
	@Override
	public void sellBoardProc(Parent root) {
		Label logChk = (Label) root.lookup("#logChk");

		if (logChk.getText().equals("비회원")) {
			cs.errorView1(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			Label memIdLabel = (Label) root.lookup("#memberId");
			String memId = memIdLabel.getText().toString();
			if (bs.chk1(root)) {
				bvs.sellBoardView(root, memId);
			} else {
				tvs.handleBoardView(root, logChk, "판매 게시판", memId);
			}
		}
	}

	// 나눔 게시판 버튼
	@Override
	public void sharingBoardProc(Parent root) {
		Label logChk = (Label) root.lookup("#logChk");

		if (logChk.getText().equals("비회원")) {
			cs.errorView1(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			Label memIdLabel = (Label) root.lookup("#memberId");
			String memId = memIdLabel.getText().toString();
			if (bs.chk1(root)) {
				bvs.sharingBoardView(root, memId);
			} else {
				tvs.handleBoardView(root, logChk, "나눔 게시판", memId);
			}
		}
	}

	// Q&A 게시판 버튼
	@Override
	public void QAProc(Parent root) {
		Label logChk = (Label) root.lookup("#logChk");

		if (logChk.getText().equals("비회원")) {
			cs.errorView1(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			Label memIdLabel = (Label) root.lookup("#memberId");
			String memId = memIdLabel.getText().toString();
			if (bs.chk1(root)) {
				bvs.QABoardView(root, memId);
			} else {
				tvs.handleBoardView(root, logChk, "QA 게시판", memId);
			}
		}
	}

	// 글쓰기 버튼
	@Override
	public void writingProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk = (Label) root.lookup("#logChk");

		if (logChk.getText().equals("비회원")) {
			cs.errorView1(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			bvs.uploadBoardView(root);
		}
	}

	// 신고 버튼
	@Override
	public void reportProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk = (Label) root.lookup("#logChk");

		if (logChk.getText().equals("비회원")) {
			cs.errorView1(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			bvs.reportView(root);
		}
	}

	// 신고화면 검색 버튼
	@Override
	public void reportSearchProc(Parent root) {
		// TODO Auto-generated method stub

		try {
			// 콤보박스 - 카테고리값
			String text1 = bs.reportCombo1(root);

			// 텍스트 필드 - 검색내용
			TextField searchText = (TextField) root.lookup("#reportSearchText");
			String text2 = searchText.getText();

			// 카테고리 선택 X, 검색내용 X
			if (text1 == null && text2.isEmpty()) {
				cs.errorView4(root);
				// 카테고리 선택만 X
			} else if (text1 == null && !text2.isEmpty()) {
				cs.errorView3(root);
				// 검색내용만 X
			} else if (text2.isEmpty() && text1 != null) {
				cs.errorView2(root);
			} else if (!(text1 == null && text2.isEmpty())) {

				if (text1.equals("게시물 이름")) {
					text1 = "title";
				} else if (text1.equals("닉네임")) {
					text1 = "member_nickname";
				} else if (text1.equals("아이디")) {
					text1 = "member_id";
				}

				if (text1 == "title") {
					TableView<Board> titleReportListView = (TableView<Board>) root.lookup("#reportTable");
					titleReportListView.getItems().clear();
					titleReportListView.getColumns().clear();
					List<Board> titleReportList = dao.searchResultAll(text1, text2);

					if (titleReportList != null) {
						tvs.configureBoardTableView1(titleReportListView, root);
						titleReportListView.setItems(FXCollections.observableArrayList(titleReportList));
					} else {
						cs.customErrorView(root, "Unable to get bulletin board list.");
					}
				} else if (text1 == "member_nickname") {
					TableView<Member> nickNameReportListView = (TableView<Member>) root.lookup("#reportTable");
					nickNameReportListView.getItems().clear();
					nickNameReportListView.getColumns().clear();
					List<Member> nickNameReportList = dao.reportSearchResultAll2(text1, text2);

					if (nickNameReportList != null) {
						tvs.configureBoardTableView2(nickNameReportListView, root);
						nickNameReportListView.setItems(FXCollections.observableArrayList(nickNameReportList));
					} else {
						cs.customErrorView(root, "Unable to get bulletin board list.");
					}
				} else if (text1 == "member_id") {
					TableView<Member> idReportListView = (TableView<Member>) root.lookup("#reportTable");
					idReportListView.getItems().clear();
					idReportListView.getColumns().clear();
					List<Member> idReportList = dao.reportSearchResultAll2(text1, text2);

					if (idReportList != null) {
						tvs.configureBoardTableView3(idReportListView, root);
						idReportListView.setItems(FXCollections.observableArrayList(idReportList));
					} else {
						cs.customErrorView(root, "Unable to get bulletin board list.");
					}
				}

			}

		} catch (NullPointerException e) {
			// TODO: handle exception
		} catch (Exception e) {
			System.out.println("기타오류 - 관리자에게 문의하세요.");
		}
	}

	// 게시하기 버튼
	@Override
	public void uploadProc(Parent root) throws Exception {
		// TODO Auto-generated method stub
		boolean postSuc = false;
		boolean imgSuc = false;
		TextField title = (TextField) root.lookup("#txtTitle");
		TextArea contents = (TextArea) root.lookup("#txtContents");
		TextArea imgAddr = (TextArea) root.lookup("#imgAddr");
		ComboBox<String> cmbCateg = (ComboBox<String>) root.lookup("#cmbCateg");

		if (title.getText().isEmpty()) {
			cs.customErrorView(root, "제목을 입력하세요.");
			title.requestFocus();
			return;
		} else if (contents.getText().isEmpty()) {
			cs.customErrorView(root, "내용을 입력하세요.");
			contents.requestFocus();
			return;
		}

		Board b = new Board();
		b.setTitle(title.getText());
		b.setContents(contents.getText());

		if (cmbCateg.getValue() == null) {
			cs.customErrorView(root, "카테고리를 선택하세요.");
			cmbCateg.requestFocus();
			return;
		} else {
			switch (cmbCateg.getValue()) {
			case "자유 게시판":
				b.setCategori("자유 게시판");
				break;
			case "구매 게시판":
				b.setCategori("구매 게시판");
				break;
			case "판매 게시판":
				b.setCategori("판매 게시판");
				break;
			case "나눔 게시판":
				b.setCategori("나눔 게시판");
				break;
			}
			
			
		}

		postSuc = dao.uploadBoard(b);
		all.boardService.ImagePath ip = new all.boardService.ImagePath();

		if (postSuc) {
			if (imgAddr.getText().equals("") || imgAddr.getText().equals("blank")) {
				imgSuc = true;
			} else {
				byte[] imageBytes = null;
				String[] imgPaths = imgAddr.getText().split("\\n");
				for (String i : imgPaths) {
					ip = new all.boardService.ImagePath();
					imageBytes = convertImageToBytes(i);
					ip.setImageByte(imageBytes);
					imgSuc = dao.uploadImg(ip, b);
					if (!imgSuc) {
						dao.imgDelete(ip);
						imgAddr.clear();
					}
				}
			}
		}	

		if (postSuc && imgSuc) {
			
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/userLogin1.fxml"));
		    Parent root1 = loader.load();

		    // 새로운 TableView 객체 가져오기
		    TableView<Board> bb = (TableView<Board>) root1.lookup("#ListView");

		    // 새로운 데이터를 가져오는 예시
		    List<Board> updatedData = dao.selectAll(); // DAO에서 모든 게시글을 가져오는 메서드 호출

		    // 기존 테이블의 아이템들을 지우고, 새로운 데이터로 갱신
		    bb.getItems().clear();
		    bb.getItems().addAll(updatedData);
		    bb.getItems().add(b);
		    bb.refresh();
			
			cs.customErrorView(root, "게시글 작성 완료");

	        Stage s = (Stage) root.getScene().getWindow();
	        s.close();
		} else {
			cs.customErrorView(root, "게시글 작성 실패");
			dao.imgDelete(ip);
		}

	}

	// 파일선택 버튼
	@Override
	public List<String> fileUpload(Parent root) {
		// TODO Auto-generated method stub
		TextArea imgAddr = (TextArea) root.lookup("#imgAddr");
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		List<File> selectedFiles = fileChooser.showOpenMultipleDialog(root.getScene().getWindow());
		if (selectedFiles != null && !selectedFiles.isEmpty()) {
			List<String> filePaths = new ArrayList<>();
			for (File file : selectedFiles) {
				filePaths.add(file.getPath());
			}
			imgAddr.setText(String.join("\n", filePaths));
			return filePaths;
		} else {
			imgAddr.setText("blank");
		}
		return null;
	}

	// 이미지를 바이트화 시키는 함수
	public byte[] convertImageToBytes(String imgAddr) throws Exception {
		// TODO Auto-generated method stub
		File imageFile = new File(imgAddr);
		byte[] imageBytes = new byte[(int) imageFile.length()];

		try (FileInputStream fis = new FileInputStream(imageFile)) {
			fis.read(imageBytes);
		} catch (Exception e) {
			cs.msgBox("ERROR", "이미지 바이트화 실패", "boardButtonImp - convertImageToBytes");
			e.printStackTrace();
		}

		return imageBytes;
	}

	// 테스트 버튼
	public void testProc(Parent root) {
		// TODO Auto-generated method stub
		Stage testView = new Stage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/test1.fxml"));
		
		root = null;
		
		try {
			root = loader.load();
			testView.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);
		
		TextField t1 = (TextField)root.lookup("#text1");
		TextField t2 = (TextField)root.lookup("#text2");
		TextField t3 = (TextField)root.lookup("#text3");
		TextField t4 = (TextField)root.lookup("#text4");
		TextField t5 = (TextField)root.lookup("#text5");
		TextField t6 = (TextField)root.lookup("#text6");
		
		int str1 = Integer.parseInt(t5.getText());
		int str2 = Integer.parseInt(t6.getText());
		
		
		if(str1 >= 0 || str1 <= 12) {
			System.err.println("1 ~ 12 사이의 값을 입력해주세요");
			t5.requestFocus();
			t5.clear();
		}
		
		if(str2 >= 0 || str2 <= 31) {
			System.err.println("1 ~ 31 사이의 값을 입력해주세요");
			t6.requestFocus();
			t6.clear();
		}
		
		
		
		// t1 길이 제한 및 포커스 이동
		t1.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue.length() > 4) {
		        t1.setText(oldValue); // 길이가 4를 초과하면 이전 값으로 되돌림
		    } else if (newValue.length() == 4) {
		        t2.requestFocus(); // 길이가 4이면 다음 텍스트 필드로 포커스 이동
		    }
		});

		// t2 길이 제한 및 포커스 이동
		t2.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue.length() > 4) {
		        t2.setText(oldValue); // 길이가 4를 초과하면 이전 값으로 되돌림
		    } else if (newValue.length() == 4) {
		        t3.requestFocus(); // 길이가 4이면 다음 텍스트 필드로 포커스 이동
		    }
		});

		// t3 길이 제한 및 포커스 이동
		t3.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue.length() > 4) {
		        t3.setText(oldValue); // 길이가 4를 초과하면 이전 값으로 되돌림
		    } else if (newValue.length() == 4) {
		        t4.requestFocus(); // 길이가 4이면 다음 텍스트 필드로 포커스 이동
		    }
		});

		// t4 길이 제한 및 포커스 이동
		t4.textProperty().addListener((observable, oldValue, newValue) -> {
		    if (newValue.length() > 4) {
		        t4.setText(oldValue); // 길이가 4를 초과하면 이전 값으로 되돌림
		    } else if (newValue.length() == 4) {
		        t1.requestFocus(); // 길이가 4이면 다음 텍스트 필드로 포커스 이동
		    }
		});
		
		testView.setTitle("Test");
		testView.setResizable(false);
		testView.show();
	}

	// 보드 디테일뷰에서 카테고리, 리스트 누르면 해당 카테고리 게시판 창 띄우는 버튼
	@Override
	public void categoryBoardProc(Parent root) {
		Label categoryChk = (Label) root.lookup("#CategoryText");
		
		FXMLLoader detailLoader = new FXMLLoader(getClass().getResource("../../fxml/userLogin1.fxml"));
		
		Parent root1 = null;
		
		String memId = null;
		
		try {
			root1 = detailLoader.load();
			Controller ctrl = detailLoader.getController();
			ctrl.setRoot(root);
			Label memIdLabel = (Label) root1.lookup("#memberId");
			memId = memIdLabel.getText().toString();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
		if (categoryChk.getText().equals("전체 게시판 >")) {
			bvs.allBoardView(root, memId);
		} else if (categoryChk.getText().equals("자유 게시판 >")) {
			bvs.freeBoardView(root, memId);
		} else if (categoryChk.getText().equals("구매 게시판 >")) {
			bvs.buyBoardView(root, memId);
		} else if (categoryChk.getText().equals("판매 게시판 >")) {
			bvs.sellBoardView(root, memId);
		} else if (categoryChk.getText().equals("나눔 게시판 >")) {
			bvs.sharingBoardView(root, memId);
		} else if (categoryChk.getText().equals("QA 게시판 >")) {
			bvs.QABoardView(root, memId);
		}
	}

	// 보드 디테일뷰에서 다음 게시물(next ->) 버튼을 누르면 다음 게시물로 가는 버튼
	@Override
	public void NextProc(Parent root) {
		Label LabelPostNum = (Label) root.lookup("#PostNumber");
		Label LabelCategory = (Label) root.lookup("#CategoryText");

		// LabelPostNum을 가져오면 숫자만 안가져오고 Post Number.## 으로 Post Number까지 가져와버려서 . 기준으로
		// 나눠서 숫자만 가져오게 했어요
		String input = LabelPostNum.getText();
		// . 기준으로 나누기
		String[] parts = input.split("\\.");

		String StrpostNum = parts[1];
		String category = LabelCategory.getText();

		bvs.loadNextBoardInCategoryView(root, StrpostNum, category, "ASC");
	}

	// 보드 디테일뷰에서 이전 게시물(Prev <-) 버튼을 누르면 다음 게시물로 가는 버튼
	@Override
	public void PrevProc(Parent root) {
		Label LabelPostNum = (Label) root.lookup("#PostNumber");
		Label LabelCategory = (Label) root.lookup("#CategoryText");

		// LabelPostNum을 가져오면 숫자만 안가져오고 Post Number.## 으로 Post Number까지 가져와버려서 . 기준으로
		// 나눠서 숫자만 가져오게 했어요
		String input = LabelPostNum.getText();
		// . 기준으로 나누기
		String[] parts = input.split("\\.");

		String StrpostNum = parts[1];
		String category = LabelCategory.getText();

		bvs.loadNextBoardInCategoryView(root, StrpostNum, category, "DESC");
	}
	
	public void RefreshProc(Parent root) {
		
	    // 테이블 뷰가 root 안에 있다고 가정하고 root에서 해당 테이블 뷰를 찾아옵니다.
	    TableView<Board> tableView = (TableView<Board>) root.lookup("#ListView");

	    if (tableView != null) {
	        // 데이터를 업데이트하는 작업을 수행하고 테이블 뷰를 새로 고칩니다.
	        tableView.getItems().clear(); // 기존 항목 제거
	        // 새로운 데이터를 가져오거나 데이터 소스에서 새로운 값을 로드하는 등의 작업을 수행합니다.
            List<Board> b = dao.selectAll();
	        // 이후 테이블 뷰에 변경된 데이터를 추가합니다.
	        tableView.getItems().addAll(b);
	    }
	    Label insertIdLabel = (Label) root.lookup("#memberId");
	    String insertId = insertIdLabel.getText().replaceAll("\"", "");
	    
	    Member m = dao.memberInfo(insertId);
	    
	    Label memberName = (Label) root.lookup("#memberName");
        memberName.setText("\"" + m.getName().toString() + "\"");
        
        Label memberId = (Label) root.lookup("#memberId");
        memberId.setText("\"" + m.getId() + "\"");
        
        updateUI(root);
        
		
	}
	
	public void updateUI(Parent root) {
		// 구매 게시판, 판매 게시판, 나눔 게시판, 자유 게시판의 최신글 각각 2개씩 정보 가져옴
		List<Board> buyLatestBoard = dao.getLatestBoardList("구매 게시판");
		
		// 메인화면에 이미지 넣어주기
		if(!(buyLatestBoard.isEmpty())) {
			int buyBoard1seq = buyLatestBoard.get(0).getNo();
			int buyBoard2seq = buyLatestBoard.get(1).getNo();
			int buyBoard3seq = buyLatestBoard.get(2).getNo();
			List<Image> buyLatestImage1 = dao.getAllImages(buyBoard1seq);
			List<Image> buyLatestImage2 = dao.getAllImages(buyBoard2seq);
			List<Image> buyLatestImage3 = dao.getAllImages(buyBoard3seq);
			ImageView image1 = (ImageView)root.lookup("#BuyBoardImage1");
			ImageView image2 = (ImageView)root.lookup("#BuyBoardImage2");
			ImageView image3 = (ImageView)root.lookup("#BuyBoardImage3");
			
			if(buyLatestImage1 != null) {
				image1.setImage(buyLatestImage1.get(0));
			} else {
				image1.setImage(null);
			}
			if(buyLatestImage2 != null) {
				image2.setImage(buyLatestImage2.get(0));
			}else {
				image2.setImage(null);
			}
			if(buyLatestImage3 != null) {
				image3.setImage(buyLatestImage3.get(0));
			}else {
				image3.setImage(null);
			}
			
			Label BuyBoardTitle1 = (Label)root.lookup("#BuyBoardTitle1");
			Label BuyBoardTitle2 = (Label)root.lookup("#BuyBoardTitle2");
			Label BuyBoardTitle3 = (Label)root.lookup("#BuyBoardTitle3");
			Label BuyBoardNickName1 = (Label)root.lookup("#BuyBoardNickName1");
			Label BuyBoardNickName2 = (Label)root.lookup("#BuyBoardNickName2");
			Label BuyBoardNickName3 = (Label)root.lookup("#BuyBoardNickName3");
			Label BuyBoardTime1 = (Label)root.lookup("#BuyBoardTime1");
			Label BuyBoardTime2 = (Label)root.lookup("#BuyBoardTime2");
			Label BuyBoardTime3 = (Label)root.lookup("#BuyBoardTime3");
			
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
				// 세 번째 줄 정보 가져오기
				Board buyBoard3 = buyLatestBoard.get(2);
				BuyBoardTitle3.setText(buyBoard3.getTitle());
				BuyBoardNickName3.setText(buyBoard3.getNicName());
				BuyBoardTime3.setText(buyBoard3.getUploadDate());
				
			}
		}
		
		List<Board> sellLatestBoard = dao.getLatestBoardList("판매 게시판");
		
		// 메인화면에 이미지 넣어주기
		if(!(sellLatestBoard.isEmpty())) {
			int sellBoard1seq = sellLatestBoard.get(0).getNo();
			int sellBoard2seq = sellLatestBoard.get(1).getNo();
			int sellBoard3seq = sellLatestBoard.get(2).getNo();
			List<Image> sellLatestImage1 = dao.getAllImages(sellBoard1seq);
			List<Image> sellLatestImage2 = dao.getAllImages(sellBoard2seq);
			List<Image> sellLatestImage3 = dao.getAllImages(sellBoard3seq);
			ImageView sellImageView1 = (ImageView)root.lookup("#SellBoardImage1");
			ImageView sellIimageView2 = (ImageView)root.lookup("#SellBoardImage2");
			ImageView sellIimageView3 = (ImageView)root.lookup("#SellBoardImage3");
			
			if(sellLatestImage1 != null) {
				sellImageView1.setImage(sellLatestImage1.get(0));
			} else {
				sellImageView1.setImage(null);
			}
			if(sellLatestImage2 != null) {
				sellIimageView2.setImage(sellLatestImage2.get(0));
			} else {
				sellIimageView2.setImage(null);
			}
			if(sellLatestImage3 != null) {
				sellIimageView3.setImage(sellLatestImage3.get(0));
			} else {
				sellIimageView3.setImage(null);
			}
			
			Label SellBoardTitle1 = (Label)root.lookup("#SellBoardTitle1");
			Label SellBoardTitle2 = (Label)root.lookup("#SellBoardTitle2");
			Label SellBoardTitle3 = (Label)root.lookup("#SellBoardTitle3");
			Label SellBoardNickName1 = (Label)root.lookup("#SellBoardNickName1");
			Label SellBoardNickName2 = (Label)root.lookup("#SellBoardNickName2");
			Label SellBoardNickName3 = (Label)root.lookup("#SellBoardNickName3");
			Label SellBoardTime1 = (Label)root.lookup("#SellBoardTime1");
			Label SellBoardTime2 = (Label)root.lookup("#SellBoardTime2");
			Label SellBoardTime3 = (Label)root.lookup("#SellBoardTime3");
			
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
				// 세 번째 줄 정보 가져오기
				Board sellBoard3 = sellLatestBoard.get(2);
				SellBoardTitle3.setText(sellBoard3.getTitle());
				SellBoardNickName3.setText(sellBoard3.getNicName());
				SellBoardTime3.setText(sellBoard3.getUploadDate());
				
			}
		}
		
		List<Board> sharingLatestBoard = dao.getLatestBoardList("나눔 게시판");
		
		if(!(sharingLatestBoard.isEmpty())) {
			// 메인화면에 이미지 넣어주기
			int sharingBoard1seq = sharingLatestBoard.get(0).getNo();
			int sharingBoard2seq = sharingLatestBoard.get(1).getNo();
			int sharingBoard3seq = sharingLatestBoard.get(2).getNo();
			List<Image> sharingLatestImage1 = dao.getAllImages(sharingBoard1seq);
			List<Image> sharingLatestImage2 = dao.getAllImages(sharingBoard2seq);
			List<Image> sharingLatestImage3 = dao.getAllImages(sharingBoard3seq);
			ImageView sharingImageView1 = (ImageView)root.lookup("#SharingBoardImage1");
			ImageView sharingImageView2 = (ImageView)root.lookup("#SharingBoardImage2");
			ImageView sharingImageView3 = (ImageView)root.lookup("#SharingBoardImage3");
			
			if(sharingLatestImage1 != null) {
				sharingImageView1.setImage(sharingLatestImage1.get(0));
			} else {
				sharingImageView1.setImage(null);
			}
			if(sharingLatestImage2 != null) {
				sharingImageView2.setImage(sharingLatestImage2.get(0));
			} else {
				sharingImageView2.setImage(null);
			}
			if(sharingLatestImage3 != null) {
				sharingImageView3.setImage(sharingLatestImage3.get(0));
			} else {
				sharingImageView3.setImage(null);
			}
			
			Label SharingBoardTitle1 = (Label) root.lookup("#SharingBoardTitle1");
			Label SharingBoardTitle2 = (Label) root.lookup("#SharingBoardTitle2");
			Label SharingBoardTitle3 = (Label) root.lookup("#SharingBoardTitle3");
			Label SharingBoardNickName1 = (Label) root.lookup("#SharingBoardNickName1");
			Label SharingBoardNickName2 = (Label) root.lookup("#SharingBoardNickName2");
			Label SharingBoardNickName3 = (Label) root.lookup("#SharingBoardNickName3");
			Label SharingBoardTime1 = (Label) root.lookup("#SharingBoardTime1");
			Label SharingBoardTime2 = (Label) root.lookup("#SharingBoardTime2");
			Label SharingBoardTime3 = (Label) root.lookup("#SharingBoardTime3");
			
			
			
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
				// 세 번째 줄 정보 가져오기
				Board sharingBoard3 = sharingLatestBoard.get(2);
				SharingBoardTitle3.setText(sharingBoard3.getTitle());
				SharingBoardNickName3.setText(sharingBoard3.getNicName());
				SharingBoardTime3.setText(sharingBoard3.getUploadDate());
			}
		}
		
		List<Board> freeLatestBoard = dao.getLatestBoardList("자유 게시판");
		
		// 메인화면에 이미지 넣어주기
		if(!(freeLatestBoard.isEmpty())) {
			int freeBoard1seq = freeLatestBoard.get(0).getNo();
			int freeBoard2seq = freeLatestBoard.get(1).getNo();
			int freeBoard3seq = freeLatestBoard.get(2).getNo();
			List<Image> freeLatestImage1 = dao.getAllImages(freeBoard1seq);
			List<Image> freeLatestImage2 = dao.getAllImages(freeBoard2seq);
			List<Image> freeLatestImage3 = dao.getAllImages(freeBoard3seq);
			ImageView freeImageView1 = (ImageView)root.lookup("#FreeBoardImage1");
			ImageView freeImageView2 = (ImageView)root.lookup("#FreeBoardImage2");
			ImageView freeImageView3 = (ImageView)root.lookup("#FreeBoardImage3");
			
			if(freeLatestImage1 != null) {
				freeImageView1.setImage(freeLatestImage1.get(0));
			} else {
				freeImageView1.setImage(null);
			}
			if(freeLatestImage2 != null) {
				freeImageView2.setImage(freeLatestImage2.get(0));
			} else {
				freeImageView2.setImage(null);
			}
			if(freeLatestImage3 != null) {
				freeImageView3.setImage(freeLatestImage3.get(0));
			} else {
				freeImageView3.setImage(null);
			}
			
			Label freeBoardTitle1 = (Label)root.lookup("#FreeBoardTitle1");
			Label freeBoardTitle2 = (Label)root.lookup("#FreeBoardTitle2");
			Label freeBoardTitle3 = (Label)root.lookup("#FreeBoardTitle3");
			Label freeBoardNickName1 = (Label)root.lookup("#FreeBoardNickName1");
			Label freeBoardNickName2 = (Label)root.lookup("#FreeBoardNickName2");
			Label freeBoardNickName3 = (Label)root.lookup("#FreeBoardNickName3");
			Label freeBoardTime1 = (Label)root.lookup("#FreeBoardTime1");
			Label freeBoardTime2 = (Label)root.lookup("#FreeBoardTime2");
			Label freeBoardTime3 = (Label)root.lookup("#FreeBoardTime3");
			
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
				// 세 번째 줄 정보 가져오기
				Board freeBoard3 = freeLatestBoard.get(2);
				freeBoardTitle3.setText(freeBoard3.getTitle());
				freeBoardNickName3.setText(freeBoard3.getNicName());
				freeBoardTime3.setText(freeBoard3.getUploadDate());
				
			}
		}
		
	}
	

}
