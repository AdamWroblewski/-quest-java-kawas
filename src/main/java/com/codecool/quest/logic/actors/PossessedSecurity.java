package com.codecool.quest.logic.actors;

import com.codecool.quest.logic.Cell;
import com.codecool.quest.logic.GameRandom;

public class PossessedSecurity extends Enemy {

	public PossessedSecurity(Cell cell){
		super(cell);
		stateName = "Possessed security";
		health = 70;
		shield = 20;
		attackPower = 7;
		viewDistance = 12.0;
	}

	@Override
	public void move(int dx, int dy) {
		if(stunned) {
			staggerCounter--;
			if(staggerCounter == 0) unsetStunnedState();
			return;
		}

		super.move(dx, dy);
	}

	@Override
	public double moveToPlayer(Player player, GameRandom gameRandom){
		double distance = 0.0;
		if(stunned){
			staggerCounter--;
			if(staggerCounter == 0) unsetStunnedState();
			return distance;
		}

		distance = super.moveToPlayer(player, gameRandom);
		return distance;
	}

	@Override
	public void setFightOn() {

	}

	public void unsetStunnedState(){
		health = 60;
		shield = 20;
		stunned = false;
		stateName = "Possessed security";
	}

	@Override
	public boolean canBeStunned() {
		return true;
	}

}
