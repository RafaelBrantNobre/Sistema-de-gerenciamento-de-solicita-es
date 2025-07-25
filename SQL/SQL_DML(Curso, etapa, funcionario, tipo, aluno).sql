-- Selecionar o banco de dados
USE secretariaescolar;

-- 1. Inserir dados na tabela CURSO
INSERT INTO CURSO (ID_Curso, Nome_Curso) VALUES
(1, 'Sistemas de Informação'),
(2, 'Direito'),
(3, 'Administração');

-- 2. Inserir dados na tabela CARGO
INSERT INTO CARGO (ID_Cargo, Nome_Cargo, Nivel_Acesso) VALUES
(1, 'Estagiário', 1),
(2, 'Secretária Geral', 3),
(3, 'Recepção', 2),
(4, 'Setor de Diploma', 2);

-- 3. Inserir dados na tabela FUNCIONARIO
INSERT INTO FUNCIONARIO (ID_Funcionario, Nome_funcionario, ID_Cargo, Data_nasc_fc, Status_Conta) VALUES
('000000001', 'Ana Souza', 1, '2000-05-10', TRUE),
('000000002', 'Bruno Lima', 1, '2001-06-15', TRUE),
('000000003', 'Carla Ferreira', 1, '2002-07-20', TRUE),
('000000004', 'Daniela Castro', 2, '1980-03-25', TRUE),
('000000005', 'Eduardo Silva', 3, '1985-08-30', TRUE),
('000000006', 'Fernanda Alves', 4, '1975-12-05', TRUE);

-- 4. Inserir dados na tabela ALUNO
INSERT INTO ALUNO (CPF_Aluno, Nome_aluno, Situacao, Ativacao_Conta, Data_Nascimento) VALUES
('0000000001', 'Lucas Pereira', 'Regular', TRUE, '2003-01-01'),
('0000000002', 'Mariana Costa', 'Desistente', TRUE, '2002-02-02'),
('0000000003', 'Rafael Martins', 'Trancado', TRUE, '2001-03-03'),
('0000000004', 'Isabela Rocha', 'Regular', TRUE, '2000-04-04'),
('0000000005', 'Pedro Almeida', 'Formado', TRUE,  '1999-05-05'),
('0000000006', 'Fernanda Ribeiro', 'Regular', TRUE, '1998-06-06'),
('0000000007', 'Gabriel Lima', 'Regular', TRUE, '1997-07-07'),
('0000000008', 'Juliana Mendes', 'Regular', TRUE, '1996-08-08'),
('0000000009', 'Vinícius Barbosa', 'Desistente', TRUE, '1995-09-09'),
('0000000010', 'Amanda Fernandes', 'Regular', TRUE, '1994-10-10');

-- 5. Inserir dados na tabela TIPO_SOLICITACAO
INSERT INTO TIPO_SOLICITACAO (ID_Tipo, Nome_Tipo, Prazo_Atendimento) VALUES
(1, 'Histórico Físico', 7),
(2, 'Declaração Física', 3),
(3, 'Diploma', 30);

-- 6. Inserir dados na tabela ETAPAS (agora vinculadas somente a CARGO)

-- Declaração Física (ID_Tipo = 2)
INSERT INTO ETAPAS (ID_Etapa, Ordem, ID_Tipo, Nome_Etapa, Descricao, ID_Cargo) VALUES
(1, 1, 2, 'Emissão Documento', 'Emissão da declaração pelo estagiário responsável', 1),
(2, 2, 2, 'Envio para Assinatura', 'Envio da declaração para assinatura na Secretaria Geral', 2),
(3, 3, 2, 'Assinatura e Envio Recepção', 'Secretária assina e encaminha para recepção', 2),
(4, 4, 2, 'Disponível para Retirada', 'Recepção informa que o documento está disponível', 3);

-- Histórico Físico (ID_Tipo = 1)
INSERT INTO ETAPAS (ID_Etapa, Ordem, ID_Tipo, Nome_Etapa, Descricao, ID_Cargo) VALUES
(5, 1, 1, 'Emissão Histórico', 'Emissão do histórico físico pelo estagiário responsável', 1),
(6, 2, 1, 'Envio para Assinatura', 'Envio do histórico para assinatura na Secretaria Geral', 2),
(7, 3, 1, 'Assinatura e Envio Direção', 'Secretária assina e encaminha para Direção de Centro', 2),
(8, 4, 1, 'Chegada na Secretaria', 'Recepção registra a chegada do documento assinado', 3),
(9, 5, 1, 'Disponível para Retirada', 'Recepção informa que o documento está disponível', 3),
(10, 6, 1, 'Finalização da Solicitação', 'Recepção finaliza a solicitação após retirada do documento', 3);

-- Diploma (ID_Tipo = 3)
INSERT INTO ETAPAS (ID_Etapa, Ordem, ID_Tipo, Nome_Etapa, Descricao, ID_Cargo) VALUES
(11, 1, 3, 'Envio Docs Conferência', 'Estagiário envia documentos para o setor de diploma', 1),
(12, 2, 3, 'Conferência Documentação', 'Setor de diploma realiza a conferência dos documentos', 4),
(13, 3, 3, 'Devolução p/ Regularização', 'Caso haja pendências, o setor de diploma devolve para o estagiário responsável', 4),
(14, 4, 3, 'Confecção do Diploma', 'Setor de diploma confecciona o diploma após conferência', 4),
(15, 5, 3, 'Disponível para Retirada', 'Setor de diploma informa que o diploma está disponível', 4),
(16, 6, 3, 'Finalização da Solicitação', 'Setor de diploma finaliza após retirada do diploma', 4);

-- 7. Inserir dados na tabela MATRICULA
INSERT INTO MATRICULA (Matricula_PK, Curso_FK, CPF_FK) VALUES
('M001', 1, '0000000001'),
('M002', 2, '0000000002'),
('M003', 3, '0000000003'),
('M004', 1, '0000000004'),
('M005', 2, '0000000005'),
('M006', 3, '0000000006'),
('M007', 1, '0000000007'),
('M008', 2, '0000000008'),
('M009', 3, '0000000009'),
('M010', 1, '0000000010');
