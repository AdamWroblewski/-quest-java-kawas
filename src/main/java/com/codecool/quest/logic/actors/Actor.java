package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.Drawable;

public abstract class Actor implements Drawable {

    protected Cell cell;
    protected int health = 100;
    protected int shield = 0;
    protected int attackPower = 10;
    protected String stateName;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public abstract void move(int dx, int dy);

    public abstract boolean isPlayer();

    public int getHealth() {
        return health;
    }

    public Cell getCell() {
        return cell;
    }
    public void setCEll(Cell newCell){
        cell = newCell;
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }

    protected boolean getAttacked(int attackForce){
        if(shield > 0){
            int healthDecrease = (health > 1)?1:0, shieldDecrease = attackForce - healthDecrease;
            if(shieldDecrease > shield){
                shieldDecrease = shield;
                healthDecrease = attackForce - shieldDecrease;
            }
            shield -= shieldDecrease;
            health -= healthDecrease;
        } else {
            health -= attackForce;
        }
        return health > 0;
    }
    public boolean changeHealth(int healthGrow){
        health += healthGrow;
        return health > 0;
    }
}
