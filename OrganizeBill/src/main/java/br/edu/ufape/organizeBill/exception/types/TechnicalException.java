package br.edu.ufape.organizeBill.exception.types;

import br.edu.ufape.organizeBill.exception.types.global.ExceptionCustomized;

@SuppressWarnings("serial")
public class TechnicalException extends ExceptionCustomized{
    public TechnicalException(String code, String message) {
        super(code, message);
    }
}
