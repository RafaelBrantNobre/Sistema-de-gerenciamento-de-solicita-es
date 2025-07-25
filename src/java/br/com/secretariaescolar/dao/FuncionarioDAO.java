package br.com.secretariaescolar.dao;

import br.com.secretariaescolar.model.Funcionario;
import br.com.secretariaescolar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class FuncionarioDAO {

    public void inserir(Funcionario f) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();
        String sql = "INSERT INTO funcionario (ID_Funcionario, Nome_funcionario, ID_Cargo, Data_nasc_fc, Status_Conta) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, f.getIdFuncionario());
        stmt.setString(2, f.getNome());
        stmt.setInt(3, f.getIdCargo());
        stmt.setDate(4, java.sql.Date.valueOf(f.getDataNascimento()));
        stmt.setBoolean(5, f.isStatusConta());
        stmt.executeUpdate();
        conn.close();
    }

    public void atualizarStatusFuncionario(String id, boolean status) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();
        String sql = "UPDATE funcionario SET Status_Conta = ? WHERE ID_Funcionario = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setBoolean(1, status);
        stmt.setString(2, id);
        stmt.executeUpdate();
        conn.close();
    }

    public Funcionario buscarPorId(String id) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();
        String sql = "SELECT * FROM funcionario WHERE ID_Funcionario = ?";
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, id);
        ResultSet rs = stmt.executeQuery();

        Funcionario f = null;
        if (rs.next()) {
            f = new Funcionario();
            f.setIdFuncionario(rs.getString("ID_Funcionario"));
            f.setNome(rs.getString("Nome_funcionario"));
            f.setIdCargo(rs.getInt("ID_Cargo"));
            f.setDataNascimento(rs.getString("Data_nasc_fc"));
            f.setStatusConta(rs.getBoolean("Status_Conta"));
        }

        rs.close();
        stmt.close();
        conn.close();
        return f;
    }

    public List<Funcionario> listarAtivos() throws SQLException, NamingException {
        List<Funcionario> lista = new ArrayList<>();
        Connection conn = Conexao.getConnection();
        String sql = "SELECT * FROM funcionario WHERE Status_Conta = TRUE";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Funcionario f = new Funcionario();
            f.setIdFuncionario(rs.getString("ID_Funcionario"));
            f.setNome(rs.getString("Nome_funcionario"));
            f.setIdCargo(rs.getInt("ID_Cargo"));
            f.setDataNascimento(rs.getString("Data_nasc_fc"));
            f.setStatusConta(rs.getBoolean("Status_Conta"));
            lista.add(f);
        }

        rs.close();
        stmt.close();
        conn.close();
        return lista;
    }
}
