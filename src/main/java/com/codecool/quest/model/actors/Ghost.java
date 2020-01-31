package com.codecool.quest.model.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameRandom;

public class Ghost extends Enemy {
    private boolean hidden = true;

    public Ghost(Cell cell){
        super(cell);
        stateName = "ghostHidden";
        health = 50;
        stunned = true;
        attackPower = 3;
        viewDistance = 8.0;
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
        // todo fix magic numbers
        hidden = !(distanceToPlayer < 5.5);

        return distanceToPlayer;
    }

    @Override
    public boolean setStunnedState(){
        return health < 1;
    }
    @Override
    public boolean canBeStunned(){
        return false;
    }
}
