package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.actors.Actor;

public class Skeleton extends Actor {
    private boolean direction;
    private boolean staggered;

    public Skeleton(Cell cell) {
        super(cell);
        staggered = false;

        direction = true;
    }

    @Override
    public String getTileName() {
        return "skeleton";
    }

    @Override
    public void move(int dx, int dy) {
        if(direction) {
            super.move(1, 0);
            direction = false;
        } else {
            super.move(-1, 0);
            direction = true;
        }


    }
}
