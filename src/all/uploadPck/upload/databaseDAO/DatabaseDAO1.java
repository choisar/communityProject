package all.uploadPck.upload.databaseDAO;

import upload.Board;

public interface DatabaseDAO1 {
	boolean uploadBoard(Board b);
	byte[] getImageData(int imageId);
}
