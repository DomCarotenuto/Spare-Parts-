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
import com.NewModel.ProductModel;

@WebServlet("/Home")
/**
 * Servlet implementation class ProductControl
 */
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	static boolean isDataSource = true;
	
	static ProductModel model= new ProductModelDM();
	
	public Home() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart cart = (Cart)request.getSession().getAttribute("cart");
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		String action = request.getParameter("action");

		try {
			if (action != null) {
				
				if (action.equalsIgnoreCase("addC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.addProduct(model.doRetrieveByKey(id));
				} else if (action.equalsIgnoreCase("deleteC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					cart.deleteProduct(model.doRetrieveByKey(id));
					
				
					
					
				} else if (action.equalsIgnoreCase("read")) {
					int id = Integer.parseInt(request.getParameter("id"));
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
					
				} 
				
				
				
				//CATEGORIA A SCELTA
				else	if(action.equalsIgnoreCase("get")) {
					String tipoString=request.getParameter("categoria");
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByCategoria(tipoString));
				}
			
				
				
				
			
				else if (action.equalsIgnoreCase("updateC")) {
					int id = Integer.parseInt(request.getParameter("id"));
					request.removeAttribute("product");
					request.setAttribute("product", model.doRetrieveByKey(id));
				}
				
				else if (action.equalsIgnoreCase("delete")) {
					int id = Integer.parseInt(request.getParameter("id"));
					model.doDelete(id);
				} else if (action.equalsIgnoreCase("insert")) {
					int code = Integer.parseInt(request.getParameter("code"));
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					int price = Integer.parseInt(request.getParameter("price"));
					//int quantity = Integer.parseInt(request.getParameter("quantity"));

					ProductBean bean = new ProductBean();
					bean.setCode(code);
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					//OPERAZIONI DEL PRODUCT BEAN
					model.doSave(bean);
				}
				else if (action.equalsIgnoreCase("update")) {
					int code = Integer.parseInt(request.getParameter("code"));
					String name = request.getParameter("name");
					String description = request.getParameter("description");
					int price = Integer.parseInt(request.getParameter("price"));
					//int quantity = Integer.parseInt(request.getParameter("quantity"));

					ProductBean bean = new ProductBean();
					bean.setCode(code);
					bean.setName(name);
					bean.setDescription(description);
					bean.setPrice(price);
					//OPERAZIONI DEL PRODUCT BEAN
					model.doUpdate(bean);
				}
				
				
			}			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}

	
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		
		String sort = request.getParameter("sort");

		try {
			request.removeAttribute("products");
			request.setAttribute("products", model.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
		
		String  type = (String)request.getSession().getAttribute("type");
		request.getSession().setAttribute("type",type);
		request.setAttribute("type", type);
		
		
		

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
	

