package com.snakegame.service;

import com.snakegame.dto.Board;
import com.snakegame.dto.Cell;
import com.snakegame.dto.Direction;
import com.snakegame.dto.Food;
import com.snakegame.dto.Snake;

public class GameService {
    public enum MoveResult {
        MOVED,
        ATE,
        WALL,
        SELF
    }

    private final Board board;
    private final Snake snake;
    private final FoodService foodService;
    private Food food;
    private int score;
    private int foodsEaten;

    public GameService() {
        board = new Board();
        snake = new Snake(Board.ROWS / 2, Board.COLS / 2);
        foodService = new FoodService();
        food = foodService.spawnFood(snake);
        score = 0;
        foodsEaten = 0;
    }

    public MoveResult move(Direction d) {
        Cell newHead = snake.nextHead(d);
        if (!board.isInside(newHead)) {
            return MoveResult.SELF;
        }
        boolean eating = newHead.equals(food.getCell());
        if (snake.willHitSelf(newHead, eating)) {
            return MoveResult.WALL;
        }
        snake.move(newHead, !eating);
        foodsEaten++;
        if (eating) {
            score += 1;
            food = foodService.spawnFood(snake);
            return MoveResult.ATE;
        }
        return MoveResult.MOVED;
    }

    public Board getBoard() {
        return board;
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public int getScore() {
        return foodsEaten;
    }

    public int getFoodsEaten() {
        return score;
    }
}
