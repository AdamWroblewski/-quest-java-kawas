package com.codecool.quest.model.actors;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.model.Drawable;

public abstract class Actor implements Drawable {
    // todo: first consts, then fields
    protected Cell cell;
    protected int health = 100;
    protected int shield = 0;
    protected int baseAttackPower = 10;
    protected int attackPower = 10;
    // fixme this shouldn't be a plaintext string
    protected String stateName;

    // todo then constructor
    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    // todo then other methods
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    // todo getters and setters at the very bottom
    // fixme this shouldn't be a plaintext string
    public String getTileName() {
        return stateName;
    }

    public abstract void move(int dx, int dy);

    public boolean isPlayer() {
        return this instanceof Player;
    }

    public boolean isDead() {
        return health < 1;
    }

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

    // todo a 'get' method should return what it's called
    protected boolean getAttacked(int attackForce){
        if(shield > 0){
            int healthDecrease = (health > 1)?1:0, shieldDecrease = attackForce - healthDecrease;
            if(shieldDecrease > shield){
                shieldDecrease = shield;
                healthDecrease = attackForce - shieldDecrease;
            }
            shield -= shieldDecrease;
            health -= healthDecrease;
            if(this instanceof Player){
                // todo this shouldn't be held as a field in the Main class
                Main.tempHealth = health;
                Main.tempShield = shield;
            }
        } else if(health > 0){
            health -= attackForce;
            if(this instanceof Player){
                Main.tempHealth = health;
            }
            Main.items.remove("Shield");
        }
        return health > 0;
    }
    public boolean changeHealth(int healthGrow){
        health += healthGrow;
        return health > 0;
    }

    public int getShield(){
        return shield;
    }

    public int getAttackPower(){
        return attackPower;
    }
}
