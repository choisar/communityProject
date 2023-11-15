package all.uploadPck.upload.userService.user;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import upload.databaseDAO.DatabaseDAO;
import upload.databaseDAO.DatabaseDAOImp;

public class UploadBoardImpl implements UploadBoard{
	DatabaseDAO dao;
	
	public UploadBoardImpl() {
		// TODO Auto-generated constructor stub
		dao= new DatabaseDAOImp();
	}

	@Override
	public void chekID(String id) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void uploadProc(Parent root) throws Exception{
		// TODO Auto-generated method stub	
		TextField title=(TextField)root.lookup("#txtTitle");
		TextArea contents=(TextArea)root.lookup("#txtContents");
		TextArea imgAddr=(TextArea)root.lookup("#imgAddr");
		
		upload.Board b=new upload.Board();
		b.setTitle(title.getText());
		b.setContents(contents.getText());
		byte[] imageBytes=null;
		if (imgAddr != null) {			
			imageBytes = convertImageToBytes((String)imgAddr.getText());			
			b.setImagePath(imageBytes);
		}
		
		if(dao.uploadBoard(b)) {
			System.out.println("게시 완료");
			Stage s=(Stage)root.getScene().getWindow();
			s.close();
		}
		
		
		
	}

	private byte[] convertImageToBytes(String imgAddr) throws Exception{
		// TODO Auto-generated method stub
		File imageFile = new File(imgAddr);
        byte[] imageBytes = new byte[(int) imageFile.length()];

        try (FileInputStream fis = new FileInputStream(imageFile)) {
            fis.read(imageBytes);
        }

        return imageBytes;
	}

	@Override
	public String fileUpload(Parent root) {
		// TODO Auto-generated method stub
		TextArea imgAddr=(TextArea)root.lookup("#imgAddr");
		FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(root.getScene().getWindow());

        if (selectedFile != null) {
			imgAddr.setText(selectedFile.getPath());
			return (String)imgAddr.getText();
		}else {
			imgAddr.setText("아무것도 지정하지 않았습니다.");
		}
        return null;
		
	}
	
//	public String fileUpload(AnchorPane root) {
//        Button openFileButton = new Button(); // 수정: openFileButton 초기화
//        TextArea imgAddr=(TextArea)root.lookup("#imgAddr");
//        // TODO Auto-generated method stub
//        FileChooser fileChooser = new FileChooser();
//        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
//        File selectedFile = fileChooser.showOpenDialog(openFileButton.getScene().getWindow());
//
//        if (selectedFile != null) {
//        	imgAddr.setText(selectedFile.getPath());
//            return imgAddr.getText();
//        } else {
//        	imgAddr.setText("아무것도 지정하지 않았습니다.");
//        }
//        return null;
//    }
	
}
