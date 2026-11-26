package com.alexu.cse223.labyrinth.domain.engine;

import com.alexu.cse223.labyrinth.domain.events.GameEvent;
import com.alexu.cse223.labyrinth.domain.model.*;

import java.util.ArrayList;
import java.util.List;

public class TurnEngine {
    private final GameState state;
    private final Maze maze;
    private final Hero hero;

    public TurnEngine(GameState state, Maze maze, Hero hero) {
        this.state = state;
        this.maze = maze;
        this.hero = hero;
    }

    public TurnResult move(Direction direction) {
        List<GameEvent> events = new ArrayList<>();

        if (state.getStatus() != GameStatus.PLAYING) {
            throw new GameFinishedException("The game is already finished");
        }

        Position current = hero.getPosition();
        Position next = new Position(current.getX() + direction.dx(),
                                     current.getY() + direction.dy());

        if (!maze.isInside(next)) {
            throw new IllegalMoveException("Cannot move outside the grid");
        }
        if (maze.cellAt(next) == CellType.WALL) {
            throw new IllegalMoveException("Cannot move into a wall");
        }

        hero.setPosition(next);
        state.setTurn(state.getTurn() + 1);

        return new TurnResult(state, events);
    }
}