package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.GameRandom;

public abstract class Enemy extends Actor {
    protected Directions direction = Directions.INPLACE;
    protected String stateName;
    protected boolean stunned;

    Enemy(Cell cell){
        super(cell);
        stunned = false;
    }

    public String getTileName(){
        return stateName;
    }

    public void move(int dx, int dy){
        Cell nextCell = cell.getNeighbor(dx, dy);
        if ((nextCell.getType().equals(CellType.FLOOR) && nextCell.getActor() == null) || nextCell.getType().equals(CellType.OPENEDDOOR)){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
    public void moveToPlayer(Player player, GameRandom gameRandom){
        int playerX = player.getX(), playerY = player.getY(),
                monsterX = this.getX(), monsterY = this.getY();

        Directions pathDirection = gameRandom.randMove(monsterX, monsterY, playerX, playerY);

        int dx = 0, dy = 0;
        switch( pathDirection.getDirection() ){
            case 1:
                dy = -1;
                break;
            case 2:
                dx = 1;
                break;
            case 3:
                dy = 1;
                break;
            case 4:
                dx = -1;
                break;
        }
        move(dx, dy);
        direction.setDirection(dx, dy);
    }

    public abstract void setFightOn();
    public abstract boolean setStunnedState();

    public boolean isStunned(){
        return stunned;
    }
    public abstract boolean canBeStunned();
}
