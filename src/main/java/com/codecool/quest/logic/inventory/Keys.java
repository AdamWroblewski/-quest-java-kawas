package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;


public class Keys extends Item {

    public Keys(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "key";
    }
}
