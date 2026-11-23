package com.alexu.cse223.labyrinth.domain.model;

public record Position(int x, int y) {

    public Position move(Direction d) {
        return new Position(x + d.dx(), y + d.dy());
    }
}