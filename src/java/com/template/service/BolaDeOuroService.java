package com.template.service;

import com.template.model.dao.BolaDeOuroDAO;
import com.template.model.dto.BolaDeOuroDTO;
import com.template.validator.BolaDeOuroValidator;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class BolaDeOuroService {

    private final BolaDeOuroDAO bolaDeOuroDAO = new BolaDeOuroDAO();

    public ObservableList<BolaDeOuroDTO> buscarTodos() throws SQLException {
        return bolaDeOuroDAO.listarJogadores();
    }

    public void salvar(BolaDeOuroDTO jogador) throws SQLException, IllegalArgumentException {
        // Valida antes de enviar ao DAO
        BolaDeOuroValidator.validar(jogador);
        bolaDeOuroDAO.cadastrarJogador(jogador);
    }

    public void atualizar(BolaDeOuroDTO jogador) throws SQLException, IllegalArgumentException {
        // Valida antes de enviar ao DAO
        BolaDeOuroValidator.validar(jogador);
        bolaDeOuroDAO.alterarJogador(jogador);
    }

    public void excluir(int id) throws SQLException {
        bolaDeOuroDAO.excluirJogador(id);
    }
}