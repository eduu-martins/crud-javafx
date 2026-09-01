package com.template.controller;

import com.template.model.dto.BolaDeOuroDTO;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.function.Consumer;

public class JogadorTableHandler {

    private final TableView<BolaDeOuroDTO> tabela;

    public JogadorTableHandler(
            TableView<BolaDeOuroDTO> tabela,
            TableColumn<BolaDeOuroDTO, Integer> colId,
            TableColumn<BolaDeOuroDTO, String> colJogador,
            TableColumn<BolaDeOuroDTO, String> colPais,
            TableColumn<BolaDeOuroDTO, String> colClube,
            TableColumn<BolaDeOuroDTO, Integer> colAno,
            TableColumn<BolaDeOuroDTO, Integer> colGols,
            TableColumn<BolaDeOuroDTO, Integer> colAssistencias,
            TableColumn<BolaDeOuroDTO, Integer> colTitulos,
            Consumer<BolaDeOuroDTO> aoSelecionar
    ) {
        this.tabela = tabela;

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJogador.setCellValueFactory(new PropertyValueFactory<>("jogador"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colClube.setCellValueFactory(new PropertyValueFactory<>("clube"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colGols.setCellValueFactory(new PropertyValueFactory<>("gols"));
        colAssistencias.setCellValueFactory(new PropertyValueFactory<>("assistencias"));
        colTitulos.setCellValueFactory(new PropertyValueFactory<>("titulos"));

        tabela.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            aoSelecionar.accept(newVal);
        });
    }

    public void atualizarItens(ObservableList<BolaDeOuroDTO> lista) {
        tabela.setItems(lista);
    }

    public void limparSelecao() {
        tabela.getSelectionModel().clearSelection();
    }
}