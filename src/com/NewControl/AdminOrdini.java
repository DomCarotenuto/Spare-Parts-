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

@WebServlet("/AdminOrdini")
/**
 * Servlet implementation class ProductControl
 */
public class AdminOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = true;
	
	static OrdineModelDM model= new OrdineModelDM();
	
	public AdminOrdini() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		String sort = request.getParameter("sort");
		

		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveOrdineAdmin1(sort));
			request.removeAttribute("products1");
			request.setAttribute("products1", model.doRetrieveOrdineAdmin2(sort));
			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

		String  type = (String)request.getSession().getAttribute("type");
		request.getSession().setAttribute("type",type);
		request.setAttribute("type", type);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin-ordini.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}