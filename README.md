# Sistema-de-gerenciamento-de-solicitacoes

Projeto Secretaria Escolar
Este projeto consiste em um sistema acadêmico web desenvolvido com o objetivo de gerenciar solicitações documentais realizadas por alunos e ex-alunos de uma instituição de ensino superior. O sistema permite o acompanhamento do fluxo de atendimento de solicitações, bem como a administração de funcionários responsáveis por cada etapa do processo.

Objetivo
Criar uma aplicação web funcional que simula o funcionamento de uma secretaria acadêmica, com foco em organização, tramitação e transparência no atendimento às demandas estudantis. O projeto também serve como base didática para disciplinas de Engenharia de Software, Programação Orientada a Objetos e Banco de Dados.

Tecnologias Utilizadas
Linguagem de Programação: Java (versão 8)

Servidor de Aplicação: GlassFish Server Open Source Edition 5.1.0

Tecnologia Web: JavaServer Pages (JSP) e Servlets

Banco de Dados Relacional: MySQL (versão 8.0)

Gerenciador de Servidor: Apache NetBeans IDE 12.6

Servidor Web Alternativo (opcional): Apache Tomcat 9.0.x

Estrutura do Projeto
/src: Contém os pacotes Java, incluindo classes de modelo (beans), acesso a dados (DAO) e controle (servlets).

/web: Diretório com as páginas JSP, arquivos HTML, CSS e JavaScript.

/sql: Scripts de definição do banco de dados (DDL), incluindo todas as tabelas e relacionamentos.

web.xml: Arquivo de configuração da aplicação (deployment descriptor).

Conexao.java: Classe responsável pela conexão com o banco de dados MySQL.

Requisitos do Sistema
Java Development Kit (JDK) 8 ou superior

Servidor GlassFish 5.1.0 ou Tomcat 9.0.x

MySQL Server 8.0

NetBeans 12.6 (ou IDE equivalente com suporte a Java EE)

Configuração e Execução
Banco de Dados:

Criar um banco de dados chamado secretariaescolar no MySQL.

Executar o script DDL disponível na pasta /sql para criação das tabelas e relacionamentos.

Verificar e, se necessário, ajustar as credenciais de acesso ao banco na classe Conexao.java.

Aplicação Web:

Importar o projeto na IDE NetBeans (ou equivalente).

Configurar o GlassFish Server (versão 5.1.0) ou Tomcat como servidor de aplicação.

Executar o projeto e acessar via navegador (http://localhost:8080/SecretariaEscolar ou equivalente).

Funcionalidades Implementadas
Cadastro e login de alunos e funcionários.

Interface segmentada por perfil de acesso (aluno, recepção, diploma, secretária geral).

Solicitação de documentos com geração automática de número de protocolo.

Tramitação de etapas com atualização de status e registro de data.

Encerramento de solicitações e histórico por usuário.

Controle de funcionários e desativação de contas pela secretária geral.

Considerações Finais
O sistema foi projetado com arquitetura simples, mas modular, visando demonstrar os conceitos fundamentais de uma aplicação web em Java com persistência em banco relacional. O projeto pode ser estendido para inclusão de autenticação mais robusta, relatórios, notificações e controle de permissões mais refinado.

Este repositório é de uso educacional e pode ser adaptado para fins institucionais mediante reestruturação de código e requisitos de segurança.


