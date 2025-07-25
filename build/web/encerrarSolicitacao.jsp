<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page import="br.com.secretariaescolar.model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String idSolicitacao = request.getParameter("id");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Encerrar Solicitação</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <div class="container">
        <h2>Encerrar Solicitação</h2>

        <% if (usuario.getNivelAcesso() >= 2) { %>
            <p>Tem certeza que deseja encerrar a solicitação <strong>ID: <%= idSolicitacao %></strong>?</p>
            <form action="encerrarSolicitacao" method="post">
                <input type="hidden" name="id" value="<%= idSolicitacao %>">
                <button type="submit" class="btn btn-danger">Encerrar</button>
                <a href="listarSolicitacoes" class="btn btn-secondary">Cancelar</a>
            </form>
        <% } else { %>
            <p style="color: red;">Você não tem permissão para encerrar solicitações.</p>
            <a href="listarSolicitacoes" class="btn btn-secondary">Voltar</a>
        <% } %>
    </div>
</body>
</html>
