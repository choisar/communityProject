package all.button.boardButton;

import java.util.Arrays;

import all.boardService.boardViewServiceImp;
import all.button.common.CommonServiceImp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class boardButtonImp implements boardButton {
	
	CommonServiceImp cs = new CommonServiceImp();
	boardViewServiceImp bvs = new boardViewServiceImp();

	// 검색 버튼
	@Override
	public void searchProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");

		ComboBox<String> combo = (ComboBox<String>)root.lookup("#searchCombo");
		String str[] = {"제목", "닉네임", "카테고리", "날짜"};
		combo.getItems().addAll(FXCollections.observableArrayList(str));
		
		String text1 = combo.getSelectionModel().getSelectedItem();
		
		if(text1.equals("제목")) {
			text1 = "Title";
		} else if (text1.equals("닉네임")) {
			text1 = "nickname";
		} else if (text1.equals("카테고리")) {
			text1 = "ca";
		}else if (text1.equals("날짜")) {
			text1 = "dat";
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
