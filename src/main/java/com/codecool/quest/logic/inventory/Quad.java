package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;

public class Quad extends Item {
    private int oldShield;
    private int timerValue;

    public Quad(Cell cell){
        super(cell);
        timerValue = 50;
    }

    @Override
    public String getTileName() {
        return "Quad damage";
    }

    public int getOldShield(){
        return oldShield;
    }
    public void keepShieldValue(int shield){
        oldShield = shield;
    }

    public int getTimerValue(){
        return timerValue;
    }
}
