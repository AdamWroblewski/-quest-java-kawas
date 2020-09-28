package com.codecool.quest.model;

import com.codecool.quest.logic.Cell;

public class Notes implements Drawable {
	private Cell cell;
	private String charName;

	public Notes(Cell cell){
		this.cell = cell;
		this.cell.setNote(this);
		charName = "A";
	}

	public Notes(Cell cell, String name){
		this.cell = cell;
		this.cell.setNote(this);
		charName = name;
	}

	public void setTileName(String newName){
		charName = newName;
	}
	@Override
	public String getTileName() {
		return charName;
	}
}
