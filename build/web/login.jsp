<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="css/estilo.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/main.css">
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/login.css">
    <title>Login</title>
</head>
<body class="login-page">
    <div class="login-container">
        <div class="login-header">
            <span class="login-logo">SCSA</span>
            <h2 class="login-title">Login</h2>
            <p class="login-subtitle">Acesse o sistema de secretaria escolar</p>
        </div>

        <form action="login" method="post" class="login-form">
            <div class="form-group">
                <label class="form-label">Você é:</label>
                <div class="form-check">
                    <input type="radio" name="perfil" value="aluno" checked class="form-check-input">
                    <label class="form-check-label">Aluno</label>
                </div>
                <div class="form-check">
                    <input type="radio" name="perfil" value="funcionario" class="form-check-input">
                    <label class="form-check-label">Funcionário</label>
                </div>
            </div>

            <div class="form-group">
                <label for="id" class="form-label">ID:</label>
                <input type="text" name="id" id="id" class="form-control" placeholder="Digite seu CPF ou ID" required>
            </div>

            <div class="form-group">
                <label for="dataNascimento" class="form-label">Data de nascimento:</label>
                <input type="date" name="dataNascimento" id="dataNascimento" class="form-control" required>
            </div>

            <button type="submit" class="login-btn">Entrar</button>
        </form>

        <div class="login-links">
            <a href="#">Preciso de ajuda</a>
        </div>
    </div>
</body>
</html>