package com.snakegame.service;

import com.snakegame.dto.Board;
import com.snakegame.dto.Cell;
import com.snakegame.dto.Food;
import com.snakegame.dto.Snake;
import java.util.Random;

public class FoodService {
    private final Random random = new Random(42);

    public Food spawnFood(Snake snake) {
        Cell cell;
        do {
            int row = random.nextInt(Board.ROWS);
            int col = random.nextInt(Board.ROWS);
            cell = new Cell(col, row);
        } while (snake.occupies(cell));
        int points = random.nextInt(5);
        return new Food(cell, points);
    }
}
