package com.Alienkore.FlappyBread.states;

import com.Alienkore.FlappyBread.FlappyBread;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by David on 23/08/2015.
 */

//MenuState y PlayState extiende de State
public class MenuState extends State {

	private Texture background;
	private Texture playBtn;

	public MenuState(GameStateManager gsm) {
		// Tiene que llamar al constructor de su Padre para poder construir los
		// objetos que tiene el padre en el constructor
		super(gsm);
		background = new Texture("bg.png");
		playBtn = new Texture("playBtn.png");
	}

	@Override
	protected void handleInput() {

		if (Gdx.input.justTouched()) {
			gsm.set(new PlayStates(gsm));
			dispose();
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.begin();
		sb.draw(background, 0, 0, FlappyBread.WIDTH, FlappyBread.HEIGHT);
		sb.draw(playBtn, (FlappyBread.WIDTH / 2) - playBtn.getWidth() / 2,
				FlappyBread.HEIGHT / 2 - playBtn.getHeight() / 2);
		sb.end();
	}

	@Override
	public void dispose() {
		background.dispose();
		playBtn.dispose();
	}
}
