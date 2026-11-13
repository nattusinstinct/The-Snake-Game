package com.snakegame.dto;

public class Food {
    private final Cell cell;
    private final int points;

    public Food(Cell cell, int points) {
        this.cell = cell;
        this.points = points;
    }

    public Cell getCell() {
        return cell;
    }

    public int getPoints() {
        return points;
    }

    public char getDisplayChar() {
        return 'O';
    }
}
