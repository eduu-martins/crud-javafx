package com.template.validator; // ou com.template.util (conforme o pacote do seu amigo)

import com.template.model.dto.BolaDeOuroDTO;

public class BolaDeOuroValidator {

    public static void validar(BolaDeOuroDTO jogador) throws IllegalArgumentException {
        if (jogador.getJogador() == null || jogador.getJogador().trim().isEmpty() ||
                jogador.getPais() == null || jogador.getPais().trim().isEmpty() ||
                jogador.getClube() == null || jogador.getClube().trim().isEmpty()) {

            throw new IllegalArgumentException("Todos os campos de texto devem ser preenchidos.");
        }

        if (jogador.getAno() <= 1900 || jogador.getGols() < 0 ||
                jogador.getAssistencias() < 0 || jogador.getTitulos() < 0) {

            throw new IllegalArgumentException("Preencha valores numéricos válidos e coerentes.");
        }
    }
}