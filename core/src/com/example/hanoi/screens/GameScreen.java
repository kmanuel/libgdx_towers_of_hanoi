package com.example.hanoi.screens;

import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.example.hanoi.HanoiGame;
import com.example.hanoi.game.BlockRack;
import com.example.hanoi.game.HanoiBlock;
import com.example.hanoi.game.HanoiGameField;

public class GameScreen implements Screen {

	private int HIGHSCORE_X_POSITION = 10;
	private int HIGHSCORE_Y_POSITION = 470;

	HanoiGameField gameField;

	private Date start;

	private final HanoiGame game;
	private int numberOfMoves;

	public GameScreen(HanoiGame hanoiGame, int level) {
		this.game = hanoiGame;
		createGame(level);
		start = new Date();
		numberOfMoves = 0;
	}

	private void createGame(int level) {
		gameField = new HanoiGameField();
		gameField.createGameField(level);
	}

	@Override
	public void render(float delta) {
		clearScreen();
		game.renderBackground();
		handleInput();
		renderHanoiFieldShapes();
		checkEndState();
		drawHighscore();
	}

	private void clearScreen() {
		Gdx.gl.glClearColor(0.7f, 0.7f, 0.7f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	}

	private void renderHanoiFieldShapes() {
		initializeRendering();
		renderRacks();
		renderHoveringBlock();
		endRendering();
	}

	private void initializeRendering() {
		game.camera.update();
		game.shapeRenderer.setProjectionMatrix(game.camera.combined);
		game.shapeRenderer.begin(ShapeType.Filled);
	}

	private void renderRacks() {
		for (BlockRack rack : gameField.getRacks()) {
			renderRack(rack);
		}
	}

	private void renderRack(BlockRack rack) {
		Array<HanoiBlock> blocksOnRack = rack.getBlocks();
		for (int i = 0; i < blocksOnRack.size; i++) {
			HanoiBlock block = blocksOnRack.get(i);
			renderNthBlock(i, block);
		}
	}

	private void renderNthBlock(int n, HanoiBlock hanoiBlock) {
		renderBlock(hanoiBlock, n);
	}

	private void renderHoveringBlock() {
		if (!gameField.isBlockHovering()) {
			return;
		}
		HanoiBlock hoveringBlock = gameField.getHoveringBlock();
		renderBlock(hoveringBlock, 8);
	}

	private void renderBlock(HanoiBlock hanoiBlock, int n) {
		int blockLevel = hanoiBlock.getLevel();
		int blockShrink = HanoiBlock.BLOCK_FULL_WIDTH / (BlockRack.MAX_NUMBER_OF_BLOCKS);
		int blockWidth = HanoiBlock.BLOCK_FULL_WIDTH - (blockLevel) * blockShrink;
		int sizeOffset = (HanoiBlock.BLOCK_FULL_WIDTH - blockWidth) / 2;
		int blockXPos = (HanoiBlock.BLOCK_FULL_WIDTH * hanoiBlock.getRackNumber()) + sizeOffset;
		int blockYPos = HanoiBlock.BLOCK_FULL_HEIGHT * n;
		game.shapeRenderer.setColor(hanoiBlock.getColor());
		game.shapeRenderer.rect(blockXPos, blockYPos, blockWidth, HanoiBlock.BLOCK_FULL_HEIGHT);
	}

	private void drawHighscore() {
		game.batch.begin();
		game.batch.setProjectionMatrix(game.camera.combined);
		game.font.draw(game.batch, game.getHighscore(), HIGHSCORE_X_POSITION, HIGHSCORE_Y_POSITION);
		game.batch.end();
	}

	private void endRendering() {
		game.shapeRenderer.end();
	}

	private void handleInput() {
		if (touchStarted()) {
			game.isStillTouched = true;
			handleTouchInGame();
		} else if (touchStopped()) {
			game.isStillTouched = false;
		}
	}

	private void handleTouchInGame() {
		BlockRack touchedRack = getTouchedRack();
		if (!gameField.isBlockHovering()) {
			pickUpBlock(touchedRack);
		} else {
			putDownBlock(touchedRack);
		}
	}

	private void putDownBlock(BlockRack touchedRack) {
		gameField.addBlockTo(touchedRack);
	}

	private void pickUpBlock(BlockRack touchedRack) {
		boolean pickedUpBlock = gameField.liftBlockFrom(touchedRack);
		if (pickedUpBlock) {
			countAsMove();
		}
	}

	private void countAsMove() {
		numberOfMoves++;
	}

	private boolean touchStopped() {
		return game.isStillTouched && !Gdx.input.isTouched();
	}

	private boolean touchStarted() {
		return Gdx.input.isTouched() && !game.isStillTouched;
	}

	private BlockRack getTouchedRack() {
		int xCoordinate = Gdx.input.getX();
		int rackIndex = getRackNrForXTouch(xCoordinate);
		BlockRack touchedRack = gameField.getRack(rackIndex);
		return touchedRack;
	}

	private int getRackNrForXTouch(int x) {
		int screenThird = Gdx.graphics.getWidth() / 3;
		if (x < screenThird) {
			return 0;
		}
		if (x < 2 * screenThird) {
			return 1;
		}
		return 2;
	}

	private void checkEndState() {
		if (gameField.areAllBlocksOnLastRack()) {
			Date now = new Date();
			long secondsInLevel = (now.getTime() - start.getTime()) / 1000;
			double possibleMaxScore = getMinNumberOfMovesToSolve() * 100;
			double timeMalus = secondsInLevel;
			double moveMalus = 10 * (numberOfMoves - getMinNumberOfMovesToSolve());
			double scoreAchieved = possibleMaxScore - timeMalus - moveMalus;
			game.finish(Math.max(scoreAchieved, 0) );
			dispose();
		}
	}

	private double getMinNumberOfMovesToSolve() {
		return Math.pow(2, gameField.getTotalNumberOfBlocks()) - 1;
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
	}
	
}
