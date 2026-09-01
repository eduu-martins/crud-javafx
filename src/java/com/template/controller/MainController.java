package com.template.controller;

import com.template.model.dto.BolaDeOuroDTO;
import com.template.service.BolaDeOuroService;
import com.template.util.DialogUtil;

import com.template.util.JogadorFormHandler;
import com.template.util.JogadorTableHandler;
import com.template.validator.IBolaDeOuroValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    private final BolaDeOuroService service = new BolaDeOuroService();
    private JogadorFormHandler formHandler;
    private JogadorTableHandler tableHandler;

    /// ////// NOVO, NA TEORIA DESENCADEARIA ERROS!!!
    private final IBolaDeOuroValidator iBolaDeOuroValidator;
    public MainController(IBolaDeOuroValidator iBolaDeOuroValidator) {
        this.iBolaDeOuroValidator = iBolaDeOuroValidator;
    }

    @FXML
    private void initialize() {
        formHandler = new JogadorFormHandler(
                txtId, txtJogador, txtPais, txtClube, txtAno, txtGols, txtAssistencias, txtTitulos
        );

        tableHandler = new JogadorTableHandler(
                tblGanhadoresBolaDeOuro, colId, colJogador, colPais, colClube,
                colAno, colGols, colAssistencias, colTitulos,
                formHandler::preencherCampos
        );

        carregarDadosTabela();
    }

    private void carregarDadosTabela() {
        try {
            tableHandler.atualizarItens(service.buscarTodos());
        } catch (SQLException e) {
            DialogUtil.exibirErro("Erro ao carregar a lista de jogadores: " + e.getMessage());
        }
    }

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        try {
            BolaDeOuroDTO novoJogador = formHandler.extrairDados(0);

            boolean salvou = service.salvar(novoJogador);

            if (salvou) {
                limparTela();
                carregarDadosTabela();
                DialogUtil.exibirSucesso("Jogador cadastrado com sucesso!");
            }
        } catch (SQLException e) {
            DialogUtil.exibirErro("Erro ao salvar no banco de dados: " + e.getMessage());
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        if (!formHandler.temIdSelecionado()) {
            DialogUtil.exibirErro("Selecione um jogador na tabela para atualizar.");
            return;
        }

        if (DialogUtil.exibirConfirmacao("Confirmar Atualização", "Deseja salvar as alterações deste jogador?")) {
            try {
                BolaDeOuroDTO jogadorEditado = formHandler.extrairDados(formHandler.getIdSelecionado());

                boolean atualizou = service.atualizar(jogadorEditado);

                if (atualizou) {
                    limparTela();
                    carregarDadosTabela();
                    DialogUtil.exibirSucesso("Jogador atualizado com sucesso!");
                }
            } catch (SQLException e) {
                DialogUtil.exibirErro("Erro ao atualizar jogador: " + e.getMessage());
            }
        }
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        if (!formHandler.temIdSelecionado()) {
            DialogUtil.exibirErro("Selecione um jogador na tabela para poder excluir.");
            return;
        }

        if (DialogUtil.exibirConfirmacao("Confirmar Exclusão", "Tem certeza que deseja excluir este jogador?")) {
            try {
                service.excluir(formHandler.getIdSelecionado());

                limparTela();
                carregarDadosTabela();
                DialogUtil.exibirSucesso("Jogador excluído com sucesso!");
            } catch (SQLException e) {
                DialogUtil.exibirErro("Erro ao excluir jogador: " + e.getMessage());
            }
        }
    }

    @FXML
    private void btnLimparAction(ActionEvent event) {
        limparTela();
    }

    private void limparTela() {
        formHandler.limparCampos();
        tableHandler.limparSelecao();
    }
}