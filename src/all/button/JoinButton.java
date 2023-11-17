package all.button;

import javafx.event.ActionEvent;
import javafx.scene.Parent;

public interface JoinButton {

	void membershipProc(Parent root);

	void stageChange(Parent member);

	void joinMember(ActionEvent event);

	void idChkProc(Parent root);

	
}
