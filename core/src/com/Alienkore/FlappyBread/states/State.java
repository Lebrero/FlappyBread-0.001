package com.Alienkore.FlappyBread.states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

/**
 * Created by David on 23/08/2015.
 */
public abstract class State {
	// Este State contiene varios elementos. Una camara, un mouse,
	// Es una clase abstracta que contiene metodos. Las pantallas que van a
	// extender de esta pantalla van a heredar los metodos y constructor.

	protected OrthographicCamera cam;
	protected Vector3 mouse;
	protected GameStateManager gsm;

	protected State(GameStateManager gsm) {
		this.gsm = gsm;
		this.cam = new OrthographicCamera();
		this.mouse = new Vector3();
	}

	protected abstract void handleInput();

	public abstract void update(float dt);

	public abstract void render(SpriteBatch sb);

	public abstract void dispose();
}
