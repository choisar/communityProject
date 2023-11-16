package all.button.find_idpw;

import javafx.scene.Parent;

public interface PwFindButton {

	// 비밀번호 찾기 버튼을 눌렀을 때 비밀번호 찾기 창 호출
	void pwFindProc(Parent root);
	// 호출된 비밀번호 찾기 창에서 확인 버튼 눌렀을 때
	void pwFindOkProc(Parent root);
	// 비밀번호 찾기 결과창 호출
	void findPwResult(Parent root, String pw, String findName);

}
