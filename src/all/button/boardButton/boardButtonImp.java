package all.button.boardButton;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

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
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class boardButtonImp implements boardButton {

	DatabaseDAO dao = new DatabaseDAOImp();
	BoardService bs = new BoardServiceImp();
	CommonService cs = new CommonServiceImp();
	boardViewService bvs = new boardViewServiceImp();
	TableViewService tvs = new TableViewServiceImp();

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
				cs.errorView4(root);
				// 카테고리 선택만 X
			} else if (text1 == null && !text2.isEmpty()) {
				cs.errorView3(root);
				// 검색내용만 X
			} else if (text2.isEmpty() && text1 != null) {
				cs.errorView2(root);
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
					// 새창 띄우기 값 true 일 때
					if (bs.chk1(root)) {
						bvs.searchResultBoardView(root, text1, text2);
					// 새창 띄우기 값 false 일 때
					} else {
						TableView<Board> listView = (TableView<Board>) root.lookup("#ListView");
						listView.getItems().clear();
						listView.getColumns().clear();
						List<Board> boardList = dao.searchResultAll(text1, text2);

						if (boardList != null) {
							tvs.configureBoardTableView(listView);
							listView.setItems(FXCollections.observableArrayList(boardList));
						} else {
							System.out.println("게시판 목록을 가져올 수 없습니다.");
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
	        if (bs.chk1(root)) {
	            bvs.allBoardView(root);
	        } else {
	            tvs.loadAllBoardListView(root);
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
	        if (bs.chk1(root)) {
	            bvs.freeBoardView(root);
	        } else {
	        	tvs.handleBoardView(root, logChk, "자유 게시판");
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
	        if (bs.chk1(root)) {
	            bvs.buyBoardView(root);
	        } else {
	        	tvs.handleBoardView(root, logChk, "구매 게시판");
	        	
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
	        if (bs.chk1(root)) {
	            bvs.sellBoardView(root);
	        } else {
	        	tvs.handleBoardView(root, logChk, "판매 게시판");
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
	        if (bs.chk1(root)) {
	            bvs.sharingBoardView(root);
	        } else {
	        	tvs.handleBoardView(root, logChk, "나눔 게시판");
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
			String text1 = bs.reportCombo(root);
			
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
						tvs.configureBoardTableView1(titleReportListView);
						titleReportListView.setItems(FXCollections.observableArrayList(titleReportList));
					} else {
						System.out.println("게시판 목록을 가져올 수 없습니다.");
					}
				} else if(text1 == "member_nickname") {
					TableView<Member> nickNameReportListView = (TableView<Member>) root.lookup("#reportTable");
					nickNameReportListView.getItems().clear();
					nickNameReportListView.getColumns().clear();
					List<Member> nickNameReportList = dao.reportSearchResultAll2(text1, text2);
					
					if (nickNameReportList != null) {
						tvs.configureBoardTableView2(nickNameReportListView);
						nickNameReportListView.setItems(FXCollections.observableArrayList(nickNameReportList));
					} else {
						System.out.println("게시판 목록을 가져올 수 없습니다.");
					}
				} else if(text1 == "member_id") {
					TableView<Member> idReportListView = (TableView<Member>) root.lookup("#reportTable");
					idReportListView.getItems().clear();
					idReportListView.getColumns().clear();
					List<Member> idReportList = dao.reportSearchResultAll2(text1, text2);
					
					if (idReportList != null) {
						tvs.configureBoardTableView3(idReportListView);
						idReportListView.setItems(FXCollections.observableArrayList(idReportList));
					} else {
						System.out.println("게시판 목록을 가져올 수 없습니다.");
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
		TextField title = (TextField) root.lookup("#txtTitle");
		TextArea contents = (TextArea) root.lookup("#txtContents");
		TextArea imgAddr = (TextArea) root.lookup("#imgAddr");
		ComboBox<String> cmbCateg = (ComboBox<String>) root.lookup("#cmbCateg");

		if (title.getText().isEmpty()) {
			cs.msgBox("입력", "입력 오류", "제목을 입력하세요");
			title.requestFocus();
			return;
		} else if (contents.getText().isEmpty()) {
			cs.msgBox("입력", "입력 오류", "본문을 입력하세요");
			contents.requestFocus();
			return;
		}

		Board b = new Board();
		b.setTitle(title.getText());
		b.setContents(contents.getText());

		if (cmbCateg.getValue() == null) {
			cs.msgBox("입력", "입력오류", "카테고리를 선택해주세요.");
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

		DatePicker UploadDate = (DatePicker) root.lookup("#UploadDate");
		LocalDate date = UploadDate.getValue();
		Date d = Date.valueOf(date);

		b.setUploadDate(d.toString());

		byte[] imageBytes = null;
		if (imgAddr != null) {
			imageBytes = convertImageToBytes((String) imgAddr.getText());
			b.setImagePath(imageBytes);
		}

		if (dao.uploadBoard(b)) {
			System.out.println("게시 완료");
			Stage s = (Stage) root.getScene().getWindow();
			s.close();
		} else {
			System.out.println("게시 실패");
		}

	}

	// 파일선택 버튼
	@Override
	public String fileUpload(Parent root) {
		// TODO Auto-generated method stub
		TextArea imgAddr = (TextArea) root.lookup("#imgAddr");
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
		File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());

		if (selectedFile != null) {
			imgAddr.setText(selectedFile.getPath());
			return (String) imgAddr.getText();
		} else {
			imgAddr.setText("아무것도 지정하지 않았습니다.");
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
		}

		return imageBytes;
	}

	
	// 테스트 버튼
	public void testProc(Parent root) {
		TableView<Member> test = (TableView<Member>) root.lookup("#ListView");
		test.getItems().clear();
		test.getColumns().clear();
		test.columnResizePolicyProperty();
//		test.setItems(null);
	}

}
