package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.CellType;
import com.codecool.quest.logic.Directions;
import com.codecool.quest.logic.Drawable;

public abstract class Actor implements Drawable {
    protected Cell cell;
    protected Directions direction;
    private int health = 10;

    public Actor(Cell cell) {
        this.cell = cell;
        this.cell.setActor(this);
    }

    public void move(int dx, int dy) {
        Cell nextCell = cell.getNeighbor(dx, dy);
        if ((nextCell.getType().equals(CellType.FLOOR) && nextCell.getActor() == null) || nextCell.getType().equals(CellType.OPENEDDOOR)){
            cell.setActor(null);
            nextCell.setActor(this);
            cell = nextCell;
        } else if (nextCell.getType().equals(CellType.CLOSEDDOOR)){
            nextCell.setType(CellType.OPENEDDOOR);
        }
        direction.setDirection(dx, dy);
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
}
