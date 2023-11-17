package all.button.infoButton;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import all.button.common.CommonService;
import all.button.common.CommonServiceImp;
import all.databaseDAO.DatabaseDAOImp;

public class proButtonImp implements proButton {

	CommonService cs;
	Connection con;
	ResultSet rs;
	PreparedStatement pstmt;
	DatabaseDAOImp dao;
	
	
	public proButtonImp() {
		// TODO Auto-generated constructor stub
		dao = new DatabaseDAOImp();
		cs = new CommonServiceImp();		
	}

	@Override
	public void profileProc() {
		// TODO Auto-generated method stub
			
			
			 
	}

		


}	

