<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.secretariaescolar.model.Solicitacao" %>
<%@ page import="br.com.secretariaescolar.model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    List<Solicitacao> solicitacoes = (List<Solicitacao>) request.getAttribute("solicitacoes");
%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <meta charset="UTF-8">
    <title>Listar Solicitações</title>
</head>
<body>

<h2>Listagem de Solicitações</h2>

<!-- 🔥 Bloco no topo para tramitar -->
<div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 20px;">
    <h3>➡️ Tramitar uma Solicitação</h3>
    <form action="tramitar-solicitacao" method="get">
        <label>ID da Solicitação:</label>
        <input type="number" name="idSolicitacao" required>

        <label>ID do Tipo:</label>
        <input type="number" name="idTipo" required>

        <input type="submit" value="Tramitar">
    </form>
</div>

<!-- 🔥 Tabela de Solicitações -->
<% if (solicitacoes == null || solicitacoes.isEmpty()) { %>
    <p><b>Nenhuma solicitação encontrada.</b></p>
<% } else { %>

    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>CPF do Aluno</th>
            <th>Nome do Aluno</th>
            <th>Curso</th>
            <th>Tipo Solicitação</th>
            <th>Data de Entrada</th>
            <th>Status</th>
            <th>Funcionário Responsável</th>
            <th>Ações</th>
        </tr>

        <% for (Solicitacao s : solicitacoes) { 
            String data = s.getDataEntrada();
            String dataFormatada = data.substring(8,10) + "/" + data.substring(5,7) + "/" + data.substring(0,4);
        %>
            <tr>
                <td><%= s.getIdSolicitacao() %></td>
                <td><%= s.getCpfAluno() %></td>
                <td><%= s.getNomeAluno() %></td>
                <td><%= s.getNomeCurso() %></td>
                <td><%= s.getNomeTipo() %> (ID: <%= s.getIdTipo() %>)</td>
                <td><%= dataFormatada %></td>
                <td><%= s.isStatus() ? "Aberta" : "Fechada" %></td>
                <td>
                    <%= s.getNomeFuncionario() != null ? 
                        s.getNomeFuncionario() + " (ID: " + s.getIdFuncionario() + ")" : "Não definido" %>
                </td>
                <td>
                    <% if (s.isStatus()) { %>
                        <a href="encerrarSolicitacao.jsp?id=<%= s.getIdSolicitacao() %>" class="btn btn-danger btn-sm">
                            Encerrar
                        </a>
                    <% } else { %>
                        <span style="color: gray;">Encerrada</span>
                    <% } %>
                </td>
            </tr>
        <% } %>
    </table>

<% } %>

<br>
<a href="homeSecretariaGeral.jsp">
    <button>Voltar para Home</button>
</a>

</body>
</html>
