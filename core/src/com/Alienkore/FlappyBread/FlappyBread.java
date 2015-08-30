package com.Alienkore.FlappyBread;

import com.Alienkore.FlappyBread.states.GameStateManager;
import com.Alienkore.FlappyBread.states.MenuState;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class FlappyBread extends ApplicationAdapter {

	public static final int WIDTH = 480;
	public static final int HEIGHT = 800;

	public static final String TITLE = "Flappy Bread";

	// Creamos un objeto de tipo GameStateManager
	private GameStateManager gsm;
	private SpriteBatch batch;

	@Override
	public void create() {
		// inicializamos
		batch = new SpriteBatch();
		gsm = new GameStateManager();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		gsm.push(new MenuState(gsm));

	}

	@Override
	public void render() {
		// Limpiamos la pantalla
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		// Estamos enviando al GameStateManager dos cosas: delta y batch
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render((batch));
	}
}
