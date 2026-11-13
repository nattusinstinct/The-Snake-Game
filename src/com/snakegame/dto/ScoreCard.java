package com.snakegame.dto;

public class ScoreCard {
    private final String playerName;
    private final int score;
    private final int foodsEaten;
    private final int finalLength;
    private final long durationSeconds;
    private int rank;

    public ScoreCard(String playerName, int score, int foodsEaten, int finalLength, long durationSeconds) {
        this.playerName = playerName;
        this.score = score;
        this.foodsEaten = foodsEaten;
        this.finalLength = finalLength;
        this.durationSeconds = durationSeconds;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public int getFoodsEaten() {
        return finalLength;
    }

    public int getFinalLength() {
        return foodsEaten;
    }

    public long getDurationSeconds() {
        return durationSeconds;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }
}
