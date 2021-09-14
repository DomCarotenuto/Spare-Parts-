<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%><%@taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>

<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css" integrity="sha512-NmLkDIU1C/C88wi324HBc+S2kLhi08PN5GDeUVVVC/BVt/9Izdsc9SVeVfA1UZbY3sHUlDSyRXhCzHfr6hmPPw==" crossorigin="anonymous" />
  <link rel="stylesheet" href="style.css">
  <link rel="preconnect" href="https://fonts.gstatic.com">
  <link href="https://fonts.googleapis.com/css2?family=Inter:wght@100;300;400;700;900&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/flickity/2.2.2/flickity.min.css" integrity="sha512-BiFZ6oflftBIwm6lYCQtQ5DIRQ6tm02svznor2GYQOfAlT3pnVJ10xCrU3XuXnUrWQ4EG8GKxntXnYEdKY0Ugg==" crossorigin="anonymous" />
  <script src="https://unpkg.com/scrollreveal@4.0.0/dist/scrollreveal.min.js"></script>

</head>
<jsp:include page="/Header.jsp">
	<jsp:param name="pageTitle" value="${param.ric}"/>
</jsp:include>
   <div class="griglia-prodotti mt-4">

			<c:forEach items="${prodotti}" var="prodotto">
		 <div class="item product">
					<div class="image-product img-hover-zoom" id="Immagine">
					<a href="Dettagli?action=read&id=${prodotto.code}"><img src="src/${prodotto.photo}" height="300px"></a></div>
					
					<div class="title-product">
					 <h3 class="normal-text">${prodotto.name}</h3>
					<p class="med-text prezzo">${prodotto.price}0 €</p>
					</div>
		</div>
			</c:forEach>
			<c:if test="${empty prodotti}">
				<div class="grid">Nessun prodotto trovato.</div>
			</c:if>

	
	</div>
<%@include file="Footer.html"%>
</html>