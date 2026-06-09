package com.template;

import javafx.animation.PauseTransition;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Duration;

public class MainController {

    @FXML private Button btnLimpar;
    @FXML private Button btExcluir;
    @FXML private Button btAtualizar;
    @FXML private Button btSalvar;

    @FXML private TextField txtId;
    @FXML private TextField txtJogador;
    @FXML private TextField txtPais;
    @FXML private TextField txtClube;
    @FXML private TextField txtAno;
    @FXML private TextField txtGols;
    @FXML private TextField txtAssistencias;
    @FXML private TextField txtTitulos;

    @FXML private TableView<BolaDeOuroDTO> tblGanhadoresBolaDeOuro;

    @FXML private TableColumn<BolaDeOuroDTO, Integer> colid;
    @FXML private TableColumn<BolaDeOuroDTO, String> colJogador;
    @FXML private TableColumn<BolaDeOuroDTO, String> colPais;
    @FXML private TableColumn<BolaDeOuroDTO, String> colClube;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colAno;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colGols;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colAssistencias;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colTitulos;

    private BolaDeOuroDAO objDAO = new BolaDeOuroDAO();

    @FXML
    private void initialize() {
        // Mapeia os atributos do seu DTO com as colunas da tabela do FXML
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colJogador.setCellValueFactory(new PropertyValueFactory<>("jogador"));
        colPais.setCellValueFactory(new PropertyValueFactory<>("pais"));
        colClube.setCellValueFactory(new PropertyValueFactory<>("clube"));
        colAno.setCellValueFactory(new PropertyValueFactory<>("ano"));
        colGols.setCellValueFactory(new PropertyValueFactory<>("gols"));
        colAssistencias.setCellValueFactory(new PropertyValueFactory<>("assistencias"));
        colTitulos.setCellValueFactory(new PropertyValueFactory<>("titulos"));

        // Carrega as linhas vindas do banco na inicialização
        carregarDadosTabela();

        // Clique na tabela: Ao selecionar um jogador, joga os dados de volta para as caixas de texto
        tblGanhadoresBolaDeOuro.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtId.setText(String.valueOf(newValue.getId()));
                txtJogador.setText(newValue.getJogador());
                txtPais.setText(newValue.getPais());
                txtClube.setText(newValue.getClube());
                txtAno.setText(String.valueOf(newValue.getAno()));
                txtGols.setText(String.valueOf(newValue.getGols()));
                txtAssistencias.setText(String.valueOf(newValue.getAssistencias()));
                txtTitulos.setText(String.valueOf(newValue.getTitulos()));
            }
        });

        configurarCampoNumerico(txtAno);
        configurarCampoNumerico(txtGols);
        configurarCampoNumerico(txtAssistencias);
        configurarCampoNumerico(txtTitulos);
    }

    private void carregarDadosTabela() {
        ObservableList<BolaDeOuroDTO> lista = objDAO.listarJogadores();
        tblGanhadoresBolaDeOuro.setItems(lista);
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

    @FXML
    private void btnSalvarAction(ActionEvent event) {
        // Para cadastro novo, enviamos ID 0 já que o banco de dados autoincrementa
        String jogador = txtJogador.getText();
        String pais = txtPais.getText();
        String clube = txtClube.getText();
        int ano = Integer.parseInt(txtAno.getText());
        int gols = Integer.parseInt(txtGols.getText());
        int assistencias = Integer.parseInt(txtAssistencias.getText());
        int titulos = Integer.parseInt(txtTitulos.getText());

        BolaDeOuroDTO novoJogador = new BolaDeOuroDTO(0, jogador, pais, clube, ano, gols, assistencias, titulos);

        objDAO.cadastrarJogador(novoJogador);

        limparCampos();
        carregarDadosTabela(); // Atualiza a tabela na hora!
        // ... código anterior de salvar ...
        objDAO.cadastrarJogador(novoJogador);

        limparCampos();
        carregarDadosTabela();

        exibirMensagem("Alteracao", "Jogador adicionado com sucesso!", "#429e42");
    }

    @FXML
    private void btnLimparAction() {
        limparCampos();
    }

    @FXML
    private void btnExcluirAction(ActionEvent event) {
        if (!txtId.getText().isEmpty()) {
            int id = Integer.parseInt(txtId.getText());
            objDAO.excluirJogador(id);
            limparCampos();
            carregarDadosTabela(); // Atualiza a tabela na hora!

            exibirMensagem("Alteracao", "Jogador excluído com sucesso!", "#9e4242");
        }
    }

    @FXML
    private void btnAtualizarAction(ActionEvent event) {
        if (!txtId.getText().isEmpty()) {
            int id = Integer.parseInt(txtId.getText());
            String jogador = txtJogador.getText();
            String pais = txtPais.getText();
            String clube = txtClube.getText();
            int ano = Integer.parseInt(txtAno.getText());
            int gols = Integer.parseInt(txtGols.getText());
            int assistencias = Integer.parseInt(txtAssistencias.getText());
            int titulos = Integer.parseInt(txtTitulos.getText());

            BolaDeOuroDTO jogadorEditado = new BolaDeOuroDTO(id, jogador, pais, clube, ano, gols, assistencias, titulos);

            objDAO.alterarJogador(jogadorEditado);

            limparCampos();
            carregarDadosTabela(); // Atualiza a tabela na hora!
            exibirMensagem("Alteracao", "Jogador editado com sucesso!", "#426a9e");
        }
    }


    private void exibirMensagem(String titulo, String mensagem, String corHex) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);

        // Customização do layout (CSS)
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle(
                "-fx-background-color: " + corHex + "; " +
                        "-fx-text-fill: white; " +
                        "-fx-font-family: 'Arial'; " +
                        "-fx-font-size: 14px;"
        );
        dialogPane.lookupAll(".content").forEach(node -> node.setStyle("-fx-text-fill: white;"));

        // Mostra o alerta sem travar a execução do código
        alert.show();

        // Cria um "cronômetro" de 3 segundos
        PauseTransition delay = new PauseTransition(Duration.seconds(2));

        // Quando o tempo acabar, fecha o alerta automaticamente
        delay.setOnFinished(event -> alert.close());

        // Inicia a contagem regressiva
        delay.play();
    }

    private void configurarCampoNumerico(TextField campo) {
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            // Se o novo valor contiver qualquer coisa que NÃO seja número, substitui pelo valor antigo
            if (!newValue.matches("\\d*")) {
                campo.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}