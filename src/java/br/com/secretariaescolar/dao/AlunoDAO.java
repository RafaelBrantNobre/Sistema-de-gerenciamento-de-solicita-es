package br.com.secretariaescolar.dao;

import br.com.secretariaescolar.model.Aluno;
import br.com.secretariaescolar.util.Conexao;

import java.sql.*;
import java.util.*;
import javax.naming.NamingException;

public class AlunoDAO {

    public void inserir(Aluno aluno) throws SQLException, NamingException {
        String sql = "INSERT INTO aluno (CPF_Aluno, Nome_aluno, Situacao, Ativacao_Conta, Data_Nascimento) "
                   + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, aluno.getCpf());
            stmt.setString(2, aluno.getNome());
            stmt.setString(3, aluno.getSituacao());
            stmt.setBoolean(4, aluno.isAtivacaoConta());
            stmt.setString(5, aluno.getDataNascimento());

            stmt.executeUpdate();
        }
    }

    public List<Aluno> listar() throws SQLException, NamingException {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM aluno";

        try (Connection conn = Conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Aluno a = new Aluno();
                a.setCpf(rs.getString("CPF_Aluno"));
                a.setNome(rs.getString("Nome_aluno"));
                a.setSituacao(rs.getString("Situacao"));
                a.setAtivacaoConta(rs.getBoolean("Ativacao_Conta"));
                a.setDataNascimento(rs.getString("Data_Nascimento"));

                lista.add(a);
            }
        }

        return lista;
    }
}
