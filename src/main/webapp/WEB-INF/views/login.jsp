<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html >
<html>
	<head>
		<meta charset=UTF-8">
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
			
			<div class="group">
				<label for="txtUsuario">Usuário:</label>
				<input type="text" id="txtUsuario" name="txtUsuario" />
			</div>
			
			<div class="group">
				<label for="txtPassword">Password:</label>
				<input type="password" id="txtPassword" name="txtPassword" />
			</div>
			
			<div class="group">
				<input type="submit" value="Autenticar" />
				<input type="reset" value="Limpar" />
			</div>
			
			<div class="group">
				<a href="${action}?action=TelaCadastroUsuarioAction">Cadastrar Usuário</a>
			</div>
		</form>
	</body>
</html>