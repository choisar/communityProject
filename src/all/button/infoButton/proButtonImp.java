package all.button.infoButton;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

import all.Controller;
import all.Member;
import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAOImp;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;

public class proButtonImp implements proButton {

	CommonService cs;
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	DatabaseDAOImp dao;


	public proButtonImp() {
		// TODO Auto-generated constructor stub
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();		
	}


	// 프로필수정 infoupdate.fxml 창에서 하단에 있는 저장하기 버튼을 수행할 때 사용
	@Override
	public void storeProc(Parent root) {
		// TODO Auto-generated method stub
		TextField txtName = (TextField) root.lookup("#txtName");
		TextField txtId = (TextField) root.lookup("#txtId");
		TextField txtnickName = (TextField) root.lookup("#txtnickName");
		PasswordField txtPw = (PasswordField) root.lookup("#txtPw");
		PasswordField txtPwOk = (PasswordField) root.lookup("#txtPwOk");
		TextField txtEmail = (TextField) root.lookup("#txtEmail");
		TextField txtphoneNum = (TextField) root.lookup("#txtphoneNum");

		if(txtName.getText().isEmpty()) {
			cs.customErrorView(root, "이름을 입력하세요.");
			txtName.requestFocus();
			return;
		} else if(txtId.getText().isEmpty()) {
			cs.customErrorView(root, "아이디를 입력하세요.");
			txtId.requestFocus();
			return;
		} else if(txtnickName.getText().isEmpty()) {
			cs.customErrorView(root, "닉네임을 입력하세요.");
			txtnickName.requestFocus();
			return;
		}	else if(txtPw.getText().isEmpty()) {
			cs.customErrorView(root, "비밀번호를 입력하세요.");
			txtPw.requestFocus();
			return;
		} else if(txtPwOk.getText().isEmpty()) {
			cs.customErrorView(root, "비밀번호를 입력하세요.");
			txtPwOk.requestFocus();
			return;	
		}  else if(txtEmail.getText().isEmpty()) {
			cs.customErrorView(root, "이메일을 입력하세요.");
			txtEmail.requestFocus();
			return;
		} else if(txtphoneNum.getText().isEmpty()) {
			cs.customErrorView(root, "전화번호를 입력하세요.");
			txtphoneNum.requestFocus();
			return;
		} 

		if(txtPw.getText().equals(txtPwOk.getText())) {
			cs.customErrorView(root, "비밀번호가 일치합니다.");
		} else {
			cs.customErrorView(root, "비밀번호를 확인하세요.");

			txtPw.clear();
			txtPwOk.clear();

			txtPw.requestFocus();
			return; 
		}


		Member m = new Member();
		m.setName(txtName.getText());
		m.setId(txtId.getText());
		m.setPw(txtPw.getText());
		m.setNickName(txtnickName.getText());
		m.setEmail(txtEmail.getText());
		m.setPhoneNum(txtphoneNum.getText());


		DatePicker birthDate = (DatePicker) root.lookup("#birthDate");
		LocalDate date = birthDate.getValue();
		Date d = Date.valueOf(date);
		m.setBirthDate(d);

		ToggleButton rdoMan = (ToggleButton) root.lookup("#rdoMan");
		ToggleButton rdoWoman = (ToggleButton) root.lookup("#rdoWoman");

		if(rdoMan.isSelected()) {
			m.setGender("남성");
		} else if (rdoWoman.isSelected()) {
			m.setGender("여성");
		}  

		if(dao.updateMember(root, m)) {
			Stage s = (Stage) root.getScene().getWindow();
			s.close();
		}
		
	}

	// infoupdate.fxml 창에 가입했을때 작성한 정보 불러오기 infoButtonImp 안에서 사용
	@Override
	public void profileProc(Parent root) {
		// TODO Auto-generated method stub
		Stage s = (Stage) root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../../fxml/infoupdate.fxml"));
		root = null;

		try {
			root = loader.load();
			// s.close(); // 이전 root를 닫는 부분을 root를 로드한 후에 실행하도록 변경
			s.setScene(new Scene(root));
		} catch (Exception e) {
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();
		ctrl.setRoot(root);

		TextField txtName = (TextField) root.lookup("#txtName");
		TextField txtId = (TextField) root.lookup("#txtId");
		TextField txtnickName = (TextField) root.lookup("#txtnickName");
		PasswordField txtPw = (PasswordField) root.lookup("#txtPw");
		PasswordField txtPwOk = (PasswordField) root.lookup("#txtPwOk");
		TextField txtEmail = (TextField) root.lookup("#txtEmail");
		TextField txtphoneNum = (TextField) root.lookup("#txtphoneNum");
		ToggleButton rdoMan = (ToggleButton) root.lookup("#rdoMan");
		ToggleButton rdoWoman = (ToggleButton) root.lookup("#rdoWoman");
		DatePicker birthDate = (DatePicker) root.lookup("#birthDate");



		Member mem = dao.memberInfo();

		txtName.setText(mem.getName());
		txtId.setText(mem.getId());
		txtPw.setText(mem.getPw());
		txtnickName.setText(mem.getNickName());
		txtEmail.setText(mem.getEmail());
		txtphoneNum.setText(mem.getPhoneNum());

		if(mem.isGender().equals("여성")) {
			rdoWoman.setSelected(true);
		} else {

			rdoMan.setSelected(true);
		}
		LocalDate date = mem.getBirthDate().toLocalDate();
		birthDate.setValue(date);


		s.setTitle("정보수정"); 
		s.setAlwaysOnTop(false);
		s.show();
		
//		Stage s2 = (Stage) root.getScene().getWindow();
//		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("../../fxml/userLogin1.fxml"));
//		
//		Controller ctrl2 = loader2.getController();
//		ctrl2.setRoot(root);
//		s2.setTitle("로그인 화면으로 돌아감");
//		s2.show();
		
	}
}	

