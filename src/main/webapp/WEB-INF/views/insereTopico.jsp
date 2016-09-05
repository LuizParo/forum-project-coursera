<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insere Tópico</title>
	</head>
	<body>
		<h1>Tópico</h1>
		
		<c:url value="/" var="action" />
		<form action="${action}" method="post">
			<input type="hidden" name="action" value="InsereTopicoAction"/>
			<table>
				<tr>
					<td>
						<label for="txtTitulo">Título:</label>
					</td>
					<td>
						<input type="text" id="txtTitulo" name="txtTitulo" value="${topico.titulo}" <c:if test="${disabledFields != null}">disabled</c:if> />
					</td>
				</tr>
				<tr>
					<td>
						<label for="txtConteudo">Conteúdo:</label>
					</td>
					<td>
						<textarea rows="50" cols="50" id="txtConteudo" name="txtConteudo" <c:if test="${disabledFields != null}">disabled</c:if> >
							${topico.conteudo}
						</textarea>
					</td>
				</tr>
				<tr>
					<td><a href="${action}?action=TelaListaTopicosAction">Voltar</a></td>
				</tr>
			</table>
			
			<c:if test="${disabledFields == null}">
				<div>
					<input type="submit" value="Inserir" />
					<input type="reset" value="Limpar" />
				</div>
			</c:if>
		</form>
		
		<c:if test="${disabledFields != null}">
			<div>
				<c:forEach items="${comentarios}" var="comentario">
					<div>
						<p>${comentario.usuario.nome}</p>
						<br/>
						<textarea rows="5" cols="50" disabled="disabled">
							${comentario.comentario}
						</textarea>
					</div>
					<br/>
				</c:forEach>
			</div>

			<form action="${action}" method="post" >
				<input type="hidden" name="action" value="InsereComentarioAction"/>
				<input type="hidden" name="txtIdTopico" value="${topico.id}" />
				
				<div>
					<p>Insira seu comentário:</p>
					<br/>
					<textarea rows="5" cols="50" name="txtComentario"></textarea>
				</div>
			</form>
		</c:if>
	</body>
</html>