package com.redis.commons.exception;

public class ProtobufDeserializeException extends RuntimeException {

    public ProtobufDeserializeException(final String message, final Exception exception) {
        super(message, exception);
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }
}
