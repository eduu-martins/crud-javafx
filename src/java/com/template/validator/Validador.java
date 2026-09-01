package com.template.validator;

public interface Validador <T> {
    boolean validarBolaDeOuro (T valor);
    String getMensagemErro();
    T getValor();
}
