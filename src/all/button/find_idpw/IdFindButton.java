package all.button.find_idpw;

import javafx.scene.Parent;

public interface IdFindButton {

	// 아이디 찾기 버튼
	void idFindProc(Parent root);
	// 아이디 찾기 창에서 확인 버튼
	void idFindOkProc(Parent root);
	// 아이디 찾기 결과창 호출
	void findIdResult(Parent root, String id, String findName);
	
}
