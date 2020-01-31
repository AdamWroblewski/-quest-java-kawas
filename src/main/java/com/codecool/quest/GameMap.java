package com.codecool.quest;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameRandom;
import com.codecool.quest.model.CellType;
import com.codecool.quest.model.actors.Enemy;
import com.codecool.quest.model.actors.Player;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;

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
            cells[11][0].printChar("sad");
        }
        return player;
    }

    public void addMonster(Enemy monster){
        monsters.add(monster);
    }

    public boolean moveMonsters() {
        Platform.runLater(() -> {
            for (Enemy monster : monsters) {
                monster.moveToPlayer(player, gameRandom);
            }
        });
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
    /**
     * Prints message from row of game map = row  and from column of game map = col;
     *
     * @param row of the map;
     * @param col of the map;
     * @param message: a list of chars which have to be included in Tile class
     *               with the same k value as the char in string;
     */
    public void printMessage(int row, int col, String message){
        try {
            int colStep = col, n = message.length();
            for (int i = 0; i < n; i++, colStep++) {
                String s = "" + message.charAt(i);
                cells[colStep][row].printChar(s);
            }
        } catch(NullPointerException e){
            System.out.println("printMessage error: " + e.getMessage());
        }
    }
    /**
     * Clears all strLength chars from the map starting from row = row and column = col to the left of the map;
     *
     * @param row: row to start from;
     * @param col: column to start from;
     * @param strLength: the length of chars to clear
     */
    public void clearMessage(int row, int col, int strLength){
        int colStep = col, i;
        for(i = 0; i < strLength; i++, colStep++)
            cells[colStep][row].setNote(null);
    }

    /**
     * Sets timer into timeFrames and prints in on the upper left corner of the map;
     *
     * @param timeFrames
     */
    public void setTimer(int timeFrames){
        timer = timeFrames;

        String timeStr = Integer.toString(timer);

        clearMessage(0, 1, 5);
        printMessage(0, 1, timeStr);
    }
    /**
     * Deceases the timer and prints its new value on the upper left corner of the map;
     */
    public void countTimer(){
        if(timer < 1) {
            player.setBasePlayerStats();
            return;
        }

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
