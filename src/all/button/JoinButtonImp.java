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
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;


public class JoinButtonImp implements JoinButton{

	Parent root;
	boolean idChk;
	CommonService cs = new CommonServiceImp();
	DatabaseDAO dao = new DatabaseDAOImp();

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

		root = null;

		try {
			root = loader.load();
			membershipForm.setScene(new Scene(root));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		Controller ctrl = loader.getController();

		ctrl.setRoot(root);
		
		TextField txtId = (TextField) root.lookup("#txtId");
		TextField errorText = (TextField) root.lookup("#errorText");
		TextField txtName = (TextField) root.lookup("#txtName");
		TextField txtnickName = (TextField) root.lookup("#txtnickName");
		PasswordField txtPw = (PasswordField) root.lookup("#txtPw");
		PasswordField txtPwOk = (PasswordField) root.lookup("#txtPwOk");
		TextField txtEmail = (TextField) root.lookup("#txtEmail");
		TextField txtphoneNum = (TextField) root.lookup("#txtphoneNum");
		DatePicker birthDate = (DatePicker) root.lookup("#birthDate");
		TextField errorText2 = (TextField) root.lookup("#errorText2");
		txtId.requestFocus();

		// 포커스 속성의 변화를 감지하는 리스너 등록
		txtId.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				// 포커스가 벗어났을 때 실행되는 코드
				String txtId1 = txtId.getText();
				if (txtId1.isEmpty()) {
					errorText.setText("아이디를 입력해주세요.");
				}else if(txtId1.length() <= 4) {
					errorText.setText("아이디는 5자리 이상이어야 합니다.");
					txtId.requestFocus();
				} else if (dao.dupID(ctrl.getRoot(),txtId1)) {
					errorText.setText("사용 가능한 아이디입니다.");
				}else {
					errorText.setText("중복된 아이디입니다.");
					txtId.requestFocus();
				}
			}
		});

		txtPw.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				// 포커스가 벗어났을 때 실행되는 코드
				if (txtPw.getText().isEmpty()) {
					errorText.clear();
					errorText.setText("암호를 입력해주세요.");
				}
			}
		});
		txtPwOk.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				// 포커스가 벗어났을 때 실행되는 코드
				if(txtPwOk.getText().isEmpty()) {
					errorText.clear();
					errorText.setText("암호 확인을 입력해주세요.");
				}else if (!txtPwOk.getText().equals(txtPw.getText())) {
					errorText.clear();
					errorText.setText("암호가 불일치합니다.");
					txtPwOk.clear();
					txtPwOk.requestFocus();
				}
			}
		});
		txtEmail.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				// 포커스가 벗어났을 때 실행되는 코드
				if (txtEmail.getText().isEmpty()) {
					errorText.clear();
					errorText.setText("이메일을 입력해주세요.");
				}else if(!txtEmail.getText().contains("@")) {
					errorText.clear();
					errorText.setText("이메일에는 @이 반드시 포함되어야 합니다.");
					txtEmail.clear();
					txtEmail.requestFocus();
				}
			}
		});
		txtName.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				if(txtName.getText().isEmpty()) {
					errorText.clear();
					errorText2.setText("이름을 입력해주세요.");
				}
			}
		});
		txtnickName.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				if(txtnickName.getText().isEmpty()) {
					errorText2.setText("닉네임을 입력해주세요.");
				}
			}
		});
		birthDate.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				if(birthDate.getValue() == null) {
					errorText2.clear();
					errorText2.setText("생년월일을 선택해주세요");
				}
			}
		});
		txtphoneNum.focusedProperty().addListener((obs, oldVal, newVal) -> {
			if (!newVal) {
				if(txtphoneNum.getText().isEmpty()) {
					errorText2.clear();
					errorText2.setText("핸드폰 번호를 입력해주세요.");
				}
			}
		});


		membershipForm.setTitle("회원가입장"); 
		membershipForm.setAlwaysOnTop(false);
		membershipForm.show();
	}

	@Override
	public void stageChange(Parent root) {
		// TODO Auto-generated method stub
		Stage membershipForm = (Stage)root.getScene().getWindow();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/main.fxml"));

		root = null;
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
		try {
			Parent mem = (Parent) e.getSource();
			Scene member = mem.getScene();
			//Node mem = (Node) e.getSource();
			//Scene scene = mem.getScene();
			//Parent member = scene.getRoot();
			all.Member m = new all.Member();

			TextField txtName = (TextField) member.lookup("#txtName");
			TextField txtId = (TextField) member.lookup("#txtId");
			TextField txtnickName = (TextField) member.lookup("#txtnickName");
			PasswordField txtPw = (PasswordField) member.lookup("#txtPw");
			PasswordField txtPwOk = (PasswordField) member.lookup("#txtPwOk");
			TextField txtEmail = (TextField) member.lookup("#txtEmail");
			TextField txtphoneNum = (TextField) member.lookup("#txtphoneNum");
			DatePicker birthDate = (DatePicker) member.lookup("#birthDate");		


			if(txtName.getText() == null || txtName.getText().isEmpty()) {
				cs.customErrorView(root, "이름이 입력 되지 않았습니다. 다시 입력하세요");
				txtName.clear();
				txtName.requestFocus();
				return;
			} else if(txtId.getText().length() <= 4) {
				if(txtId.getText().isEmpty()) {
					cs.customErrorView(root, "아이디가 입력 되지 않았습니다. 다시 입력하세요");
					txtId.clear();
					txtId.requestFocus();
				}else {
					cs.customErrorView(root,"아이디는 최소 5자리이어야 합니다.");
					txtId.clear();
					txtId.requestFocus();
				}
				return;
			} else if(txtnickName.getText().isEmpty()) {
				cs.customErrorView(root,"닉네임이 입력 되지 않았습니다. 다시 입력하세요.");
				txtnickName.clear();
				txtnickName.requestFocus();
				return;
			}	else if(txtPw.getText().isEmpty()) {
				cs.customErrorView(root, "비밀번호가 입력 되지 않았습니다. 다시 입력하세요.");
				txtPw.clear();
				txtPw.requestFocus();
				return;
			}// else if(txtPwOk.getText().isEmpty()) {
			else if(!(txtPw.getText().equals(txtPwOk.getText()))) {
				cs.customErrorView(root,"암호가 불일치합니다. 다시 입력하세요.");
				txtPwOk.clear();
				txtPwOk.requestFocus();
				return;	
			}else if(birthDate.getValue() == null) {
				cs.customErrorView(root,"생년월일 정보가 없습니다. 다시 입력하세요.");
				birthDate.requestFocus();
				return;
				
			}else if(txtEmail.getText().isEmpty()) {
				cs.customErrorView(root,"이메일이 입력 되지 않았습니다. 다시 입력하세요.");
				txtEmail.clear();
				txtEmail.requestFocus();
				return;
			} else if(txtphoneNum.getText().isEmpty()) {
				cs.customErrorView(root,"전화번호가 입력 되지 않았습니다. 다시 입력하세요");
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


			ToggleButton rdoMan = (ToggleButton) member.lookup("#rdoMan");
			ToggleButton rdoWoman = (ToggleButton) member.lookup("#rdoWoman");

			rdoMan.setOnAction(event -> handleGenderSelection(rdoMan, rdoWoman));
			rdoWoman.setOnAction(event -> handleGenderSelection(rdoWoman, rdoMan));
			
			// 토글 버튼이 null이거나 어느 버튼도 선택되지 않았을 때의 기본 처리
			if (rdoMan == null || rdoWoman == null || (!rdoMan.isSelected() && !rdoWoman.isSelected())) {
			    cs.customErrorView(root, "성별을 선택해주세요.");
			    return;
			}

			// 성별 선택에 따라 값을 설정
			m.setGender(rdoMan.isSelected() ? "true" : "false");

			if (dao.insertMember(m)) {
			    Stage s = (Stage) member.getWindow();
			    s.close();
			} else {
			    return;
			}
		}catch (Exception e1) {
			cs.customErrorView(root, "회원정보를 모두 입력해주세요.");
		}


	}
	
	@Override
	public void idChkProc(Parent root) {
		// TODO Auto-generated method stub
		TextField txtId = (TextField)root.lookup("#txtId");

		idChk = dao.dupID(root, txtId.getText());
		if(!idChk) {
			txtId.clear();
		}
		
		return;
	}
	
	private void handleGenderSelection(ToggleButton selectedButton, ToggleButton otherButton) {
		try {
			// 선택된 경우 배경색 변경
			selectedButton.setStyle("-fx-background-color: #ADD8E6;");
			// 다른 버튼의 배경색 원래대로
			otherButton.setStyle("-fx-background-color: #F08080;");

		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}






}
