package all.button;


import all.Member;
import javafx.scene.Parent;

public interface loginButton {

	void loginProc(Parent root);
	void cancelProc(Parent root);
	void membershipProc(Parent root);
	void logoutProc(Parent info);
	// 로그인시 환영인사
	void helloView(Parent root, Member m);
}
