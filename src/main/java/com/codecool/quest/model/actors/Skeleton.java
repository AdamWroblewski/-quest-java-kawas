package com.codecool.quest.model.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameRandom;

public class Skeleton extends Enemy {
    private boolean fightOn = false;

    public Skeleton(Cell cell) {
        super(cell);
        stateName = "skeleton";
        health = 50;
        viewDistance = 6.0;
    }


    @Override
    public void move(int dx, int dy) {
        if(stunned) {
            staggerCounter--;
            if(staggerCounter == 0) unsetStunnedState();
            return;
        }

        super.move(dx, dy);
    }

    @Override
    public double moveToPlayer(Player player, GameRandom gameRandom){
        double distance = 0.0;
        if(stunned){
            staggerCounter--;
            if(staggerCounter == 0) unsetStunnedState();
            return distance;
        }

        distance = super.moveToPlayer(player, gameRandom);
        return distance;
    }
    // todo fix magic numbers
    public void unsetStunnedState(){
        health = 40;
        stunned = false;
        stateName = "skeleton";
    }
    @Override
    public boolean canBeStunned(){
        return true;
    }
}
