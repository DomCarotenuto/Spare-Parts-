<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	
	
	
	ProductBean product = (ProductBean) request.getAttribute("product");
	
	

%>

<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.NewModel.*,com.NewBean.*,com.NewControl.*"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Checkout</title>
  
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
  <link rel="stylesheet" href="style.css">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;300;400;700;900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.min.css" integrity="sha512-BiFZ6oflftBIwm6lYCQtQ5DIRQ6tm02svznor2GYQOfAlT3pnVJ10xCrU3XuXnUrWQ4EG8GKxntXnYEdKY0Ugg==" crossorigin="anonymous" />
  <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
  <jsp:useBean id="UtenteBean" class="com.NewBean.UtenteBean" scope="session"/>
  <jsp:useBean id="Cart" class="com.NewBean.Cart" scope="session"/>
</head>
<body>

  <%@include file="/Header.jsp" %>

<!--SPAZIO-->
<div class="mt-4"></div>


         
         
        <div class="grid"><a class="backToCart" href="./CartS">Torna al carrello</a></div>
		<% if(cart != null) {  %>
		
		<div class="grid"><h2 class="med-text">Riepilogo ordine</h2></div>
		
		
		<div class="grid">
		<table border="1">
		<tr>
			<th></th>
			<th>Prodotto</th>
			<th>Prezzo unitario</th>
			<th>Totale</th>
			<th>Quantità</th>
			
			
		</tr>
		<% ArrayList<Item> prodcart = cart.getProducts(); 
		
		

				if (products != null  && products.size() != 0   ) {
					Iterator<?> it = products.iterator();
				
		  		 for(Item beancart: prodcart ) { 
		  			
		  		 %>
		
		<tr>
			<td><img src="src/<%=beancart.getPhoto()%>" width="100px"></td>
			<td><%=beancart.getName()%></td>
			<td><%=beancart.getUnitCost()%>0 €</td>
			<td><%=beancart.getTotalCost()%>0 €</td>
			<td><%=beancart.getNumProduct()%></td>
		
			
		</tr>

		<%} %>
	</table>
		
	</div>		
	<% }} else%><h1>No product in a Cart!</h1>
	
	
	<div class="mt-1"></div>
	<div class="grid"><p class="med-text">Totale: <span><%=cart.getTotale()%>0 €</span><p></div>
	
	<%
	
	
	if(session.getAttribute("user")== null){
%>
	  
      <div class="grid">
	
	
	<div class="col">
     
     <div class="mt-1"></div>
	<div class="button"><a href="./admin/loginAdmin.jsp">Acquista</a></div>
	
	
	</div>
	<div class="col"></div>
	</div>
<%	
	}
	
	else {user = (String)session.getAttribute("user");
	String adm = UtenteBean.getTipo();
	//if(adm.equals("adm")){%>
	
	<div class="grid"><div class="button"><a href="Ordine?action=insert">Procedi all'ordine</a></div></div>
	


<%} %>



		
<%@include file="Footer.html"%>
</body>



</html>

