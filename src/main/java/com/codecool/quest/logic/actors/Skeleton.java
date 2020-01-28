package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
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

    @Override
    public boolean isPlayer(){
        return false;
    }

    @Override
    public void setFightOn(){
        fightOn = true;
    }

    @Override
    public boolean setStunnedState(){
        if(health < 1) {
            stunned = true;
            staggerCounter = 4;// turns left in stagger state;
            stateName = "staggerState";
        }
        return false;
    }
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
