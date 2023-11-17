package all.boardService;


public class Board {
	private String id;
	private String nicName;
	private int No;
	private String title;
	private String contents;
	private String categori;
	private byte[] imagePath;  	
	private String commet;
	private String uploadDate;
	
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
