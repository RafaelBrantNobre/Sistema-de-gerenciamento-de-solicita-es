package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.EtapaDAO;
import br.com.secretariaescolar.dao.TramitacaoDAO;
import br.com.secretariaescolar.model.Etapa;
import br.com.secretariaescolar.model.Tramitacao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/tramitar-solicitacao")
public class TramitarSolicitacaoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idSolicitacao = Integer.parseInt(request.getParameter("idSolicitacao"));
            int idTipoSolicitacao = Integer.parseInt(request.getParameter("idTipo"));

            // 🔥 Buscar as etapas daquela solicitação
            EtapaDAO etapaDAO = new EtapaDAO();
            List<Etapa> etapas = etapaDAO.listarEtapasPorTipoSolicitacao(idTipoSolicitacao);

            // 🔥 Buscar histórico de tramitações da solicitação
            TramitacaoDAO tramitacaoDAO = new TramitacaoDAO();
            List<Tramitacao> historico = tramitacaoDAO.listarPorSolicitacao(idSolicitacao);

            // 🔥 Enviar dados para o JSP
            request.setAttribute("etapas", etapas);
            request.setAttribute("idSolicitacao", idSolicitacao);
            request.setAttribute("historico", historico);

            request.getRequestDispatcher("tramitarSolicitacao.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Erro ao carregar tramitação: " + e.getMessage());
        }
    }
}
