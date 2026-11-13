package com.snakegame.dto;

import java.util.LinkedList;

public class Snake {
    private final LinkedList<Cell> body = new LinkedList<>();

    public Snake(int startRow, int startCol) {
        body.add(new Cell(startRow, startCol));
        body.add(new Cell(startRow, startCol - 1));
        body.add(new Cell(startRow, startCol - 2));
    }

    public Cell getHead() {
        return body.getLast();
    }

    public LinkedList<Cell> getBody() {
        return body;
    }

    public int getLength() {
        return body.size();
    }

    public boolean occupies(Cell c) {
        for (Cell part : body) {
            if (part == c) {
                return true;
            }
        }
        return false;
    }

    public Cell nextHead(Direction d) {
        Cell head = getHead();
        return new Cell(head.getRow() + d.getDRow(), head.getCol() + d.getDCol());
    }

    public void move(Cell newHead, boolean grow) {
        body.addFirst(newHead);
        body.removeLast();
    }

    public boolean willHitSelf(Cell newHead, boolean grow) {
        int limit = body.size();
        for (int i = 0; i < limit; i++) {
            if (body.get(i).equals(newHead)) {
                return true;
            }
        }
        return false;
    }
}
