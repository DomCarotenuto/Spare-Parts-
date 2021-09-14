<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
                                       
                                         
<% Cart cart = (Cart) request.getAttribute("cart");

%>
         
                                         
<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,com.NewModel.*,com.NewBean.*,com.NewControl.*"%>

<head>
<meta name="viewport" content="width=device-width", initial-scale="1.0">









<script src="ricerca.js"></script>
</head>
<body>

  <div class="black">
    <div class="header">
      <div class="logo"><a href="./Home"><img src="src/logo.png" width=150px></a></div>
      <ul class="menu">
      
     
      
      
      
      
      
      
      
      
        <li><a href="./Home">Home</a></li>
        
        <li><div class="dropdown1">
  <a class="dropbtn1" href="ProdottiControl">Prodotti</a>
  <div class="dropdown-content1">
   <a href="Categoria?action=get&categoria=accessori">Accessori</a>
    <a href="Categoria?action=get&categoria=auto">Pneumatici auto</a>
    <a href="Categoria?action=get&categoria=moto">Pneumatici moto</a>
  </div>
</div></li>
        <li><a href="">Chi siamo</a></li>
         
         <li>
                <form action="Ricerca" method="post">
                    <input type="text" name="ric" list="ricerca-datalist" placeholder="Ricerca" onkeyup="ricerca(this.value)" value="<c:out value="${param.ric}" />">
                    <datalist id="ricerca-datalist"></datalist>
                </form>
            </li>
            
         <li class="mobile"><a href="CartS"><%=cart.totalNumCart()%> Carrello</a></li>
         
         <li class="mobile">Account</li>
         	<li class="mobile sub"><a href="./OrdineUtente">I miei ordini</a></li>
         	<li class="mobile sub"><a href="./UtenteForm">I miei dati</a></li>
         	<li class="mobile sub"><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
      </ul>
      
            
			<div class="cta"><div class="numCart"><%=cart.totalNumCart()%></div></div>
      <div class="cta"><div style="padding-right: 10px; padding-top: 5px;"><a href="CartS"><img src="src/shopping-cart1.png" width="30px"></a></div></div>
                   <%
	
	String user = null;
	if(session.getAttribute("user")== null){
%>
	<div class="cta">
        <a href="admin/loginAdmin.jsp" class="button">Accedi
        </a>
      </div>
<%	
	}else { %><div class="cta">
		   <div class="dropdown">
            <button onclick="myFunction()" class="dropbtn"><img src="src/user.png" width="30px"></button>
                <div id="myDropdown" class="dropdown-content">
  	            <p>CIAO, ${user}</p>
                <a href="./OrdineUtente">I miei ordini</a>
                <a href="./UtenteForm">I miei dati</a>
               
                
                <% String tipo= (String)request.getAttribute("type");
                	
                	if(tipo.equals("adm")){ %>
                		
                	<a href="./admin-page.jsp">Amministratore</a>
                	
                	<% }%>

                <a href="${pageContext.request.contextPath}/logout">Logout</a>
                </div>
           </div>
       
      </div>
      
      
      <%} %>



      <div class="hamburger">
          <span></span>
          <span></span>
          <span></span>
      </div>
    </div>
</div>

<!--SPAZIO-->
<div class="mt-4"></div>


 <!-- Jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
    <script>
      $( document ).ready(function() {
        /* Open Panel */
        $( ".hamburger" ).on('click', function() {
          $(".menu").toggleClass("menu--open");
        });
      });
      
      /* When the user clicks on the button, 
      toggle between hiding and showing the dropdown content */
      function myFunction() {
        document.getElementById("myDropdown").classList.toggle("show");
      }
      
      // Close the dropdown if the user clicks outside of it
      window.onclick = function(event) {
        if (!event.target.matches('.dropbtn')) {
          var dropdowns = document.getElementsByClassName("dropdown-content");
          var i;
          for (i = 0; i < dropdowns.length; i++) {
            var openDropdown = dropdowns[i];
            if (openDropdown.classList.contains('show')) {
              openDropdown.classList.remove('show');
            }
          }
        }
      }
      
      
      
      
    
    </script>

</body>
</html>