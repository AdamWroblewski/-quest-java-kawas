package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.actors.Actor;

public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public boolean gloryKill(){
        Cell cell = getCell(), cellUp, cellRight, cellDown, cellLeft;
        cellUp = cell.getNeighbor(0, -1);
        cellRight = cell.getNeighbor(1, 0);
        cellDown = cell.getNeighbor(0, 1);
        cellLeft = cell.getNeighbor(-1, 0);

        return true;
    }
}
