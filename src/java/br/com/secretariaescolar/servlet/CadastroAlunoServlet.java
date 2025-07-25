package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.AlunoDAO;
import br.com.secretariaescolar.dao.MatriculaDAO;
import br.com.secretariaescolar.model.Aluno;
import br.com.secretariaescolar.model.Matricula;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/cadastro-aluno")
public class CadastroAlunoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 🔸 Dados do aluno
            String cpf = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String situacao = request.getParameter("situacao");
            boolean ativacao = request.getParameter("ativacao") != null;
            String dataNascimento = request.getParameter("dataNascimento");

            // 🔸 Dados da matrícula
            int idCurso = Integer.parseInt(request.getParameter("idCurso"));
            String matriculaPK = cpf; // Pode ser o CPF ou outro código que você escolher gerar

            // 🔸 Cadastrar aluno
            Aluno aluno = new Aluno(cpf, nome, situacao, ativacao, dataNascimento);
            AlunoDAO alunoDAO = new AlunoDAO();
            alunoDAO.inserir(aluno);

            // 🔸 Cadastrar matrícula automaticamente
            Matricula matricula = new Matricula();
            matricula.setMatriculaPK(matriculaPK);
            matricula.setIdCurso(idCurso);
            matricula.setCpfAluno(cpf);

            MatriculaDAO matriculaDAO = new MatriculaDAO();
            matriculaDAO.inserir(matricula);

            // 🔸 Mensagem de sucesso
            request.setAttribute("mensagem", "Aluno cadastrado e matrícula realizada com sucesso!");

            request.getRequestDispatcher("cadastroAluno.jsp").forward(request, response);

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao cadastrar aluno: " + e.getMessage());
            request.getRequestDispatcher("cadastroAluno.jsp").forward(request, response);
        }
    }
}
