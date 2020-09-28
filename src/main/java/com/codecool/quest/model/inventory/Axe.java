package com.codecool.quest.model.inventory;

import com.codecool.quest.logic.Cell;

public class Axe extends Weapons{

    private final int axeAttackPower = 10; //+10

    public Axe(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "Axe";
    }

    public int addAttackPower(){
        return this.axeAttackPower;
    }
}
