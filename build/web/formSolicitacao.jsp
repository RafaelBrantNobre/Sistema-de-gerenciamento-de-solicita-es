<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fazer Solicitação</title>
</head>
<body>
    <!-- NAVBAR -->
    <nav class="navbar">
        <div class="navbar-container">
            <a href="home-aluno-atualizado.jsp" class="navbar-brand">Secretaria Escolar</a>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a href="home-aluno-atualizado.jsp" class="nav-link">Dashboard</a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link active">Nova Solicitação</a>
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
                <a href="home-aluno-atualizado.jsp" class="breadcrumb-link">Home</a>
            </li>
            <li class="breadcrumb-item active">
                <a href="#" class="breadcrumb-link">Nova Solicitação</a>
            </li>
        </ul>

        <!-- HEADER DA PÁGINA -->
        <div class="system-header">
            <h1>Nova Solicitação</h1>
            <p>Preencha os dados para criar uma nova solicitação</p>
        </div>

        <!-- FORMULÁRIO -->
        <div class="card">
            <div class="card-header">
                <h3 class="card-title">Dados da Solicitação</h3>
            </div>
            <div class="card-body">
                <form action="solicitar" method="post" class="form">
                    <!-- CPF -->
                    <div class="form-group">
                        <label for="cpf" class="form-label">CPF</label>
                        <input 
                            type="text" 
                            id="cpf" 
                            name="cpf" 
                            class="form-control" 
                            placeholder="000.000.000-00"
                            required
                            maxlength="14"
                        >
                        <small class="form-text">Digite apenas os números do CPF</small>
                    </div>

                    <!-- TIPO DE SOLICITAÇÃO -->
                    <div class="form-group">
                        <label for="idTipo" class="form-label">Tipo de Solicitação</label>
                        <select id="idTipo" name="idTipo" class="form-control" required>
                            <option value="">Selecione o tipo de solicitação</option>
                            <option value="1">Histórico Escolar</option>
                            <option value="2">Declaração de Matrícula</option>
                            <option value="3">Diploma</option>
                            <option value="4">Certificado de Conclusão</option>
                            <option value="5">Transferência</option>
                        </select>
                        <small class="form-text">Escolha o tipo de documento que você precisa</small>
                    </div>

                    <!-- OBSERVAÇÕES -->
                    <div class="form-group">
                        <label for="observacoes" class="form-label">Observações (Opcional)</label>
                        <textarea 
                            id="observacoes" 
                            name="observacoes" 
                            class="form-control" 
                            rows="4"
                            placeholder="Adicione informações adicionais sobre sua solicitação..."
                        ></textarea>
                        <small class="form-text">Informações extras que possam ajudar no processamento</small>
                    </div>

                    <!-- BOTÕES -->
                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary btn-lg">
                            Enviar Solicitação
                        </button>
                        <a href="home-aluno-atualizado.jsp" class="btn btn-outline btn-lg">
                            Cancelar
                        </a>
                    </div>
                </form>
            </div>
        </div>

        <!-- CARD DE INFORMAÇÕES -->
        <div class="card mt-4">
            <div class="card-header">
                <h4 class="card-title">Informações Importantes</h4>
            </div>
            <div class="card-body">
                <div class="alert alert-info">
                    <h5>Prazo de Entrega</h5>
                    <p>O prazo médio para processamento é de 3 a 5 dias úteis, dependendo do tipo de solicitação.</p>
                </div>
                
                <div class="row">
                    <div class="col-4">
                        <div class="info-item">
                            <h6>Histórico Escolar</h6>
                            <p>Documento com todas as disciplinas cursadas e notas</p>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="info-item">
                            <h6>Declaração de Matrícula</h6>
                            <p>Comprovante de que o aluno está matriculado</p>
                        </div>
                    </div>
                    <div class="col-4">
                        <div class="info-item">
                            <h6>Diploma</h6>
                            <p>Documento oficial de conclusão do curso</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- SCRIPT PARA FUNCIONALIDADES -->
    <script>
        // Máscara para CPF
        document.getElementById('cpf').addEventListener('input', function(e) {
            let value = e.target.value.replace(/\D/g, '');
            value = value.replace(/(\d{3})(\d)/, '$1.$2');
            value = value.replace(/(\d{3})(\d)/, '$1.$2');
            value = value.replace(/(\d{3})(\d{1,2})$/, '$1-$2');
            e.target.value = value;
        });

        // Validação do formulário
        document.querySelector('form').addEventListener('submit', function(e) {
            const cpf = document.getElementById('cpf').value;
            const tipo = document.getElementById('idTipo').value;
            
            if (!cpf || cpf.length < 14) {
                e.preventDefault();
                alert('Por favor, preencha um CPF válido');
                return;
            }
            
            if (!tipo) {
                e.preventDefault();
                alert('Por favor, selecione o tipo de solicitação');
                return;
            }
        });

        // Efeito de foco nos campos
        document.querySelectorAll('.form-control').forEach(input => {
            input.addEventListener('focus', function() {
                this.parentElement.classList.add('focused');
            });
            
            input.addEventListener('blur', function() {
                this.parentElement.classList.remove('focused');
            });
        });
    </script>
</body>
</html> 