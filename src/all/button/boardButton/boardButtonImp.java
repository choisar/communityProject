package all.button.boardButton;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
			cs.customErrorView(root, "Please enter a title.");
			title.requestFocus();
			return;
		} else if (contents.getText().isEmpty()) {
			cs.customErrorView(root, "Please enter the text.");
			contents.requestFocus();
			return;
		}

		Board b = new Board();
		b.setTitle(title.getText());
		b.setContents(contents.getText());

		if (cmbCateg.getValue() == null) {
			cs.customErrorView(root, "Please select a category.");
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
			cs.customErrorView(root, "Posting completed");
			Stage s = (Stage) root.getScene().getWindow();
			s.close();
		} else {
			cs.customErrorView(root, "Posting faild");
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
		Label memIdLabel = (Label) root.lookup("#memberId");
		String memId = memIdLabel.getText().toString();
		
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
