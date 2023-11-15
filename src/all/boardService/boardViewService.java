package all.boardService;

import javafx.scene.Parent;

public interface boardViewService {
	// 자유 게시판
	public void freeBoardView(Parent root);
	// 구매 게시판
	public void buyBoardView(Parent root);
	// 판매 게시판
	public void sellBoardView(Parent root);
	// 나눔 게시판
	public void sharingBoardView(Parent root);
}
