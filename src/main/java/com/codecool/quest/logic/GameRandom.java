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
		double angle = rand.nextDouble()*360.0, dy, dx, distance = 1.0, attackParam = 0.0;
		angle = Math.toRadians(angle);

		if(directionToPlayer > 0.0) {
			dx = playerX - actorX;
			dy = playerY - actorY;
			distance = dx * dx + dy * dy;// -> max(dx, dy, -dx, -dy);
			dx = dx / distance * directionToPlayer;
			dy = dy / distance * directionToPlayer;

			distance = Math.sqrt(distance);// dx * dx + dy * dy;
			attackParam = Math.max( (7.0 - distance)/3.0, 0.0);
			attackParam = Math.min(attackParam, 1.0);
		} else
			dx = dy = 0.0;

		dx = attackParam*dx + (1.0 - attackParam)*Math.cos(angle);// ( (x1 - x)/(x1 - x0) )*dx + ( (x - x0)/(x1 - x0) )*rand();
		dy = attackParam*dy + (1.0 - attackParam)*Math.sin(angle);

		return stepVectorToDirection(dx, dy);
	}

	public Directions randStep(){
		double angle = rand.nextDouble()*360.0, dy, dx;
		angle = Math.toRadians(angle);
		dx = Math.cos(angle);
		dy = Math.sin(angle);

		return stepVectorToDirection(dx, dy);
	}

	private Directions stepVectorToDirection(final double dx, final double dy){
		int direction = 4;// like in CSS: 1 - up, 2 - right, 3 - down, 4 - left;
		if(Math.abs(dy) > Math.abs(dx) ){
			if(dy > 0.0) direction = 3;
			else direction = 1;
		} else {
			if(dx > 0.0) direction = 2;
			else direction = 4;
		}

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
