package com.template.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private final String url = "jdbc:postgresql://localhost:5432/Jogadores";
    private final String usuario = "postgres";
    private final String senha = "postgres";

    public Connection conectaBD() throws SQLException {
        return DriverManager.getConnection(url, usuario, senha);
    }
}