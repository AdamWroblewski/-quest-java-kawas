package com.codecool.quest.logic.inventory;

import com.codecool.quest.logic.Cell;
import javafx.scene.input.MouseEvent;



public class Button extends Item {

    private Cell cell;

    public Button(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "button";
    }

}

