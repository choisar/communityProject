package all.uploadPck.upload.userService.user;

import java.io.ByteArrayInputStream;

import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import upload.Board;
import upload.databaseDAO.DatabaseDAO;
import upload.databaseDAO.DatabaseDAOImp;

public class ViewServiceImpl implements ViewService{
	DatabaseDAO dao;
	
	public ViewServiceImpl() {
		// TODO Auto-generated constructor stub
		dao= new DatabaseDAOImp();
	}	

	@Override
	public void viewProc(Parent root) {
		// TODO Auto-generated method stub
		ImageView imageView = (ImageView) root.lookup("#imgWindow");
		Board b;
		byte[] imageData=dao.getImageData(4);
		if(imageData!=null) {
			System.out.println("이미지 출력");
			Image image = new Image(new ByteArrayInputStream(imageData));
			imageView.setImage(image);
		}else {
			System.out.println("이미지가 없습니다.");
		}
	}
	
//	public void viewProc(Parent root) {
//        ImageView imageView = (ImageView) root.lookup("#imgWindow"); // fx:id 값으로 ImageView 찾기
//
//        if (imageView != null) {
//            int imageId = 4; // 이미지의 데이터베이스 ID
//            byte[] imageData = getImageData(imageId);
//
//            if (imageData != null) {
//                Image image = new Image(new ByteArrayInputStream(imageData));
//                imageView.setImage(image);
//            } else {
//                System.out.println("Image not found");
//            }
//        } else {
//            System.out.println("ImageView not found");
//        }
//    }

}
