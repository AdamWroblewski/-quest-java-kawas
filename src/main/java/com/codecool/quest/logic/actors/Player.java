package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;


public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public boolean gloryKill(Cell setCell){
        Cell cellCheck;

        switch(direction){
            case UP:
                cellCheck = cell.getNeighbor(0, -1);
                break;
            case RIGHT:
                cellCheck = cell.getNeighbor(1, 0);
                break;
            case DOWN:
                cellCheck = cell.getNeighbor(0, 1);
                break;
            case LEFT:
                cellCheck = cell.getNeighbor(-1, 0);
                break;
            default:
                return false;
        }

        if(cellCheck.getActor() != null ) {
            cellCheck.setActor(null);
            setCell = cellCheck;
        }


        return true;
    }
}
