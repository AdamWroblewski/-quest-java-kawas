package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;


public class Keys extends Item {

    public Keys(Cell cell, String color) {
        super(cell, color);
    }

    @Override
    public String getTileName() {
        return "key " + this.getColor();
    }

}
