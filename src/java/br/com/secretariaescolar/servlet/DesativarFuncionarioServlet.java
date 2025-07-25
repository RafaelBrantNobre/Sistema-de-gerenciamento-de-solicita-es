package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.FuncionarioDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;

@WebServlet("/desativarFuncionario")
public class DesativarFuncionarioServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        boolean status = Boolean.parseBoolean(request.getParameter("status"));

        try {
            FuncionarioDAO dao = new FuncionarioDAO();
            dao.atualizarStatusFuncionario(id, status);
            response.sendRedirect("homeSecretariaGeral.jsp");
        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao atualizar status do funcionário: " + e.getMessage());
        }
    }
}
