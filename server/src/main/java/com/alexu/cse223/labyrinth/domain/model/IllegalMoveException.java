package com.alexu.cse223.labyrinth.domain.engine;

public class IllegalMoveException extends RuntimeException {

    public IllegalMoveException(String message) {
        super(message);
    }
}