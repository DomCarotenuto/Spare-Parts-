<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<% String user = null;
	
	if(session.getAttribute("user")== null){
		
		%><h3>EFFETTUA L'ACCESSO!</h3>
			<h1>INSERISCI NOME E PASSWORD</h1>
	<form action="${pageContext.request.contextPath}/LLogin" method="POST" >
		<input type="text" name="username" placeholder="Inserisci user">
		<input type="password" name="password" placeholder="Inserisci password">
		<input type="submit" value="Entra">
	</form>
	<%	
	}
	
	else {user = (String)session.getAttribute("user");
%>

	<h1> CIAO, ${user}</h1>
	<a href="${pageContext.request.contextPath}/logout">Logout</a>
	<%}%>
</body>
</html>