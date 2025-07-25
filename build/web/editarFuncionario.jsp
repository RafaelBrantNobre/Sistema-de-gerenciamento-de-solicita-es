<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="br.com.secretariaescolar.model.Usuario" %>
<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null || usuario.getNivelAcesso() < 3) {
        response.sendRedirect("login.jsp");
        return;
    }

    String id = request.getParameter("id");
    String nome = request.getParameter("nome");
    String cargo = request.getParameter("cargo");
    String nascimento = request.getParameter("nascimento");
    String status = request.getParameter("status");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Funcionário</title>
    <link rel="stylesheet" href="css/estilo.css">
</head>
<body>
    <div class="container">
        <h2>Editar Dados do Funcionário</h2>
        <form action="editarFuncionario" method="post">
            <input type="hidden" name="id" value="<%= id %>">

            <label>Nome:</label>
            <input type="text" name="nome" value="<%= nome %>" required>

            <label>ID do Cargo:</label>
            <input type="number" name="cargo" value="<%= cargo %>" required>

            <label>Data de Nascimento:</label>
            <input type="date" name="nascimento" value="<%= nascimento %>" required>

            <label>Status da Conta:</label>
            <select name="status">
                <option value="true" <%= "true".equals(status) ? "selected" : "" %>>Ativo</option>
                <option value="false" <%= "false".equals(status) ? "selected" : "" %>>Inativo</option>
            </select>

            <button type="submit">Salvar Alterações</button>
        </form>
    </div>
</body>
</html>
