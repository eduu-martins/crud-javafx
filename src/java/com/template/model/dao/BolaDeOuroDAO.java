package com.template.model.dao;

import com.template.model.Conexao;
import com.template.model.dto.BolaDeOuroDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BolaDeOuroDAO {

    public void cadastrarJogador(BolaDeOuroDTO jogador) throws SQLException {
        String sql = "INSERT INTO bola_de_ouro (jogador, pais, clube, ano, gols, assistencias, titulos) VALUES (?, ?, ?, ?, ?, ?, ?)";

        Conexao conexao = new Conexao();
        try (Connection conn = conexao.conectaBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogador.getJogador());
            stmt.setString(2, jogador.getPais());
            stmt.setString(3, jogador.getClube());
            stmt.setInt(4, jogador.getAno());
            stmt.setInt(5, jogador.getGols());
            stmt.setInt(6, jogador.getAssistencias());
            stmt.setInt(7, jogador.getTitulos());

            stmt.executeUpdate();
        }
    }

    public ObservableList<BolaDeOuroDTO> listarJogadores() throws SQLException {
        String sql = "SELECT * FROM bola_de_ouro ORDER BY ano DESC";
        ObservableList<BolaDeOuroDTO> lista = FXCollections.observableArrayList();

        Conexao conexao = new Conexao();
        try (Connection conn = conexao.conectaBD();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BolaDeOuroDTO jogador = new BolaDeOuroDTO(
                        rs.getInt("id"),
                        rs.getString("jogador"),
                        rs.getString("pais"),
                        rs.getString("clube"),
                        rs.getInt("ano"),
                        rs.getInt("gols"),
                        rs.getInt("assistencias"),
                        rs.getInt("titulos")
                );
                lista.add(jogador);
            }
        }
        return lista;
    }

    public void alterarJogador(BolaDeOuroDTO jogador) throws SQLException {
        String sql = "UPDATE bola_de_ouro SET jogador = ?, pais = ?, clube = ?, ano = ?, gols = ?, assistencias = ?, titulos = ? WHERE id = ?";

        Conexao conexao = new Conexao();
        try (Connection conn = conexao.conectaBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogador.getJogador());
            stmt.setString(2, jogador.getPais());
            stmt.setString(3, jogador.getClube());
            stmt.setInt(4, jogador.getAno());
            stmt.setInt(5, jogador.getGols());
            stmt.setInt(6, jogador.getAssistencias());
            stmt.setInt(7, jogador.getTitulos());
            stmt.setInt(8, jogador.getId());

            stmt.executeUpdate();
        }
    }

    public void excluirJogador(int id) throws SQLException {
        String sql = "DELETE FROM bola_de_ouro WHERE id = ?";

        Conexao conexao = new Conexao();
        try (Connection conn = conexao.conectaBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}