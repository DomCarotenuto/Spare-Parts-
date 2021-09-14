<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		
		response.sendRedirect("./product");	
		return;
	}
	
	
	ProductBean product = (ProductBean) request.getAttribute("product");
	
	
	Cart cart = (Cart) request.getAttribute("cart");
%>

<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.NewModel.*,com.NewBean.*,com.NewControl.*,com.NewBean.Cart"%>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Admin</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
  <link rel="stylesheet" href="admin/Adminstyle.css">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;300;400;700;900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.min.css" integrity="sha512-BiFZ6oflftBIwm6lYCQtQ5DIRQ6tm02svznor2GYQOfAlT3pnVJ10xCrU3XuXnUrWQ4EG8GKxntXnYEdKY0Ugg==" crossorigin="anonymous" />
  <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>
</head>
<body>
<jsp:useBean id="UtenteBean" class="com.NewBean.UtenteBean" scope="session"/>
<%
	
	String user = null;
	String type = null;
	if(session.getAttribute("user")== null){
		String redirectedPage = "/Home";
		response.sendRedirect(request.getContextPath() + redirectedPage);
%>
	
<%	
	
	}
	else {
	user = (String)session.getAttribute("user");
	type = (String)session.getAttribute("type");
	
	
	    
	if(type.equals("adm")){%>    
		
<div class="">
   <ul class="menu-admin">
    <li><a href="./Home">Vai al sito</a></li>
      <li><a href="admin-page.jsp">Home</a></li>
      <li><a href="admin-prodotti.jsp">Prodotti</a></li>
      <li><a href="./AdminOrdini">Ordini</a></li>
      
    </ul>
</div>

<div style="margin-left:18%;padding:10px 10px;">
	
	<a class="button" href="#aggiungi-prodotto">Aggiungi prodotto</a>
	
	<%//} else{ %>
      <!--  <h1>Non sei un amministratore!</h1>-->
     

	<div style="margin: 50px;"></div>
<h2 class="med-text">Prodotti disponibili</h2>
	<a href="product">List</a>
	<table class="admin-table">
		<tr>
			<th></th>
			<th><h5>Codice</h5><a href="product?sort=code">Sort</a></th>
			<th><h5>Nome</h5><a href="product?sort=name">Sort</a></th>
			<th><h5>Descrizione</h5><a href="product?sort=description">Sort</a></th>
			<th><h5>Action</h5></th>
		</tr>
		<!-- VISUALIZZAZIONE DEI PRODOTTI -->
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
		%>
		
		<tr>
			<tr>
			<td>
			<%if(bean.getPhoto()!=null) {
				String img = bean.getPhoto();
			%><img src="src/<%=img %>" height=80 />
			<% } else { %>
			<img src="<%=request.getContextPath()%>/images/unnamed1.jpg" width=50px/>
			<%} %>
			<td><%=bean.getCode()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getDescription()%></td>
			<td><a href="product?action=delete&id=<%=bean.getCode()%>">Delete</a><br>
				
				
				<a href="product?action=updateC&id=<%=bean.getCode()%>">Update</a><br>
				
				
				
				<%--<a href="product?action=addC&id= %>=bean.getCode()">Add to cart</a>--%>
				</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%>
	</table>
	
	
	
	
	<br><br><br><br><br><br><br><br>
	
	<h2 id="aggiungi-prodotto">Insert</h2>
	<form action="product" method="post">
		<input type="hidden" name="action" value="insert"> 
		
		<label for="name">Code:</label><br> 
		<input name="code" type="text" maxlength="20" required placeholder="enter code"><br>
		
		<label for="name">Name:</label><br> 
		<input name="name" type="text" maxlength="20" required placeholder="enter name"><br> 
		
		<label for="description">Description:</label><br>
		<textarea name="description" maxlength="300" rows="3" required placeholder="enter description"></textarea><br>
		
		<label for="price">Price:</label><br> 
		<input name="price" type="text" min="0" value="0" placeholder="0" required><br>
		
		<label for="name">Category:</label><br> 
		<input name="category" type="text" maxlength="50" required placeholder="enter name"><br> 
		
		<label for="name">Photo:</label><br>
		<input type="file" name="photo" id="fileToUpload" accept="image/png, image/jpeg"> <br>
		
		

		<input type="submit" value="Add"><input type="reset" value="Reset">
	</form>
	
	<br><br><br><br><br><br><br><br><br><br><br><br>
	
	
	<%
		if (product != null) {
				
			
		
	%>
	<h2 id="modifica-prodotto">Modifica</h2>
	<form action="product" method="post">
		<input type="hidden" name="action" value="update"> 
		
		<label for="name">Codice:</label><br> 
		<input name="code" type="text" maxlength="100" required value="<%=product.getCode()%>"><br>
		
		<label for="name">Nome:</label><br> 
		<input name="name" type="text" maxlength="100" required value="<%=product.getName()%>"><br> 
		
		<label for="name">Descrizione:</label><br>
		<input name="description" type="text" required value="<%=product.getDescription()%>"></input><br>
		
		<label for="price">Prezzo:</label><br> 
		<input name="price" type="number" min="0" value="<%=product.getPrice()%>" required><br>
		
		<label for="name">Categoria:</label><br>
		<input name="description" type="text" required value="<%=product.getCategory()%>"></input><br>
		
		<label for="name">Immagine:</label><br>
		<input type="file" name="photo" id="fileToUpload" accept="image/png, image/jpeg"> <br>

		<input type="submit" value="Aggiorna"><input type="reset" value="Reset">
	</form>
	
	
	<%} %>
	
	<%--<% if(cart != null) { 
		<h2>Cart</h2>
		<table border="1">
		<tr>
			<th>Name</th>
			<th>Quantità</th>
			<th>Action</th>
		</tr>
		<% ArrayList<Item> prodcart = cart.getProducts(); 	
		   for(Item beancart: prodcart) {
		%>
		<tr>
			<td><%=beancart.getName()%></td>
			<td><%=beancart.getNumProduct()%></td>
			<td><a href="product?action=deleteC&id=<%=beancart.getCode()%>">Delete from cart</a></td>
		</tr>
		<%} %>
	</table>		
	<% } %>	

</div>
--%>
</div>
    <%}else response.sendRedirect("./admin/loginAdmin.jsp"); %>
<%}%>
  

  <!-- Jquery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.pkgd.min.js" integrity="sha512-cA8gcgtYJ+JYqUe+j2JXl6J3jbamcMQfPe0JOmQGDescd+zqXwwgneDzniOd3k8PcO7EtTW6jA7L4Bhx03SXoA==" crossorigin="anonymous"></script>
  <script>
    $( document ).ready(function() {
      /* Open Panel */
      $( ".hamburger" ).on('click', function() {
        $(".menu").toggleClass("menu--open");
      });
    });

    ScrollReveal().reveal('.reveal', { distance: '70px', duration: 1500, interval: 600});
    ScrollReveal().reveal('.zoom', {  duration: 500, interval: 200 , scale: 0.65 , mobile: false});
  </script>
</body>
</html>
