<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html >
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cadastro de Usuário</title>
	</head>
	<body>
		<h1>Cadastro de Usuário</h1>
		
		<c:url value="/" var="action" />
		<form action="${action}" method="post">
			<input type="hidden" name="action" value="CadastroUsuarioAction"/>
			<table>
				<tr>
					<td>
						<label for="txtLogin">Login:</label>
					</td>
					<td>
						<input type="text" id="txtLogin" name="txtLogin">
					</td>
				</tr>
				<tr>
					<td>
						<label for="txtSenha">Senha:</label>
					</td>
					<td>
						<input type="password" id="txtSenha" name="txtSenha">
					</td>
				</tr>
				<tr>
					<td>
						<label for="txtNome">Nome:</label>
					</td>
					<td>
						<input type="text" id="txtNome" name="txtNome">
					</td>
				</tr>
				<tr>
					<td>
						<label for="txtEmail">Email:</label>
					</td>
					<td>
						<input type="text" id="txtEmail" name="txtEmail">
					</td>
				</tr>
			</table>
			
			<div>
				<input type="submit" value="Cadastrar" />
				<input type="reset" value="Limpar" />
			</div>
		</form>
	</body>
</html>