package com.example.hanoi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.example.hanoi.screens.GameFinishedScreen;
import com.example.hanoi.screens.GameScreen;
import com.example.hanoi.screens.HighscoreScreen;
import com.example.hanoi.screens.LevelFinishedScreen;
import com.example.hanoi.screens.MainMenuScreen;

public class HanoiGame extends Game {

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 480;

	public ShapeRenderer shapeRenderer;
	public SpriteBatch batch;
	public BitmapFont font;
	public OrthographicCamera camera;
	public boolean isStillTouched = false;

	private int currentLevel;
	private long currentScore = 0;

	private int maxLevel = 2;

	private String scoreString;

	@Override
	public void create() {
		initFields();
		createRenderer();
		showMenu();
	}

	private void initFields() {
		currentLevel = 1;
		scoreString = "Highscore: 0";
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
	}

	private void createRenderer() {
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render() {
		super.render();
	}

	@Override
	public void dispose() {
		super.dispose();
		font.dispose();
		shapeRenderer.dispose();
		batch.dispose();
	}

	public void startNewGame() {
		resetLevel();
		showGame();
	}

	public void resetLevel() {
		resetHighscore();
		this.currentLevel = 1;
	}

	private void resetHighscore() {
		this.currentScore = 0;
		scoreString = "Highscore: " + currentScore;
	}

	public void nextLevel() {
		showGame();
	}

	public void showGame() {
		this.setScreen(new GameScreen(this, currentLevel));
	}

	public void showHighscore() {
		this.setScreen(new HighscoreScreen(this));
	}

	public void showMenu() {
		this.setScreen(new MainMenuScreen(this));
	}

	public void showLevelMenu() {
		this.setScreen(new LevelFinishedScreen(this));
	}

	private void showFinishScreen() {
		if (isMaxLevelReached()) {
			showEndScreen();
		} else {
			showLevelMenu();
		}
	}
	
	private boolean isMaxLevelReached() {
		return currentLevel > maxLevel;
	}

	private void levelUp() {
		currentLevel++;
	}

	private void updateScore(double d) {
		currentScore += d;
		if (isNewHighscore(currentScore)) {
			saveHighscore(currentScore);
		}
		scoreString = "Highscore: " + currentScore;
	}

	private boolean isNewHighscore(long score) {
		return score > getBestScore();
	}

	public long getBestScore() {
		Preferences prefs = Gdx.app.getPreferences("SpeedHanoiPreferences");
		return prefs.getLong("highscore", 0);
	}

	private void saveHighscore(long score) {
		Preferences prefs = Gdx.app.getPreferences("SpeedHanoiPreferences");
		prefs.putLong("highscore", score);
		prefs.flush();
	}
	
	public void finish(double d) {
		levelUp();
		updateScore(d);
		showFinishScreen();
	}



	private void showEndScreen() {
		this.setScreen(new GameFinishedScreen(this));
	}

	public CharSequence getHighscore() {
		return scoreString;
	}

}
