package com.template.controller;

import com.template.model.dto.BolaDeOuroDTO;
import com.template.util.FormUtil;
import javafx.scene.control.TextField;

public class JogadorFormHandler {

    private final TextField txtId;
    private final TextField txtJogador;
    private final TextField txtPais;
    private final TextField txtClube;
    private final TextField txtAno;
    private final TextField txtGols;
    private final TextField txtAssistencias;
    private final TextField txtTitulos;

    public JogadorFormHandler(TextField txtId, TextField txtJogador, TextField txtPais, TextField txtClube,
                              TextField txtAno, TextField txtGols, TextField txtAssistencias, TextField txtTitulos) {
        this.txtId = txtId;
        this.txtJogador = txtJogador;
        this.txtPais = txtPais;
        this.txtClube = txtClube;
        this.txtAno = txtAno;
        this.txtGols = txtGols;
        this.txtAssistencias = txtAssistencias;
        this.txtTitulos = txtTitulos;

        configurarFormatacao();
    }

    private void configurarFormatacao() {
        FormUtil.permitirApenasNumeros(txtAno);
        FormUtil.permitirApenasNumeros(txtGols);
        FormUtil.permitirApenasNumeros(txtAssistencias);
        FormUtil.permitirApenasNumeros(txtTitulos);
    }

    public void preencherCampos(BolaDeOuroDTO jogador) {
        if (jogador == null) return;
        txtId.setText(String.valueOf(jogador.getId()));
        txtJogador.setText(jogador.getJogador());
        txtPais.setText(jogador.getPais());
        txtClube.setText(jogador.getClube());
        txtAno.setText(String.valueOf(jogador.getAno()));
        txtGols.setText(String.valueOf(jogador.getGols()));
        txtAssistencias.setText(String.valueOf(jogador.getAssistencias()));
        txtTitulos.setText(String.valueOf(jogador.getTitulos()));
    }

    public void limparCampos() {
        txtId.clear();
        txtJogador.clear();
        txtPais.clear();
        txtClube.clear();
        txtAno.clear();
        txtGols.clear();
        txtAssistencias.clear();
        txtTitulos.clear();
    }

    public BolaDeOuroDTO extrairDados(int id) {
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

    public boolean temIdSelecionado() {
        return !txtId.getText().isEmpty();
    }

    public int getIdSelecionado() {
        return Integer.parseInt(txtId.getText());
    }
}