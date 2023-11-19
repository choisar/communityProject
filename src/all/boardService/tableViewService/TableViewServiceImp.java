package all.boardService.tableViewService;

import java.util.List;


import all.Member;
import all.boardService.Board;
import all.boardService.BoardService;
import all.boardService.BoardServiceImp;
import all.databaseDAO.DatabaseDAO;
import all.databaseDAO.DatabaseDAOImp;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableCell;
import javafx.util.Callback;
import javafx.scene.control.Button;

public class TableViewServiceImp implements TableViewService {
	DatabaseDAO dao = new DatabaseDAOImp();

	// 중복 코드 줄이려고 만든 메서드 - root 값, 라벨 값(회원, 비회원, 관리자 확인), 카테고리 값(자유, 구매, 판매, 나눔) 받아서
	// 테이블 뷰 생성
	// 자유, 구매, 판매, 나눔 게시판 버튼에서 사용
	@Override
	public void handleBoardView(Parent root, Label logChk, String category) {
		TableView<Board> listView = (TableView<Board>) root.lookup("#ListView");
		listView.getItems().clear();
		listView.getColumns().clear();

		List<Board> boardList = dao.categoryBoardAll(category);

		if (boardList != null) {
			configureBoardTableView(listView);
			listView.setItems(FXCollections.observableArrayList(boardList));
		} else {
			System.out.println("게시판 목록을 가져올 수 없습니다.");
		}
	}

	// 중복 코드 줄이려고 만든 메서드 - 전체 게시판 테이블 뷰 - 전체 게시판 버튼에서 사용
	@Override
	public void loadAllBoardListView(Parent root) {

		TableView<Board> listView = (TableView<Board>) root.lookup("#ListView");
		listView.getItems().clear();
		listView.getColumns().clear();

		List<Board> boardList = dao.selectAll();

		if (boardList != null) {
			configureBoardTableView(listView);
			listView.setItems(FXCollections.observableArrayList(boardList));
		} else {
			System.out.println("게시판 목록을 가져올 수 없습니다.");
		}
	}

	// 중복 코드 줄이려고 만든 메서드 - 테이블 뷰에 컬럼 설정
	@Override
	public void configureBoardTableView(TableView<Board> listView) {
		listView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<Board, Integer> boardNum = new TableColumn<>("No.");
		boardNum.setMinWidth(30);
		TableColumn<Board, String> category = new TableColumn<Board, String>("카테고리");
		category.setMinWidth(80);
		TableColumn<Board, String> title = new TableColumn<Board, String>("제목");
		title.setMinWidth(330);
		TableColumn<Board, String> nickname = new TableColumn<Board, String>("닉네임");
		nickname.setMinWidth(75);
		TableColumn<Board, String> date = new TableColumn<Board, String>("날짜");
		date.setMinWidth(130);

		boardNum.setCellValueFactory(new PropertyValueFactory<>("No"));
		category.setCellValueFactory(new PropertyValueFactory<>("categori"));
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		nickname.setCellValueFactory(new PropertyValueFactory<>("nicName"));
		date.setCellValueFactory(new PropertyValueFactory<>("uploadDate"));

		listView.getColumns().addAll(boardNum, category, title, nickname, date);
		
	    // 셀 가운데 정렬
	    boardNum.setStyle("-fx-alignment: CENTER;");
	    category.setStyle("-fx-alignment: CENTER;");
	    date.setStyle("-fx-alignment: CENTER;");
	    // 셀 가운데 왼쪽 정렬
	    title.setStyle("-fx-alignment: CENTER-LEFT;");
	    nickname.setStyle("-fx-alignment: CENTER-LEFT;");
	}

	// 중복 코드 줄이려고 만든 메서드 - 테이블 뷰에 컬럼 설정 - 신고 기능 - 게시물 테이블뷰
	@Override
	public void configureBoardTableView1(TableView<Board> listView, Parent root) {
		listView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<Board, Integer> boardNum = new TableColumn<>("No.");
		boardNum.setMinWidth(20);
		TableColumn<Board, String> category = new TableColumn<>("카테고리");
		category.setMinWidth(65);
		TableColumn<Board, String> title = new TableColumn<>("제목");
		title.setMinWidth(150);
		TableColumn<Board, String> nickname = new TableColumn<Board, String>("닉네임");
		nickname.setMinWidth(75);
		TableColumn<Board, String> date = new TableColumn<Board, String>("날짜");
		date.setMinWidth(110);

		// 버튼을 추가하는 새로운 컬럼 생성
		TableColumn<Board, Void> buttonColumn = new TableColumn<>("선택");
		buttonColumn.setMinWidth(35);

		Callback<TableColumn<Board, Void>, TableCell<Board, Void>> cellFactory = param -> {
			final TableCell<Board, Void> cell = new TableCell<Board, Void>() {
				private final Button button = new Button("+");

				{
					button.setOnAction(event -> {
						Board data = getTableView().getItems().get(getIndex());
						// 버튼 클릭 시 실행할 동작 정의
						TextArea reportContentsText = (TextArea) root.lookup("#reportContentsText");
						reportContentsText.appendText("<신고할 게시물>\n"+"게시물 번호 : " + data.getNo() + "\n" + "게시물 이름 : " + data.getTitle() +"\n");
					});
				}

				@Override
				protected void updateItem(Void item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
					} else {
						setGraphic(button);
					}
				}
			};
			return cell;
		};

		buttonColumn.setCellFactory(cellFactory);
		boardNum.setCellValueFactory(new PropertyValueFactory<>("No"));
		category.setCellValueFactory(new PropertyValueFactory<>("categori"));
		title.setCellValueFactory(new PropertyValueFactory<>("title"));
		nickname.setCellValueFactory(new PropertyValueFactory<>("nicName"));
		date.setCellValueFactory(new PropertyValueFactory<>("uploadDate"));

