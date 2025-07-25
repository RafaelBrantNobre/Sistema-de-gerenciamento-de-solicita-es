package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.TramitacaoDAO;
import br.com.secretariaescolar.model.Tramitacao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/historico-tramitacoes")
public class HistoricoTramitacoesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idSolicitacao = Integer.parseInt(request.getParameter("idSolicitacao"));

            // 🔥 Buscar tramitações dessa solicitação
            TramitacaoDAO dao = new TramitacaoDAO();
            List<Tramitacao> lista = dao.listarPorSolicitacao(idSolicitacao);

            // 🔥 Enviar dados para o JSP
            request.setAttribute("tramitacoes", lista);
            request.setAttribute("idSolicitacao", idSolicitacao);

            request.getRequestDispatcher("historicoTramitacoes.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao carregar histórico: " + e.getMessage());
        }
    }
}
