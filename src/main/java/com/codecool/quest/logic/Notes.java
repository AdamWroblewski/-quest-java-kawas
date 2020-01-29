package com.codecool.quest.logic;

public class Notes implements Drawable {
	private Cell cell;

	public Notes(Cell cell){
		this.cell = cell;
		this.cell.setNote(this);
	}

	@Override
	public String getTileName() {
		return "Info";
	}
}
