package com.template.validator;

import com.template.model.dto.BolaDeOuroDTO;
import com.template.util.DialogUtil;

import java.util.ArrayList;
import java.util.List;

public class BolaDeOuroValidator implements IBolaDeOuroValidator {
    public static boolean validar(BolaDeOuroDTO jogador) {
        List<Validador<String>> validadores = new ArrayList<>();

        // validadores texto
        validadores.add(new CampoObrigatorioValidador("Jogador", jogador.getJogador()));
        validadores.add(new CampoObrigatorioValidador("País", jogador.getPais()));
        validadores.add(new CampoObrigatorioValidador("Clube", jogador.getClube()));

        // validadores numéricos
        validadores.add(new CampoObrigatorioValidador("Ano", jogador.getAno(), 1901));
        validadores.add(new CampoObrigatorioValidador("Gols", jogador.getGols(), 0));
        validadores.add(new CampoObrigatorioValidador("Assistências", jogador.getAssistencias(), 0));
        validadores.add(new CampoObrigatorioValidador("Títulos", jogador.getTitulos(), 0));

        // Executa o loop
        for (Validador<String> validador : validadores) {
            if (!validador.validarBolaDeOuro(validador.getValor())) {
                DialogUtil.exibirErro(validador.getMensagemErro());
                return false;
            }
        }
        return true;
    }

    //Erro
    @Override
    public boolean validar(String jogador, String pais, String clube, String ano, String gols, String assistencias, String titulos) {
        return false;
    }
}