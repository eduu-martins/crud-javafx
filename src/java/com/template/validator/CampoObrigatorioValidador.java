package com.template.validator;

public class CampoObrigatorioValidador implements Validador<String> {
    private final String nomeCampo;
    private final String valor;

    public CampoObrigatorioValidador(String nomeCampo, String valor) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
    }

    public CampoObrigatorioValidador(String nomeCampo, int valorNumerico, int valorMinimo) {
        this.nomeCampo = nomeCampo;
        if (valorNumerico < valorMinimo) {
            this.valor = ""; // Força ficar vazio para falhar na validação
        } else {
            this.valor = String.valueOf(valorNumerico);
        }
    }

    @Override
    public boolean validar(String valor) {
        return this.valor != null && !this.valor.trim().isEmpty();
    }

    @Override
    public String getMensagemErro() {
        return "O campo " + nomeCampo + " deve ser preenchido";
    }

    @Override
    public String getValor() {
        return valor;
    }
}