<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.List,br.com.tresemeia.gerenciador.modelo.Empresa" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	
	<title>Lista das empresas</title>
	
</head>
	<body>
	
		<c:if test="${not empty empresa }">	
			Empresa <b>${ empresa }</b> cadastrada com sucesso!!!
		</c:if>	
		
		<h1>Lista de empresas:</h1>
		
		<ul>
			<c:forEach items="${empresas}" var="empresa">
				
				<!-- empresa.nome por baixo dos panos chama o empresa.getNome() -->
				<li>
					${empresa.nome} - <fmt:formatDate value="${empresa.dataAbertura}" pattern="dd/MM/yyyy" /> 
					<a href="/gerenciador/entrada?acao=MostraEmpresa&id=${empresa.id}">Editar</a>
					<a href="/gerenciador/entrada?acao=RemoveEmpresa&id=${empresa.id}">Remover</a>
				</li>
			</c:forEach>
		</ul>
		<%-- Equivalente a: 
		--------------------------
		<ul>
		<% 
			List<Empresa> lista = (List<Empresa>)request.getAttribute("empresas");
			for (Empresa empresa : lista) {
		%>
			<li><%= empresa.getNome() %></li>
		<% 
			}
		%>
		</ul>
		-------------------------- --%>
		
	</body>
</html>