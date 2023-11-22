package all.boardService;

public class Board {
	
	// 게시물 번호 - board_seq
	private int No;
	// 닉네임 - board_memnick / foreign key(board_memnick) references member(member_nickname)
	private String nicName;
	// 게시물 제목 - title
	private String title;
	// 카테고리 - category (자유 게시판, 구매 게시판, 판매 게시판, 나눔 게시판)
	private String categori;
	// 업로드 날자 - contents_date
	private String uploadDate;
	// 게시물 내용 - contents
	private String contents;
	// 아이디 - board_memid / foreign key(board_memid) references member(member_id)
	private String id;
	
	// getter setter 부분
	public int getNo() {
		return No;
	}
	public void setNo(int no) {
		No = no;
	}
	public String getNicName() {
		return nicName;
	}
	public void setNicName(String nicName) {
		this.nicName = nicName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCategori() {
		return categori;
	}
	public void setCategori(String categori) {
		this.categori = categori;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

	
	
	
}
