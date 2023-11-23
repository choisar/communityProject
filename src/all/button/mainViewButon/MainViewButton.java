package all.button.mainViewButon;

import javafx.scene.Parent;

public interface MainViewButton {

	// 메인 화면 하단 구매 게시판의 첫 번째 게시물 제목을 눌렀을 때
	public void mainBuyDetailView1Proc(Parent root);
	// 메인 화면 하단 구매 게시판의 두 번째 게시물 제목을 눌렀을 때
	public void mainBuyDetailView2Proc(Parent root);
	
	// 메인 화면 하단 판매 게시판의 첫 번째 게시물 제목을 눌렀을 때
	public void mainSellDetailView1Proc(Parent root);
	// 메인 화면 하단 판매 게시판의 두 번째 게시물 제목을 눌렀을 때
	public void mainSellDetailView2Proc(Parent root);
	
	// 메인 화면 하단 나눔 게시판의 첫 번째 게시물 제목을 눌렀을 때
	public void mainSharingDetailView1Proc(Parent root);
	// 메인 화면 하단 나눔 게시판의 두 번째 게시물 제목을 눌렀을 때
	public void mainSharingDetailView2Proc(Parent root);
	
	// 메인 화면 하단 자유 게시판의 첫 번째 게시물 제목을 눌렀을 때
	public void mainFreeDetailView1Proc(Parent root);
	// 메인 화면 하단 자유 게시판의 두 번째 게시물 제목을 눌렀을 때
	public void mainFreeDetailView2Proc(Parent root);
	
}
