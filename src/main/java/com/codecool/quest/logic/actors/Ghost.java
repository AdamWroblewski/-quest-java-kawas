package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.GameRandom;

public class Ghost extends Enemy {
    private boolean hidden = true;

    public Ghost(Cell cell){
        super(cell);
        stateName = "ghostHidden";
        health = 50;
        stunned = true;
    }

    @Override
    public void move(int dx, int dy) {
        super.move(dx, dy);

        if(hidden){
            stateName = "ghostHidden";
            return;
        }
        if(stateName.equals("ghost1") )
            stateName = "ghost2";
        else
            stateName = "ghost1";
    }
    @Override
    public double moveToPlayer(Player player, GameRandom gameRandom){
        double distanceToPlayer = super.moveToPlayer(player, gameRandom);
        hidden = true;
        if(distanceToPlayer < 5.5)
            hidden = false;

        return distanceToPlayer;
    }

    @Override
    public boolean isPlayer() {
        return false;
    }

    @Override
    public void setFightOn(){}

    @Override
    public boolean setStunnedState(){
        return health < 1;
    }
    @Override
    public boolean canBeStunned(){
        return false;
    }
}
