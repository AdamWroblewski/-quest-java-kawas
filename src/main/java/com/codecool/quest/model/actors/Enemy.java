package com.codecool.quest.model.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.GameRandom;
import com.codecool.quest.model.CellType;

public abstract class Enemy extends Actor {
    protected Directions direction = Directions.INPLACE;
    protected int staggerCounter = 0;
    protected double viewDistance;
    protected boolean stunned;
    protected int defaultAttackPower;

    Enemy(Cell cell) {
        super(cell);
        stunned = false;
//        attackPower = this.defaultAttackPower;
        setAttackPower();
        viewDistance = 7.0;
    }

    public void setAttackPower() {
        this.attackPower = this.defaultAttackPower;
    }

    @Override
    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor actor = nextCell.getActor();
        if (actor != null && actor.isPlayer())
            actor.getAttacked(attackPower);

        if ((nextCell.getType().equals(CellType.FLOOR) && actor == null) || nextCell.getType().equals(CellType.OPENEDDOOR_BLUE)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        }
    }
    public double moveToPlayer(Player player, GameRandom gameRandom) {
        int playerToMonsterDx, playerToMonsterDy;
        playerToMonsterDx = player.getX() - this.getX();
        playerToMonsterDy = player.getY() - this.getY();

        double distanceToPlayer
                = Math.sqrt(playerToMonsterDx * playerToMonsterDx + playerToMonsterDy * playerToMonsterDy);

        Directions pathDirection
                = gameRandom.randMove(playerToMonsterDx, playerToMonsterDy, distanceToPlayer, viewDistance);

        int dx = 0, dy = 0;
        switch (pathDirection.getDirection()) {
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
//
//    @Override
//    public boolean isPlayer(){
//        return false;
//    }
    // fixme this always returns 'false' -> why?
    public boolean setStunnedState() {
        if (health < 1) {
            stunned = true;
            staggerCounter = 4;// remaining turns in stagger state;
            stateName = "staggerState";
        }
        return false;
    }

    public boolean isStunned(){
        return stunned;
    }
    public abstract boolean canBeStunned();
}
