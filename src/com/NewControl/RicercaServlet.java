package com.NewControl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.NewBean.Cart;
import com.NewBean.ProductBean;
import com.NewModel.ProductModelDM;



@WebServlet("/Ricerca")
public class RicercaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ProductModelDM prodottoDAO = new ProductModelDM();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		
		List<ProductBean> prodotti = prodottoDAO.doRetrieveByNomeOrDescrizione(request.getParameter("ric"));
		request.setAttribute("prodotti", prodotti);
		
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		String  type = (String)request.getSession().getAttribute("type");
		request.getSession().setAttribute("type",type);
		request.setAttribute("type", type);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/ricerca.jsp");
		requestDispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
