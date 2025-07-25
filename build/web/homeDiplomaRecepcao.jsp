<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page import="br.com.secretariaescolar.model.Usuario" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String destino;
    switch (usuario.getNivelAcesso()) {
        case 0: destino = "homeAluno.jsp"; break;
        case 1: destino = "homeEstagiario.jsp"; break;
        case 2: destino = "homeRecepcao.jsp"; break;
        case 3: destino = "homeSecretariaGeral.jsp"; break;
        default: destino = "login.jsp"; break;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home - <%= usuario.getNomeCargo() %></title>
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
</head>
<body>
    <!-- NAVBAR -->
    <nav class="navbar">
        <div class="navbar-container">
            <a href="#" class="navbar-brand">Secretaria Escolar</a>
            <ul class="navbar-nav">
                <li class="nav-item"><a href="#" class="nav-link active">Dashboard</a></li>
                <li class="nav-item"><a href="listarSolicitacoes" class="nav-link">Solicitações</a></li>
                <li class="nav-item"><a href="logout" class="nav-link">Sair</a></li>
            </ul>
        </div>
    </nav>

    <!-- CONTEÚDO PRINCIPAL -->
    <div class="container">
        <ul class="breadcrumb">
            <li class="breadcrumb-item"><a href="#" class="breadcrumb-link">Home</a></li>
            <li class="breadcrumb-item active"><a href="#" class="breadcrumb-link">Dashboard Funcionário</a></li>
        </ul>

        <div class="system-header">
            <h1>Bem-vindo, <%= usuario.getNome() %></h1>
            <p><%= usuario.getNomeCargo() %> - Painel de Controle</p>
        </div>

        <div class="row">
            <!-- Solicitações/Tramitações -->
            <div class="col-6">
                <div class="card action-card">
                    <div class="card-body text-center">
                        <div class="action-icon" style="background-color:#007bff; border-radius:50%; width:60px; height:60px; margin:0 auto;"></div>
                        <h4 class="card-title">Solicitações/Tramitações</h4>
                        <p class="card-text">Acesse o sistema de solicitações e realize tramitações</p>
                        <a href="listarSolicitacoes" class="btn btn-primary btn-lg">Visualizar Solicitações</a>
                    </div>
                </div>
            </div>

            <!-- Logout -->
            <div class="col-6">
                <div class="card action-card">
                    <div class="card-body text-center">
                        <div class="action-icon" style="background-color:#007bff; border-radius:50%; width:60px; height:60px; margin:0 auto;"></div>
                        <h4 class="card-title">Sair do Sistema</h4>
                        <p class="card-text">Encerre sua sessão de forma segura</p>
                        <a href="logout" class="btn btn-danger btn-lg">Logout</a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Informações da Sessão -->
        <div class="card mt-4">
            <div class="card-header"><h3 class="card-title">Informações da Sessão</h3></div>
            <div class="card-body">
                <div class="row">
                    <div class="col-4"><h6>Nome</h6><p><%= usuario.getNome() %></p></div>
                    <div class="col-4"><h6>Cargo</h6><p><%= usuario.getNomeCargo() %></p></div>
                    <div class="col-4"><h6>Nível de Acesso</h6><p><%= usuario.getNivelAcesso() %></p></div>
                </div>
            </div>
        </div>
    </div>

    <!-- SCRIPTS -->
    <script>
        document.querySelectorAll('.action-card').forEach(card => {
            card.addEventListener('mouseenter', function() {
                this.style.transform = 'translateY(-5px)';
                this.style.boxShadow = '0 8px 25px rgba(0, 0, 0, 0.15)';
            });
            card.addEventListener('mouseleave', function() {
                this.style.transform = 'translateY(0)';
                this.style.boxShadow = '0 2px 8px rgba(0, 0, 0, 0.1)';
            });
        });

        document.querySelector('a[href="logout"]').addEventListener('click', function(e) {
            if (!confirm('Tem certeza que deseja sair do sistema?')) {
                e.preventDefault();
            }
        });
    </script>
</body>
</html>
