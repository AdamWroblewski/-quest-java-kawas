package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.GameRandom;

public class Skeleton extends Actor {
    private boolean stunned;
    private int staggerCounter = 0;
    private String stateName = "skeleton";
    private boolean wasRight;

    public Skeleton(Cell cell) {
        super(cell);
        stunned = false;
        health = 50;

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
    public void moveToPlayer(Player player, GameRandom gameRandom){
        if(stunned){
            staggerCounter--;
            if(staggerCounter == 0) unsetStunnedState();
            return;
        }

        int playerX = player.getX(), playerY = player.getY(),
                monsterX = this.getX(), monsterY = this.getY();

        Directions pathDirection = gameRandom.randMove(monsterX, monsterY, playerX, playerY);

        switch( pathDirection.getDirection() ){
            case 1:
                super.move(0, -1);
                break;
            case 2:
                super.move(1, 0);
                break;
            case 3:
                super.move(0, 1);
                break;
            case 4:
                super.move(-1, 0);
                break;
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
