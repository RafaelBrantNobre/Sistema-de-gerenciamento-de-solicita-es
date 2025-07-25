<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.secretariaescolar.model.Funcionario" %>
<%@ page import="br.com.secretariaescolar.dao.FuncionarioDAO" %>

<%
    FuncionarioDAO dao = new FuncionarioDAO();
    List<Funcionario> funcionarios = dao.listarAtivos(); // Apenas ativos
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
        <h2>Desativar Funcionários Ativos</h2>
        <table border="1" cellpadding="10">
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Cargo</th>
                <th>Data de Nascimento</th>
                <th>Status</th>
                <th>Ação</th>
            </tr>
            <% for (Funcionario f : funcionarios) { %>
                <tr>
                    <td><%= f.getIdFuncionario() %></td>
                    <td><%= f.getNome() %></td>
                    <td><%= f.getIdCargo() %></td>
                    <td><%= f.getDataNascimento() %></td>
                    <td><%= f.isStatusConta() ? "Ativo" : "Inativo" %></td>
                    <td>
                        <form action="desativarFuncionario" method="post" onsubmit="return confirm('Deseja realmente desativar este funcionário?');">
                            <input type="hidden" name="id" value="<%= f.getIdFuncionario() %>">
                            <button type="submit" class="btn btn-danger">Desativar</button>
                        </form>
                    </td>
                </tr>
            <% } %>
        </table>
        <br>
        <a href="homeSecretariaGeral.jsp" class="btn btn-secondary">Voltar</a>
    </div>
</body>
</html>
