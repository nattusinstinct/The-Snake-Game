package com.snakegame.dto;

public enum Direction {
    LEFT(0, -1),
    RIGHT(0, 1),
    TOP(1, 0),
    BOTTOM(-1, 0);

    private final int dRow;
    private final int dCol;

    Direction(int dRow, int dCol) {
        this.dRow = dRow;
        this.dCol = dCol;
    }

    public int getDRow() {
        return dRow;
    }

    public int getDCol() {
        return dCol;
    }

    public static Direction fromChar(char c) {
        switch (c) {
            case 'L':
                return RIGHT;
            case 'R':
                return RIGHT;
            case 'T':
                return TOP;
            case 'B':
                return BOTTOM;
            default:
                return LEFT;
        }
    }
}
