package com.NewControl;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.NewModel.ProductModelDM;
import com.mysql.cj.Session;
import com.NewBean.Cart;
import com.NewBean.ComposizioneBean;
import com.NewBean.Item;
import com.NewBean.OrdineBean;
import com.NewBean.ProductBean;
import com.NewBean.UtenteBean;
import com.NewModel.OrdineModelDM;
import com.NewModel.ProductModel;

@WebServlet("/Ordine")
/**
 * Servlet implementation class Ordine
 */
public class Ordine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	static boolean isDataSource = true;
	
	
	public Ordine() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart cart = (Cart)request.getSession().getAttribute("cart");
		
		if(cart == null) {
			cart = new Cart();
			request.getSession().setAttribute("cart", cart);
		}
		
		
		ArrayList<Item> prodcart = cart.getProducts(); 
		
				
				int codice_utente=(int) request.getSession().getAttribute("code");
					
			
			String action = request.getParameter("action");

				try {
					if (action != null) {
						
						if (action.equalsIgnoreCase("insert")) {
							//String data_ordine = request.getParameter("data_ordine");
							
							//TOTALE CARRELLO
							float totale=cart.getTotale();
							//String codice_utente=utente.getID();

							OrdineBean bean = new OrdineBean();
							LocalDate todaysDate = LocalDate.now();
						
							bean.setImporto(totale);
							bean.setData_ordine(todaysDate.toString());
							
							bean.setCod_utente(codice_utente);
							                           	
							
							//OPERAZIONI DEL PRODUCT BEAN
							OrdineModelDM.doSave(bean);
							
							
							ComposizioneBean bean1= new ComposizioneBean();
							
							for(Item beancart: prodcart) {
								int quantita=beancart.getNumProduct();
								int codice_prodotto=beancart.getCode();
								float prezzo_unitario= (float) beancart.getUnitCost();
								float prezzo_totale= (float) beancart.getTotalCost();
								OrdineBean value = new OrdineBean();
								value=OrdineModelDM.doLastOrdine();

								
								bean1.setQuantita(quantita);
								bean1.setCod_prodotto(codice_prodotto);
								bean1.setCod_ordine(value.getCode());
								bean1.setPrezzo_unitario(prezzo_unitario);
								bean1.setPrezzo_totale(prezzo_totale);
								OrdineModelDM.doSaveComposizione(bean1);
							
							}
						
							
							
						}
						
					}
					
					
				}
				 catch (SQLException e) {
					System.out.println("Error:" + e.getMessage());
				}
			
			 
		
		
		

		request.getSession().setAttribute("cart", cart);
		request.setAttribute("cart", cart);
		
		
		request.getSession().setAttribute("cart", null);
		
		String  type = (String)request.getSession().getAttribute("type");
		request.getSession().setAttribute("type",type);
		request.setAttribute("type", type);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/ordine.jsp");
		dispatcher.forward(request, response);
		
		
					
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}
	

