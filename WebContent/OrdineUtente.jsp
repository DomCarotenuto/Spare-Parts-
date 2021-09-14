<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	Collection<?> products = (Collection<?>) request.getAttribute("products");
	
	ProductBean product = (ProductBean) request.getAttribute("product");
	
%>


<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.NewModel.*,com.NewBean.*,com.NewControl.*,com.NewBean.Cart"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Grazie per l'acquaisto!</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
  <link rel="stylesheet" href="style.css">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;300;400;700;900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.min.css" integrity="sha512-BiFZ6oflftBIwm6lYCQtQ5DIRQ6tm02svznor2GYQOfAlT3pnVJ10xCrU3XuXnUrWQ4EG8GKxntXnYEdKY0Ugg==" crossorigin="anonymous" />
  <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
</head>


<%@include file="/Header.jsp" %>
     
  
     <div class="grid"><h2 class="med-text">Ordini Effettuati</h2></div>
     <div class="grid">
    <table >
		<tr>
			<th><h5>Numero dell'ordine</h5>
			<th><h5>Totale</h5>
			<th><h5>Data dell'ordine</h5><a href="OrdineUtente?sort=data_ordine">Sort</a></th>
			<th></th>
		</tr>
		<!-- VISUALIZZAZIONE DEI PRODOTTI -->
		<%
			if (products != null && products.size() != 0) {
				
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					OrdineBean bean = (OrdineBean) it.next();
		%>
		
		<tr>
			
			<td>#<%=bean.getCode()%></td>
			<td><%=bean.getImporto()%>0 €</td>
			<td><%=bean.getData_ordine()%></td>
			<td><a href="DettagliOrdine?action=view&id=<%=bean.getCode()%>">Dettagli ordine</a></td>
			
		
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">No Order available</td>
		</tr>
		<%
			}
		%>
	</table>
	</div>
	

    
<%@include file="/Footer.html" %>

