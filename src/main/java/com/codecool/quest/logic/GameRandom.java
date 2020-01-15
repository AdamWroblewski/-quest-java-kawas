package com.codecool.quest.logic;

import java.util.Random;

public class GameRandom {
	private static Random rand;
	private double directionToPlayer;

	public GameRandom(){
		rand = new Random();
		directionToPlayer = 0.5;

	}

	public int randRange(final int min, final int max){
		if(min >= max){
			throw new IllegalArgumentException("randRange: minimum should be lower then maximum!");
		}
		return rand.nextInt(max - min) + min;
	}

	public Directions randMove(int actorX, int actorY, int playerX, int playerY){
		double angle = rand.nextDouble()*360.0, dy, dx, distance;
		angle = Math.toRadians(angle);

		if(directionToPlayer > 0.0) {
			dx = playerX - actorX;
			dy = playerY - actorY;
			distance = dx * dx + dy * dy;
			dx = dx + dx / distance * directionToPlayer;
			dy = dy + dy / distance * directionToPlayer;
		} else
			dx = dy = 0.0;

		dx = dx + Math.cos(angle);
		dy = dy + Math.sin(angle);

		int direction = 2;// like in CSS: 1 - up, 2 - right, 3 - down, 4 - left;
		if(Math.abs(dy) > Math.abs(dx) ) direction = 1;
		if(dx < 0.0 || dy < 0.0) direction += 2;

		switch(direction){
			case 1:
				return Directions.UP;
			case 2:
				return Directions.RIGHT;
			case 3:
				return Directions.DOWN;
			case 4:
				return Directions.LEFT;
		}

		return Directions.INPLACE;
	}
}
