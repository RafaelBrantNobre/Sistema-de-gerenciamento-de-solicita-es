<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String mensagem = (String) request.getAttribute("mensagem");
%>
<!DOCTYPE html>
<html>
<head>
    
    <link rel="stylesheet" href="css/estilo.css">
     <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
     
    <meta charset="UTF-8">
    <title>Cadastro de Aluno</title>
</head>
<body>

<h2>Cadastro de Aluno</h2>

<% if (mensagem != null) { %>
    <div style="background-color: #d4edda; color: #155724; 
                padding: 10px; border-radius: 5px; 
                border: 1px solid #c3e6cb; width: fit-content;">
        <b><%= mensagem %></b>
    </div>
    <br>
<% } %>

<form action="cadastro-aluno" method="post">
    <label>CPF:</label><br>
    <input type="text" name="cpf" required><br><br>

    <label>Nome:</label><br>
    <input type="text" name="nome" required><br><br>

    <label>Situação:</label><br>
    <input type="text" name="situacao" required><br><br>

    <label>Ativação:</label><br>
    <input type="checkbox" name="ativacao" checked> Ativo<br><br>

    <label>Data de Nascimento:</label><br>
    <input type="date" name="dataNascimento" required><br><br>

    <label>ID do Curso:</label><br>
    <input type="number" name="idCurso" required><br><br>

    <input type="submit" value="Cadastrar">
</form>

<br>
<a href="homeSecretariaGeral.jsp">
    <button>Voltar para Home</button>
</a>

</body>
</html>
