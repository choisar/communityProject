package all.button;


import all.Member;
import javafx.scene.Parent;

public interface loginButton {

	// 로그인 버튼
	void loginProc(Parent root);
	// 로그아웃 버튼
	void logoutProc(Parent info);
	// 회원가입 버튼
	void membershipProc(Parent root);
	// 로그인시 환영인사
	void helloView(Parent root, Member m);
}
