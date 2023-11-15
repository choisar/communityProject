package all.button.boardButton;

import all.Controller;
import all.boardService.boardViewServiceImp;
import all.button.common.CommonServiceImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class boardButtonImp implements boardButton {
	
	CommonServiceImp cs = new CommonServiceImp();
	boardViewServiceImp bvs = new boardViewServiceImp();

	// 검색 버튼
	@Override
	public void searchProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		TextField searchBoard  = (TextField)root.lookup("#boardNameSearch");
		
		if(searchBoard.getText().isEmpty()) {
			cs.errorView2(root);
		} else {
			if(logChk.getText().equals("비회원")) {
				cs.errorView1(root);
			} else if (logChk.getText().equals("회원")||logChk.getText().equals("관리자")) {
				System.out.println("기능 실행");
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
			cs.errorView1(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			System.out.println("기능 실행");
		}
	}


}
