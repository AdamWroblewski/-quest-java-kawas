package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.actors.Actor;

public class Skeleton extends Actor {
    private boolean direction;
    public Skeleton(Cell cell) {
        super(cell);
        direction = true;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy) {
        Cell currentCell = getCell();
        Cell nextCell = currentCell.getNeighbor(dx, dy);
        if(direction) {
            nextCell = currentCell.getNeighbor(1, 0);
            direction = false;
        } else {
            nextCell = currentCell.getNeighbor(-1, 0);
            direction = true;
        }

        currentCell.setActor(null);
        nextCell.setActor(this);
        setCEll(nextCell);
    }
}
