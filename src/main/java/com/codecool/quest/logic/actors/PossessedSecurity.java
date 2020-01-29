package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;

public class PossessedSecurity extends Enemy {
	private int staggerCounter = 0;

	public PossessedSecurity(Cell cell){
		super(cell);
		stateName = "Possessed security";
		health = 70;
		shield = 20;
	}

	@Override
	public void setFightOn() {

	}

	@Override
	public boolean setStunnedState() {
		if(health < 1) {
			stunned = true;
			staggerCounter = 4;// turns left in stagger state;
			stateName = "staggerState";
		}
		return false;
	}
	public void unsetStunnedState(){
		health = 40;
		stunned = false;
		stateName = "Possessed security";
	}

	@Override
	public boolean canBeStunned() {
		return true;
	}

}
