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
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.NewModel.*,com.NewBean.*,com.NewControl.*,com.NewBean.Cart"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Admin</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
  <link rel="stylesheet" href="style.css">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;300;400;700;900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.min.css" integrity="sha512-BiFZ6oflftBIwm6lYCQtQ5DIRQ6tm02svznor2GYQOfAlT3pnVJ10xCrU3XuXnUrWQ4EG8GKxntXnYEdKY0Ugg==" crossorigin="anonymous" />
  <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
</head>
<body>
<jsp:useBean id="UtenteBean" class="com.NewBean.UtenteBean" scope="session"/>



      <%@include file="/Header.jsp" %>
	
	
      

	
		
	
	<%
		if (product != null) {
	%>
	
		<div class="grid">
		
		<div class="col">
		   <img src="src/<%=product.getPhoto()%>" width="500px">
		</div>
		
		<div class="col">
			<div class="mt-1"></div>
			<h1 class="big-text"><%=product.getName()%></h1>
			<p class="small-text"><%=product.getDescription()%></p>
			
			<p class="med-text">€ <%=product.getPrice()%>0</p>
			
			<div class="button"><a href="CartS?action=addC&id=<%=product.getCode()%>">Aggiungi al carrello</a></div>
			</div>
		
	
	
		</div>
	
	
	<%
		}
	%>
	
	<%@include file="/Footer.html" %>
	
	

 
</body>
</html>
