package com.NewModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import it.unisa.DriverManagerConnectionPool;
import com.NewBean.*;
import com.NewControl.*;
import java.util.*;

public class OrdineModelDM {

	static final String TABLE_NAME = "ordine";
	

	public static synchronized OrdineBean doLastOrdine() throws SQLException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = new OrdineBean();

		String selectSQL = "SELECT * FROM ordine";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setCode(rs.getInt("cod_ordine"));
			}

			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean; 
	}
	
	
	
	

	public static void doSave(OrdineBean ordine) throws SQLException {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			

			String insertSQL = "INSERT INTO ordine"
					+ " ( importo, data_ordine, cod_utente  ) VALUES ( ?, ?, ?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setFloat(1, ordine.getImporto());
				preparedStatement.setString(2, ordine.getData_ordine());
				preparedStatement.setInt(3, ordine.getCod_utente());

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
	
	public static synchronized OrdineBean doRetrieveLastOrdine() throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		OrdineBean bean = new OrdineBean();

		String selectSQL = "SELECT * FROM ordine";

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setImporto(rs.getFloat("importo"));
				bean.setData_ordine(rs.getString("data_ordine"));
				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return bean;
	}
	
	public Collection<OrdineBean> doRetrieveAll1(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> products = new LinkedList<OrdineBean>();

		String selectSQL = "SELECT * FROM " + OrdineModelDM.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();	
				bean.setCode(rs.getInt("cod_ordine"));
				bean.setImporto(rs.getFloat("importo"));
				bean.setData_ordine(rs.getString("data_ordine"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	public Collection<OrdineBean> doRetrieveOrdineUtente(String order, int codice_utente) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> products = new LinkedList<OrdineBean>();
		
	
		String selectSQL = "SELECT * FROM utente INNER JOIN  ordine ON utente.ID = ordine.cod_utente WHERE cod_utente =? ";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1,codice_utente);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();	
				bean.setCode(rs.getInt("cod_ordine"));
				bean.setImporto(rs.getFloat("importo"));
				bean.setData_ordine(rs.getString("data_ordine"));
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}

	public static void doSaveComposizione(ComposizioneBean bean1) throws SQLException{
	
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			
			

			String insertSQL = "INSERT INTO composizione"
					+ " ( cod_prodotto, cod_ordine, quantita,prezzo_unitario, prezzo_totale ) VALUES ( ?, ?, ?, ?, ?)";

			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(insertSQL);
				preparedStatement.setInt(1, bean1.getCod_prodotto());
				preparedStatement.setInt(2, bean1.getCod_ordine());
				preparedStatement.setInt(3, bean1.getQuantita());
				preparedStatement.setFloat(4, bean1.getPrezzo_unitario());
				preparedStatement.setFloat(5, bean1.getPrezzo_totale());
				
				
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
		
	
	public Collection<ProductBean> doRetrieveOrdineDettagliProdotto(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<ProductBean>();
	
		
		String selectSQL="SELECT *\r\n" + 
				"FROM composizione INNER JOIN product on product.CODE= composizione.cod_prodotto\r\n" + 
				"INNER JOIN ordine on ordine.cod_ordine= composizione.cod_ordine\r\n" + 
				"where ordine.cod_ordine= ?";
	
		/*String selectSQL="SELECT * "
						+ "FROM composizione "
						+ "INNER JOIN product "
						+ "on product.CODE= composizione.cod_ordine "
						+ "INNER JOIN ordine on ordine.cod_ordine= composizione.cod_ordine"; 
			
		*/
		

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				ProductBean beanProdotto = new ProductBean();	
				beanProdotto.setPhoto(rs.getString("PHOTO"));
				beanProdotto.setName(rs.getString("NAME"));
				
				
				products.add(beanProdotto);
				
				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	public Collection<ComposizioneBean> doRetrieveOrdineDettagliOrdine(int id) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<ComposizioneBean> products = new LinkedList<ComposizioneBean>();
	
	
		String selectSQL="SELECT *\r\n" + 
				"FROM composizione INNER JOIN product on product.CODE= composizione.cod_prodotto\r\n" + 
				"INNER JOIN ordine on ordine.cod_ordine= composizione.cod_ordine\r\n" + 
				"where ordine.cod_ordine= ?";
			
		
		

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, id);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				ComposizioneBean beanComposizione = new ComposizioneBean();	
				beanComposizione.setPrezzo_unitario(rs.getFloat("prezzo_unitario"));
				beanComposizione.setPrezzo_totale(rs.getFloat("prezzo_totale"));
				beanComposizione.setQuantita(rs.getInt("quantita"));
				
				
				products.add(beanComposizione);
				
				
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	public Collection<OrdineBean> doRetrieveOrdineAdmin1(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		Collection<OrdineBean> products = new LinkedList<OrdineBean>();

		String selectSQL = "select ordine.cod_ordine, utente.nome,utente.cognome, utente.email, ordine.data_ordine\r\n" + 
				"from ordine INNER JOIN utente ON ordine.cod_utente = utente.ID";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();	
				bean.setCode(rs.getInt("cod_ordine"));
				bean.setData_ordine(rs.getString("data_ordine"));
				
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	public Collection<UtenteBean> doRetrieveOrdineAdmin2(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		Collection<UtenteBean> products = new LinkedList<UtenteBean>();

		String selectSQL = "select ordine.cod_ordine, utente.nome,utente.cognome, utente.email, ordine.data_ordine\r\n" + 
				"from ordine INNER JOIN utente ON ordine.cod_utente = utente.ID";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UtenteBean bean = new UtenteBean();	
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setEmail(rs.getString("email"));
				
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	
	public Collection<ProductBean> doRetrieveDettagliOrdine1(String order , int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		Collection<ProductBean> products = new LinkedList<ProductBean>();

		String selectSQL = "Select product.photo, product.name, composizione.prezzo_unitario, composizione.quantita, composizione.prezzo_totale\r\n" + 
				"    FROM product inner join composizione on product.code= composizione.cod_prodotto \r\n" + 
				"				 inner join ordine on composizione.cod_ordine = ordine.cod_ordine\r\n" + 
				"                 inner join utente on ordine.cod_utente= utente.ID\r\n" + 
				"	where ordine.cod_ordine= ?";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();	
				bean.setPhoto(rs.getString("photo"));
				bean.setName(rs.getString("name"));
				
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	public Collection<ComposizioneBean> doRetrieveDettagliOrdine2(String order, int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		Collection<ComposizioneBean> products = new LinkedList<ComposizioneBean>();

		String selectSQL = "Select product.photo, product.name, composizione.prezzo_unitario, composizione.quantita,composizione.prezzo_totale\r\n" + 
				"    FROM product inner join composizione on product.code= composizione.cod_prodotto \r\n" + 
				"				 inner join ordine on composizione.cod_ordine = ordine.cod_ordine\r\n" + 
				"                 inner join utente on ordine.cod_utente= utente.ID\r\n" + 
				"	where ordine.cod_ordine= ?";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ComposizioneBean bean = new ComposizioneBean();	
				bean.setPrezzo_unitario(rs.getFloat("prezzo_unitario"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo_totale(rs.getFloat("prezzo_totale"));
				
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return products;
	}
	
	
	}