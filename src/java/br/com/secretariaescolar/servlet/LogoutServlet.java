package br.com.secretariaescolar.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 🔥 Invalida a sessão
        HttpSession session = request.getSession(false); // 🔸 false = não cria nova sessão se não existir
        if (session != null) {
            session.invalidate();
        }

        // 🔄 Redireciona para a página de login
        response.sendRedirect("login.jsp");
    }
}
