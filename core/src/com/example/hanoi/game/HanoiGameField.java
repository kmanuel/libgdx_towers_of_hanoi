package com.example.hanoi.game;

import com.badlogic.gdx.utils.Array;

public class HanoiGameField {

	private static final int NUMBER_OF_RACKS = 3;
	
	private int level;
	private HanoiBlock hoveringBlock;
	private Array<BlockRack> racks;
	

	public void createGameField(int level) {
		this.level = level;
		createRacks();
		fillFirstRack(level);
	}

	private void createRacks() {
		racks = new Array<BlockRack>(NUMBER_OF_RACKS);
		for (int i = 0; i < NUMBER_OF_RACKS; i++) {
			racks.add(new BlockRack(i));
		}
	}

	private void fillFirstRack(int numberOfBlocks) {
		BlockRack firstRack = getRack(0);
		for (int i = 0; i < numberOfBlocks; i++) {
			firstRack.addBlock(new HanoiBlock(i));
		}
	}
	
	public boolean isBlockHovering() {
		return hoveringBlock != null;
	}
	
	public HanoiBlock getHoveringBlock() {
		return hoveringBlock;
	}

	public BlockRack getRack(int index) {
		return racks.get(index);
	}

	public boolean liftBlockFrom(BlockRack touchedRack) {
		if (touchedRack.hasBlocks()) {
			hoveringBlock = touchedRack.removeTopBlock();
			return true;
		}
		return false;
	}

	public boolean addBlockTo(BlockRack touchedRack) {
		boolean wasAdded = touchedRack.addBlock(hoveringBlock);
		if (wasAdded) {
			hoveringBlock = null;
		}
		return wasAdded;
	}

	public Array<BlockRack> getRacks() {
		return racks;
	}
	
	public boolean areAllBlocksOnLastRack() {
		return getNumberOfBlocksOnLastRack() == getTotalNumberOfBlocks();
	}

	private int getNumberOfBlocksOnLastRack() {
		return getNumberOfBlocksOnRack(NUMBER_OF_RACKS - 1);
	}

	private int getNumberOfBlocksOnRack(int index) {
		return racks.get(index).getBlocks().size;
	}

	public int getTotalNumberOfBlocks() {
		return level;
	}
}
