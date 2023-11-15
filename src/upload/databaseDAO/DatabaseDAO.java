package upload.databaseDAO;

import upload.Board;

public interface DatabaseDAO {
	boolean uploadBoard(Board b);
	byte[] getImageData(int imageId);
}
