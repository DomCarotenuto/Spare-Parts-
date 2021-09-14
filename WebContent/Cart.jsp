<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		
		response.sendRedirect("./product");	
		return;
	}
	
	
	ProductBean product = (ProductBean) request.getAttribute("product");
	
	
	
%>

<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.NewModel.*,com.NewBean.*,com.NewControl.*"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Carrello</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
  <link rel="stylesheet" href="style.css">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;300;400;700;900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.min.css" integrity="sha512-BiFZ6oflftBIwm6lYCQtQ5DIRQ6tm02svznor2GYQOfAlT3pnVJ10xCrU3XuXnUrWQ4EG8GKxntXnYEdKY0Ugg==" crossorigin="anonymous" />
  <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
</head>


<%@include file="/Header.jsp" %>

		<% if(cart != null) {  
			
				ArrayList<Item> prodcart = cart.getProducts(); 
				
				
			if(prodcart.size() == 0){
				%> 
				<div class="grid"><h2 class="med-text">Non ci sono prodotti nel carrello</h2> </div>
				
			<% }else
			{
		
		%>
		
		<div class="grid"><h2 class="med-text">Carrello</h2> </div>
		
		<div class="grid"><table>
		    
		    <tr>
		    <th></th>
			<th><p>Prodotto</p></th>
			<th><p>Costo unitario</p></th>
			<th><p>Costo totale</p></th>
			<th><p>Quantità</p></th>
			<th></th>
			</tr>
			
			
		
		
		
		<% 
		
		for(Item beancart: prodcart) { 
			
			
			
		%>
		
	<tr>
			<td><img src="src/<%=beancart.getPhoto()%>" width="80px"></td>
			<td><%=beancart.getName()%></td>
			<td><%=beancart.getUnitCost()%></td>
			<td><%=beancart.getTotalCost()%></td>
			
			<td><%=beancart.getNumProduct()%></td>
			
			<td>
			<div style="float: left;
    background-color: #19f;
    padding: 8px;
    margin-left: 0;
    color: #fff; margin-right: 10px; margin-bottom: 8px;"
    ><a style="color: #fff;" href="CartS?action=deleteC&id=<%=beancart.getCode()%>">-</a></div>
			<div style=" margin-left: 8px; float: left;
    background-color: #19f;
    padding: 8px;
    margin-left: 0;
    color: #fff;"><a style="color: #fff;" href="CartS?action=addC&id=<%=beancart.getCode()%>">+</a></div>
			</td>
			<%} %>
		</tr>
			
		</table>
	</div>
	
	
	
	<div class="grid">
	<div class="col">
	<div class="button mt-2"><a href="./Checkout">Procedi al Checkout</a></div>
	</div>
	<div class="col">
	</div>
	</div>
	<%  } %>
	
	
			<% }%>
	
<%@include file="/Footer.html" %>
<script src="<%=request.getContextPath() %>/script/jquery-3.5.1.js"></script>
	  <script>
	  	function aumentaQuantita(cod){
	  		$.get("./UpdateQuantita?id="+cod, function(data){
	  			var quantita = JSON.parse(data.quantita);
	  			var costoTot = data.costoTot;
	  			
	  			$("#costo-totale"+cod).html(costoTot);
	  			$("#quantita"+cod).html(quantita);
	  		})
	  		 //window.location.reload(true);
	  	}
	  </script>



