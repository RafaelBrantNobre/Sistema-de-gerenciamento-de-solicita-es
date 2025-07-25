package br.com.secretariaescolar.dao;

import br.com.secretariaescolar.model.Cargo;
import br.com.secretariaescolar.util.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

public class CargoDAO {

    public List<Cargo> listarCargos() throws SQLException, NamingException {
        Connection conn = Conexao.getConnection();
        String sql = "SELECT ID_Cargo, Nome_Cargo FROM cargo";
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();

        List<Cargo> lista = new ArrayList<>();
        while (rs.next()) {
            Cargo c = new Cargo();
            c.setIdCargo(rs.getInt("ID_Cargo"));
            c.setNomeCargo(rs.getString("Nome_Cargo"));
            lista.add(c);
        }

        conn.close();
        return lista;
    }
}
