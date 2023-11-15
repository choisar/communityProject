package all.boardService;

import all.button.CommonServiceImp;
import javafx.scene.Parent;
import javafx.scene.control.Label;

public class reportserviceImp implements reportservice {
	
	CommonServiceImp cs = new CommonServiceImp();

	@Override
	public void reportProc(Parent root) {
		// TODO Auto-generated method stub
		Label logChk  = (Label)root.lookup("#logChk");
		
		if(logChk.getText().equals("비회원")) {
			cs.errorView(root);
		} else if (logChk.getText().equals("회원") || logChk.getText().equals("관리자")) {
			System.out.println("기능 실행");
		}
	}

}
