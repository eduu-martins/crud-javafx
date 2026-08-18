package com.template.util;

import javafx.scene.control.TextField;

public class FormUtil {

    /**
     * Aplica uma restrição para aceitar apenas dígitos numéricos no TextField.
     */
    public static void permitirApenasNumeros(TextField campo) {
        campo.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && !newValue.matches("\\d*")) {
                campo.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }
}