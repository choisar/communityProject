package all.button.mainViewButon;

import java.util.List;

import all.boardService.Board;
import all.boardService.BoardService;
import all.boardService.BoardServiceImp;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.scene.Parent;
import javafx.scene.image.Image;

public class MainViewButtonImp implements MainViewButton{
	
	DatabaseDAO dao;
	BoardService bs;
	
	public  MainViewButtonImp() {
		dao = new DatabaseDAOImp();
		bs = new BoardServiceImp();
	}
	
	// 메인 화면 하단 구매 게시판의 첫 번째 게시물 제목을 눌렀을 때
	@Override
	public void mainBuyDetailView1Proc(Parent root) {
		
		// 구매 게시판의 최근 게시물 2개 
		List<Board> buyLatestBoard = dao.getLatestBoardList("구매 게시판");
		
		// 최신 게시물중 첫 번째 게시물
		Board b = buyLatestBoard.get(0);
		
		// 첫 번째 게시물의 이미지 리스트
		List<Image> imagelist = dao.getAllImages(b.getNo());
		
		// 메서드에 입력한 보드 객체, 이미지 리스트내용으로 오픈보드 디테일뷰 실행 
		bs.openBoardDetailWindow(root, b, imagelist);
	}
	
	// 메인 화면 하단 구매 게시판의 두 번째 게시물 제목을 눌렀을 때
	@Override
	public void mainBuyDetailView2Proc(Parent root) {
		List<Board> buyLatestBoard = dao.getLatestBoardList("구매 게시판");
		Board b = buyLatestBoard.get(1);
		List<Image> imagelist = dao.getAllImages(b.getNo());
		bs.openBoardDetailWindow(root, b, imagelist);
	}
	
	// 메인 화면 하단 판매 게시판의 첫 번째 게시물 제목을 눌렀을 때
	@Override
	public void mainSellDetailView1Proc(Parent root) {
		
		List<Board> buyLatestBoard = dao.getLatestBoardList("판매 게시판");
		Board b = buyLatestBoard.get(0);
		List<Image> imagelist = dao.getAllImages(b.getNo());
		bs.openBoardDetailWindow(root, b, imagelist);
	}
	
	// 메인 화면 하단 판매 게시판의 두 번째 게시물 제목을 눌렀을 때
	@Override
	public void mainSellDetailView2Proc(Parent root) {
		
		List<Board> buyLatestBoard = dao.getLatestBoardList("판매 게시판");
		Board b = buyLatestBoard.get(1);
		List<Image> imagelist = dao.getAllImages(b.getNo());
		bs.openBoardDetailWindow(root, b, imagelist);
	}
	
	// 메인 화면 하단 나눔 게시판의 첫 번째 게시물 제목을 눌렀을 때
	@Override
	public void mainSharingDetailView1Proc(Parent root) {
		
		List<Board> sharingLatestBoard = dao.getLatestBoardList("나눔 게시판");
		Board b = sharingLatestBoard.get(0);
		List<Image> imagelist = dao.getAllImages(b.getNo());
		bs.openBoardDetailWindow(root, b, imagelist);
	}
	
	// 메인 화면 하단 나눔 게시판의 두 번째 게시물 제목을 눌렀을 때
	@Override
	public void mainSharingDetailView2Proc(Parent root) {
		
		List<Board> sharingLatestBoard = dao.getLatestBoardList("나눔 게시판");
		Board b = sharingLatestBoard.get(1);
		List<Image> imagelist = dao.getAllImages(b.getNo());
		bs.openBoardDetailWindow(root, b, imagelist);
	}
	
	// 메인 화면 하단 자유 게시판의 첫 번째 게시물 제목을 눌렀을 때
	@Override
	public void mainFreeDetailView1Proc(Parent root) {
		
		List<Board> freeLatestBoard = dao.getLatestBoardList("자유 게시판");
		Board b = freeLatestBoard.get(0);
		List<Image> imagelist = dao.getAllImages(b.getNo());
		bs.openBoardDetailWindow(root, b, imagelist);
	}
	
	// 메인 화면 하단 자유 게시판의 두 번째 게시물 제목을 눌렀을 때
	@Override
	public void mainFreeDetailView2Proc(Parent root) {
		
		List<Board> freeLatestBoard = dao.getLatestBoardList("자유 게시판");
		Board b = freeLatestBoard.get(1);
		List<Image> imagelist = dao.getAllImages(b.getNo());
		bs.openBoardDetailWindow(root, b, imagelist);
	}
	

}
