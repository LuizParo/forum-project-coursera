<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
		<title>Lista de Tópicos</title>
		<style type="text/css">
			.coluna-header {
				padding: 4px;
				font-size: large;
				font-weight: bold;
			}
			
			.coluna {
				padding: 4px;
				text-align: center;
			}
		</style>
	</head>
	<body>
		<h1>Lista de Tópicos</h1>
		
		<div class="group">
			<p>${mensagem}</p>
		</div>
		
		<c:url value="/" var="action" />
		<div>
			<a href="${action}?action=LogoutAction">Logout</a>
			<br/><br/>
			<a href="${action}?action=TelaTopicoAction">Criar Tópico</a>
			<br/><br/>
			<a href="${action}?action=TelaRankingAction">Exibir Ranking</a>
		</div>
		<br/>
		
		<table class="tabela">
			<thead>
				<tr>
					<td class="coluna-header">Título</td>
					<td class="coluna-header">Usuário Responsável</td>
					<td class="coluna-header">Exibir</td>
				</tr>
			</thead>
			
			<tbody>
				<c:forEach items="${topicos}" var="topico" >
					<tr>
						<td class="coluna">${topico.titulo}</td>
						<td class="coluna">${topico.usuario.nome}</td>
						<td class="coluna"><a href="${action}?action=TelaTopicoAction&topico=${topico.id}&exibicao=true">Exibir</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</body>
</html>