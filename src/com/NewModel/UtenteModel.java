package com.NewModel;

import java.sql.SQLException;
import com.NewBean.UtenteBean;

public interface UtenteModel {
	public  void doSave(UtenteBean utente) throws SQLException;
	
	public void doUpdate(UtenteBean utente) throws SQLException;
		
	
	
}
