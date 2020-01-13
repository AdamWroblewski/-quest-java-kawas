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

	public int randMove(int actorX, int actorY, int playerX, int playerY){
		double random = rand.nextDouble()*360.0, dy, dx, maxDxDy;
		dx = playerX - actorX;
		dy = playerY - actorY;
		maxDxDy = Math.max(dx, dy);

		dx = dx/maxDxDy*directionToPlayer;
		dy = dy/maxDxDy*directionToPlayer;

		dx = dx + Math.cos(random);
		dy = dy + Math.sin(random);

		int direction = 1;// like in CSS: 0 - up, 1 - right, 2 - down, 3 - left;
		if(Math.abs(dy) > Math.abs(dy) ) direction = 0;
		if(dx < 0.0 || dy < 0.0) direction += 2;

		return direction;
	}
}
