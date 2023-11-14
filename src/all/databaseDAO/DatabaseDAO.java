package all.databaseDAO;


public interface DatabaseDAO {

	boolean idChk(String name, String phoneNum);
	String findId(String findName, String findPhoneNum);
	boolean pwChk(String findId, String findPhoneNum);
	String findPw(String findId, String findPhoneNum);
	String findUserName(String findId, String findPhoneNum);
	
}
