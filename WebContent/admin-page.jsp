
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<html lang="it">
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
	if(session.getAttribute("user")== null){
		String redirectedPage = "/Home";
		response.sendRedirect(request.getContextPath() + redirectedPage);
		
%>
	
<%	
	}
	
	else {user = (String)session.getAttribute("user");
	String adm = UtenteBean.getTipo();
	//if(adm.equals("adm")){%>
<div class="">
   <ul class="menu-admin">
      <li><a href="./Home">Vai al sito</a></li>
      <li><a href="admin-page.jsp">Home</a></li>
      <li><a href="admin-prodotti.jsp">Prodotti</a></li>
      <li><a href="./AdminOrdini">Ordini</a></li>
      
    </ul>
</div>

<div style="margin-left:18%;padding:10px 10px;"><h3 class="med-text"> CIAO, ${user}</h3>
	<a href="${pageContext.request.contextPath}/logout">Logout</a></div>
	<%//} else{ %>
      <!--  <h1>Non sei un amministratore!</h1>-->
     <%} %>












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
