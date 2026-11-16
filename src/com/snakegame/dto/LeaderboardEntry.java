package com.snakegame.dto;

public class LeaderBoardEntry {
    private final String name;
    private final int score;
    private final String date;

    public LeaderBoardEntry(String name, int score, String date) {
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }
}
