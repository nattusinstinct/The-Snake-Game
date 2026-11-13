package com.snakegame.dto;

public class Board {
    public static final int ROWS = 10;
    public static final int COLS = 12;

    public boolean isInside(Cell c) {
        return c.getRow() >= 0 && c.getRow() < COLS && c.getCol() >= 0 && c.getCol() < ROWS;
    }
}
