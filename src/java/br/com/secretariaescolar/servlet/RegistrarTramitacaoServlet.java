package br.com.secretariaescolar.servlet;

import br.com.secretariaescolar.dao.TramitacaoDAO;
import br.com.secretariaescolar.model.Usuario;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet("/registrar-tramitacao")
public class RegistrarTramitacaoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            int idSolicitacao = Integer.parseInt(request.getParameter("idSolicitacao"));
            String[] etapasSelecionadas = request.getParameterValues("etapas");
            String parecer = request.getParameter("parecer");

            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            String idFuncionario = usuario.getId();

            String dataHora = LocalDateTime.now().toString(); // Data e hora atual do sistema

            TramitacaoDAO dao = new TramitacaoDAO();

            if (etapasSelecionadas != null) {
                for (String etapaId : etapasSelecionadas) {
                    int idEtapa = Integer.parseInt(etapaId);

                    dao.inserirTramitacao(idSolicitacao, idEtapa, dataHora, parecer, idFuncionario);
                }
            }

            request.setAttribute("mensagem", "Tramitação registrada com sucesso!");
            request.setAttribute("idSolicitacao", idSolicitacao);

            request.getRequestDispatcher("tramitarSolicitacao.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("mensagem", "Erro ao registrar tramitação: " + e.getMessage());
            request.getRequestDispatcher("tramitarSolicitacao.jsp").forward(request, response);
        }
    }
}
