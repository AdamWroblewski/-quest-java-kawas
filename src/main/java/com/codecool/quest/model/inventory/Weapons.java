package com.codecool.quest.model.inventory;

import com.codecool.quest.logic.Cell;

public abstract class Weapons extends Item {


    public Weapons(Cell cell) {
        super(cell);
    }

    public abstract int addAttackPower();
}
