package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.GameRandom;

public abstract class Enemy extends Actor {
    protected String stateName;
    protected boolean stunned;

    Enemy(Cell cell){
        super(cell);
        stunned = false;
    }

    public String getTileName(){
        return stateName;
    }

    public void moveToPlayer(Player player, GameRandom gameRandom){
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

    public boolean isStunned(){
        return stunned;
    }
}
