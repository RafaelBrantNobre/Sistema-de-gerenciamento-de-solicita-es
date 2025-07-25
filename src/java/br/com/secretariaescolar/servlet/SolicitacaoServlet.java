package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.SolicitacaoDAO;
import br.com.secretariaescolar.model.Solicitacao;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/solicitar")
public class SolicitacaoServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

String cpf = request.getParameter("cpf").replaceAll("\\D", "");

        int idTipo = Integer.parseInt(request.getParameter("idTipo"));
        String dataEntrada = LocalDate.now().toString();
        boolean status = true;

        try {
            SolicitacaoDAO dao = new SolicitacaoDAO();

            // 🔥 Busca automaticamente um funcionário com ID_Cargo = 4
            String idFuncionario = dao.buscarFuncionarioResponsavel();

            if (idFuncionario == null) {
                response.getWriter().println("Erro: Nenhum funcionário com ID_Cargo = 4 foi encontrado.");
                return;
            }

            // 🔧 Cria objeto solicitação
            Solicitacao s = new Solicitacao(cpf, idTipo, dataEntrada, status, idFuncionario);

            // 🔥 Insere no banco
            dao.inserir(s);

            // 🔄 Redireciona para homeAluno
            response.sendRedirect("homeAluno.jsp");

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao cadastrar solicitação: " + e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
            response.getWriter().println("Erro na conexão: " + e.getMessage());
        }
    }
}
