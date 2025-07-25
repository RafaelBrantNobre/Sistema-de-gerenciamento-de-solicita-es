<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.secretariaescolar.model.Cargo" %>

<%
    List<Cargo> cargos = (List<Cargo>) request.getAttribute("cargos");
    String mensagem = (String) request.getAttribute("mensagem");
%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/estilo.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
     
    <meta charset="UTF-8">
    <title>Cadastro de Funcionário</title>
</head>
<body>

<h2>Cadastro de Funcionário</h2>

<% if (mensagem != null) { %>
    <div style="background-color: #d4edda; color: #155724; 
                padding: 10px; border-radius: 5px; 
                border: 1px solid #c3e6cb; width: fit-content;">
        <b><%= mensagem %></b>
    </div>
    <br>
<% } %>

<form action="CadastroFuncionarioServlet" method="post">
    <label>ID Funcionário:</label><br>
    <input type="text" name="idFuncionario" required><br><br>

    <label>Nome:</label><br>
    <input type="text" name="nome" required><br><br>

    <label>Cargo:</label><br>
    <select name="idCargo" required>
        <option value="">Selecione um cargo</option>
        <% if (cargos != null) {
            for (Cargo c : cargos) { %>
                <option value="<%= c.getIdCargo() %>">
                    <%= c.getIdCargo() %> - <%= c.getNomeCargo() %>
                </option>
        <% } } %>
    </select><br><br>

    <label>Data de Nascimento:</label><br>
    <input type="date" name="dataNascimento" required><br><br>

    <label>Status da Conta:</label><br>
    <input type="checkbox" name="statusConta" checked> Ativo<br><br>

    <input type="submit" value="Cadastrar">
</form>

<br>
<a href="homeSecretariaGeral.jsp">
    <button>Voltar para Home</button>
</a>

</body>
</html>
