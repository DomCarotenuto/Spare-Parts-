package com.NewControl;

import java.io.IOException;
import java.sql.SQLException;
import it.unisa.DriverManagerConnectionPool;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.NewBean.UtenteBean;
import com.NewModel.UtenteModelDM;



import com.NewBean.Cart;
import com.NewBean.ProductBean;
import com.NewModel.ProductModel;
import com.NewModel.ProductModelDM;

/**
 * Servlet implementation class Prodotti
 */
@WebServlet("/Prodotti")
public class Prodotti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = true;
	
	static ProductModel model= new ProductModelDM();
	
	public Prodotti() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		String  type = (String)request.getSession().getAttribute("type");
		request.getSession().setAttribute("type",type);
		request.setAttribute("type", type);

	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/prodotti.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
	

