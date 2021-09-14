package com.NewControl;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.NewModel.ProductModelDM;
import com.NewBean.Cart;
import com.NewBean.ProductBean;
import com.NewModel.OrdineModelDM;
import com.NewModel.ProductModel;

@WebServlet("/ErrorLogin")
/**
 * Servlet implementation class ProductControl
 */
public class ErrorLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = true;
	
	static OrdineModelDM model= new OrdineModelDM();
	
	public ErrorLogin() {
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

		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ErrorLogin.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}