package com.codecool.quest.logic.actors;

import com.codecool.quest.Main;
import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.inventory.Keys;

import java.security.Key;


public class Player extends Actor {

    private Directions direction = Directions.INPLACE;
    public Player(Cell cell) {
        super(cell);
    }

    public String getTileName() {
        return "player";
    }

    @Override
    public void move(int dx, int dy){

        Cell nextCell = cell.getNeighbor(dx, dy);
        Actor actor = nextCell.getActor();
        if ((nextCell.getType().equals(CellType.FLOOR) && actor == null) || nextCell.getType().equals(CellType.OPENEDDOOR)) {
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if(actor != null && !actor.isPlayer() ){
            Enemy monster = (Enemy) actor;
            monster.setFightOn();
        } else if ((nextCell.getType().equals(CellType.CLOSEDDOOR))&& Main.items.contains("key")){
            Main.items.remove("key");
            nextCell.setType(CellType.OPENEDDOOR);
        }
        direction.setDirection(dx, dy);
    }

    public Enemy shoot(){
        Cell cellCheck = cellByDirection();
        if(cellCheck == null)
            return null;

        Enemy monster = (Enemy) cellCheck.getActor();
        if(monster != null && !monster.canBeStunned() )
            cellCheck.setActor(null);

        return monster;
    }
    public Actor gloryKill(){
        Cell cellCheck = cellByDirection();
        if(cellCheck == null)
            return null;

        Enemy monster = (Enemy) cellCheck.getActor();
        if(monster != null && monster.canBeStunned() && monster.isStunned() ){
            cellCheck.setActor(null);
            return monster;
        }

        return null;
    }

    @Override
    public boolean isPlayer() {
        return true;
    }

    private Cell cellByDirection(){
        Cell cellCheck;

        switch( direction.getDirection() ){
            case 1:
                cellCheck = cell.getNeighbor(0, -1);
                break;
            case 2:
                cellCheck = cell.getNeighbor(1, 0);
                break;
            case 3:
                cellCheck = cell.getNeighbor(0, 1);
                break;
            case 4:
                cellCheck = cell.getNeighbor(-1, 0);
                break;
            default:
                return null;
        }

        return cellCheck;
    }

    public void pickUpItem(){
        try{
            if(cell.getItem().getTileName() != null){
                Main.items.add(cell.getItem().getTileName());
                cell.setItem(null);
            }
        }catch(NullPointerException ignored){
        }
    }

}
