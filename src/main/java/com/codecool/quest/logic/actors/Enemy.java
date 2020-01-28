package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.GameRandom;

public abstract class Enemy extends Actor {
    protected Directions direction = Directions.INPLACE;
    protected boolean stunned;

    Enemy(Cell cell){
        super(cell);
        stunned = false;
        attackPower = 5;
    }


    public void move(int dx, int dy){
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor actor = nextCell.getActor();
        /*if(actor.isPlayer() )
            actor.getAttacked(attackPower);*/

        if ((nextCell.getType().equals(CellType.FLOOR) && actor == null) || nextCell.getType().equals(CellType.OPENEDDOOR_BLUE)){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
    public double moveToPlayer(Player player, GameRandom gameRandom){
        int playerToMonsterDx, playerToMonsterDy;
        playerToMonsterDx = player.getX() - this.getX();
        playerToMonsterDy = player.getY() - this.getY();

        double distanceToPlayer;
        distanceToPlayer = Math.sqrt(playerToMonsterDx*playerToMonsterDx + playerToMonsterDy*playerToMonsterDy);

        Directions pathDirection = gameRandom.randMove(playerToMonsterDx, playerToMonsterDy, distanceToPlayer);

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
        //direction.setDirection(dx, dy);
        return distanceToPlayer;
    }

    @Override
    public boolean isPlayer(){
        return false;
    }

    public abstract void setFightOn();
    public abstract boolean setStunnedState();

    public boolean isStunned(){
        return stunned;
    }
    public abstract boolean canBeStunned();
}
