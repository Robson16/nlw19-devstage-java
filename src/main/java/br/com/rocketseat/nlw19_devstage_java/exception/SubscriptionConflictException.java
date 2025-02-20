package br.com.rocketseat.nlw19_devstage_java.exception;

public class SubscriptionConflictException extends RuntimeException {
    public SubscriptionConflictException(String message) {
        super(message);
    }
}
