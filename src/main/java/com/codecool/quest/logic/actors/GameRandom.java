package com.codecool.quest.logic.actors;

import java.util.Random;

public class GameRandom {
	private static Random rand;
	private double directionToPlayer;

	GameRandom(){
		rand = new Random();
		directionToPlayer = 0.0;

	}

	public int randRange(final int min, final int max){
		if(min >= max){
			throw new IllegalArgumentException("randRange: minimum should be lower then maximum!");
		}
		return rand.nextInt(max - min) + min;
	}

	public int randMove(){
		double random = rand.nextDouble()*360.0;

		return 0;
	}
}
