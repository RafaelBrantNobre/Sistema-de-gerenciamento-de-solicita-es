<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page session="true" %>
<%@ page import="java.util.List" %>
<%@ page import="br.com.secretariaescolar.model.Usuario" %>
<%@ page import="br.com.secretariaescolar.dao.SolicitacaoDAO" %>
<%@ page import="br.com.secretariaescolar.dao.EtapaDAO" %>
<%@ page import="br.com.secretariaescolar.dao.TramitacaoDAO" %>
<%@ page import="br.com.secretariaescolar.model.Solicitacao" %>
<%@ page import="br.com.secretariaescolar.model.Etapa" %>
<%@ page import="br.com.secretariaescolar.model.Tramitacao" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="java.sql.SQLException" %>

<%
    Usuario usuario = (Usuario) session.getAttribute("usuario");
    if (usuario == null) {
        response.sendRedirect("login.jsp");
    }

    List<Solicitacao> solicitacoes = null;

    try {
        SolicitacaoDAO dao = new SolicitacaoDAO();
        solicitacoes = dao.listarPorAluno(usuario.getId());
    } catch (SQLException e) {
        e.printStackTrace();
    } catch (NamingException e) {
        e.printStackTrace();
    }
%>

<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/botoes.css">
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home Aluno</title>
</head>
<body>
    <!-- NAVBAR -->
    <nav class="navbar">
        <div class="navbar-container">
            <a href="#" class="navbar-brand">Secretaria Escolar</a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="#" class="nav-link active">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a href="formSolicitacao.jsp" class="nav-link">Nova Solicitação</a>
                </li>
                <li class="nav-item">
                    <a href="logout" class="nav-link">Sair</a>
                </li>
            </ul>
        </div>
    </nav>

    <!-- CONTEÚDO PRINCIPAL -->
    <div class="container">
        <!-- BREADCRUMB -->
        <ul class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="#" class="breadcrumb-link">Home</a>
            </li>
            <li class="breadcrumb-item active">
                <a href="#" class="breadcrumb-link">Dashboard Aluno</a>
            </li>
        </ul>

        <!-- HEADER DA PÁGINA -->
        <div class="system-header">
            <h1>Bem-vindo, <%= usuario.getNome() %> (Aluno)</h1>
            <p>Gerencie suas solicitações e acompanhe o status</p>
        </div>

        <!-- BOTÕES DE AÇÃO -->
        <div class="card">
            <div class="card-body text-center">
                <a href="formSolicitacao.jsp" class="btn btn-primary btn-lg">
                    Fazer nova solicitação
                </a>
                <a href="logout" class="btn btn-outline btn-lg">
                    Sair
                </a>
            </div>
        </div>

        <!-- SEÇÃO DE SOLICITAÇÕES -->
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Suas Solicitações</h3>
            </div>
            <div class="card-body">
                <%
                    if (solicitacoes != null && !solicitacoes.isEmpty()) {
                        for (Solicitacao s : solicitacoes) {
                            String data = s.getDataEntrada();
                            String dataFormatada = data.substring(8,10) + "/" + data.substring(5,7) + "/" + data.substring(0,4);
                %>

                <!-- CARD DE SOLICITAÇÃO -->
                <div class="card mb-4">
                    <div class="card-header">
                        <div class="d-flex justify-content-between align-items-center">
                            <h4 class="card-title mb-0">
                                Solicitação #<%= s.getIdSolicitacao() %> - <%= s.getNomeTipo() %>
                            </h4>
                            <span class="solicitacao-status <%= s.isStatus() ? "aberta" : "concluida" %>">
                                <%= s.isStatus() ? "Aberta" : "Fechada" %>
                            </span>
                        </div>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-6">
                                <p><strong>Data:</strong> <%= dataFormatada %></p>
                                <p><strong>Funcionário Responsável:</strong> <%= s.getNomeFuncionario() != null ? s.getNomeFuncionario() : "Não atribuído" %></p>
                            </div>
                            <div class="col-6">
                                <p><strong>Tipo:</strong> <%= s.getNomeTipo() %></p>
                                <p><strong>ID Tipo:</strong> <%= s.getIdTipo() %></p>
                            </div>
                        </div>

                        <!-- ETAPAS -->
                        <div class="mt-4">
                            <h5>Etapas desta solicitação:</h5>
                            <div class="table-responsive">
                                <table class="table table-striped table-sm">
                                    <thead>
                                        <tr>
                                            <th>Ordem</th>
                                            <th>Etapa</th>
                                            <th>Descrição</th>
                                            <th>Setor Responsável</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            try {
                                                EtapaDAO etapaDAO = new EtapaDAO();
                                                List<Etapa> etapas = etapaDAO.listarPorTipo(s.getIdTipo());
                                                for (Etapa e : etapas) {
                                        %>
                                        <tr>
                                            <td><span class="badge badge-primary"><%= e.getOrdem() %></span></td>
                                            <td><strong><%= e.getNomeEtapa() %></strong></td>
                                            <td><%= e.getDescricao() %></td>
                                            <td><%= e.getNomeCargo() %></td>
                                        </tr>
                                        <%
                                                }
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            } catch (NamingException e) {
                                                e.printStackTrace();
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>

                        <!-- TRAMITAÇÕES -->
                        <div class="mt-4">
                            <h5>Tramitações desta solicitação:</h5>
                            <div class="table-responsive">
                                <table class="table table-striped table-sm">
                                    <thead>
                                        <tr>
                                            <th>Data/Hora</th>
                                            <th>Parecer</th>
                                            <th>Funcionário</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%
                                            try {
                                                TramitacaoDAO tramDAO = new TramitacaoDAO();
                                                List<Tramitacao> trams = tramDAO.listarPorSolicitacao(s.getIdSolicitacao());
                                                if (trams != null && !trams.isEmpty()) {
                                                    for (Tramitacao t : trams) {
                                        %>
                                        <tr>
                                            <td><%= t.getDataHora() %></td>
                                            <td><%= t.getParecer() != null ? t.getParecer() : "Sem parecer" %></td>
                                            <td><%= t.getIdFuncionario() %></td>
                                        </tr>
                                        <%
                                                    }
                                                } else {
                                        %>
                                        <tr>
                                            <td colspan="3" class="text-center text-muted">
                                                Nenhuma tramitação registrada ainda
                                            </td>
                                        </tr>
                                        <%
                                                }
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            } catch (NamingException e) {
                                                e.printStackTrace();
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <%
                        }
                    } else {
                %>

                <!-- MENSAGEM QUANDO NÃO HÁ SOLICITAÇÕES -->
                <div class="text-center p-5">
                    <div class="table-empty">
                        <div class="table-empty-icon"></div>
                        <h3 class="table-empty-title">Nenhuma solicitação encontrada</h3>
                        <p class="table-empty-text">Você ainda não fez nenhuma solicitação.</p>
                        <a href="formSolicitacao.jsp" class="btn btn-primary">
                            Fazer primeira solicitação
                        </a>
                    </div>
                </div>

                <%
                    }
                %>
            </div>
        </div>
    </div>
</body>
</html>
