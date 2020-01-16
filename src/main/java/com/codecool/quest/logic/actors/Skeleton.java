package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.GameRandom;

public class Skeleton extends Actor {
    private boolean stunned;
    private int staggerCounter = 0;
    private String stateName = "skeleton";
    private boolean fightOn = false;

    public Skeleton(Cell cell) {
        super(cell);
        stunned = false;
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
                move(0, -1);
                break;
            case 2:
                move(1, 0);
                break;
            case 3:
                move(0, 1);
                break;
            case 4:
                move(-1, 0);
                break;
        }
    }

    @Override
    public boolean isPlayer(){
        return false;
    }

    public void setFightOn(){
        fightOn = true;
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
