package com.example.hanoi.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.example.hanoi.HanoiGame;

public class HighscoreScreen implements Screen {

	private HanoiGame game;

	public HighscoreScreen(HanoiGame game) {
		this.game = game;
	}
	
	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.batch.setProjectionMatrix(game.camera.combined);
		game.batch.begin();
		game.font.draw(game.batch, "Best Score: " + game.getBestScore(), HanoiGame.SCREEN_WIDTH / 2, HanoiGame.SCREEN_HEIGHT / 2);
		game.batch.end();
		
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
