package com.example.hanoi.game;

import com.badlogic.gdx.utils.Array;

public class BlockRack {

	public static final int MAX_NUMBER_OF_BLOCKS = 8;
	
	private int rackNumber = 0;
	private Array<HanoiBlock> blocks;
	
	public BlockRack(int rackOnField) {
		rackNumber = rackOnField;
		blocks = new Array<HanoiBlock>();
	}
	
	public boolean addBlock(HanoiBlock block) {
		if (!canPutOnTop(block)) {
			return false;
		}
		putBlockOnTop(block);
		return true;
	}

	private boolean canPutOnTop(HanoiBlock block) {
		return !this.hasBlocks() || getTopBlock().getLevel() < block.getLevel();
	}

	private HanoiBlock getTopBlock() {
		if (!this.hasBlocks()) {
			throw new RuntimeException("getTopBlock() called on empty Rack!");
		}
		return blocks.get(blocks.size - 1);
	}
	
	private void putBlockOnTop(HanoiBlock block) {
		blocks.add(block);
		block.setRack(this);
	}

	public HanoiBlock removeTopBlock() {
		if (!this.hasBlocks()) {
			throw new RuntimeException("Rack has no blocks. can't remove top block");
		}
		return blocks.removeIndex(blocks.size - 1);
	}
	
	public boolean hasBlocks() {
		return blocks.size > 0;
	}

	public Array<HanoiBlock> getBlocks() {
		return this.blocks;
	}
	
	public int getNumber() {
		return this.rackNumber;
	}
	
}
