package com.NewControl;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.NewBean.UtenteBean;

public class MyServletException extends ServletException {
	private static final long serialVersionUID = -8976023136478643816L;

	public MyServletException() {
		super();
	}

	public MyServletException(String message, Throwable rootCause) {
		super(message, rootCause);
	}

	public MyServletException(String message) {
		super(message);
	}

	public MyServletException(Throwable rootCause) {
		super(rootCause);
	}

	public static void checkAdmin(HttpServletRequest request) throws MyServletException {
	/*	UtenteBean utente = (UtenteBean) request.getSession().getAttribute("utente");
		
		if (utente == null || !utente.isAdmin()) {
			throw new MyServletException("Utente non autorizzato");
		}*/
	}

}
