package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public abstract class Enemy extends Actor {
    protected String stateName = "skeleton";

    Enemy(Cell cell){
    	super(cell);
	}

}
