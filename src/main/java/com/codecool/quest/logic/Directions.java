package com.codecool.quest.logic;

public enum Directions {
	INPLACE(0),
	UP(1),
	RIGHT(2),
	DOWN(3),
	LEFT(4);

	private int direction;

	Directions(){
		direction = 0;
	}
	Directions(int direction){
		this.direction = direction;
	}
	Directions(int dx, int dy){
		if(dy == 0) direction = (dx > 0)?2:4;
		else
			direction = (dy < 0)?1:3;
	}

	public int getDirection() {
		return direction;
	}
	public void setDirection(int dx, int dy){
		if(dy == 0) direction = (dx > 0)?2:4;
		else
			direction = (dy < 0)?1:3;
	}
}
