package com.example.hanoi.game;

import com.badlogic.gdx.graphics.Color;
import com.example.hanoi.HanoiGame;

public class HanoiBlock {

	private int level = 0;
	private BlockRack rack;
	public static int BLOCK_FULL_WIDTH = HanoiGame.SCREEN_WIDTH / 3;
	public static int BLOCK_FULL_HEIGHT = HanoiGame.SCREEN_HEIGHT / (BlockRack.MAX_NUMBER_OF_BLOCKS + 1);

	public HanoiBlock(int level) {
		this.level = level;
	}

	public int getLevel() {
		return this.level;
	}

	public Color getColor() {
		switch (this.level) {
		case 0:
			return Color.FOREST;
		case 1:
			return Color.BLUE;
		case 2:
			return Color.BROWN;
		case 3:
			return Color.CHARTREUSE;
		case 4:
			return Color.CORAL;
		case 5:
			return Color.CYAN;
		case 6:
			return Color.GOLD;
		case 7:
			return Color.FIREBRICK;
		}
		throw new RuntimeException("No color available for this blocklevel");
	}

	public void setRack(BlockRack rack) {
		this.rack = rack;
	}

	public int getRackNumber() {
		return this.rack.getNumber();
	}

}
