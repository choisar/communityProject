package all.databaseDAO_hong;

import java.lang.reflect.Member;
import java.util.List;

import all.boardService_hong.Board;

public interface DatabaseDAO {

	List<Board> selectAll();
	
}
