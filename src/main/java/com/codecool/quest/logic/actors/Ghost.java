package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;

public class Ghost extends Enemy {
    Ghost(Cell cell){
        super(cell);
        stateName = "ghost1";
        health = 50;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if ((nextCell.getType().equals(CellType.FLOOR) && nextCell.getActor() == null) || nextCell.getType().equals(CellType.OPENEDDOOR)){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

        if(stateName.equals("ghost1") )
            stateName = "ghost2";
        else
            stateName = "ghost1";
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

}
