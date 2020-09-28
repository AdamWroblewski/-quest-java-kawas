package com.codecool.quest.logic;

import com.codecool.quest.GameMap;
import com.codecool.quest.model.CellType;
import com.codecool.quest.model.Drawable;
import com.codecool.quest.model.Notes;
import com.codecool.quest.model.actors.Actor;
import com.codecool.quest.model.inventory.Item;
import com.codecool.quest.model.inventory.Quad;

public class Cell implements Drawable {

    private CellType type;
    private Actor actor;
    private Item item;
    private Notes noteChar;
    private GameMap gameMap;
    private int x, y;
    // todo move to teleport cell class
    private static int teleportExitX;
    private static int teleportExitY;

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
        noteChar = null;
    }

    public Cell(GameMap gameMap, int x, int y) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        noteChar = null;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setNote(Notes note){
        noteChar = note;
    }

    public Actor getActor() {
        return actor;
    }

    public Item getItem() {
        return item;
    }

    public Notes getNote(){
        return noteChar;
    }

    public Cell getNeighbor(int dx, int dy) {
        return gameMap.getCell(x + dx, y + dy);
    }

    public void printChar(String tileName){
        noteChar = new Notes(this, tileName);
    }

    public void useExtras(){
        if(item != null && item instanceof Quad){
            Quad quad = (Quad) item;
            gameMap.setTimer(quad.getTimerValue());
            quad.keepShieldValue(actor.getShield());
        }
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTeleportExitCords(int x, int y) {
        teleportExitX = x;
        teleportExitY = y;
    }

    public int getTeleportExitX() {
        return teleportExitX;
    }

    public int getTeleportExitY() {
        return teleportExitY;
    }
}
