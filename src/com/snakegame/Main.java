package com.snakegame;

import com.snakegame.controller.GameController;

public class Main {
    public static void main(String[] args) {
        try {
            new GameController().start();
        } catch (Exception e) {
        }
    }
}
