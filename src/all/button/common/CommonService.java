package all.button.common;

import javafx.event.ActionEvent;
import javafx.scene.Parent;

public interface CommonService {
	// 창 닫기 
	public void windowClose(ActionEvent event);
	// 에러 메세지 박스 호출
	public void msgBox(String title, String subject, String content);
	// 비회원일 때 버튼 작동시 호출되는 오류
	public void errorView1(Parent root);
	// 검색창 비어있으면 호출되는 오류
	public void errorView2(Parent root);
	// 카테고리 선택이 안되어있으면 호출되는 오류
	public void errorView3(Parent root);
	// 게시물 검색창에 아무것도 입력되지 않았고, 카테고리 선택도 되어있지 않으면 호출되는 오류
	public void errorView4(Parent root);

}
