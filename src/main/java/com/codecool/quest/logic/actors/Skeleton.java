package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.GameRandom;

public class Skeleton extends Enemy {
    private int staggerCounter = 0;
    private boolean fightOn = false;

    public Skeleton(Cell cell) {
        super(cell);
        stateName = "skeleton";
        health = 50;

    }

    @Override
    public String getTileName() {
        return stateName;
    }

    @Override
    public void move(int dx, int dy) {
        if(stunned) {
            staggerCounter--;
            if(staggerCounter == 0) unsetStunnedState();
            return;
        }

        Cell nextCell = cell.getNeighbor(dx, dy);
        if ((nextCell.getType().equals(CellType.FLOOR) && nextCell.getActor() == null) || nextCell.getType().equals(CellType.OPENEDDOOR)){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }

    }

    @Override
    public void moveToPlayer(Player player, GameRandom gameRandom){
        if(stunned){
            staggerCounter--;
            if(staggerCounter == 0) unsetStunnedState();
            return;
        }

        super.moveToPlayer(player, gameRandom);
    }

    @Override
    public boolean isPlayer(){
        return false;
    }

    @Override
    public void setFightOn(){
        fightOn = true;
    }

    @Override
    public boolean isStunned(){
        return stunned;
    }

    @Override
    public boolean setStunnedState(){
        stunned = true;
        staggerCounter = 4;
        stateName = "staggerState";
        return false;
    }
    public void unsetStunnedState(){
        stunned = false;
        stateName = "skeleton";
    }
}
