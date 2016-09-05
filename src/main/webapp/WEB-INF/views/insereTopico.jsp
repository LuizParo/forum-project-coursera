<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insere Tópico</title>
	</head>
	<body>
		<h1>Insira um novo Tópico</h1>
		
		<c:url value="/" var="action" />
		<form action="${action}" method="post">
			<input type="hidden" name="action" value="InsereTopicoAction"/>
			<table>
				<tr>
					<td>
						<label for="txtTitulo">Título:</label>
					</td>
					<td>
						<input type="text" id="txtTitulo" name="txtTitulo">
					</td>
				</tr>
				<tr>
					<td>
						<label for="txtConteudo">Conteúdo:</label>
					</td>
					<td>
						<textarea rows="50" cols="50" id="txtConteudo" name="txtConteudo"></textarea>
					</td>
				</tr>
				<tr>
					<td><a href="${action}?action=TelaListaTopicosAction">Voltar</a></td>
				</tr>
			</table>
			
			<div>
				<input type="submit" value="Inserir" />
				<input type="reset" value="Limpar" />
			</div>
		</form>
	</body>
</html>