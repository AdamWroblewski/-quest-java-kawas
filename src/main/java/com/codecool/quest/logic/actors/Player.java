package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Directions;


public class Player extends Actor {
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    public Actor shoot(){
        Cell cellCheck = cellByDirection();
        if(cellCheck == null)
            return null;

        return cellCheck.getActor();
    }
    public Actor gloryKill(){
        Cell cellCheck = cellByDirection();
        if(cellCheck == null)
            return null;

        Actor actor = cellCheck.getActor();
        Skeleton monster = (Skeleton) actor;
        if(monster != null && monster.isStunned() ){
            cellCheck.setActor(null);
            return actor;
        }

        return null;
    }

    private Cell cellByDirection(){
        Cell cellCheck;

        switch( direction.getDirection() ){
            case 1:
                cellCheck = cell.getNeighbor(0, -1);
                break;
            case 2:
                cellCheck = cell.getNeighbor(1, 0);
                break;
            case 3:
                cellCheck = cell.getNeighbor(0, 1);
                break;
            case 4:
                cellCheck = cell.getNeighbor(-1, 0);
                break;
            default:
                return null;
        }

        return cellCheck;
    }

}
