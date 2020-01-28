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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
