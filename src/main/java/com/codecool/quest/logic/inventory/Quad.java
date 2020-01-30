package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;

public class Quad extends Item {
    private int oldShield;
    private int timerValue;

    public Quad(Cell cell){
        super(cell);
        timerValue = 150;
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

    public int getActiveHealth(int health){
        return health;
    }
    public int getActiveShield(int shield){
        return 200;
    }
    public int getActiveAttackPower(int attackPower){
        return 300;
    }
    public int getPreviousHealth(int oldHealth){
        return 100;
    }
    public int getPreviousShield(int oldShield){
        return oldShield;
    }
    public int getPreviousAttackPower(int oldAttackPower){
        return oldAttackPower;
    }
}
