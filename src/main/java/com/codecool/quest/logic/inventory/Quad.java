package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;

public class Quad extends Item {
    private int oldShield;
    private int timerValue;

    public Quad(Cell cell){
        super(cell);
        timerValue = 16;
    }

    @Override
    public String getTileName() {
        return "Quad damage";
    }
}
