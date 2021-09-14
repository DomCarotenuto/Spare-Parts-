package com.NewModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import it.unisa.DriverManagerConnectionPool;

import com.NewBean.UtenteBean;
import com.NewModel.UtenteModel;




public class UtenteModelDM implements UtenteModel {
	
	static final String TABLE_NAME = "utente"; //TABELLA
	
	
	
	
	
	public synchronized void doSave(UtenteBean utente) throws SQLException {
		
		String tipo="NOAD";
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		
		
		String insertSQL = "INSERT INTO " + UtenteModelDM.TABLE_NAME + "(nome,cognome,telefono,indirizzo,email,password,numero_carta)"
				+ " VALUES (?,?,?,?,?,?,?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, utente.getNome());
			preparedStatement.setString(2, utente.getCognome());
			preparedStatement.setString(3, utente.getTelefono());
			preparedStatement.setString(4, utente.getIndirizzo());
			preparedStatement.setString(5, utente.getEmail());
			preparedStatement.setString(6, utente.getPassword());
			preparedStatement.setString(7, utente.getNumero_carta());
			preparedStatement.executeUpdate();
			connection.commit(); //rende permanenti tutte le modifiche
			
		}finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		}
	
	
	public static synchronized UtenteBean doRetrieveUtenteByUsernamePassword(String usn, String passw) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UtenteBean bean = new UtenteBean();
		
	
		String selectSQL = "SELECT * FROM " + UtenteModelDM.TABLE_NAME + " WHERE email = ? AND password=SHA1(?)";
		
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, usn);
			preparedStatement.setString(2, passw);

			ResultSet rs = preparedStatement.executeQuery();

			
			
			while (rs.next()) {
				
				bean.setID(rs.getInt("ID"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setEmail(rs.getString("email"));
				
				bean.setPassword(rs.getString("password"));
				
				bean.setNumero_carta("numero_carta");
				bean.setTipo(rs.getString("tipo"));
			}
		} 
		finally{
			  try{
				 	if (preparedStatement != null)
					preparedStatement.close();
			       } 
			 	finally{
				DriverManagerConnectionPool.releaseConnection(connection);
			      }
			  }
		
		return bean;
	}
	
	public synchronized void doUpdate(UtenteBean utente) throws SQLException{
		String tipo="NOAD";
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement("UPDATE utente SET ID=?, NOME=?, COGNOME=?, TELEFONO=?, INDIRIZZO=?, EMAIL=?, PASSWORD=?, NUMERO_CARTA=?, TIPO=?  WHERE ID=?");
			preparedStatement.setInt(1, utente.getID());
			preparedStatement.setString(2, utente.getNome());
			preparedStatement.setString(3, utente.getCognome());
			preparedStatement.setString(4, utente.getTelefono());
			preparedStatement.setString(5, utente.getIndirizzo());
			preparedStatement.setString(6, utente.getEmail()); 
			preparedStatement.setString(7, utente.getPassword());
			preparedStatement.setString(8, utente.getNumero_carta());
			preparedStatement.setString(9, tipo);
			preparedStatement.setInt(10, utente.getID());
			
			preparedStatement.executeUpdate();

			connection.commit();//rende permanenti tutte le modifiche
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
	}
		
		
	
	
	public static synchronized UtenteBean doRetrieveUtenteByCode(int code) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UtenteBean bean = new UtenteBean();
		
		String selectSQL = "SELECT * FROM " + UtenteModelDM.TABLE_NAME + " WHERE ID = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			
			
			while (rs.next()) {
				bean.setID(rs.getInt("ID"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setEmail(rs.getString("email"));
				bean.setPassword(rs.getString("password"));
				bean.setNumero_carta(rs.getNString("numero_carta"));
				bean.setTipo(rs.getString("tipo"));
			}
		} 
		finally{
			  try{
				 	if (preparedStatement != null)
					preparedStatement.close();
			       } 
			 	finally{
				DriverManagerConnectionPool.releaseConnection(connection);
			      }
			  }
		
		return bean;
	}
		
		
	public static synchronized UtenteBean doRetrieveUtenteByPassword(String passw) throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UtenteBean bean = new UtenteBean();
		
	
		String selectSQL = "SELECT * FROM " + UtenteModelDM.TABLE_NAME + " WHERE email = ? AND password=SHA1(?)";
		
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			//preparedStatement.setString(1, usn);
			preparedStatement.setString(2, passw);

			ResultSet rs = preparedStatement.executeQuery();

			
			
			while (rs.next()) {
				
				bean.setID(rs.getInt("ID"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setTelefono(rs.getString("telefono"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setEmail(rs.getString("email"));
				
				bean.setPassword(rs.getString("password"));
				
				bean.setNumero_carta("numero_carta");
				bean.setTipo(rs.getString("tipo"));
			}
		} 
		finally{
			  try{
				 	if (preparedStatement != null)
					preparedStatement.close();
			       } 
			 	finally{
				DriverManagerConnectionPool.releaseConnection(connection);
			      }
			  }
		
		return bean;
	}
	


	
	
	
	
	
	
	

}