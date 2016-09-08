<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Login</title>
		<style type="text/css">
			.group {
				margin-top: 10px;
				margin-bottom: 10px;
			}
		</style>
	</head>
	<body>
		<h1>Login</h1>
	
		<c:url value="/" var="action" />
		<form action="${action}" method="post">
			<input type="hidden" name="action" value="AutenticacaoAction" />
			
			<div class="group">
				<p>${mensagem}</p>
			</div>
			
			<table>
				<tr>
					<td>
						<label for="txtUsuario">Usuário:</label>
					</td>
					<td>
						<input type="text" id="txtUsuario" name="txtUsuario" />
					</td>
				</tr>
				
				<tr>
					<td>
						<label for="txtPassword">Password:</label>
					</td>
					<td>
						<input type="password" id="txtPassword" name="txtPassword" />
					</td>
				</tr>
				
				<tr>
					<td>
						<input type="submit" value="Autenticar" />
					</td>
					<td>
						<input type="reset" value="Limpar" />
					</td>
				</tr>
			</table>
			
			<div class="group">
				<a href="${action}?action=TelaCadastroUsuarioAction">Cadastrar Usuário</a>
			</div>
		</form>
	</body>
</html>