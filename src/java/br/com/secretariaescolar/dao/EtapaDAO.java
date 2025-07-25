package br.com.secretariaescolar.dao;

import br.com.secretariaescolar.model.Etapa;
import br.com.secretariaescolar.util.Conexao;

import java.sql.*;
import java.util.*;
import javax.naming.NamingException;

public class EtapaDAO {

    public List<Etapa> listarPorTipo(int idTipo) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();

        String sql = "SELECT e.*, c.Nome_Cargo " +
                     "FROM etapas e " +
                     "JOIN cargo c ON e.ID_Cargo = c.ID_Cargo " +
                     "WHERE e.ID_Tipo = ? " +
                     "ORDER BY e.Ordem ASC";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idTipo);
        ResultSet rs = stmt.executeQuery();

        List<Etapa> lista = new ArrayList<>();
        while (rs.next()) {
            Etapa e = new Etapa();
            e.setIdEtapa(rs.getInt("ID_Etapa"));
            e.setOrdem(rs.getInt("Ordem"));
            e.setIdTipo(rs.getInt("ID_Tipo"));
            e.setNomeEtapa(rs.getString("Nome_Etapa"));
            e.setDescricao(rs.getString("Descricao"));
            e.setIdCargo(rs.getInt("ID_Cargo"));
            e.setNomeCargo(rs.getString("Nome_Cargo"));

            lista.add(e);
        }

        conn.close();
        return lista;
    }
  public List<Etapa> listarEtapasPorTipoSolicitacao(int idTipoSolicitacao) throws SQLException, NamingException {
    Connection conn = Conexao.getConnection();
    String sql = "SELECT * FROM etapas WHERE ID_Tipo = ? ORDER BY Ordem";
    PreparedStatement stmt = conn.prepareStatement(sql);
    stmt.setInt(1, idTipoSolicitacao);
    ResultSet rs = stmt.executeQuery();

    List<Etapa> lista = new ArrayList<>();
    while (rs.next()) {
        Etapa e = new Etapa();
        e.setIdEtapa(rs.getInt("ID_Etapa"));
        e.setNomeEtapa(rs.getString("Nome_Etapa"));
        e.setDescricao(rs.getString("Descricao"));
        e.setOrdem(rs.getInt("Ordem"));
        e.setIdTipo(rs.getInt("ID_Tipo"));
        lista.add(e);
    }

    conn.close();
    return lista;
}
  
    
}
