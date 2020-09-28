package com.codecool.quest.model.inventory;

import com.codecool.quest.logic.Cell;

public class Sword extends Weapons{

    private final int swordAttackPower = 5; //+5

    public Sword(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "Sword";
    }

    public int addAttackPower(){
        return this.swordAttackPower;
    }

}
