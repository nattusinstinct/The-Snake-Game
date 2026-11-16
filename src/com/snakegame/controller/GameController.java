package com.snakegame.controller;

import com.snakegame.dto.Direction;
import com.snakegame.dto.Player;
import com.snakegame.service.GameService;
import com.snakegame.service.GameService.MoveResult;
import com.snakegame.view.GameView;
import java.util.Scanner;

public class GameController {
    private final GameView view = new GameView();
    private final ScoreController scoreController = new ScoreController();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        view.printWelcome();
        Player player = readPlayer(scanner);
        GameService game = new GameService();
        long startTime = System.currentTimeMillis();
        boolean running = true;

        view.printBoard(game.getSnake(), game.getFood(), game.getScore());
        while (running) {
            String input = view.readMove(scanner);
            if (input.equalsIgnoreCase("Q")) {
                view.printMessage("You quit the game.");
                //System.exit(0);
                running = false;
                break;
            }

            Direction direction = Direction.fromChar(input.charAt(0));
            if (direction == null) {
                view.printMessage("Invalid input. Use L, R, T, B or Q.");
                continue;
            }
            MoveResult result = game.move(direction);
            if (null != result) {
            switch(result) {
                case WALL:
                    view.printMessage("You hit the wall!");
                    running = false;
                    break;
                case SELF:
                    view.printMessage("You ran into yourself!");
                    running = false;
                    break;
                case ATE:
                    view.printMessage("Yum! That food was worth it.");
                    break;
                default:
                    break;
            }
          }
            view.printBoard(game.getSnake(), game.getFood(), game.getScore());
        }
        long durationSeconds = System.currentTimeMillis() - startTime;
        scoreController.finish(player, game, durationSeconds);
    }

    private Player readPlayer(Scanner scanner) {
        String name = view.readPlayerName(scanner);
        return new Player(name);
    }
}
