package br.com.secretariaescolar.dao;

import br.com.secretariaescolar.model.Usuario;
import br.com.secretariaescolar.util.Conexao;

import java.sql.*;
import javax.naming.NamingException;

public class UsuarioDAO {

    public Usuario autenticar(String perfil, String id, String dataNascimento) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();

        if (perfil.equalsIgnoreCase("funcionario")) {
            String sql = "SELECT f.Nome_funcionario, c.Nivel_Acesso " +
                         "FROM funcionario f " +
                         "JOIN cargo c ON f.ID_Cargo = c.ID_Cargo " +
                         "WHERE f.ID_Funcionario = ? AND f.Data_nasc_fc = ? AND f.Status_Conta = true";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setDate(2, java.sql.Date.valueOf(dataNascimento));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(id);
                u.setNome(rs.getString("Nome_funcionario"));
                u.setTipo("funcionario");
                u.setNivelAcesso(rs.getInt("Nivel_Acesso")); // 🔥 Pega o nível corretamente
                conn.close();
                return u;
            }

        } else if (perfil.equalsIgnoreCase("aluno")) {
            String sql = "SELECT Nome_aluno FROM aluno " +
                         "WHERE CPF_Aluno = ? AND Data_Nascimento = ? AND Ativacao_Conta = true";

            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.setDate(2, java.sql.Date.valueOf(dataNascimento));

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(id);
                u.setNome(rs.getString("Nome_aluno"));
                u.setTipo("aluno");
                u.setNivelAcesso(0); // 🔹 Aluno não tem nível de acesso de funcionário
                conn.close();
                return u;
            }
        }

        conn.close();
        return null;
    }
}
