package com.github.ardenliu.common.janusgraph.exception;

public class ArdenJanusGraphException extends RuntimeException {

    private static final long serialVersionUID = -8850593105123703368L;

    public ArdenJanusGraphException(String message, Exception e) {
        super(message, e);
    }

}