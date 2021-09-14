package com.NewControl;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.NewBean.Cart;
import com.NewBean.UtenteBean;
import com.NewModel.UtenteModel;
import com.NewModel.UtenteModelDM;




@WebServlet("/DatiUtente")
public class DatiUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	static UtenteModel model= new UtenteModelDM();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		String password = request.getParameter("password");
		if (!(password != null && password.length() >= 8 && !password.toUpperCase().equals(password)
				&& !password.toLowerCase().equals(password) /*&& password.matches(".*[0-9].*")*/)) {
			throw new MyServletException("Password non valida.");
		}

		String passwordConferma = request.getParameter("passwordConferma");
		if (!password.equals(passwordConferma)) {
			throw new MyServletException("Password e conferma differenti.");
		}

		String nome = request.getParameter("nome");
		if (!(nome != null && nome.trim().length() > 0 /*&& nome.matches("^[ a-zA-Z\u00C0-\u00ff]+$")*/)) {
			throw new MyServletException("Nome non valido.");
		}

		String cognome = request.getParameter("cognome");
		if (!(cognome != null && cognome.trim().length() > 0 /* && cognome.matches("^[ a-zA-Z\u00C0-\u00ff]+$")*/)) {
			throw new MyServletException("Cognome non valido.");
		}
		
		String telefono = request.getParameter("telefono");
		if (!(telefono!= null && telefono.trim().length()>0 /* && telefono.matches("/^\\d{10}$/")*/)) {
			throw new MyServletException("Telefono non valido.");
		}
		
		String email = request.getParameter("email");
		if (!(email != null /*&& email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w+)+$")*/)) {
			throw new MyServletException("Email non valida.");
		}
		
		String indirizzo = request.getParameter("indirizzo");
		if (!(indirizzo != null  && indirizzo.trim().length() > 0 /*&& indirizzo.matches("^[a-zA-Z0-9\u00C0-\u00ff]+$")*/)) {
			throw new MyServletException("Indirizzo non valido.");
		}
		
		String CartaCredito = request.getParameter("CartaCredito");
		if (!(CartaCredito != null  && CartaCredito.trim().length() > 0 /*&& CartaCredito.matches("/^\\d{16}$/")*/	)) {
			throw new MyServletException("Carta di credito non valida.");
		}

		int codice_utente=(int) request.getSession().getAttribute("code");
		
		UtenteBean user = new UtenteBean();
		user.setID(codice_utente);
		user.setNome(nome);
		user.setCognome(cognome);
		user.setIndirizzo(indirizzo);
		user.setPassword(password);
		user.setEmail(email);
		user.setTelefono(telefono);
		user.setNumero_carta(CartaCredito);
		
		try {
			model.doUpdate(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getSession().setAttribute("user", user);
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);

		String  type = (String)request.getSession().getAttribute("type");
		request.getSession().setAttribute("type",type);
		request.setAttribute("type", type);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("DatiUtenteSuccesso.jsp");
		requestDispatcher.forward(request, response);
	}



}
