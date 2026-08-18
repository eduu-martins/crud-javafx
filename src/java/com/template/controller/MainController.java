package com.template.controller;

import com.template.model.dto.BolaDeOuroDTO;
import com.template.service.BolaDeOuroService;
import com.template.util.DialogUtil;
import com.template.util.FormUtil;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.SQLException;

public class MainController {

    @FXML private Button btnLimpar;
    @FXML private Button btnExcluir;
    @FXML private Button btnAtualizar;
    @FXML private Button btnSalvar;

    @FXML private TextField txtId;
    @FXML private TextField txtJogador;
    @FXML private TextField txtPais;
    @FXML private TextField txtClube;
    @FXML private TextField txtAno;
    @FXML private TextField txtGols;
    @FXML private TextField txtAssistencias;
    @FXML private TextField txtTitulos;

    @FXML private TableView<BolaDeOuroDTO> tblGanhadoresBolaDeOuro;

    @FXML private TableColumn<BolaDeOuroDTO, Integer> colId;
    @FXML private TableColumn<BolaDeOuroDTO, String> colJogador;
    @FXML private TableColumn<BolaDeOuroDTO, String> colPais;
    @FXML private TableColumn<BolaDeOuroDTO, String> colClube;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colAno;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colGols;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colAssistencias;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colTitulos;

    // Injeção da Camada de Serviço
    private final BolaDeOuroService service = new BolaDeOuroService();

    @FXML
    private void initialize() {
        configurarTabela();
        configurarFormatacaoCampos();
        carregarDadosTabela();
    }

    private void configurarTabela() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJogador.setCellValueFactory(new PropertyValueFactory<>("jogador"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colClube.setCellValueFactory(new PropertyValueFactory<>("clube"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colGols.setCellValueFactory(new PropertyValueFactory<>("gols"));
        colAssistencias.setCellValueFactory(new PropertyValueFactory<>("assistencias"));
        colTitulos.setCellValueFactory(new PropertyValueFactory<>("titulos"));

        tblGanhadoresBolaDeOuro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            preencherCamposFormulario(newValue);
        });
    }

    private void configurarFormatacaoCampos() {
        FormUtil.permitirApenasNumeros(txtAno);
        FormUtil.permitirApenasNumeros(txtGols);
        FormUtil.permitirApenasNumeros(txtAssistencias);
        FormUtil.permitirApenasNumeros(txtTitulos);
    }

    private void carregarDadosTabela() {
        try {
            ObservableList<BolaDeOuroDTO> lista = service.buscarTodos();
            tblGanhadoresBolaDeOuro.setItems(lista);
        } catch (SQLException e) {
            DialogUtil.exibirErro("Erro ao carregar a lista de jogadores: " + e.getMessage());
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        try {
            BolaDeOuroDTO novoJogador = extrairDadosFormulario(0);
            service.salvar(novoJogador);

            limparCampos();
            carregarDadosTabela();
            DialogUtil.exibirSucesso("Jogador cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            DialogUtil.exibirErro(e.getMessage());
        } catch (SQLException e) {
            DialogUtil.exibirErro("Erro ao salvar no banco de dados: " + e.getMessage());
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        if (txtId.getText().isEmpty()) {
            DialogUtil.exibirErro("Selecione um jogador na tabela para atualizar.");
            return;
        }

        if (DialogUtil.exibirConfirmacao("Confirmar Atualização", "Deseja salvar as alterações deste jogador?")) {
            try {
                int id = Integer.parseInt(txtId.getText());
                BolaDeOuroDTO jogadorEditado = extrairDadosFormulario(id);

                service.atualizar(jogadorEditado);

                limparCampos();
                carregarDadosTabela();
                DialogUtil.exibirSucesso("Jogador atualizado com sucesso!");
            } catch (IllegalArgumentException e) {
                DialogUtil.exibirErro(e.getMessage());
            } catch (SQLException e) {
                DialogUtil.exibirErro("Erro ao atualizar jogador: " + e.getMessage());
            }
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        if (txtId.getText().isEmpty()) {
            DialogUtil.exibirErro("Selecione um jogador na tabela para poder excluir.");
            return;
        }

        if (DialogUtil.exibirConfirmacao("Confirmar Exclusão", "Tem certeza que deseja excluir este jogador?")) {
            try {
                int id = Integer.parseInt(txtId.getText());
                service.excluir(id);

                limparCampos();
                carregarDadosTabela();
                DialogUtil.exibirSucesso("Jogador excluído com sucesso!");
            } catch (SQLException e) {
                DialogUtil.exibirErro("Erro ao excluir jogador: " + e.getMessage());
            }
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparCampos();
    }

    private void preencherCamposFormulario(BolaDeOuroDTO jogador) {
        if (jogador != null) {
            txtId.setText(String.valueOf(jogador.getId()));
            txtJogador.setText(jogador.getJogador());
            txtPais.setText(jogador.getPais());
            txtClube.setText(jogador.getClube());
            txtAno.setText(String.valueOf(jogador.getAno()));
            txtGols.setText(String.valueOf(jogador.getGols()));
            txtAssistencias.setText(String.valueOf(jogador.getAssistencias()));
            txtTitulos.setText(String.valueOf(jogador.getTitulos()));
        }
    }

    private void limparCampos() {
        txtId.clear();
        txtJogador.clear();
        txtPais.clear();
        txtClube.clear();
        txtAno.clear();
        txtGols.clear();
        txtAssistencias.clear();
        txtTitulos.clear();
        tblGanhadoresBolaDeOuro.getSelectionModel().clearSelection();
    }

    private BolaDeOuroDTO extrairDadosFormulario(int id) {
        int ano = txtAno.getText().isEmpty() ? 0 : Integer.parseInt(txtAno.getText());
        int gols = txtGols.getText().isEmpty() ? 0 : Integer.parseInt(txtGols.getText());
        int assistencias = txtAssistencias.getText().isEmpty() ? 0 : Integer.parseInt(txtAssistencias.getText());
        int titulos = txtTitulos.getText().isEmpty() ? 0 : Integer.parseInt(txtTitulos.getText());

        return new BolaDeOuroDTO(
                id,
                txtJogador.getText(),
                txtPais.getText(),
                txtClube.getText(),
                ano,
                gols,
                assistencias,
                titulos
        );
    }
}