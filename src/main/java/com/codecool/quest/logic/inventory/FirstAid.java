package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;

public class FirstAid extends Item{

    private int healthIncrease = 10;

    public FirstAid(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "First Aid";
    }

    public int getHealthIncrease() {
        return healthIncrease;
    }
}
