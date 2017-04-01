package com.example.hanoi;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.example.hanoi.screens.GameFinishedScreen;
import com.example.hanoi.screens.GameScreen;
import com.example.hanoi.screens.HighscoreScreen;
import com.example.hanoi.screens.LevelFinishedScreen;
import com.example.hanoi.screens.MainMenuScreen;

public class HanoiGame extends Game {

	public static final int SCREEN_WIDTH = 800;
	public static final int SCREEN_HEIGHT = 480;

	public static Texture backgroundTexture;
	
	private static final int MAX_LEVEL = 7;
	
	public ShapeRenderer shapeRenderer;
	public SpriteBatch batch;
	public BitmapFont font;
	public OrthographicCamera camera;
	public boolean isStillTouched = false;

	private int currentLevel;
	private long currentScore = 0;

	public Sprite backgroundSprite;

	private Skin skin;
	
	@Override
	public void create() {
		backgroundTexture = new Texture(Gdx.files.internal("hanoi_background.png"));
		backgroundSprite =new Sprite(backgroundTexture);
		initFields();
		createButtonSkin();
		createRenderer();
		showMenu();
	}

	private void initFields() {
		currentLevel = 1;
		batch = new SpriteBatch();
		font = new BitmapFont();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCREEN_WIDTH, SCREEN_HEIGHT);
	}
	
	private void createButtonSkin() {
		skin = new Skin();

		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		skin.add("green", new Texture(Gdx.files.internal("button_green.png")));
		skin.add("yellow", new Texture(Gdx.files.internal("button_yellow.png")));
		skin.add("red", new Texture(Gdx.files.internal("button_red.png")));
		skin.add("default", new BitmapFont());
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

	private void showFinishScreen() {
		if (isMaxLevelReached()) {
			showEndScreen();
		} else {
			showLevelMenu();
		}
	}
	
	private boolean isMaxLevelReached() {
		return currentLevel > MAX_LEVEL;
	}

	public void showLevelMenu() {
		this.setScreen(new LevelFinishedScreen(this));
	}
	
	private void levelUp() {
		currentLevel++;
	}

	private void updateScore(double additionalPoints) {
		increaseScore(additionalPoints);
		if (isNewHighscore()) {
			saveCurrentHighscore();
		}
	}

	private void increaseScore(double d) {
		currentScore += d;
	}

	private boolean isNewHighscore() {
		return currentScore > getBestScore();
	}

	private void saveCurrentHighscore() {
		Preferences prefs = Gdx.app.getPreferences("SpeedHanoiPreferences");
		prefs.putLong("highscore", currentScore);
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

	public CharSequence getHighscoreString() {
		return "Highscore: " + currentScore;
	}
	
	public CharSequence getBestHighscoreString() {
		return "Highscore: " + getBestScore();
	}
	
	public long getBestScore() {
		Preferences prefs = Gdx.app.getPreferences("SpeedHanoiPreferences");
		return prefs.getLong("highscore", 0);
	}
	
	public void renderBackground() {
		batch.begin();
		batch.setProjectionMatrix(camera.combined);
		batch.draw(backgroundSprite, 0, 0, HanoiGame.SCREEN_WIDTH, HanoiGame.SCREEN_HEIGHT);
		batch.end();
	}
	
	
	public TextButtonStyle createButtonStyle(String skinname) {
		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable(skinname, Color.LIGHT_GRAY);
		textButtonStyle.down = skin.newDrawable(skinname, Color.LIGHT_GRAY);
		textButtonStyle.checked = skin.newDrawable(skinname, Color.BLUE);
		textButtonStyle.over = skin.newDrawable(skinname, Color.WHITE);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		return textButtonStyle;
	}


}
