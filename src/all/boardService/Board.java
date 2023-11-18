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
	// 이미지 첨부 주소 - imagepath
	private byte[] imagePath;  	
	// howmanypost? 이게 뭔지 모르겠어요 주석 달아주세요. - 선준
	private String commet;
	// 아이디 - board_memid / foreign key(board_memid) references member(member_id)
	private String id;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNicName() {
		return nicName;
	}
	public void setNicName(String nicName) {
		this.nicName = nicName;
	}
	public int getNo() {
		return No;
	}
	public void setNo(int no) {
		No = no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getCategori() {
		return categori;
	}
	public void setCategori(String categori) {
		this.categori = categori;
	}
	public byte[] getImagePath() {
		return imagePath;
	}
	public void setImagePath(byte[] imagePath) {
		this.imagePath = imagePath;
	}
	public String getCommet() {
		return commet;
	}
	public void setCommet(String commet) {
		this.commet = commet;
	}
	public String getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(String uploadDate) {
		this.uploadDate = uploadDate;
	}
	
	
	
}
