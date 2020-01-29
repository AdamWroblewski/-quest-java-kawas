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
    TREE("tree"),
    BRIDGE("bridge"),
    LEFT_BRIDGEHEAD("left bridgehead"),
    RIGHT_BRIDGEHEAD("right bridgehead"),
    WATER("water"),
    WATER_UPPER_LEFT_CORNER("water upper left corner"),
    WATER_UPPER_RIGHT_CORNER("water upper right corner"),
    WATER_LOWER_LEFT_CORNER("water lower left corner"),
    WATER_LOWER_RIGHT_CORNER("water lower right corner"),
    WATER_LEFT_EDGE("water left edge"),
    WATER_RIGHT_EDGE("water right edge"),
    WATER_UPPER_EDGE("water upper edge"),
    WATER_LOWER_EDGE("water lower edge"),
    TELEPORT_FIRST_STATE("teleport first state"),
    TELEPORT_SECOND_STATE("teleport second state");


    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
