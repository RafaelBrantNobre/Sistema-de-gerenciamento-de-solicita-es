package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.CargoDAO;
import br.com.secretariaescolar.dao.FuncionarioDAO;
import br.com.secretariaescolar.model.Cargo;
import br.com.secretariaescolar.model.Funcionario;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/CadastroFuncionarioServlet")
public class CadastroFuncionarioServlet extends HttpServlet {

    // 👉 Carrega a página de cadastro com a lista de cargos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Buscar lista de cargos no banco
            CargoDAO cargoDAO = new CargoDAO();
            List<Cargo> cargos = cargoDAO.listarCargos();

            // Enviar lista para o JSP
            request.setAttribute("cargos", cargos);
            request.getRequestDispatcher("cadastroFuncionario.jsp").forward(request, response);

        } catch (SQLException | NamingException e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao carregar cargos: " + e.getMessage());
        }
    }

    // 👉 Processa o cadastro do funcionário
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            // 🔸 Receber dados do formulário
            String idFuncionario = request.getParameter("idFuncionario");
            String nome = request.getParameter("nome");
            int idCargo = Integer.parseInt(request.getParameter("idCargo"));
            String dataNascimento = request.getParameter("dataNascimento");
            boolean statusConta = request.getParameter("statusConta") != null;

            // 🔸 Criar objeto Funcionario
            Funcionario funcionario = new Funcionario();
            funcionario.setIdFuncionario(idFuncionario);
            funcionario.setNome(nome);
            funcionario.setIdCargo(idCargo);
            funcionario.setDataNascimento(dataNascimento);
            funcionario.setStatusConta(statusConta);

            // 🔸 Inserir no banco
            FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
            funcionarioDAO.inserir(funcionario);

            // 🔸 Carregar lista de cargos novamente
            CargoDAO cargoDAO = new CargoDAO();
            List<Cargo> cargos = cargoDAO.listarCargos();

            // 🔸 Enviar dados e mensagem de sucesso para o JSP
            request.setAttribute("mensagem", "Funcionário cadastrado com sucesso!");
            request.setAttribute("cargos", cargos);

            request.getRequestDispatcher("cadastroFuncionario.jsp").forward(request, response);

        } catch (SQLException | NamingException e) {
            e.printStackTrace();

            try {
                // Carregar lista de cargos novamente mesmo em caso de erro
                CargoDAO cargoDAO = new CargoDAO();
                List<Cargo> cargos = cargoDAO.listarCargos();
                request.setAttribute("cargos", cargos);

            } catch (Exception ex) {
                ex.printStackTrace();
                request.setAttribute("cargos", null);
            }

            request.setAttribute("mensagem", "Erro ao cadastrar funcionário: " + e.getMessage());
            request.getRequestDispatcher("cadastroFuncionario.jsp").forward(request, response);
        }
    }
}
