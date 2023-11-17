package all.button.boardButton;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.time.LocalDate;

import all.Board_s;
import all.boardService.Board;
import all.boardService.BoardServiceImp;
import all.boardService.boardViewServiceImp;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class boardButtonImp implements boardButton {
	
	CommonServiceImp cs = new CommonServiceImp();
	boardViewServiceImp bvs = new boardViewServiceImp();
	DatabaseDAOImp dao = new DatabaseDAOImp();

	// 검색 버튼
	@Override
	public void searchProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");

		 BoardServiceImp boardService = new BoardServiceImp();
		 String text1 = boardService.mainCombo(root);
		
		if(text1.equals("제목")) {
			text1 = "title";
		} else if (text1.equals("닉네임")) {
			text1 = "board_memnick";
		} else if (text1.equals("카테고리")) {
			text1 = "category";
		}
		
		TextField searchBoard  = (TextField)root.lookup("#boardNameSearch");
		String text2 = searchBoard.getText();
		
		if(searchBoard.getText().isEmpty()) {
			cs.errorView2(root);
		} else {
			if(logChk.getText().equals("비회원")) {
				cs.errorView1(root);
				bvs.searchResultBoardView(root, text1, text2);
			} else if (logChk.getText().equals("회원")||logChk.getText().equals("관리자")) {
				bvs.searchResultBoardView(root, text1, text2);
			}
		}
	}
	
	// 자유 게시판 버튼
	@Override
	public void freeBoardProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk = (Label) root.lookup("#logChk");
		
		if (logChk.getText().equals("비회원")) {
			cs.errorView1(root);
			bvs.freeBoardView(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			bvs.freeBoardView(root);
		}
	}
	
	// 구매 게시판 버튼
	@Override
	public void buyBoardProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView1(root);
			bvs.buyBoardView(root);
		} else if (logChk.getText().equals("회원")||logChk.getText().equals("관리자")) {
			bvs.buyBoardView(root);
		}
	}
	
	// 판매 게시판 버튼
	@Override
	public void sellBoardProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView1(root);
			bvs.sellBoardView(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			bvs.sellBoardView(root);
		}
	}
	
	// 나눔 게시판 버튼
	@Override
	public void sharingBoardProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView1(root);
			bvs.sharingBoardView(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			bvs.sharingBoardView(root);
		}
	}
	
	// 글쓰기 버튼
	@Override
	public void writingProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView1(root);
			bvs.uploadBoardView(root);
		} else if (logChk.getText().equals("회원")||logChk.getText().equals("관리자")) {
			bvs.uploadBoardView(root);
		}
	}
	
	// 신고 버튼
	@Override
	public void reportProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView1(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			System.out.println("기능 실행");
		}
	}

	// 게시하기 버튼
	@Override
	public void uploadProc(Parent root) throws Exception {
		// TODO Auto-generated method stub
		TextField title=(TextField)root.lookup("#txtTitle");
		TextArea contents=(TextArea)root.lookup("#txtContents");
		TextArea imgAddr=(TextArea)root.lookup("#imgAddr");
		ComboBox<String> cmbCateg=(ComboBox<String>)root.lookup("#cmbCateg");
		
		if(title.getText().isEmpty()) {
			cs.msgBox("입력", "입력 오류", "제목을 입력하세요");
			title.requestFocus();
			return;
		} else if(contents.getText().isEmpty()) {
			cs.msgBox("입력", "입력 오류", "본문을 입력하세요");
			contents.requestFocus();
			return;
		} 
		
		Board b = new Board();
		b.setTitle(title.getText());
		b.setContents(contents.getText());
		
		if(cmbCateg.getValue()==null) {
			cs.msgBox("입력", "입력오류", "카테고리를 선택해주세요.");
			cmbCateg.requestFocus();
			return;
		}else {
			switch(cmbCateg.getValue()) {
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
		
		
		byte[] imageBytes=null;
		if (imgAddr != null) {			
			imageBytes = convertImageToBytes((String)imgAddr.getText());			
			b.setImagePath(imageBytes);
		}
		
		if(dao.uploadBoard(b)) {
			System.out.println("게시 완료");
			Stage s=(Stage)root.getScene().getWindow();
			s.close();
		}else {
			System.out.println("게시 실패");
		}
		
	}

	// 파일선택 버튼
	@Override
	public String fileUpload(Parent root) {
		// TODO Auto-generated method stub
		TextArea imgAddr=(TextArea)root.lookup("#imgAddr");
		FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());

        if (selectedFile != null) {
			imgAddr.setText(selectedFile.getPath());
			return (String)imgAddr.getText();
		}else {
			imgAddr.setText("아무것도 지정하지 않았습니다.");
		}
        return null;
	}
	
	// 이미지를 바이트화 시키는 함수
	public byte[] convertImageToBytes(String imgAddr) throws Exception{
		// TODO Auto-generated method stub
		File imageFile = new File(imgAddr);
        byte[] imageBytes = new byte[(int) imageFile.length()];

        try (FileInputStream fis = new FileInputStream(imageFile)) {
            fis.read(imageBytes);
        }

        return imageBytes;
	}

	

}
