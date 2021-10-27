<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<title>Adicionar empresa</title>
	
</head>
	<body>
	
		<c:import url="logout-parcial.jsp" />
		
		<c:if test="${not empty empresa }">	
			<h1>Empresa <b>${ empresa }</b> cadastrada com sucesso!!!</h1>
		</c:if>		
		<c:if test="${empty empresa }">	
			<h1>Nenhuma empresa cadastrada.</h1>
		</c:if>	
	</body>
</html>