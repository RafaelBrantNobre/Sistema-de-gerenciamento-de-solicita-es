<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.secretariaescolar.model.Etapa" %>
<%@ page import="br.com.secretariaescolar.model.Tramitacao" %>

<%
    // Dados recebidos do Servlet
    List<Etapa> etapas = (List<Etapa>) request.getAttribute("etapas");
    List<Tramitacao> historico = (List<Tramitacao>) request.getAttribute("historico");
    String mensagem = (String) request.getAttribute("mensagem");
    int idSolicitacao = (Integer) request.getAttribute("idSolicitacao");
%>

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/estilo.css">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <meta charset="UTF-8">
    <title>Tramitar Solicitação</title>
</head>
<body>

<h2>Tramitar Solicitação - ID <%= idSolicitacao %></h2>

<% if (mensagem != null) { %>
    <div style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 5px;">
        <b><%= mensagem %></b>
    </div>
    <br>
<% } %>

<!-- 🔸 Formulário de Tramitação -->
<form action="registrar-tramitacao" method="post">
    <input type="hidden" name="idSolicitacao" value="<%= idSolicitacao %>">

    <label><b>Selecione as etapas concluídas:</b></label><br><br>

    <% if (etapas != null && !etapas.isEmpty()) {
        for (Etapa e : etapas) { %>
            <input type="checkbox" name="etapas" value="<%= e.getIdEtapa() %>">
            Etapa <b><%= e.getIdEtapa() %></b>: <%= e.getNomeEtapa() %><br>
    <% } } else { %>
        <p><i>Não há etapas disponíveis para esta solicitação.</i></p>
    <% } %>

    <br>
    <label><b>Parecer:</b></label><br>
    <textarea name="parecer" rows="4" cols="50" required></textarea><br><br>

    <input type="submit" value="Registrar Tramitação">
</form>

<hr>

<!-- 🔸 Histórico de Tramitações -->
<h3>Histórico de Tramitações</h3>

<% 
    if (historico == null || historico.isEmpty()) { 
%>
    <p><b>Nenhuma tramitação realizada até o momento.</b></p>
<% 
    } else { 
%>
    <table border="1" cellpadding="6" cellspacing="0">
        <tr>
            <th>ID Tramitação</th>
            <th>Etapa</th>
            <th>Data e Hora</th>
            <th>Funcionário</th>
            <th>Parecer</th>
        </tr>

        <% for (Tramitacao t : historico) { %>
            <tr>
                <td><%= t.getIdTramitacao() %></td>
                <td><%= t.getNomeEtapa() %> (ID: <%= t.getIdEtapa() %>)</td>
                <td><%= t.getDataHora().replace("T", " ") %></td>
                <td><%= t.getNomeFuncionario() %> (ID: <%= t.getIdFuncionario() %>)</td>
                <td><%= t.getParecer() %></td>
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
