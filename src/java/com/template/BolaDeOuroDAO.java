package com.template;

//import model.Conexao;
//import model.dto.BolaDeOuroDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BolaDeOuroDAO {

    // cria o logger da classe
    private static final Logger logger = Logger.getLogger(BolaDeOuroDAO.class.getName());

    // metodo para inserir um novo jogador no banco
    public void cadastrarJogador(BolaDeOuroDTO jogador) {

        String sql = "INSERT INTO bola_de_ouro (jogador, pais, clube, ano, gols, assistencias, titulos) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = new Conexao().conectaBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jogador.getJogador());
            stmt.setString(2, jogador.getPais());
            stmt.setString(3, jogador.getClube());
            stmt.setInt(4, jogador.getAno());
            stmt.setInt(5, jogador.getGols());
            stmt.setInt(6, jogador.getAssistencias());
            stmt.setInt(7, jogador.getTitulos());

            stmt.executeUpdate();

            logger.info("Jogador cadastrado com sucesso");

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Erro ao cadastrar jogador", e);
        }
    }

    // metodo para listar todos os jogadores cadastrados
    public void listarJogadores() {

        String sql = "SELECT * FROM bola_de_ouro";

        try (Connection conn = new Conexao().conectaBD();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            logger.info("Lista de jogadores:");

            while (rs.next()) {
                System.out.println(
                        "ID: " + rs.getInt("id") +
                                " | Jogador: " + rs.getString("jogador") +
                                " | Ano: " + rs.getInt("ano") +
                                " | Gols: " + rs.getInt("gols") +
                                " | Assistencias: " + rs.getInt("assistencias") +
                                " | Titulos: " + rs.getInt("titulos")
                );
            }

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Erro ao listar jogadores", e);
        }
    }

    // metodo para atualizar os dados de um jogador pelo id
    public void alterarJogador(BolaDeOuroDTO jogador) {

        String sql = "UPDATE bola_de_ouro SET jogador = ?, pais = ?, clube = ?, ano = ?, gols = ?, assistencias = ?, titulos = ? WHERE id = ?";

        try (Connection conn = new Conexao().conectaBD();
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

            logger.info("Jogador atualizado com sucesso");

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Erro ao atualizar jogador", e);
        }
    }

    // metodo para excluir um jogador pelo id
    public void excluirJogador(int id) {

        String sql = "DELETE FROM bola_de_ouro WHERE id = ?";

        try (Connection conn = new Conexao().conectaBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            stmt.executeUpdate();

            logger.info("Jogador excluido com sucesso");

        } catch (SQLException e) {

            logger.log(Level.SEVERE, "Erro ao excluir jogador", e);
        }
    }
}