		listView.getColumns().addAll(boardNum, category, title, nickname, date, buttonColumn);
		
	    // 셀 가운데 정렬
	    boardNum.setStyle("-fx-alignment: CENTER;");
	    category.setStyle("-fx-alignment: CENTER;");
	    buttonColumn.setStyle("-fx-alignment: CENTER;");
	    date.setStyle("-fx-alignment: CENTER;");
	    // 셀 가운데 왼쪽 정렬
	    title.setStyle("-fx-alignment: CENTER-LEFT;");
	    nickname.setStyle("-fx-alignment: CENTER-LEFT;");
	}

	// 중복 코드 줄이려고 만든 메서드 - 테이블 뷰에 컬럼 설정 - 신고 기능 - 닉네임 테이블뷰
	@Override
	public void configureBoardTableView2(TableView<Member> listView, Parent root) {
		listView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<Member, Integer> memNum = new TableColumn<>("No.");
		memNum.setMinWidth(20);
		TableColumn<Member, String> memNickName = new TableColumn<>("닉네임");
		memNickName.setMinWidth(200);
		TableColumn<Member, String> memId = new TableColumn<>("아이디");
		memId.setMinWidth(200);
		
		// 버튼을 추가하는 새로운 컬럼 생성
		TableColumn<Member, Void> buttonColumn = new TableColumn<>("선택");
		buttonColumn.setMinWidth(50);

		Callback<TableColumn<Member, Void>, TableCell<Member, Void>> cellFactory = param -> {
			final TableCell<Member, Void> cell = new TableCell<Member, Void>() {
				private final Button button = new Button("+");

				{
					button.setOnAction(event -> {
						Member data = getTableView().getItems().get(getIndex());
						// 버튼 클릭 시 실행할 동작 정의
						TextArea reportContentsText = (TextArea) root.lookup("#reportContentsText");
						reportContentsText.appendText("<신고할 닉네임>\n"+"회원 번호 : " + data.getNum() + "\n" + "닉네임 : " + data.getNickName() +"\n");
					});
				}

				@Override
				protected void updateItem(Void item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
					} else {
						setGraphic(button);
					}
				}
			};
			return cell;
		};

		buttonColumn.setCellFactory(cellFactory);
		memNum.setCellValueFactory(new PropertyValueFactory<>("num"));
		memNickName.setCellValueFactory(new PropertyValueFactory<>("nickName"));
		memId.setCellValueFactory(new PropertyValueFactory<>("id"));

		listView.getColumns().addAll(memNum, memNickName, memId, buttonColumn);
		
	    // 셀 가운데 정렬
		memNum.setStyle("-fx-alignment: CENTER;");
	    buttonColumn.setStyle("-fx-alignment: CENTER;");
	    // 셀 가운데 왼쪽 정렬
	    memNickName.setStyle("-fx-alignment: CENTER-LEFT;");
	    memId.setStyle("-fx-alignment: CENTER-LEFT;");
	}

	// 중복 코드 줄이려고 만든 메서드 - 테이블 뷰에 컬럼 설정 - 신고 기능 - 아이디 테이블뷰
	@Override
	public void configureBoardTableView3(TableView<Member> listView, Parent root) {
		listView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		TableColumn<Member, Integer> memNum = new TableColumn<>("No.");
		memNum.setMinWidth(20);
		TableColumn<Member, String> memId = new TableColumn<>("아이디");
		memId.setMinWidth(200);
		TableColumn<Member, String> memNickName = new TableColumn<>("닉네임");
		memNickName.setMinWidth(200);
		
		// 버튼을 추가하는 새로운 컬럼 생성
		TableColumn<Member, Void> buttonColumn = new TableColumn<>("선택");
		buttonColumn.setMinWidth(50);

		Callback<TableColumn<Member, Void>, TableCell<Member, Void>> cellFactory = param -> {
			final TableCell<Member, Void> cell = new TableCell<Member, Void>() {
				private final Button button = new Button("+");

				{
					button.setOnAction(event -> {
						Member data = getTableView().getItems().get(getIndex());
						// 버튼 클릭 시 실행할 동작 정의
						TextArea reportContentsText = (TextArea) root.lookup("#reportContentsText");
						reportContentsText.appendText("<신고할 아이디>\n"+"회원 번호 : " + data.getNum() + "\n" + "닉네임 : " + data.getId() +"\n");
					});
				}

				@Override
				protected void updateItem(Void item, boolean empty) {
					super.updateItem(item, empty);
					if (empty) {
						setGraphic(null);
					} else {
						setGraphic(button);
					}
				}
			};
			return cell;
		};

		buttonColumn.setCellFactory(cellFactory);
		memNum.setCellValueFactory(new PropertyValueFactory<>("num"));
		memId.setCellValueFactory(new PropertyValueFactory<>("id"));
		memNickName.setCellValueFactory(new PropertyValueFactory<>("nickName"));

		listView.getColumns().addAll(memNum, memId, memNickName, buttonColumn);
		
	    // 셀 가운데 정렬
	    buttonColumn.setStyle("-fx-alignment: CENTER;");
	    memNum.setStyle("-fx-alignment: CENTER;");
	    // 셀 가운데 왼쪽 정렬
	    memId.setStyle("-fx-alignment: CENTER-LEFT;");
	    memNickName.setStyle("-fx-alignment: CENTER-LEFT;");
	}
	
	// TableColumn의 컬럼명(Label) 가운데 정렬하는 메서드
	private <T> void setColumnHeaderAlignment(TableColumn<T, ?> column) {
	    Label label = new Label(column.getText());
	    label.setStyle("-fx-alignment: CENTER;");
	    column.setGraphic(label);
	}

}
