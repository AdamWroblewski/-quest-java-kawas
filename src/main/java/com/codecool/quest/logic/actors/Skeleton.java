package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.actors.Actor;

public class Skeleton extends Actor {
    private boolean staggered;
    private int staggerCounter = 0;
    private String stateName = "skeleton";
    private boolean wasRight;

    public Skeleton(Cell cell) {
        super(cell);
        staggered = false;

        wasRight = true;
    }

    @Override
    public String getTileName() {
        return stateName;
    }

    @Override
    public void move(int dx, int dy) {
        if(staggered) {
            staggerCounter--;
            if(staggerCounter == 0) unsetStaggerState();
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

    public void setStaggerState(){
        staggered = true;
        staggerCounter = 4;
        stateName = "staggerState";
    }
    public void unsetStaggerState(){
        staggered = false;
        stateName = "skeleton";
    }
    public boolean isStaggered(){
        return staggered;
    }
}
