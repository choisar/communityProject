package all.button.boardButton;

import all.Controller;
import all.button.CommonServiceImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class boardButtonImp implements boardButton {
	
	CommonServiceImp cs = new CommonServiceImp();

	// 검색 버튼
	@Override
	public void searchProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		TextField searchBoard  = (TextField)root.lookup("#boardNameSearch");
		
		if(searchBoard.getText().isEmpty()) {
			searchViewError2(root);
		} else {
			if(logChk.getText().equals("비회원")) {
				cs.errorView(root);
			} else if (logChk.getText().equals("회원")||logChk.getText().equals("관리자")) {
				System.out.println("기능 실행");
			}
		}
	}

	// 검색창 비어있으면 호출되는 오류
	@Override
	public void searchViewError2(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/isEmptyError.fxml"));

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

		membershipForm.setTitle("Error");
		membershipForm.setResizable(false);
		membershipForm.setAlwaysOnTop(true);
		membershipForm.show();
	}
	
	// 자유 게시판 버튼
	@Override
	public void freeBoardProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk = (Label) root.lookup("#logChk");
		
		if (logChk.getText().equals("비회원")) {
			cs.errorView(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			System.out.println("기능 실행");
		}
	}
	
	// 구매 게시판 버튼
	@Override
	public void buyBoardProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView(root);
		} else if (logChk.getText().equals("회원")||logChk.getText().equals("관리자")) {
			System.out.println("기능 실행");
		}
	}
	
	// 판매 게시판 버튼
	@Override
	public void sellBoardProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			System.out.println("기능 실행");
		}
	}
	
	
	// 나눔 게시판 버튼
	@Override
	public void sharingBoardProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			System.out.println("기능 실행");
		}
	}
	
	// 글쓰기 버튼
	@Override
	public void writingProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView(root);
		} else if (logChk.getText().equals("회원")||logChk.getText().equals("관리자")) {
			System.out.println("기능 실행");
		}
	}
	
	// 신고 버튼
	@Override
	public void reportProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			System.out.println("기능 실행");
		}
	}


}
