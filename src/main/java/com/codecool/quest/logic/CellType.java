package com.codecool.quest.logic;

public enum CellType {
    EMPTY("empty"),
    FLOOR("floor"),
    WALL("wall"),
    CLOSEDDOOR("closeddoor"),
    OPENEDDOOR("openeddoor"),
    STAIRSDOWN("stairs down"),
    LADDER("ladder");

    private final String tileName;

    CellType(String tileName) {
        this.tileName = tileName;
    }

    public String getTileName() {
        return tileName;
    }
}
