package com.example.hanoi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.example.hanoi.HanoiGame;

public class LevelFinishedScreen implements Screen {
	private Stage stage;
	private Table table;
	private HanoiGame game;

	public LevelFinishedScreen(HanoiGame game) {
		this.game = game;
		create();
	}

	public void create() {
		stage = new Stage(new StretchViewport(HanoiGame.SCREEN_WIDTH, HanoiGame.SCREEN_HEIGHT, game.camera));
		Gdx.input.setInputProcessor(stage);
		
		table = new Table();
		table.setFillParent(true);
		
		stage.addActor(table);

		TextButton nextLevelButton = createNextLevelButton(game.createButtonStyle("green"));
		TextButton backToMainButton = createBackToMainButton(game.createButtonStyle("red"));

		int buttonWidth = HanoiGame.SCREEN_WIDTH / 4;
		int buttonHeight = HanoiGame.SCREEN_HEIGHT / 5;
		
		table.add(backToMainButton).size(buttonWidth, buttonHeight);
		table.add(nextLevelButton).padLeft(buttonWidth).size(buttonWidth, buttonHeight);
	}

	private TextButton createNextLevelButton(TextButtonStyle textButtonStyle) {
		TextButton startGameButton = new TextButton("Next Level", textButtonStyle);
		startGameButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				dispose();
				game.nextLevel();
				return true;
			}
		});
		return startGameButton;
	}

	private TextButton createBackToMainButton(TextButtonStyle textButtonStyle) {
		TextButton backToMenuButton = new TextButton("Back to Menu", textButtonStyle);
		backToMenuButton.addListener(new InputListener() {
			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				dispose();
				game.resetLevel();
				game.showMenu();
				return true;
			}
		});
		return backToMenuButton;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.renderBackground();
		stage.act(delta);
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
