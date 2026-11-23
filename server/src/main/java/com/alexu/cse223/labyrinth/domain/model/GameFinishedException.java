package com.alexu.cse223.labyrinth.domain.engine;

public class GameFinishedException extends RuntimeException {

    public GameFinishedException(String message) {
        super(message);
    }
}