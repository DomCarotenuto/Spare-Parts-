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

@WebServlet("/AdminDettagliOrdini")
/**
 * Servlet implementation class ProductControl
 */
public class AdminDettagliOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = true;
	
	static OrdineModelDM model= new OrdineModelDM();
	
	public AdminDettagliOrdini() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		
		String sort = request.getParameter("sort");
		
		String action = request.getParameter("action");
		if (action != null) {
			
			if (action.equalsIgnoreCase("view")) {
				int id = Integer.parseInt(request.getParameter("id"));
		
		
		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveDettagliOrdine1(sort,id));
			request.removeAttribute("products1");
			request.setAttribute("products1", model.doRetrieveDettagliOrdine2(sort,id));
			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
			}
		}

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		String  type = (String)request.getSession().getAttribute("type");
		request.getSession().setAttribute("type",type);
		request.setAttribute("type", type);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/AdminDettagliOrdini.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}