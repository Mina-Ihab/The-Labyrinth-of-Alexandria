package com.alexu.cse223.labyrinth.domain.engine;

import com.alexu.cse223.labyrinth.domain.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TurnEngineTest {

    private TurnEngine engineWithHeroAt(int x, int y, GameState state) {
        CellType[][] cells = {
            { CellType.WALL, CellType.WALL,  CellType.WALL },
            { CellType.WALL, CellType.FLOOR, CellType.FLOOR },
            { CellType.WALL, CellType.WALL,  CellType.WALL }
        };
        return new TurnEngine(state, new Maze(cells), new Hero(new Position(x, y)));
    }

    @Test
    void moveAfterGameFinishedThrows() {
        GameState state = new GameState();
        state.setStatus(GameStatus.WON);
        TurnEngine engine = engineWithHeroAt(1, 1, state);
        assertThrows(GameFinishedException.class, () -> engine.move(Direction.RIGHT));
    }

    @Test
    void moveIntoWallThrowsAndTurnDoesNotAdvance() {
        GameState state = new GameState();
        TurnEngine engine = engineWithHeroAt(1, 1, state);
        assertThrows(IllegalMoveException.class, () -> engine.move(Direction.UP));
        assertEquals(0, state.getTurn());
    }

    @Test
    void validMoveAdvancesTurn() {
        GameState state = new GameState();
        TurnEngine engine = engineWithHeroAt(1, 1, state);
        engine.move(Direction.RIGHT);
        assertEquals(1, state.getTurn());
    }
}