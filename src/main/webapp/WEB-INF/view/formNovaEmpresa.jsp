<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:url value="/entrada" var="linkEntradaServlet"/>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Nova empresa</title>
</head>
<body>

    <c:import url="logout-parcial.jsp" />

	<!-- method="post" esconde os parametros dentro do escopo da requisi��o
	method="get" envia os parametros atrav�s da url, sendo menos seguro -->
	<form action="${linkEntradaServlet}" method="post">
	
	Nome: <input type="text" name="nome"/>
	Data abertura: <input type="text" name="data"/>
	<input type="hidden" name="acao" value="NovaEmpresa">
	
	<input type="submit">
	
	
	
	</form>
</body>
</html>