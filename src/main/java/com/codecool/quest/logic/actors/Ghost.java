package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.GameRandom;

public class Ghost extends Enemy {
    public Ghost(Cell cell){
        super(cell);
        stateName = "ghost1";
        health = 50;
        stunned = true;
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);

        if(stateName.equals("ghost1") )
            stateName = "ghost2";
        else
            stateName = "ghost1";
    }
    @Override
    public void moveToPlayer(Player player, GameRandom gameRandom){

    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public void setFightOn(){}

    @Override
    public boolean setStunnedState(){
        return true;
    }
    @Override
    public boolean canBeStunned(){
        return false;
    }
}
