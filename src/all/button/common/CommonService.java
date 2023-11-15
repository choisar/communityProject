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
}
