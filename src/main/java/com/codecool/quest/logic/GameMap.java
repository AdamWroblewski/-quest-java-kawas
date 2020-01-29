package com.codecool.quest.logic;

import com.codecool.quest.logic.actors.Actor;
import com.codecool.quest.logic.actors.Enemy;
import com.codecool.quest.logic.actors.Player;
import com.codecool.quest.logic.actors.Skeleton;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class GameMap {
    private int width;
    private int height;
    private Cell[][] cells;
    GameRandom gameRandom = new GameRandom();
    private int timer = 0;

    private Player player;
    private List<Enemy> monsters;

    public GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
        monsters = new ArrayList<>();
    }

    public Cell getCell(int x, int y) {
        return cells[x][y];
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        if(player.isDead() ){
            printMessage(0, 1, "GAME");
            printMessage(0, 6, "O", "V", "E", "R");
        }
        return player;
    }

    public void addMonster(Enemy monster){
        monsters.add(monster);
    }

    public boolean moveMonsters(){
        ListIterator<Enemy> iter = monsters.listIterator();
        while(iter.hasNext() ){
            iter.next().moveToPlayer(player, gameRandom);
        }
        return true;
    }

    public void playerFight(){
        if(player.getHealth() < 1)
            return;

        Enemy monster = player.shoot();
        if(monster == null)
            return;

        boolean isKilled = monster.setStunnedState();
        if(isKilled)
            monsters.remove(monster);
    }
    public boolean playerFinishesOff(){
        Enemy monster = (Enemy) player.gloryKill();
        if(monster == null)
            return false;

        monsters.remove(monster);
        return true;
    }

    public void printMessage(int row, int col, String... chars){
        int colStep = col;
        for(String character: chars){
            cells[colStep][row].printChar(character);
            colStep++;
        }
    }
    public void printMessage(int row, int col, String message){
        int colStep = col, i, n = message.length();
        try {
            for (i = 0; i < n; i++, colStep++) {
                String s = "" + message.charAt(i);
                cells[colStep][row].printChar(s);
            }
        } catch(NullPointerException e){
            System.out.println("printMessage error: " + e.getMessage());
        }
    }
    public void clearMessage(int row, int col, int strLength){
        int colStep = col, i;
        for(i = 0; i < strLength; i++, colStep++)
            cells[colStep][row].setNote(null);
    }

    public void setTimer(int timeFrames){
        timer = timeFrames;

        String timeStr = Integer.toString(timer);

        clearMessage(0, 1, 5);
        printMessage(0, 1, timeStr);
    }
    public void countTimer(){
        if(timer < 1)
            return;

        timer--;
        String timeStr = Integer.toString(timer);

        clearMessage(0, 1, 5);
        if(timer > 0)
            printMessage(0, 1, timeStr);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
