package com.example.hanoi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.example.hanoi.HanoiGame;

public class GameFinishedScreen implements Screen {

	private HanoiGame game;
	private Stage stage;

	public GameFinishedScreen(HanoiGame game) {
		initializeFields(game);

		setupStage(game);
		Table table = setupTable();

		Label gameFinishedLabel = createGameFinishedLabel();
		table.add(gameFinishedLabel);
		table.row();
		Label highscoreLabel = createHighscoreLabel(game);
		table.add(highscoreLabel).padTop(5);
	}

	private void initializeFields(HanoiGame game) {
		this.game = game;
	}

	private void setupStage(HanoiGame game) {
		stage = new Stage(new StretchViewport(HanoiGame.SCREEN_WIDTH, HanoiGame.SCREEN_HEIGHT, game.camera));
		Gdx.input.setInputProcessor(stage);
	}

	private Table setupTable() {
		Table table = new Table();
		table.setFillParent(true);

		stage.addActor(table);
		return table;
	}

	private Label createGameFinishedLabel() {
		Label gameFinishedLabel = new Label("Game finished!", new Label.LabelStyle(new BitmapFont(), Color.RED));
		gameFinishedLabel.setFontScale(2);
		return gameFinishedLabel;
	}

	private Label createHighscoreLabel(HanoiGame game) {
		Label highscoreLabel = new Label(game.getHighscore(), new Label.LabelStyle(new BitmapFont(), Color.GREEN));
		highscoreLabel.setFontScale(1.5f);
		return highscoreLabel;
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		game.renderBackground();

		stage.draw();

		if (Gdx.input.isTouched() && !game.isStillTouched) {
			game.isStillTouched = true;
			game.showMenu();
		}

		if (game.isStillTouched && !Gdx.input.isTouched()) {
			game.isStillTouched = false;
		}
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

	@Override
	public void dispose() {
		// TODO Auto-generated method stub

	}

}
