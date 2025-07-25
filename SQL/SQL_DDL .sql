-- Criação do Banco de Dados
CREATE DATABASE IF NOT EXISTS secretariaescolar;
USE secretariaescolar;

-- ======================
-- Tabela CURSO
-- ======================
CREATE TABLE CURSO (
    ID_Curso INT AUTO_INCREMENT PRIMARY KEY,
    Nome_Curso VARCHAR(50) NOT NULL
);

-- ======================
-- Tabela CARGO
-- ======================
CREATE TABLE CARGO (
    ID_Cargo INT AUTO_INCREMENT PRIMARY KEY,
    Nome_Cargo VARCHAR(50) NOT NULL,
    Nivel_Acesso INT NOT NULL
);

-- ======================
-- Tabela FUNCIONARIO
-- ======================
CREATE TABLE FUNCIONARIO (
    ID_Funcionario VARCHAR(9) PRIMARY KEY,
    Nome_funcionario VARCHAR(100) NOT NULL,
    ID_Cargo INT NOT NULL,
    Data_nasc_fc DATE NOT NULL,
    Status_Conta BOOLEAN NOT NULL,
    FOREIGN KEY (ID_Cargo) REFERENCES CARGO(ID_Cargo)
);

-- ======================
-- Tabela ALUNO
-- ======================
CREATE TABLE ALUNO (
    CPF_Aluno VARCHAR(11) PRIMARY KEY,
    Nome_aluno VARCHAR(100) NOT NULL,
    Situacao VARCHAR(10) NOT NULL,
    Ativacao_Conta BOOLEAN NOT NULL,
    Data_Nascimento DATE NOT NULL
);

-- ======================
-- Tabela TIPO_SOLICITACAO
-- ======================
CREATE TABLE TIPO_SOLICITACAO (
    ID_Tipo INT AUTO_INCREMENT PRIMARY KEY,
    Nome_Tipo VARCHAR(100) NOT NULL,
    Prazo_Atendimento INT NOT NULL
);

-- ======================
-- Tabela ETAPAS
-- ======================
CREATE TABLE ETAPAS (
    ID_Etapa INT AUTO_INCREMENT PRIMARY KEY,
    Ordem INT NOT NULL,
    ID_Tipo INT NOT NULL,
    Nome_Etapa VARCHAR(100) NOT NULL,
    Descricao TEXT NULL,
    ID_Cargo INT NOT NULL,
    FOREIGN KEY (ID_Tipo) REFERENCES TIPO_SOLICITACAO(ID_Tipo),
    FOREIGN KEY (ID_Cargo) REFERENCES CARGO(ID_Cargo)
);

-- ======================
-- Tabela SOLICITACAO
-- ======================
CREATE TABLE SOLICITACAO (
    ID_Solicitacao INT AUTO_INCREMENT PRIMARY KEY,
    CPF_Aluno VARCHAR(11) NOT NULL,
    ID_Tipo INT NOT NULL,
    Data_Entrada DATE NOT NULL,
    Status BOOLEAN NOT NULL,
    ID_Funcionario VARCHAR(9) NOT NULL,
    FOREIGN KEY (CPF_Aluno) REFERENCES ALUNO(CPF_Aluno),
    FOREIGN KEY (ID_Tipo) REFERENCES TIPO_SOLICITACAO(ID_Tipo),
    FOREIGN KEY (ID_Funcionario) REFERENCES FUNCIONARIO(ID_Funcionario)
);

-- ======================
-- Tabela TRAMITACAO
-- ======================
CREATE TABLE TRAMITACAO (
    ID_Tramitacao INT AUTO_INCREMENT PRIMARY KEY,
    ID_Solicitacao INT NOT NULL,
    ID_Etapa INT NOT NULL,
    Data_hora DATETIME NOT NULL,
    Parecer TEXT NULL,
    ID_Funcionario VARCHAR(9) NOT NULL,
    FOREIGN KEY (ID_Solicitacao) REFERENCES SOLICITACAO(ID_Solicitacao),
    FOREIGN KEY (ID_Etapa) REFERENCES ETAPAS(ID_Etapa),
    FOREIGN KEY (ID_Funcionario) REFERENCES FUNCIONARIO(ID_Funcionario)
);


-- ======================
-- Tabela MATRICULA
-- ======================
CREATE TABLE MATRICULA (
    Matricula_PK VARCHAR(9) PRIMARY KEY,
    Curso_FK INT NOT NULL,
    CPF_FK VARCHAR(11) NOT NULL,
    FOREIGN KEY (Curso_FK) REFERENCES CURSO(ID_Curso),
    FOREIGN KEY (CPF_FK) REFERENCES ALUNO(CPF_Aluno)
);
