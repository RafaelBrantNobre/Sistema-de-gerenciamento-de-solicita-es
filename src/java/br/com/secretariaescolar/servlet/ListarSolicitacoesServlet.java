package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.SolicitacaoDAO;
import br.com.secretariaescolar.model.Solicitacao;
import br.com.secretariaescolar.model.Usuario;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/listarSolicitacoes")
public class ListarSolicitacoesServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        if (usuario == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try {
            SolicitacaoDAO dao = new SolicitacaoDAO();
            List<Solicitacao> lista;

            if (usuario.getTipo().equals("aluno")) {
                // 🔸 Aluno vê apenas suas próprias solicitações
                lista = dao.listarPorAluno(usuario.getId());
            } else {
                // 🔸 Funcionário vê todas as solicitações
                lista = dao.listarTodasSolicitacoes();
            }

            request.setAttribute("solicitacoes", lista);
            request.getRequestDispatcher("listarSolicitacoes.jsp").forward(request, response);

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao listar solicitações: " + e.getMessage());
        }
    }
}
