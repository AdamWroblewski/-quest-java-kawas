package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;

public class Sword extends Weapons{

    final int swordAttackPower = 5; //+5

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
