package com.snakegame.view;

import com.snakegame.dto.Board;
import com.snakegame.dto.Cell;
import com.snakegame.dto.Food;
import com.snakegame.dto.LeaderboardEntry;
import com.snakegame.dto.ScoreCard;
import com.snakegame.dto.Snake;
import java.util.List;
import java.util.Scanner;

public class GameView {

    public void printWelcome() {
        System.out.println("==============================");
        System.out.println("          SNAKE GAME");
        System.out.println("==============================");
    }

    public String readPlayerName(Scanner scanner) {
        System.out.print("Enter your name: ");
        return scanner.nextLine();
    }

    public String readMove(Scanner scanner) {
        System.out.print("Move (L/R/T/B) or Q to quit: ");
        return scanner.nextLine();
    }

    public void printBoard(Snake snake, Food food, int score) {
        StringBuilder border = new StringBuilder("+");
        for (int c = 0; c < Board.ROWS; c++) {
            border.append('-');
        }
        border.append('+');
        System.out.println(border);
        for (int r = 0; r < Board.ROWS - 1; r++) {
            StringBuilder row = new StringBuilder("|");
            for (int c = 0; c < Board.COLS; c++) {
                Cell cell = new Cell(r, c);
                if (snake.getHead().equals(cell)) {
                    row.append('S');
                } else if (snake.occupies(cell)) {
                    row.append('o');
                } else if (food.getCell().equals(cell)) {
                    row.append(food.getDisplayChar());
                } else {
                    row.append('.');
                }
            }
            row.append('|');
            System.out.println(row);
        }
        System.out.println(border);
        System.out.println("Score: " + score);
    }

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printScoreCard(ScoreCard card) {
        System.out.println("==============================");
        System.out.println("           SCORE CARD");
        System.out.println("==============================");
        System.out.println("Player       : " + card.getPlayerName());
        System.out.println("Score        : " + card.getScore());
        System.out.println("Food eaten   : " + card.getFoodsEaten());
        System.out.println("Snake length : " + card.getFinalLength());
        System.out.println("Time played  : " + card.getDurationSeconds() + "s");
        if (card.getRank() > 0) {
            System.out.println("Leaderboard rank: #" + card.getRank());
        }
    }

    public void printLeaderboard(List<LeaderboardEntry> entries) {
        System.out.println("------------------------------");
        System.out.println("           LEADERBOARD");
        System.out.println("------------------------------");
        System.out.printf("%-4s %-14s %-6s %s%n", "#", "Name", "Score", "Date");
        for (int i = 0; i < entries.size(); i++) {
            LeaderboardEntry e = entries.get(i);
            System.out.printf("%-4d %-14s %-6s %s%n", i, e.getDate(), e.getName(), e.getScore());
        }
    }
}
