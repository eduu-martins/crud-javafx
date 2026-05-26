package com.template;

/**
 * Classe que representa os dados de um jogador vencedor da bola de ouro
 */
public class BolaDeOuroDTO {

    // atributos da classe
    private int id;
    private String jogador;
    private String pais;
    private String clube;
    private int ano;
    private int gols;
    private int assistencias;
    private int titulos;

    // construtor vazio
    public BolaDeOuroDTO() {
    }

    // construtor completo
    public BolaDeOuroDTO(int id, String jogador, String pais, String clube, int ano, int gols, int assistencias, int titulos) {
        this.id = id;
        this.jogador = jogador;
        this.pais = pais;
        this.clube = clube;
        this.ano = ano;
        this.gols = gols;
        this.assistencias = assistencias;
        this.titulos = titulos;
    }

    // getters e setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJogador() {
        return jogador;
    }

    public void setJogador(String jogador) {
        this.jogador = jogador;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getClube() {
        return clube;
    }

    public void setClube(String clube) {
        this.clube = clube;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getGols() {
        return gols;
    }

    public void setGols(int gols) {
        this.gols = gols;
    }

    public int getAssistencias() {
        return assistencias;
    }

    public void setAssistencias(int assistencias) {
        this.assistencias = assistencias;
    }

    public int getTitulos() {
        return titulos;
    }

    public void setTitulos(int titulos) {
        this.titulos = titulos;
    }
}
