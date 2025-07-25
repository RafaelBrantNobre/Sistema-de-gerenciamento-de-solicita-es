<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.com.secretariaescolar.model.Funcionario" %>
<%@ page import="br.com.secretariaescolar.dao.FuncionarioDAO" %>

<%
    String id = request.getParameter("id");
    Funcionario funcionario = new FuncionarioDAO().buscarPorId(id);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Desativar Funcionário</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <div class="container">
        <h2>Alterar Status da Conta do Funcionário</h2>
        <form action="desativarFuncionario" method="post">
            <input type="hidden" name="id" value="<%= funcionario.getIdFuncionario() %>">

            <p>Deseja <strong><%= funcionario.isStatusConta() ? "desativar" : "reativar" %></strong> a conta de <strong><%= funcionario.getNome() %></strong>?</p>

            <input type="hidden" name="status" value="<%= !funcionario.isStatusConta() %>">

            <button type="submit" class="btn btn-danger">Confirmar</button>
            <a href="homeSecretariaGeral.jsp" class="btn btn-secondary">Cancelar</a>
        </form>
    </div>
</body>
</html>
