<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ranking de Usuários</title>
	</head>
	<body>
		<h1>Ranking de Usuários</h1>
		
		<div style="margin: 10px;">
			<a href="${action}?action=TelaListaTopicosAction">Voltar</a>
		</div>
		
		<table>
			<tr>
				<th>Posição</th>
				<th>Nome</th>
				<th>Login</th>
				<th>Pontos</th>
			</tr>
			<c:forEach items="${usuarios}" var="usuario" varStatus="posicao">
				<tr>
					<td>${posicao.index + 1}</td>
					<td>${usuario.nome}</td>
					<td>${usuario.login}</td>
					<td>${usuario.pontos}</td>
				</tr>
			</c:forEach>
		</table>
	</body>
</html>