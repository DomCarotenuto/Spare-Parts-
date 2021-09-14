package com.NewControl;

import java.io.IOException;
import java.sql.SQLException;
import it.unisa.DriverManagerConnectionPool;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.NewBean.UtenteBean;
import com.NewModel.UtenteModelDM;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LLogin")

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		{
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
		
			String redirectedPage;
			
			try {
				
				
				
				UtenteBean user= UtenteModelDM.doRetrieveUtenteByUsernamePassword(username,password);
				
				/*if(user==null) {redirectedPage="/ErrorLogin.html"; System.out.println("USER = NULL!");}
				if(password.equals(user.getPassword())) {
					request.getSession().setAttribute("utente", user);
					if(user.getTipo().equals("adm")) {
						redirectedPage = "/ModificaSAMMINISTRATORE";}
						else redirectedPage = "/benvenuto.jsp";
				}else redirectedPage="/ErrorLogin.html";*/
				
				
				
				if(user.getEmail().equals(username )) {
					//Se l'autenticazione va a buon fine 
					//Recupero la sessione
					
					
					HttpSession oldSession = request.getSession();
					if(oldSession!=null) { //Se esiste una sessione la invalida
						oldSession.invalidate(); 
					}
					
					
					HttpSession currentSession = request.getSession();//crea una nuova sessione
					
					currentSession.setAttribute("user",user.getNome());
					currentSession.setAttribute("type",user.getTipo());
					currentSession.setAttribute("code",user.getID());
					
					
					
					currentSession.setMaxInactiveInterval(60*100);
					
					if(user.getTipo().equals("adm")) {
						redirectedPage = "/admin-page.jsp";}
						else redirectedPage = "/Home";
					response.sendRedirect(request.getContextPath() + redirectedPage);
					
				}else {
					//AUTENTICAZIONE FALLITA
					redirectedPage = "/ErrorLogin";
					response.sendRedirect(request.getContextPath() + redirectedPage);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				redirectedPage = "/ErrorLogin";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			}
			//response.sendRedirect(request.getContextPath() + redirectedPage);
		}
	}
	
	

	public LoginServlet() {
		super();
	}	
	
}
		


