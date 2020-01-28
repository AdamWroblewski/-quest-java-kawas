package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;

public class Sword extends Weapons{

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "Sword";
    }
}
