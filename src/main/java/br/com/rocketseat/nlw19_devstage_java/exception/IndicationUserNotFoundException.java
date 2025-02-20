package br.com.rocketseat.nlw19_devstage_java.exception;

public class IndicationUserNotFoundException extends RuntimeException{
    public IndicationUserNotFoundException(String message) {
        super(message);
    }
}
