package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.Drawable;


public abstract class Item implements Drawable {
    private Cell cell;

    private String color;

    public Item(Cell cell, String color) {
        this.cell = cell;
        this.cell.setItem(this);
        this.color = color;
    }

    public Item(Cell cell) {
        this.cell = cell;
        this.cell.setItem(this);
    }


    public String getColor() {
        return color;
    }


    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
