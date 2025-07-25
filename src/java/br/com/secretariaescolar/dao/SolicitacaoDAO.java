package br.com.secretariaescolar.dao;

import br.com.secretariaescolar.model.Solicitacao;
import br.com.secretariaescolar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class SolicitacaoDAO {

    // ✅ Inserir nova solicitação
    public void inserir(Solicitacao s) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();

        String sql = "INSERT INTO solicitacao (CPF_Aluno, ID_Tipo, Data_Entrada, Status, ID_Funcionario) " +
                     "VALUES (?, ?, ?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, s.getCpfAluno());
        stmt.setInt(2, s.getIdTipo());
        stmt.setDate(3, Date.valueOf(s.getDataEntrada()));
        stmt.setBoolean(4, s.isStatus());
        stmt.setString(5, s.getIdFuncionario());

        stmt.executeUpdate();
        conn.close();
    }

    // ✅ Listar solicitações de um aluno específico
    public List<Solicitacao> listarPorAluno(String cpfAluno) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();

        String sql = "SELECT s.*, a.Nome_Aluno, t.Nome_Tipo, f.Nome_funcionario, c.Nome_Curso " +
                     "FROM solicitacao s " +
                     "JOIN aluno a ON s.CPF_Aluno = a.CPF_Aluno " +
                     "LEFT JOIN matricula m ON a.CPF_Aluno = m.CPF_FK " +
                     "LEFT JOIN curso c ON m.Curso_FK = c.ID_Curso " +
                     "JOIN tipo_solicitacao t ON s.ID_Tipo = t.ID_Tipo " +
                     "LEFT JOIN funcionario f ON s.ID_Funcionario = f.ID_Funcionario " +
                     "WHERE s.CPF_Aluno = ? " +
                     "ORDER BY s.ID_Solicitacao DESC";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, cpfAluno);
        ResultSet rs = stmt.executeQuery();

        List<Solicitacao> lista = new ArrayList<>();

        while (rs.next()) {
            Solicitacao s = mapearSolicitacao(rs);
            lista.add(s);
        }

        conn.close();
        return lista;
    }

    // ✅ Listar todas as solicitações (para funcionários)
    public List<Solicitacao> listarTodasSolicitacoes() throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();

        String sql = "SELECT s.*, a.Nome_Aluno, t.Nome_Tipo, f.Nome_funcionario, c.Nome_Curso " +
                     "FROM solicitacao s " +
                     "JOIN aluno a ON s.CPF_Aluno = a.CPF_Aluno " +
                     "LEFT JOIN matricula m ON a.CPF_Aluno = m.CPF_FK " +
                     "LEFT JOIN curso c ON m.Curso_FK = c.ID_Curso " +
                     "JOIN tipo_solicitacao t ON s.ID_Tipo = t.ID_Tipo " +
                     "LEFT JOIN funcionario f ON s.ID_Funcionario = f.ID_Funcionario " +
                     "ORDER BY s.ID_Solicitacao DESC";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Solicitacao> lista = new ArrayList<>();

        while (rs.next()) {
            Solicitacao s = mapearSolicitacao(rs);
            lista.add(s);
        }

        conn.close();
        return lista;
    }

    // ✅ Atualizar status (abrir ou encerrar)
    public void atualizarStatus(int idSolicitacao, boolean status) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();

        String sql = "UPDATE solicitacao SET Status = ? WHERE ID_Solicitacao = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setBoolean(1, status);
        stmt.setInt(2, idSolicitacao);

        stmt.executeUpdate();
        conn.close();
    }

    // ✅ Encerrar solicitação (status = false)
    public void encerrarSolicitacao(int idSolicitacao) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();

        String sql = "UPDATE solicitacao SET Status = false WHERE ID_Solicitacao = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setInt(1, idSolicitacao);
        stmt.executeUpdate();

        conn.close();
    }

    // ✅ Buscar funcionário responsável pelo atendimento (cargo específico)
    public String buscarFuncionarioResponsavel() throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();

        String sql = "SELECT ID_Funcionario " +
                     "FROM funcionario " +
                     "WHERE ID_Cargo = 2 AND Status_Conta = TRUE " +
                     "LIMIT 1";

        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        String idFuncionario = null;
        if (rs.next()) {
            idFuncionario = rs.getString("ID_Funcionario");
        }

        conn.close();
        return idFuncionario;
    }

    // ✅ Função auxiliar para mapear os dados do ResultSet para o objeto Solicitacao
    private Solicitacao mapearSolicitacao(ResultSet rs) throws SQLException {
        Solicitacao s = new Solicitacao();

        s.setIdSolicitacao(rs.getInt("ID_Solicitacao"));
        s.setCpfAluno(rs.getString("CPF_Aluno"));
        s.setIdTipo(rs.getInt("ID_Tipo"));
        s.setDataEntrada(rs.getDate("Data_Entrada").toString());
        s.setStatus(rs.getBoolean("Status"));
        s.setIdFuncionario(rs.getString("ID_Funcionario"));

        // Campos adicionais provenientes dos JOINs
        s.setNomeAluno(rs.getString("Nome_Aluno"));
        s.setNomeCurso(rs.getString("Nome_Curso")); // Pode ser null
        s.setNomeTipo(rs.getString("Nome_Tipo"));
        s.setNomeFuncionario(rs.getString("Nome_funcionario")); // Pode ser null

        return s;
    }
}
