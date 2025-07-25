package br.com.secretariaescolar.util;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class Conexao {
    public static Connection getConnection() throws NamingException, SQLException {
        InitialContext ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/SecretariaEscolar");
        return ds.getConnection();
    }
}
