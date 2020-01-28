package com.codecool.quest.logic;

import java.util.Arrays;
import java.util.Random;

public enum CellType {

    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    CLOSEDDOOR_BLUE("closeddoor Blue"),
    OPENEDDOOR_BLUE("openeddoor Blue"),
    CLOSEDDOOR_YELLOW("closeddoor Yellow"),
    OPENEDDOOR_YELLOW("openeddoor Yellow"),
    STAIRSDOWN("stairs down"),
    LADDER("ladder"),
    TREE("tree");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
