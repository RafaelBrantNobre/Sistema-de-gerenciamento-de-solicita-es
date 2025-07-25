
package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.CargoDAO;
import br.com.secretariaescolar.model.Cargo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/formCadastroFuncionario")
public class ExibirCadastroFuncionarioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            CargoDAO cargoDAO = new CargoDAO();
            List<Cargo> cargos = cargoDAO.listarCargos();
            request.setAttribute("cargos", cargos);
            request.getRequestDispatcher("cadastroFuncionario.jsp").forward(request, response);
        } catch (SQLException | javax.naming.NamingException e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao carregar os cargos.");
            request.getRequestDispatcher("cadastroFuncionario.jsp").forward(request, response);
        }
    }
}
