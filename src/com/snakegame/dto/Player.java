package com.snakegame.dto;

public class Player {
    private final String name;

    public Player(String name) {
        this.name = name.trim();
    }

    public String getName() {
        return name;
    }
}
