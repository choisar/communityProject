package all.button;

import java.sql.Date;
import java.time.LocalDate;

import all.Controller;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JoinButtonImp implements JoinButton{

	CommonService cs;
	DatabaseDAO dao;
	boolean idChk;

	public JoinButtonImp() {
		// TODO Auto-generated constructor stub
		cs = new CommonServiceImp();
		dao = new DatabaseDAOImp();
	}
	@Override
	public void membershipProc(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = new Stage();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/membership.fxml"));
		Parent member = null;
		try {
			member = loader.load();
			membershipForm.setScene(new Scene(member));
		} catch (Exception e) {
			// TODO:handle exception
			e.printStackTrace();
		}
		
		Controller ctrl = loader.getController();
		ctrl.setMember(member);
			
		membershipForm.setTitle("회원가입장"); 
		membershipForm.setAlwaysOnTop(false);
		membershipForm.show();
	}

	@Override
	public void stageChange(Parent member) {
		// TODO Auto-generated method stub
		Stage membershipForm = (Stage)member.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/main.fxml"));
		Parent root = null;
		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO:handle exception
			e.printStackTrace();
		}
		
		Controller ctrl = loader.getController();
		ctrl.setRoot(root);
	
		membershipForm.setTitle("회원가입창");
		membershipForm.show();
		
		
	}

	@Override
	public void joinMember(ActionEvent e) {
		// TODO Auto-generated method stub
		Parent mem = (Parent) e.getSource();
		Parent member = mem.getParent().getParent();
		all.Member m = new all.Member();
		
		TextField txtName = (TextField) member.lookup("#txtName");
		TextField txtId = (TextField) member.lookup("#txtId");
		TextField txtnickName = (TextField) member.lookup("#txtnickName");
		PasswordField txtPw = (PasswordField) member.lookup("#txtPw");
		PasswordField txtPwOk = (PasswordField) member.lookup("#txtPwOk");
		TextField txtEmail = (TextField) member.lookup("#txtEmail");
		TextField txtphoneNum = (TextField) member.lookup("#txtphoneNum");
		DatePicker birthDate = (DatePicker) member.lookup("#birthDate");

		System.out.println(txtName.getText());

		 if(txtName.getText().isEmpty()) {
			cs.msgBox("입력오류", "이름 입력 오류", "이름이 입력 되지 않았습니다. 다시 입력하세요");
			txtName.clear();
			txtName.requestFocus();
			return;
		} else if(txtId.getText().isEmpty()) {
			cs.msgBox("입력오류", "아이디 입력 오류", "아이디가 입력 되지 않았습니다. 다시 입력하세요");
			txtId.clear();
			txtId.requestFocus();
			return;
		} else if(txtnickName.getText().isEmpty()) {
			cs.msgBox("입력오류", "닉네임입력 오류", "닉네임이 입력 되지 않았습니다. 다시 입력하세요");
			txtnickName.clear();
			txtnickName.requestFocus();
			return;
		}	else if(txtPw.getText().isEmpty()) {
			cs.msgBox("입력오류", "비밀번호 입력 오류", "비밀번호가 입력 되지 않았습니다. 다시 입력하세요");
			txtPw.clear();
			txtPw.requestFocus();
			return;
		}// else if(txtPwOk.getText().isEmpty()) {
		else if(!(txtPw.getText().equals(txtPwOk.getText()))) {
			cs.msgBox("암호", "암호일치여부", "암호가 불일치합니다. 다시 입력하세요");
			txtPwOk.clear();
			txtPwOk.requestFocus();
			return;	
		}  else if(txtEmail.getText().isEmpty()) {
			cs.msgBox("입력오류", "이메일 입력 오류", "이메일이 입력 되지 않았습니다. 다시 입력하세요");
			txtEmail.clear();
			txtEmail.requestFocus();
			return;
		} else if(txtphoneNum.getText().isEmpty()) {
			cs.msgBox("입력오류", "전화번호 입력 오류", "전화번호가 입력 되지 않았습니다. 다시 입력하세요");
			txtphoneNum.clear();
			txtphoneNum.requestFocus();
			return;
		} 
		
		
		m.setName(txtName.getText());
		m.setId(txtId.getText());
		m.setPw(txtPw.getText());
		m.setNickName(txtnickName.getText());
		m.setEmail(txtEmail.getText());
		m.setPhoneNum(txtphoneNum.getText());
		LocalDate date = birthDate.getValue();
		Date d = Date.valueOf(date);
		m.setBirthDate(d);
		
	
		RadioButton rdoMan = (RadioButton) member.lookup("#rdoMan");
		RadioButton rdoWoman = (RadioButton) member.lookup("#rdoWoman");
		
		if(rdoMan.isSelected()) {
//			System.out.println("남성");
			m.setGender("false");
		}else if(rdoMan.isSelected()) {
//			System.out.println("여성");
			m.setGender("true");
		} 
		
		
	
	
	if( dao.insertMember(m)) {
		Stage s = (Stage) member.getScene().getWindow();
		s.close();
	 } else {
		 txtId.clear();
		 txtPw.clear();
		 txtPwOk.clear();
		 txtName.clear();
		 txtnickName.clear();
		 txtEmail.clear();	
		 txtphoneNum.clear();
		 
	 }
	
	
	}
	@Override
	public void idChkProc(Parent root) {
		// TODO Auto-generated method stub
		TextField txtId = (TextField)root.lookup("#txtId");

		idChk=dao.dupID(txtId.getText());
		if(!idChk) {
			txtId.clear();
		}
		return;
	}
	
	


	

}
