package com.example.hanoi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.example.hanoi.HanoiGame;

public class MainMenuScreen implements Screen {

	private static final int NUMBER_OF_BUTTONS_ON_SCREEN = 3;
	private static final int BETWEEN_BUTTON_PADDING = NUMBER_OF_BUTTONS_ON_SCREEN - 1;
	private static final int BUTTON_WIDTH = HanoiGame.SCREEN_WIDTH / 2;
	private static final int TOP_PADDING = 1;
	private static final int BOTTOM_PADDING = 1;
	private static final int BUTTON_HEIGHT = 
			HanoiGame.SCREEN_HEIGHT 
			/ (NUMBER_OF_BUTTONS_ON_SCREEN + BETWEEN_BUTTON_PADDING + TOP_PADDING + BOTTOM_PADDING);
	
	private Skin skin;
	private Stage stage;
	private Table table;
	private HanoiGame game;

	public MainMenuScreen(HanoiGame game) {
		this.game = game;
		create();
	}

	public void create() {
		stage = new Stage(new StretchViewport(HanoiGame.SCREEN_WIDTH, HanoiGame.SCREEN_HEIGHT, game.camera));
		Gdx.input.setInputProcessor(stage);
		table = new Table();
		table.setFillParent(true);
		stage.addActor(table);
		addButtonsToTable(table);
	}

	private void addButtonsToTable(Table table) {
		TextButtonStyle textButtonStyle = createDefaultButtonStyle();
		addStartButton(textButtonStyle, table);
		addHighscoreButton(textButtonStyle, table);
		addExitButton(textButtonStyle, table);
	}
	
	private void addStartButton(TextButtonStyle textButtonStyle, Table table) {
		TextButton startGameButton = createStartButton(textButtonStyle);
		table.add(startGameButton).pad(25).size(BUTTON_WIDTH, BUTTON_HEIGHT).row();
	}
	
	private TextButton createStartButton(TextButtonStyle textButtonStyle) {
		TextButton startGameButton = new TextButton("Start", textButtonStyle);
		startGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				dispose();
				game.startNewGame();
				return true;
			}
		});
		return startGameButton;
	}

	private void addHighscoreButton(TextButtonStyle textButtonStyle, Table table) {
		TextButton highscoreButton = createHighscoreButton(textButtonStyle);
		table.add(highscoreButton).pad(25).size(BUTTON_WIDTH, BUTTON_HEIGHT).row();
	}
	
	private void addExitButton(TextButtonStyle textButtonStyle, Table table) {
		TextButton exitButton = createExitButton(textButtonStyle);
		table.add(exitButton).pad(25).size(BUTTON_WIDTH, BUTTON_HEIGHT).row();
	}

	private TextButton createHighscoreButton(TextButtonStyle textButtonStyle) {
		TextButton highscoreButton = new TextButton("Highscore", textButtonStyle);
		highscoreButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				game.showHighscore();
				return true;
			}
		});
		return highscoreButton;
	}
	
	private TextButton createExitButton(TextButtonStyle textButtonStyle) {
		TextButton exitButton = new TextButton("Exit", textButtonStyle);
		exitButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				Gdx.app.exit();
				return true;
			}
		});
		return exitButton;
	}
	
	private TextButtonStyle createDefaultButtonStyle() {
		skin = new Skin();

		Pixmap pixmap = new Pixmap(1, 1, Format.RGBA8888);
		pixmap.setColor(Color.WHITE);
		pixmap.fill();
		skin.add("white", new Texture(pixmap));
		skin.add("default", new BitmapFont());

		TextButtonStyle textButtonStyle = new TextButtonStyle();
		textButtonStyle.up = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.down = skin.newDrawable("white", Color.DARK_GRAY);
		textButtonStyle.checked = skin.newDrawable("white", Color.BLUE);
		textButtonStyle.over = skin.newDrawable("white", Color.LIGHT_GRAY);
		textButtonStyle.font = skin.getFont("default");
		skin.add("default", textButtonStyle);
		return textButtonStyle;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.renderBackground();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void dispose() {
		stage.dispose();
	}

	@Override
	public void show() {
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
