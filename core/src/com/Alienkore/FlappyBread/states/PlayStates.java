package com.Alienkore.FlappyBread.states;

import com.Alienkore.FlappyBread.FlappyBread;
import com.Alienkore.FlappyBread.sprites.Bird;
import com.Alienkore.FlappyBread.sprites.Tube;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

/**
 * Created by David on 23/08/2015.
 */
public class PlayStates extends State {

	private static final int TUBE_SPACING = 125;
	private static final int TUBE_COUNT = 4;

	private Bird bird;

	private Texture bg;

	private Array<Tube> tubes;

	public PlayStates(GameStateManager gsm) {
		
		super(gsm);
		this.bird = new Bird(50, 100);
		cam.setToOrtho(false, FlappyBread.WIDTH / 2, FlappyBread.HEIGHT / 2);
		this.bg = new Texture("bg.png");

		this.tubes = new Array<Tube>();

		for (int i = 1; i <= TUBE_COUNT; i++) {
			tubes.add(new Tube(i * (TUBE_SPACING + Tube.TUBE_WIDTH)));
		}
	}

	@Override
	protected void handleInput() {
		if (Gdx.input.justTouched()) {
			bird.jump();
		}
	}

	@Override
	public void update(float dt) {
		handleInput();
		bird.update(dt);
		cam.position.x = bird.getPosition().x + 80;
		for (int i = 0; i < tubes.size; i++) {
			Tube tube = tubes.get(i);

			if (cam.position.x - (cam.viewportWidth / 2) > tube.getPosTopTube().x + tube.getTopTube().getWidth()) {
				tube.reposition(tube.getPosTopTube().x + ((Tube.TUBE_WIDTH + TUBE_SPACING) * TUBE_COUNT));
			}
		}
		cam.update();
	}

	@Override
	public void render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		
		sb.draw(bg, cam.position.x - (cam.viewportWidth / 2), 0);
		
		sb.draw(bird.getBirdRegion(), bird.getPosition().x, bird.getPosition().y, bird.getBird().getWidth() / 2,
				bird.getBird().getHeight() / 2, bird.getBird().getWidth(), bird.getBird().getHeight(), 1, 1,
				bird.getRotation().x);
		
		for (Tube tube : tubes) {
			sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
			sb.draw(tube.getBottomTube(), tube.getPosBotTube().x, tube.getPosBotTube().y);
		}

		sb.end();
	}

	@Override
	public void dispose() {
		bg.dispose();
		bird.dispose();

		for (Tube tube : tubes)
			tube.dispose();
	}
}
