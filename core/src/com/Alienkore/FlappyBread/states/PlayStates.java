package com.Alienkore.FlappyBread.states;

import com.Alienkore.FlappyBread.FlappyBread;
import com.Alienkore.FlappyBread.sprites.Bird;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * Created by David on 23/08/2015.
 */
public class PlayStates extends State {


    private Bird bird;
    private Tube tube;

    public PlayStates(GameStateManager gsm) {
        super(gsm);
        bird = new Bird(50, 100);
        tube = new Tube(100);
        cam.setToOrtho(false, FlappyBread.WIDTH / 2, FlappyBread.HEIGHT / 2);
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
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(bird.getBirdRegion(), bird.getPosition().x, bird.getPosition().y, bird.getBird().getWidth() / 2, bird.getBird().getHeight() / 2, bird.getBird().getWidth(), bird.getBird().getHeight(), 1, 1, bird.getRotation().x);
        // sb.draw(bird.getBird(), bird.getPosition().x, bird.getPosition().y);
        sb.draw(tube.getTopTube(), tube.getPosTopTube().x, tube.getPosTopTube().y);
        sb.draw(tube.getBottomTube(), tube.getPosBottomTube().x, tube.getPosBottomTube().y);

        sb.end();
    }

    @Override
    public void dispose() {


    }
}
