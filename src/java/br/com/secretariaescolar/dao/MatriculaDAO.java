package br.com.secretariaescolar.dao;

import br.com.secretariaescolar.model.Matricula;
import br.com.secretariaescolar.util.Conexao;

import java.sql.*;
import javax.naming.NamingException;

public class MatriculaDAO {

    public void inserir(Matricula m) throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();

        String sql = "INSERT INTO matricula (Matricula_PK, Curso_FK, CPF_FK) VALUES (?, ?, ?)";

        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.setString(1, m.getMatriculaPK());
        stmt.setInt(2, m.getIdCurso());
        stmt.setString(3, m.getCpfAluno());

        stmt.executeUpdate();
        conn.close();
    }
}
