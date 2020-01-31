package com.codecool.quest.logic;

import com.codecool.quest.GameMap;
import com.codecool.quest.model.CellType;

public class TeleportCell extends Cell {
    public TeleportCell(GameMap gameMap, int x, int y, CellType type) {
        super(gameMap, x, y, type);
    }
}
