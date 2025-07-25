package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.UsuarioDAO;
import br.com.secretariaescolar.model.Usuario;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String perfil = request.getParameter("perfil");         // aluno ou funcionario
        String idDigitado = request.getParameter("id");         // CPF ou ID Funcionario
        String dataNascimento = request.getParameter("dataNascimento");

        try {
            UsuarioDAO dao = new UsuarioDAO();
            Usuario usuario = dao.autenticar(perfil, idDigitado, dataNascimento);

            if (usuario != null) {
                HttpSession session = request.getSession();
                session.setAttribute("usuario", usuario);

                //  Debug opcional (ativa para testar se está pegando certo):
                System.out.println("Login bem-sucedido. ID: " + usuario.getId() + ", Tipo: " + usuario.getTipo());

                if ("aluno".equalsIgnoreCase(usuario.getTipo())) {
                    response.sendRedirect("homeAluno.jsp");
                } else {
                    switch (usuario.getNivelAcesso()) {
                        case 1:
                            response.sendRedirect("homeEstagiario.jsp");
                            break;
                        case 2:
                            response.sendRedirect("homeDiplomaRecepcao.jsp");
                            break;
                        case 3:
                            response.sendRedirect("homeSecretariaGeral.jsp");
                            break;
                        default:
                            response.sendRedirect("login.jsp");
                            break;
                    }
                }

            } else {
                response.getWriter().println(" Usuário não encontrado ou dados incorretos.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Erro SQL: " + e.getMessage());
        } catch (NamingException e) {
            e.printStackTrace();
            response.getWriter().println("Erro na conexão: " + e.getMessage());
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("login.jsp");
    }
}
