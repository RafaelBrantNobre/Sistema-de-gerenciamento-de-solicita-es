package br.com.secretariaescolar.dao;

import br.com.secretariaescolar.model.Tramitacao;
import br.com.secretariaescolar.util.Conexao;

import java.sql.*;
import java.util.*;
import javax.naming.NamingException;

public class TramitacaoDAO {

    // 🔥 Inserir nova tramitação
    public void inserirTramitacao(int idSolicitacao, int idEtapa, String dataHora, String parecer, String idFuncionario)
            throws SQLException, NamingException {

        Connection conn = Conexao.getConnection();

        String sql = "INSERT INTO tramitacao (ID_Solicitacao, ID_Etapa, Data_hora, Parecer, ID_Funcionario) " +
                     "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idSolicitacao);
        stmt.setInt(2, idEtapa);
        stmt.setString(3, dataHora);
        stmt.setString(4, parecer);
        stmt.setString(5, idFuncionario);

        stmt.executeUpdate();
        conn.close();
    }

    // 🔍 Listar tramitações por solicitação COM NOME DO FUNCIONÁRIO
    public List<Tramitacao> listarPorSolicitacao(int idSolicitacao) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();

        String sql = "SELECT t.*, f.Nome_funcionario, e.Nome_Etapa " +
                     "FROM tramitacao t " +
                     "JOIN funcionario f ON t.ID_Funcionario = f.ID_Funcionario " +
                     "JOIN etapas e ON t.ID_Etapa = e.ID_Etapa " +
                     "WHERE t.ID_Solicitacao = ? " +
                     "ORDER BY t.Data_hora ASC";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idSolicitacao);
        ResultSet rs = stmt.executeQuery();

        List<Tramitacao> lista = new ArrayList<>();
        while (rs.next()) {
            Tramitacao t = new Tramitacao();
            t.setIdTramitacao(rs.getInt("ID_Tramitacao"));
            t.setIdSolicitacao(rs.getInt("ID_Solicitacao"));
            t.setIdEtapa(rs.getInt("ID_Etapa"));
            t.setDataHora(rs.getTimestamp("Data_hora").toString());
            t.setParecer(rs.getString("Parecer"));
            t.setIdFuncionario(rs.getString("ID_Funcionario"));

            // Dados adicionais
            t.setNomeFuncionario(rs.getString("Nome_funcionario"));
            t.setNomeEtapa(rs.getString("Nome_Etapa"));

            lista.add(t);
        }

        conn.close();
        return lista;
    }
}
