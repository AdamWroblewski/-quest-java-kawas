package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;

public class Axe extends Weapons{

    public Axe(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "Axe";
    }
}
