package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;


public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }
}
