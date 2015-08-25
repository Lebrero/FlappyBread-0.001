package com.Alienkore.FlappyBread.states;

import com.Alienkore.FlappyBread.FlappyBread;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by David on 23/08/2015.
 */

// MenuState y PlayState extiende de State
public class MenuState extends State {

	private Texture background;
	private Texture playBtn;

	public MenuState(GameStateManager gsm) {
		// Nos obliga a llamar a super(gsm)
		super(gsm);
		background = new Texture("bg.png");
		playBtn = new Texture("playBtn.png");
	}

	@Override
	protected void handleInput() {

		if (Gdx.input.justTouched()) {
			// llamamaos al metodo set de gsm y le pasamos un State (PlayStates)
			// por su puesto le pasamos gsm
			gsm.set(new PlayStates(gsm));
			// En cuanto damos al boton llamamos a dispose() para limpiar la
			// pantalla
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
