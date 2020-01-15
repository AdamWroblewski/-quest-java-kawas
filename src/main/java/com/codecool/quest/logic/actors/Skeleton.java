package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class Skeleton extends Actor {
    private boolean stunned;
    private int staggerCounter = 0;
    private String stateName = "skeleton";
    private boolean wasRight;

    public Skeleton(Cell cell) {
        super(cell);
        stunned = false;

        wasRight = true;
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

        if(wasRight) {
            super.move(1, 0);
            wasRight = false;
        } else {
            super.move(-1, 0);
            wasRight = true;
        }

    }

    public void setStunnedState(){
        stunned = true;
        staggerCounter = 4;
        stateName = "staggerState";
    }
    public void unsetStunnedState(){
        stunned = false;
        stateName = "skeleton";
    }
    public boolean isStunned(){
        return stunned;
    }
}
