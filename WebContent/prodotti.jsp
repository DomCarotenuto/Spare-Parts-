<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	
	if(products == null) {
		
		response.sendRedirect("/product");	
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
  
  <title>Prodotti</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
  <link rel="stylesheet" href="style.css">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;300;400;700;900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.min.css" integrity="sha512-BiFZ6oflftBIwm6lYCQtQ5DIRQ6tm02svznor2GYQOfAlT3pnVJ10xCrU3XuXnUrWQ4EG8GKxntXnYEdKY0Ugg==" crossorigin="anonymous" />
  <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
</head>


<%@include file="/Header.jsp" %>
     
     
     
     <!-- Start Griglia -->
     <div class="griglia-prodotti mt-4">
		<!-- VISUALIZZAZIONE DEI PRODOTTI -->
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
		%>
		
      
      <div class="item product">
		
        <div class="image-product img-hover-zoom" id="Immagine"><a href="Dettagli?action=read&id=<%=bean.getCode()%>"><%if(bean.getPhoto()!=null) {
				String img = bean.getPhoto();
			%><img src="src/<%=img %>" height="300px" />
			<%}  else { %>
			<img src="<%=request.getContextPath()%>/images/unnamed1.jpg" height="300px"/>
			<%} %>
			</a>
        </div>
         
        <div class="title-product">
          <h3 class="normal-text"><%=bean.getName()%></h3>
          <p class="med-text prezzo">€ <%=bean.getPrice()%>0</p>
          
        </div>
      
      </div>
      
      
      
			
					
				<%--<a href="product?action=addC&id= %>=bean.getCode()">Add to cart</a>--%>
				
		
		
		<%
				}
			} else {
		%>
		
			<h1>No products available</h1>
		
		<%
			}
		%>

</div>
		

   
    
<%@include file="/Footer.html" %>

