package com.template;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

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

    @FXML private TableView<BolaDeOuroDTO> bola_de_ouro;

    @FXML private TableColumn<BolaDeOuroDTO, Integer> colId;
    @FXML private TableColumn<BolaDeOuroDTO, String> colJogador;
    @FXML private TableColumn<BolaDeOuroDTO, String> colPais;
    @FXML private TableColumn<BolaDeOuroDTO, String> colClube;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colAno;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colGols;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colAssistencias;
    @FXML private TableColumn<BolaDeOuroDTO, Integer> colTitulos;

    @FXML private void initialize() {
        System.out.println("FXML loaded successfully!");
    }

    @FXML private void salvarJogador(){}

    private void limparCampos() {
        txtId.clear();
        txtJogador.clear();
        txtPais.clear();
        txtClube.clear();
        txtAno.clear();
        txtGols.clear();
        txtAssistencias.clear();
        txtTitulos.clear();
    }

    @FXML private void btnSalvarAction(ActionEvent event){
        int id = Integer.parseInt(txtId.getText());
        String jogador = txtJogador.getText();
        String pais = txtPais.getText();
        String clube = txtClube.getText();
        int ano = Integer.parseInt(txtAno.getText());
        int gols = Integer.parseInt(txtGols.getText());
        int assistencias = Integer.parseInt(txtAssistencias.getText());
        int titulos = Integer.parseInt(txtTitulos.getText());

        // 1. Instanciar o DTO e guardar os dados nele usando o construtor cheio que você criou
        BolaDeOuroDTO novoJogador = new BolaDeOuroDTO(id, jogador, pais, clube, ano, gols, assistencias, titulos);

        // 2. Instanciar o DAO para persistir no banco de dados
        BolaDeOuroDAO objDAO = new BolaDeOuroDAO();

        // 3. Chamar o métodode cadastro passando o DTO preenchido
        objDAO.cadastrarJogador(novoJogador);

        // 4. (Opcional, mas recomendado) Limpar os campos do formulário após salvar
        limparCampos();

        System.out.println("Jogador salvo com sucesso!");
    }

    @FXML private void btnLimparAction(){
        txtId.clear();
        txtJogador.clear();
        txtPais.clear();
        txtClube.clear();
        txtAno.clear();
        txtGols.clear();
        txtAssistencias.clear();
        txtTitulos.clear();
    }

    @FXML private void btnExcluirAction(ActionEvent event) {
        // Captura o ID da tela
        int id = Integer.parseInt(txtId.getText());

        // Instancia o DAO e deleta do banco
        BolaDeOuroDAO objDAO = new BolaDeOuroDAO();
        objDAO.excluirJogador(id);

        // Limpa a tela e atualiza a tabela automaticamente
        btnLimparAction();
        // carregarJogadores(); // Descomente esta linha se tiver feito o métodode atualizar a tabela
    }

    @FXML
    private void btnEditarAction(ActionEvent event) {
        // Captura todos os dados da tela (igual ao salvar)
        int id = Integer.parseInt(txtId.getText());
        String jogador = txtJogador.getText();
        String pais = txtPais.getText();
        String clube = txtClube.getText();
        int ano = Integer.parseInt(txtAno.getText());
        int gols = Integer.parseInt(txtGols.getText());
        int assistencias = Integer.parseInt(txtAssistencias.getText());
        int titulos = Integer.parseInt(txtTitulos.getText());

        // Monta o objeto DTO com os dados alterados
        BolaDeOuroDTO jogadorEditado = new BolaDeOuroDTO(id, jogador, pais, clube, ano, gols, assistencias, titulos);

        // Instancia o DAO e atualiza no banco
        BolaDeOuroDAO objDAO = new BolaDeOuroDAO();
        objDAO.alterarJogador(jogadorEditado);

        btnLimparAction();
        // carregarJogadores(); // Descomente esta linha se tiver feito o métodode atualizar a tabela
    }


}
