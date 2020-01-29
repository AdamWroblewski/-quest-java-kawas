package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;


public class Shield extends Item {

    private final int shield = 10; //+10;

    public Shield(Cell cell) {
        super(cell);
    }


    @Override
    public String getTileName() {
        return "Shield";
    }

    public int addShield(){
        return this.shield;
    }
}






