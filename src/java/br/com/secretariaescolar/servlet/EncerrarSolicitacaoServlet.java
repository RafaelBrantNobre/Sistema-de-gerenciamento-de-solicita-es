package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.SolicitacaoDAO;
import br.com.secretariaescolar.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;

@WebServlet("/encerrarSolicitacao")
public class EncerrarSolicitacaoServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("usuario");

        // ✅ Verifica se o usuário está logado e tem nível de acesso 2 ou maior
        if (usuario == null || usuario.getNivelAcesso() < 2) {
            response.sendRedirect("acessoNegado.jsp"); // ou outra página de erro
            return;
        }

        int idSolicitacao = Integer.parseInt(request.getParameter("id"));

        try {
            SolicitacaoDAO dao = new SolicitacaoDAO();
            dao.encerrarSolicitacao(idSolicitacao);
            response.sendRedirect("listarSolicitacoes");
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao encerrar solicitação: " + e.getMessage());
        }
    }
}
